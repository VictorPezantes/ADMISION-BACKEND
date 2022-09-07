package com.pe.ttk.admision.dto.entity.master;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "historial")
public class HistorialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPostulante;
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaCambioEstado;
    private Integer estadoPostulacion;
    private String observacion;
    private Integer cantidadPostulaciones;
    private String mensajeEnviado;
    private Integer edadPostulante;

}
