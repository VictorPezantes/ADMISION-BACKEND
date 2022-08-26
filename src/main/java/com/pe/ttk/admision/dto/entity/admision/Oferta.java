package com.pe.ttk.admision.dto.entity.admision;

import com.pe.ttk.admision.dto.entity.master.Cargo;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="oferta")
public class Oferta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String titulo;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String requisito;
    @Column(nullable = false)
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private Date fechaPublicacion;
    private Date fechaDesactivado;
    @Column(nullable = false)
    private int cantidadPostulantes;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name ="fk_estado", referencedColumnName = "id")
    private Estado estadoOferta;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name ="fk_creador", referencedColumnName = "id")
    private Encargado creadorOferta;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name ="fk_cargo", referencedColumnName = "id")
    private Cargo cargoOferta;


}
