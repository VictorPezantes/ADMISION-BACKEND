package com.pe.ttk.admision.dto.entity.master;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "distrito")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProvincia;
    private String nombre;
    private Integer estado;

}
