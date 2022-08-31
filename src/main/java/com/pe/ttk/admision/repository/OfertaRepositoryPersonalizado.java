package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfertaRepositoryPersonalizado {

    List<OfertaEntity> listarOfertas(Pageable pageable, String titulo, List estado, String fechaPublicacion, List creador, Integer estadoActivo);

    List<OfertaEntity> listarOfertasLanding(Pageable pageable, Long estado, Integer estadoActivo);

}
