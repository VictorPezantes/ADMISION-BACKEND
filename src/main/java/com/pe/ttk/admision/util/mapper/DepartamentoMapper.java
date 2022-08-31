package com.pe.ttk.admision.util.mapper;

import com.pe.ttk.admision.dto.DepartamentoDto;
import com.pe.ttk.admision.dto.entity.master.Departamento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartamentoMapper {

    DepartamentoMapper INSTANCE = Mappers.getMapper(DepartamentoMapper.class);

    DepartamentoDto toDepartamento(Departamento departamento);

}
