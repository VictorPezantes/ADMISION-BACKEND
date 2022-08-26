package com.pe.ttk.admision.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.awt.*;
import java.io.InputStream;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostulanteDto {

    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String estadoCivil;
    private String fechaNacimiento;
    private String direccionPrincipal;
    private String distrito;
    private String provincia;
    private String departamento;
    private String celularPrincipal;
    private String celularFamiliar;
    private String telefonoFijo;
    private String emailPrincipal;
    private String emailSecundario;
    private String profesion;
    private String lugarEstudios;
    private String ultimoCursoRealizado;
    private String empresaCurso;
    private String trabajoReciente;
    private String fechaIngresoTrabajoReciente;
    private String fechaSalidaTrabajoreciente;
    private String EmpresaTrabajoReciente;
    private String motivoSalidaTrabajoReciente;
    private String curriculumVitae;
    private String dniFrontal;
    private String dniPosterior;
    private String fotografia;
    private String respuestaDisponibilidadViajar;
    private String respuestaExperienciaMantencion;
    private String estadoPostulacion;
    private String subEstadoPostulacion;
    private String fechaPostulacion;
    private String procedencia;
    private String cargoPostulante;
    private String ofertaPostulada;
    private int cantidadPostulaciones;
    private String responsableAsignado;
    private String dni;


}
