package com.pe.ttk.admision.util.mapper.security.dto;

import com.pe.ttk.admision.util.mapper.security.entity.Rol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {

    private int id;
    private String nombre;
    private String apellidos;
    private String nombreUsuario;
    private String email;
    private String fotografia;
    private Set<Rol> roles = new HashSet<>();
}
