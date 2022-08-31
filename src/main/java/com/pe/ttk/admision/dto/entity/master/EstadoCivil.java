package com.pe.ttk.admision.dto.entity.master;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "estado_civil")
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer estado;

}
