package com.pe.ttk.admision.security.service;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.security.dto.NuevoUsuario;
import com.pe.ttk.admision.security.dto.UsuarioDto;
import com.pe.ttk.admision.security.entity.Usuario;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> getByNombreUsuario(String nombreUsuario);

    Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail);

    List<Usuario> listarUsuarios();

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);

    void eliminarUsuario(int id);

    void save(Usuario usuario);

    Optional<Usuario> getByTokenPassword(String tokenPassword);

    List<UsuarioDto> listarUsuariosActivos();

    UsuarioDto obtenerUsuarioLogueado(Authentication auth);

    boolean existeUsuarioActivo(String email);

    Mensaje registrarUsuario(NuevoUsuario nuevoUsuario, boolean isAdmin);

}
