package com.pe.ttk.admision.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class EstadoCivilDto {

    private Long id;
    private String nombre;
    private Integer estado;

}
