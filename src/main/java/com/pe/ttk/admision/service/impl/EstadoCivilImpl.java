package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.entity.master.EstadoCivil;
import com.pe.ttk.admision.repository.EstadoCivilRepository;
import com.pe.ttk.admision.service.EstadoCivilService;
import com.pe.ttk.admision.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCivilImpl implements EstadoCivilService {

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    @Override
    public List<EstadoCivil> listarEstadoCivil() {
        return estadoCivilRepository.findByEstado(Constantes.ESTADO_ACTIVO);
    }
}
