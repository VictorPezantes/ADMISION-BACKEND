package com.pe.ttk.admision.dto.entity.master;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "provincia")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idDepartamento;
    private String nombre;
    private Integer estado;

}
