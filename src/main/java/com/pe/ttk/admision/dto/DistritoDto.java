package com.pe.ttk.admision.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class DistritoDto {

    private Long id;
    private Long idProvincia;
    private String nombre;
    private Integer estado;

}
