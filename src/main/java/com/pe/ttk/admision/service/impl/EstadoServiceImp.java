package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.repository.EstadoRepository;
import com.pe.ttk.admision.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstadoServiceImp implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Estado> listaEstados() {
        return estadoRepository.findAll();
    }

    public void registrarEstado(Estado estado) {

        estadoRepository.save(estado);
    }

    @Override
    public void eliminarEstado(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public void actualizarEstado(Long id, Estado estado) {

        Estado estadoOferta = getOne(id).get();
        estadoOferta.setEstado(estado.getEstado());
        estadoRepository.save(estadoOferta);
    }
    @Override
    public Optional<Estado> getOne(Long id) {
        return estadoRepository.findById( id);
    }
}
