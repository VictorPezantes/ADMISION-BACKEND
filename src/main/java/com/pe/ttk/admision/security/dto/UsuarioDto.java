package com.pe.ttk.admision.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JMap;
import com.pe.ttk.admision.security.entity.Rol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {
    @JMap
    private int id;
    @JMap
    private String nombre;
    @JMap
    private String apellidos;
    @JMap
    private String nombreUsuario;
    @JMap
    private String email;
    @JMap
    private String fotografia;
    @JMap
    private Set<Rol> roles = new HashSet<>();
}
