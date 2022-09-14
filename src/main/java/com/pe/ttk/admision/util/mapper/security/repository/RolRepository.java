package com.pe.ttk.admision.util.mapper.security.repository;

import java.util.Optional;

import com.pe.ttk.admision.util.mapper.security.entity.Rol;
import com.pe.ttk.admision.util.mapper.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	Optional<Rol>findByRolNombre(RolNombre rolNombre);

}
