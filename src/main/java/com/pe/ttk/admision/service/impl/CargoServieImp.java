package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.entity.master.Cargo;
import com.pe.ttk.admision.repository.CargoRepository;
import com.pe.ttk.admision.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CargoServieImp implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    Cargo cargo = new Cargo();

    @Override
    public List<Cargo> listaCargos() {
        return cargoRepository.findAll();
    }

    public void registrarCargo(Cargo cargo){

       cargoRepository.save(cargo);

    }

    @Override
    public void eliminarCargo(Long id) {

        cargoRepository.deleteById(id);

    }

    @Override
    public void actualizarCargo(Long id, Cargo cargo) {

        Cargo cargoOferta = getOne(id).get();
        cargoOferta.setNombreCargo(cargo.getNombreCargo());
        cargoRepository.save(cargoOferta);

    }

    @Override
    public Optional<Cargo> getOne(Long id) {
        return cargoRepository.findById( id);
    }
}
