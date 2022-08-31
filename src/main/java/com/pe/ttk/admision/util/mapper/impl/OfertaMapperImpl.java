package com.pe.ttk.admision.util.mapper.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pe.ttk.admision.dto.OfertaDto;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import com.pe.ttk.admision.dto.entity.master.Cargo;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.util.mapper.OfertaMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OfertaMapperImpl implements OfertaMapper {
    @Override
    public OfertaDto toOferta(OfertaEntity ofertaEntity) {
        OfertaDto ofertaDto = new OfertaDto();
        ofertaDto.setId(ofertaEntity.getId());
        ofertaDto.setEstadoOferta(ofertaEntity.getEstadoOferta());
        ofertaDto.setTitulo(ofertaEntity.getTitulo());
        ofertaDto.setDescripcion(ofertaEntity.getDescripcion());
        ofertaDto.setRequisito(ofertaEntity.getRequisito());
        ofertaDto.setCreadorOferta(ofertaEntity.getCreadorOferta());
        ofertaDto.setFechaCreacion(ofertaEntity.getFechaCreacion());
        ofertaDto.setFechaPublicacion(ofertaEntity.getFechaPublicacion());
        ofertaDto.setCargoOferta(ofertaEntity.getCargoOferta());
        ofertaDto.setCantidadPostulantes(ofertaEntity.getCantidadPostulantes());
        ofertaDto.setFechaActualizacion(ofertaEntity.getFechaActualizacion());
        ofertaDto.setFechaDesactivado(ofertaEntity.getFechaDesactivado());
        ofertaDto.setEstado(ofertaEntity.getEstado());

        return ofertaDto;
    }

    @Override
    public OfertaDto toOfertaLanding(OfertaEntity ofertaEntity) {
        OfertaDto ofertaDto = new OfertaDto();
        ofertaDto.setId(ofertaEntity.getId());
        ofertaDto.setTitulo(ofertaEntity.getTitulo());
        ofertaDto.setDescripcion(ofertaEntity.getDescripcion());
        ofertaDto.setRequisito(ofertaEntity.getRequisito());
        ofertaDto.setFechaPublicacion(ofertaEntity.getFechaPublicacion());
        ofertaDto.setCantidadPostulantes(ofertaEntity.getCantidadPostulantes());
        ofertaDto.setEstado(ofertaEntity.getEstado());

        return ofertaDto;
    }
}
