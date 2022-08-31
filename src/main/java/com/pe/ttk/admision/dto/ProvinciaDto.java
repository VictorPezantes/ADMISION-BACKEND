package com.pe.ttk.admision.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class ProvinciaDto {

    private Long id;
    private Long idDepartamento;
    private String nombre;
    private Integer estado;

}
