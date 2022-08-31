package com.pe.ttk.admision.util.mapper;

import com.pe.ttk.admision.dto.DepartamentoDto;
import com.pe.ttk.admision.dto.DistritoDto;
import com.pe.ttk.admision.dto.entity.master.Departamento;
import com.pe.ttk.admision.dto.entity.master.Distrito;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DistritoMapper {

    DistritoMapper INSTANCE = Mappers.getMapper(DistritoMapper.class);

    DistritoDto toDistrito(Distrito distrito);

}
