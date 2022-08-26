package com.pe.ttk.admision.security.service;

import java.util.Optional;

import com.pe.ttk.admision.security.entity.Rol;
import com.pe.ttk.admision.security.enums.RolNombre;
import com.pe.ttk.admision.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {
	
	@Autowired
    RolRepository rolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		
		return rolRepository.findByRolNombre(rolNombre);	
	}
	
	public void save(Rol rol) {
		rolRepository.save(rol);
	}
	
	

}
