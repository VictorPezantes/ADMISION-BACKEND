package com.pe.ttk.admision.security.repository;

import java.util.List;
import java.util.Optional;

import com.pe.ttk.admision.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario>findByNombreUsuario(String nombreUsuario);
	Optional<Usuario>findByNombreUsuarioOrEmail(String nombreUsuario,String email);
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);
	Optional<Usuario>findByTokenPassword(String tokenPassword);
	@Modifying
	@Query("UPDATE Usuario set estado = :estado WHERE id = :id")
	void eliminarUsuario(@Param("id") int id, @Param("estado") int estado);

	List<Usuario> findByEstado(int estado);

	boolean existsByEmailAndEstado(String email, int estado);

}
