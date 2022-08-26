package com.pe.ttk.admision.controller;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.entity.master.Cargo;
import com.pe.ttk.admision.service.impl.CargoServieImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@CrossOrigin(origins = "http://localhost:4200")
public class CargoController {

    @Autowired
    CargoServieImp cargoServieImp;

    @ApiOperation("Lista todos los cargos registrado y su paginacion")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/lista", produces = "application/json")
    public ResponseEntity<?> listarCargos() {
        List<Cargo> listaCargos = cargoServieImp.listaCargos();

        return ResponseEntity.ok(listaCargos);
    }

    @ApiOperation("Registrar un cargo")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/registrar", produces = "application/json")
    public ResponseEntity<?> registrarCargo(@RequestBody Cargo cargo) {

        cargoServieImp.registrarCargo(cargo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Se ha registrado el cargo correctamente"));
    }

    @ApiOperation("Eliminar un cargo por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar", produces = "application/json")
    public ResponseEntity<?> eliminarCargo(@RequestParam("id") Long id) {

        cargoServieImp.eliminarCargo(id);
        return ResponseEntity.ok(new Mensaje("Cargo eliminado"));
    }

    @ApiOperation("Actualizar distintos campos de un cargo")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/actualizar/{id}", produces = "application/json")
    public ResponseEntity<?> actualizarCargo(@PathVariable("id") Long id, @RequestBody Cargo cargo) {

        cargoServieImp.actualizarCargo(id, cargo);
        return ResponseEntity.accepted().body(new Mensaje("cargo actualizado"));
    }

}












