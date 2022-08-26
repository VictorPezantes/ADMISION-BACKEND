package com.pe.ttk.admision.dto.entity.admision;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "PostulanteMapping",
                        columns = {@ColumnResult(name = "primerNombre"),
                                @ColumnResult(name = "segundoNombre"),
                                @ColumnResult(name = "apellidoPaterno"),
                                @ColumnResult(name = "apellidoMaterno"),
                                @ColumnResult(name = "estadoCivil"),
                                //@ColumnResult(name = "fechaNacimiento"),
                                @ColumnResult(name = "direccionPrincipal"),
                                @ColumnResult(name = "distrito"),
                                @ColumnResult(name = "provincia"),
                                @ColumnResult(name = "departamento"),
                                @ColumnResult(name = "celularPrincipal"),
                                @ColumnResult(name = "celularFamiliar"),
                                @ColumnResult(name = "telefonoFijo"),
                                @ColumnResult(name = "emailPrincipal"),
                                @ColumnResult(name = "emailSecundario"),
                                @ColumnResult(name = "lugarEstudios"),
                                @ColumnResult(name = "ultimoCursoRealizado"),
                                @ColumnResult(name = "empresaCurso"),
                                @ColumnResult(name = "trabajoReciente"),
                                //  @ColumnResult(name = "fechaIngresoTrabajoReciente"),
                                //  @ColumnResult(name = "fechaSalidaTrabajoReciente"),
                                @ColumnResult(name = "EmpresaTrabajoReciente"),
                                @ColumnResult(name = "motivoSalidaTrabajoReciente"),
                                @ColumnResult(name = "curriculumVitae"),
                                @ColumnResult(name = "dniFrontal"),
                                @ColumnResult(name = "dniPosterior"),
                                @ColumnResult(name = "fotografia"),
                                @ColumnResult(name = "respuestaDisponibilidadViajar"),
                                @ColumnResult(name = "respuestaExperienciaMantencion"),
                                @ColumnResult(name = "estadoPostulacion"),
                                @ColumnResult(name = "subEstadoPostulacion"),
                                // @ColumnResult(name = "fechaPostulacion"),
                                @ColumnResult(name = "procedencia"),
                                @ColumnResult(name = "cargoPostulante"),
                                @ColumnResult(name = "ofertaPostulada"),
                                @ColumnResult(name = "cantidadPostulaciones"),
                                @ColumnResult(name = "responsableAsignado"),
                                @ColumnResult(name = "dni"),
                                @ColumnResult(name = "profesion")}),


})

@NamedNativeQueries({@NamedNativeQuery(name = "Postulante.findByQueryString",
        query =
                "SELECT p.primer_nombre as primerNombre,p.segundo_nombre as segundoNombre,p.apellido_paterno as apellidoPaterno,p.apellido_materno as apellidoMaterno,p.dni as dni,"
                        + "p.estado_civil as estadoCivil,p.fecha_nacimiento as fechaNacimiento,p.direccion_principal as direccionPrincipal,p.distrito as distrito,"
                        + "p.provincia as provincia,p.departamento as departamento,p.celular_principal as celularPrincipal,p.celular_familiar as celularFamiliar,p.telefono_fijo as telefonoFijo,"
                        + "p.email_principal as emailPrincipal,p.email_secundario as emailSecundario,p.profesion as profesion,p.lugar_estudios as lugarEstudios,p.ultimo_curso_realizado as ultimoCursoRealizado,"
                        + "p.empresa_curso as  empresaCurso,p.trabajo_reciente as trabajoReciente,p.fecha_ingreso_trabajo_reciente as fechaIngresoTrabajoReciente,p.fecha_salida_trabajo_reciente as fechaSalidaTrabajoRecient,"
                        + "p.empresa_trabajo_reciente as EmpresaTrabajoReciente,p.motivo_salida_trabajo_reciente as motivoSalidaTrabajoReciente,p.curriculum_vitae as curriculumVitae,"
                        + "p.dni_frontal as dniFrontal,p.dni_posterior as dniPosterior,p.fotografia as fotografia,p.respuesta_disponibilidad_viajar as respuestaDisponibilidadViajar,p.respuesta_experiencia_mantencion as respuestaExperienciaMantencion,"
                        + "p.estado_postulacion as estadoPostulacion,p.sub_estado_postulacion as subEstadoPostulacion,p.fecha_postulacion as fechaPostulacion,p.procedencia as procedencia,p.cargo_postulante as cargoPostulante,"
                        + "p.oferta_postulada as ofertaPostulada,p.cantidad_postulaciones as cantidadPostulaciones,p.responsable_asignado as responsableAsignado  "
                        + " FROM postulante p"
                        + " WHERE p.dni  =:dni OR :dni = '-1'"
                        + " AND p.distrito              =:distrito OR distrito = '-1'"
                        + " AND p.provincia             =:provincia OR provincia = '-1'"
                        + " AND p.departamento          =:departamento OR departamento = '-1'",
        resultSetMapping = "PostulanteMapping")

})

public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name = "direccion_principal")
    private String direccionPrincipal;
    private String distrito;
    private String provincia;
    private String departamento;
    @Column(name = "celular_principal")
    private String celularPrincipal;
    @Column(name = "celular_familiar")
    private String celularFamiliar;
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Column(name = "email_principal")
    private String emailPrincipal;
    @Column(name = "email_secundario")
    private String emailSecundario;
    private String profesion;
    @Column(name = "lugar_estudios")
    private String lugarEstudios;
    @Column(name = "ultimo_curso_realizado")
    private String ultimoCursoRealizado;
    @Column(name = "empresa_curso")
    private String empresaCurso;
    @Column(name = "trabajo_reciente")
    private String trabajoReciente;
    @Column(name = "fecha_ingreso_trabajo_reciente")
    private Date fechaIngresoTrabajoReciente;
    @Column(name = "fecha_salida_trabajo_reciente")
    private Date fechaSalidaTrabajoReciente;
    @Column(name = "empresa_trabajo_reciente")
    private String EmpresaTrabajoReciente;
    @Column(name = "motivo_salida_trabajo_reciente")
    private String motivoSalidaTrabajoReciente;
    @Column(name = "curriculum_vitae")
    private String curriculumVitae;
    @Column(name = "dni_frontal")
    private String dniFrontal;
    @Column(name = "dni_posterior")
    private String dniPosterior;
    private String fotografia;
    @Column(name = "respuesta_disponibilidad_viajar")
    private String respuestaDisponibilidadViajar;
    @Column(name = "respuesta_experiencia_mantencion")
    private String respuestaExperienciaMantencion;
    @Column(name = "estado_postulacion")
    private String estadoPostulacion;
    @Column(name = "sub_estado_postulacion")
    private String subEstadoPostulacion;
    @Column(name = "fecha_postulacion")
    private Date fechaPostulacion;
    private String procedencia;
    @Column(name = "cargo_postulante")
    private String cargoPostulante;
    @Column(name = "oferta_postulada")
    private String ofertaPostulada;
    @Column(name = "cantidad_postulaciones")
    private int cantidadPostulaciones;
    @Column(name = "responsable_asignado")
    private String responsableAsignado;
    private String dni;


}
