package com.pe.ttk.admision.util;

import com.pe.ttk.admision.security.entity.Rol;
import com.pe.ttk.admision.security.enums.RolNombre;
import com.pe.ttk.admision.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {

		/*Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
          Rol rolUser = new Rol(RolNombre.ROLE_USER);
          rolService.save(rolAdmin);
		  rolService.save(rolUser);*/


    }
}
