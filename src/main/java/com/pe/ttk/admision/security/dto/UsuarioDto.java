package com.pe.ttk.admision.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    private int id;
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String fotografia;
    private Set<Rol> roles = new HashSet<>();
}
