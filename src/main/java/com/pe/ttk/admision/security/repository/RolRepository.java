package com.pe.ttk.admision.security.repository;

import java.util.Optional;

import com.pe.ttk.admision.security.entity.Rol;
import com.pe.ttk.admision.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	Optional<Rol>findByRolNombre(RolNombre rolNombre);

}
