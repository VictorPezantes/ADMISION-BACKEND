package com.pe.ttk.admision.util.mapper.security.service;

import com.pe.ttk.admision.util.mapper.security.entity.Rol;
import com.pe.ttk.admision.util.mapper.security.enums.RolNombre;

import java.util.Optional;

public interface RolService {

    Optional<Rol> getByRolNombre(RolNombre rolNombre);

    void save(Rol rol);

}
