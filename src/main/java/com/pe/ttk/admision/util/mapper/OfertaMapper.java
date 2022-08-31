package com.pe.ttk.admision.util.mapper;

import com.pe.ttk.admision.dto.OfertaDto;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;

public interface OfertaMapper {

    OfertaDto toOferta(OfertaEntity ofertaEntity);

    OfertaDto toOfertaLanding(OfertaEntity ofertaEntity);

}
