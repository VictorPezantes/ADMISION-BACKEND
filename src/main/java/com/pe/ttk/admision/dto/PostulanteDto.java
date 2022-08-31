package com.pe.ttk.admision.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
public class PostulanteDto {

    private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long idEstadoCivil;
    private String dni;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;
    private String direccion;
    private Long idDistrito;
    private Long idProvincia;
    private Long idDepartamento;
    private String celular;
    private String celularFamiliar;
    private String telefonoFijo;
    private String email;
    private String emailSecundario;
    private String profesion;
    private String lugarEstudios;
    private String ultimoCursoRealizado;
    private String empresaCurso;
    private String trabajoReciente;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaIngresoTrabajoReciente;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaSalidaTrabajoReciente;
    private String empresaTrabajoReciente;
    private String motivoSalidaTrabajoReciente;
    private Integer disponibilidadViajar;
    private Integer experienciaRubro;
    private Integer estadoPostulacion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaPostulacion;
    private String procedencia;
    private Long idOferta;
    private String ofertaPostulada;
    private String urlCurriculumVitae;
    private String urlDniFrontal;
    private String urlDniPosterior;
    private String urlFotografia;
    private Integer estado;

}
