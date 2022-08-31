package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.DepartamentoDto;
import com.pe.ttk.admision.dto.DistritoDto;
import com.pe.ttk.admision.dto.ProvinciaDto;
import com.pe.ttk.admision.repository.DepartamentoRepository;
import com.pe.ttk.admision.repository.DistritoRepository;
import com.pe.ttk.admision.repository.ProvinciaRepository;
import com.pe.ttk.admision.service.DireccionService;
import com.pe.ttk.admision.util.Constantes;
import com.pe.ttk.admision.util.mapper.DepartamentoMapper;
import com.pe.ttk.admision.util.mapper.DistritoMapper;
import com.pe.ttk.admision.util.mapper.ProvinciaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private DistritoRepository distritoRepository;

    @Override
    public List<DepartamentoDto> listarDepartamentos() {
        return departamentoRepository.findByEstado(Constantes.ESTADO_ACTIVO).stream()
                .map(DepartamentoMapper.INSTANCE::toDepartamento).collect(Collectors.toList());
    }

    @Override
    public List<ProvinciaDto> listarProvincias(Long idDepartamento) {
        return provinciaRepository.findByIdDepartamentoAndEstado(idDepartamento, Constantes.ESTADO_ACTIVO).stream()
                .map(ProvinciaMapper.INSTANCE::toProvincia).collect(Collectors.toList());
    }

    @Override
    public List<DistritoDto> listarDistritos(Long idProvincia) {
        return distritoRepository.findByIdProvinciaAndEstado(idProvincia, Constantes.ESTADO_ACTIVO).stream()
                .map(DistritoMapper.INSTANCE::toDistrito).collect(Collectors.toList());
    }
}
