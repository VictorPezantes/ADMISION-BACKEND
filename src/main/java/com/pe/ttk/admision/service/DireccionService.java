package com.pe.ttk.admision.service;

import com.pe.ttk.admision.dto.DepartamentoDto;
import com.pe.ttk.admision.dto.DistritoDto;
import com.pe.ttk.admision.dto.ProvinciaDto;

import java.util.List;

public interface DireccionService {

    List<DepartamentoDto> listarDepartamentos();

    List<ProvinciaDto> listarProvincias(Long idDepartamento);

    List<DistritoDto> listarDistritos(Long idProvincia);

}
