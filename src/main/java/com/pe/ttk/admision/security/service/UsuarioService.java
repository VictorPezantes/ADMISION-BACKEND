package com.pe.ttk.admision.security.service;

import java.util.List;
import java.util.Optional;

import com.pe.ttk.admision.security.dto.UsuarioDto;
import com.pe.ttk.admision.security.entity.Usuario;
import com.pe.ttk.admision.security.entity.UsuarioPrincipal;
import com.pe.ttk.admision.security.repository.UsuarioRepository;
import com.pe.ttk.admision.util.Constantes;
import com.pe.ttk.admision.util.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
	@Autowired
    UsuarioRepository usuarioRepository;

	public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);

	}
	
	public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail) {
		return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);

	}

	public List<Usuario> listarUsuarios(){

		return usuarioRepository.findAll();
	}



	public boolean existsByNombreUsuario(String nombreUsuario) {

		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}

	public boolean existsByEmail(String email) {

		return usuarioRepository.existsByEmail(email);
	}

	public void eliminarUsuario(int id) {
		usuarioRepository.eliminarUsuario(id, Constantes.ESTADO_INACTIVO);
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> getByTokenPassword(String tokenPassword) {
		return usuarioRepository.findByTokenPassword(tokenPassword);

	}

	public List<UsuarioDto> listarUsuariosActivos(){
		return UsuarioMapper.INSTANCE.toUsuarioDto(usuarioRepository.findByEstado(Constantes.ESTADO_ACTIVO));
	}

	public UsuarioDto obtenerUsuarioLogueado(Authentication auth){
		UsuarioPrincipal usuario = (UsuarioPrincipal) auth.getPrincipal();

		Optional<Usuario> usuarioOp = getByNombreUsuarioOrEmail(usuario.getEmail());
		if(usuarioOp.isPresent()){
			Usuario usuarioDb = usuarioOp.get();
			return UsuarioMapper.INSTANCE.toUsuarioDto(usuarioDb);
		}
		return null;
	}

	public boolean existeUsuarioActivo(String email){
		return usuarioRepository.existsByEmailAndEstado(email, Constantes.ESTADO_ACTIVO);
	}

}
