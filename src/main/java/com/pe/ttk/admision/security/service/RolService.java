package com.pe.ttk.admision.security.service;

import com.pe.ttk.admision.security.entity.Rol;
import com.pe.ttk.admision.security.enums.RolNombre;

import java.util.Optional;

public interface RolService {

    Optional<Rol> getByRolNombre(RolNombre rolNombre);

    void save(Rol rol);

}
