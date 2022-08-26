package com.pe.ttk.admision.config.controller;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.security.dto.JwtDto;
import com.pe.ttk.admision.security.dto.LoginUsuario;
import com.pe.ttk.admision.security.dto.NuevoUsuario;
import com.pe.ttk.admision.security.dto.UsuarioDto;
import com.pe.ttk.admision.security.entity.Rol;
import com.pe.ttk.admision.security.entity.Usuario;
import com.pe.ttk.admision.security.entity.UsuarioPrincipal;
import com.pe.ttk.admision.security.enums.RolNombre;
import com.pe.ttk.admision.security.jwt.JwtProvider;
import com.pe.ttk.admision.security.service.RolService;
import com.pe.ttk.admision.security.service.UsuarioService;
import com.pe.ttk.admision.util.mapper.UsuarioMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {

    private final Path rootFolder = Paths.get("archivos/Empleado");
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/registrar-usuario")
    public ResponseEntity<?> registrarUsuario(@RequestParam(name = "foto", required = false) MultipartFile foto,
                                   @Valid @RequestBody NuevoUsuario nuevoUsuario,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("campos mal puestos o email inválido"));
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return ResponseEntity.badRequest().body(new Mensaje("ese nombre ya existe"));
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return ResponseEntity.badRequest().body(new Mensaje("ese email ya existe"));

        if(foto != null){
            try {
                Files.copy(foto.getInputStream(), this.rootFolder.resolve(foto.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()), null, 1);
        //Path rutaRelativa = Paths.get(foto.getOriginalFilename());
        //Path rutaAbsoluta = rutaRelativa.toAbsolutePath();


        //usuario.setFotografia(rutaAbsoluta.toString());

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")) roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuario.setEstado(1);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("campos mal puestos"));
        if(!usuarioService.existeUsuarioActivo(loginUsuario.getEmail())){
            return ResponseEntity.badRequest().body(new Mensaje("El usuario no existe"));
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, usuarioPrincipal.getUsername(), usuarioPrincipal.getEmail(), usuarioPrincipal.getAuthorities());
        return ResponseEntity.ok(jwtDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/usuario-logueado")
    public ResponseEntity<?> obtenerUsuarioLogueado(Authentication auth) {
        UsuarioDto usuarioDto = usuarioService.obtenerUsuarioLogueado(auth);
        if(usuarioDto != null){
            return ResponseEntity.ok(usuarioDto);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/listar-usuarios-activos")
    public ResponseEntity<?> listarUsuarios() {

        //List<UsuarioDto> listaUsuarioDto = ;

        /*for (Usuario usuario : listaUsuario) {

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setEmail(usuario.getEmail());
            usuarioDto.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDto.setNombre(usuario.getNombre());
            usuarioDto.setFotografia(usuario.getFotografia());
            usuarioDto.setRoles(usuario.getRoles());
            usuarioDto.setId(usuario.getId());

            listaUsuarioDto.add(usuarioDto);

        }*/

        return ResponseEntity.ok(usuarioService.listarUsuariosActivos());
    }

    @ApiOperation("Eliminar un usuario por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar-usuario")
    public ResponseEntity<?> eliminarUsuario(@RequestBody UsuarioDto usuario) {

        usuarioService.eliminarUsuario(usuario.getId());
        return ResponseEntity.ok(new Mensaje("Usuario eliminado"));
    }
}

