package com.pe.ttk.admision.service;


import com.pe.ttk.admision.dto.entity.master.Encargado;

import java.util.List;
import java.util.Optional;

public interface EncargadoService {

    List<Encargado> listaEncargados();

    void registrarEncargado(Encargado encargado);

    void eliminarEncargado(Long id);

    void actualizarEncargado(Long id, Encargado encargado);

    Optional<Encargado> getOne(Long id);

    boolean existsByEmail(String email);
}
