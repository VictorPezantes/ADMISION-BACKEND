package com.pe.ttk.admision.service;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.OfertaDto;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface OfertaService {


    Page<OfertaDto> listarOfertas(Integer numPagina, Integer tamPagina, String titulo, List estado, String fechaPublicacion, List creador);

    Page<OfertaDto> listarOfertasLanding(Integer numPagina, Integer tamPagina);


    Mensaje registrarOferta(OfertaDto ofertaDto, Authentication auth);

    Mensaje actualizarOferta(OfertaDto ofertaDto);
    Mensaje actualizarEstadoOferta(OfertaDto ofertaDto);

    void delete(Long id);

    Optional<OfertaEntity> getOne(Long id);

    public List<OfertaEntity> findOfertaByQueryString(String titulo);
    List<OfertaEntity> findByCreadorOferta(Encargado creador);

    List<OfertaEntity> findByfechaPublicacion(Date fechaPublicacion);

    OfertaDto obtenerOferta(Long id);

    Mensaje eliminarOferta(OfertaDto ofertaDto);

}
