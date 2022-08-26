package com.pe.ttk.admision.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CargoDto {


    private Long id;
    private String nombreCargo;
    private String descripcion;
}
