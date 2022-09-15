package com.pe.ttk.admision.security.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.security.dto.NuevoUsuario;
import com.pe.ttk.admision.security.dto.UsuarioDto;
import com.pe.ttk.admision.security.entity.Rol;
import com.pe.ttk.admision.security.entity.Usuario;
import com.pe.ttk.admision.security.entity.UsuarioPrincipal;
import com.pe.ttk.admision.security.enums.RolNombre;
import com.pe.ttk.admision.security.repository.UsuarioRepository;
import com.pe.ttk.admision.security.service.RolService;
import com.pe.ttk.admision.security.service.UsuarioService;
import com.pe.ttk.admision.util.Constantes;
import com.pe.ttk.admision.util.GuardarArchivos;
import com.pe.ttk.admision.util.mapper.UsuarioMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
    UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RolService rolService;

	private GuardarArchivos guardarArchivos = new GuardarArchivos();

	@Override
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);

	}

	@Override
	public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail) {
		return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);

	}

	@Override
	public List<Usuario> listarUsuarios(){

		return usuarioRepository.findAll();
	}

	@Override
	public boolean existsByNombreUsuario(String nombreUsuario) {

		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}

	@Override
	public boolean existsByEmail(String email) {

		return usuarioRepository.existsByEmail(email);
	}

	@Override
	public void eliminarUsuario(int id) {
		usuarioRepository.eliminarUsuario(id, Constantes.ESTADO_INACTIVO);
	}

	@Override
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> getByTokenPassword(String tokenPassword) {
		return usuarioRepository.findByTokenPassword(tokenPassword);

	}

	@Override
	public List<UsuarioDto> listarUsuariosActivos(){
		//return UsuarioMapper.INSTANCE.toUsuarioDto(usuarioRepository.findByEstado(Constantes.ESTADO_ACTIVO));
		return this.usuarioRepository.findByEstado(Constantes.ESTADO_ACTIVO).stream().map(Usuario ::toUsuarioDto).collect(Collectors.toList());
	}

	@Override
	public UsuarioDto obtenerUsuarioLogueado(Authentication auth){
		UsuarioPrincipal usuario = (UsuarioPrincipal) auth.getPrincipal();

		 return this.usuarioRepository.findByNombreUsuarioOrEmail(usuario.getEmail(), usuario.getEmail())
				.orElseThrow(()->new RuntimeException("Usuario no existe. "+ usuario.getEmail())).toUsuarioDto();


		/*Optional<Usuario> usuarioOp = getByNombreUsuarioOrEmail(usuario.getEmail());
		if(usuarioOp.isPresent()){
			Usuario usuarioDb = usuarioOp.get();
			return Usuario::toUsuarioDto;
		}
		return null;*/
	}

	@Override
	public boolean existeEmailUsuarioActivo(String email) {
		return usuarioRepository.existsByEstadoAndEmailOrNombreUsuario(Constantes.ESTADO_ACTIVO, email, email);
	}

	@Override
	public boolean existeEmailActivo(String email){
		return usuarioRepository.existsByEmailAndEstado(email, Constantes.ESTADO_ACTIVO);
	}

	@Override
	public boolean existeNombreUsuarioActivo(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuarioAndEstado(nombreUsuario, Constantes.ESTADO_ACTIVO);
	}

	@Override
	public Mensaje registrarUsuario(NuevoUsuario nuevoUsuario, boolean isAdmin, MultipartFile foto) {
		if (existeEmailActivo(nuevoUsuario.getEmail())){
			return new Mensaje("El correo electrónico ingresado ya existe");
		}

		if (existeNombreUsuarioActivo(nuevoUsuario.getNombreUsuario())){
			return new Mensaje("El usuario ingresado ya existe");
		}

		Set<Rol> roles = new HashSet<>();
		Optional<Rol> rolOp = rolService.getByRolNombre(RolNombre.ROLE_USER);
		if (rolOp.isEmpty()){
			return new Mensaje("No existe el rol");
		}
		roles.add(rolOp.get());

		if(isAdmin){
			rolOp = rolService.getByRolNombre(RolNombre.ROLE_ADMIN);
			if (rolOp.isEmpty()){
				return new Mensaje("No existe el rol");
			}
			roles.add(rolOp.get());
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(nuevoUsuario.getNombre());
		usuario.setApellidos(nuevoUsuario.getApellidos());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setNombreUsuario(nuevoUsuario.getNombreUsuario());
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		usuario.setRoles(roles);
		usuario.setEstado(Constantes.ESTADO_ACTIVO);

		if(!foto.isEmpty()){
			String nombreFoto = usuario.getNombreUsuario()+Constantes.AVATAR+"."+ FilenameUtils.getExtension(foto.getOriginalFilename());
			guardarArchivos.guardarArchivo(foto, nombreFoto, "archivos/Empleado");
			usuario.setFotografia(nombreFoto);
		}

		usuarioRepository.save(usuario);

		return new Mensaje("Usuario registrado correctamente");
	}

}
