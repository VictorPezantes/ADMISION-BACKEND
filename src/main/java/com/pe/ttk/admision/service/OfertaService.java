package com.pe.ttk.admision.service;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.OfertaDto;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.admision.Oferta;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface OfertaService {


    List<Oferta> listarOfertas();


    Mensaje registrarOferta(Oferta oferta, Authentication auth);

    void actualizarOferta(Long id, OfertaDto ofertaDto);
    void actualizarEstadoOferta(Long id, OfertaDto ofertaDto);

    void delete(Long id);

    Optional<Oferta> getOne(Long id);

    public List<Oferta> findOfertaByQueryString(String titulo);
    List<Oferta> findByCreadorOferta(Encargado creador);

    List<Oferta> findByfechaPublicacion(Date fechaPublicacion);


}
