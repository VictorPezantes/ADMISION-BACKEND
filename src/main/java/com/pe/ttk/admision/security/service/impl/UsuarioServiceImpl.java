package com.pe.ttk.admision.security.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.pe.ttk.admision.util.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
    UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RolService rolService;

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
		return UsuarioMapper.INSTANCE.toUsuarioDto(usuarioRepository.findByEstado(Constantes.ESTADO_ACTIVO));
	}

	@Override
	public UsuarioDto obtenerUsuarioLogueado(Authentication auth){
		UsuarioPrincipal usuario = (UsuarioPrincipal) auth.getPrincipal();

		Optional<Usuario> usuarioOp = getByNombreUsuarioOrEmail(usuario.getEmail());
		if(usuarioOp.isPresent()){
			Usuario usuarioDb = usuarioOp.get();
			return UsuarioMapper.INSTANCE.toUsuarioDto(usuarioDb);
		}
		return null;
	}

	@Override
	public boolean existeUsuarioActivo(String email){
		return usuarioRepository.existsByEmailAndEstado(email, Constantes.ESTADO_ACTIVO);
	}

	@Override
	public Mensaje registrarUsuario(NuevoUsuario nuevoUsuario, boolean isAdmin) {
		if (existeUsuarioActivo(nuevoUsuario.getEmail())){
			return new Mensaje("El correo electrónico ingresado ya existe");
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
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		usuario.setRoles(roles);
		usuario.setEstado(Constantes.ESTADO_ACTIVO);

		usuarioRepository.save(usuario);

		return new Mensaje("Usuario registrado correctamente");
	}

}
