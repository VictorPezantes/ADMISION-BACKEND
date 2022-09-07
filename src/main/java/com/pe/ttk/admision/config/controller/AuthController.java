package com.pe.ttk.admision.config.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.security.dto.JwtDto;
import com.pe.ttk.admision.security.dto.LoginUsuario;
import com.pe.ttk.admision.security.dto.NuevoUsuario;
import com.pe.ttk.admision.security.dto.UsuarioDto;
import com.pe.ttk.admision.security.entity.UsuarioPrincipal;
import com.pe.ttk.admision.security.jwt.JwtProvider;
import com.pe.ttk.admision.security.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    JwtProvider jwtProvider;

    @ApiOperation("Registrar un usuario final")
    @PostMapping("/registrar-usuario")
    public ResponseEntity<?> registrarUsuario(@RequestParam(name = "foto", required = false) MultipartFile foto,
                                   @RequestParam String usuario) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NuevoUsuario nuevoUsuario = objectMapper.readValue(usuario, NuevoUsuario.class);

        /*if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("Por favor ingrese los campos correctamente"));*/

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(nuevoUsuario, false, foto));
    }

    @ApiOperation("Registrar un usuario administrador")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar-admin")
    public ResponseEntity<?> registrarUsuarioAdmin(@RequestParam(name = "foto", required = false) MultipartFile foto,
                                                   @RequestParam String usuario) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NuevoUsuario nuevoUsuario = objectMapper.readValue(usuario, NuevoUsuario.class);
        /*if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("Por favor ingrese los campos correctamente"));*/

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(nuevoUsuario, true, foto));
    }

    @ApiOperation("Login del sistema")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new Mensaje("campos mal puestos"));
        }
        if(!usuarioService.existeEmailUsuarioActivo(loginUsuario.getEmail())){
            return ResponseEntity.badRequest().body(new Mensaje("El correo electronico o usuario no existe"));
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

    @ApiOperation("Obtener el usuario logueado")
    @GetMapping("/usuario-logueado")
    public ResponseEntity<?> obtenerUsuarioLogueado(Authentication auth) {
        UsuarioDto usuarioDto = usuarioService.obtenerUsuarioLogueado(auth);
        if(usuarioDto != null){
            return ResponseEntity.ok(usuarioDto);
        }
        return ResponseEntity.notFound().build();
    }


    @ApiOperation("Lista de los usuarios activos")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar-usuarios-activos")
    public ResponseEntity<?> listarUsuarios() {

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

