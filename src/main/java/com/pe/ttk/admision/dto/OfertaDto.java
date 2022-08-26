package com.pe.ttk.admision.dto;

import com.pe.ttk.admision.dto.entity.master.Cargo;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfertaDto {

    private Estado estadoOferta;
    private String titulo;
    private String descripcion;
    private String requisito;
    private Encargado creadorOferta;
    private String fechaCreacion;
    private String fechaPublicacion;
    private Cargo cargoOferta;
    private int cantidadPostulantes;
    private String fechaActualizacion;
    private String fechaDesactivado;
}
