package com.pe.ttk.admision.controller;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.service.impl.EstadoServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estado")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoController {

    @Autowired
    EstadoServiceImp estadoServiceImp;

    @ApiOperation("Lista todos los estados de las  ofertas")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<?> listarEstados() {

        return ResponseEntity.ok(estadoServiceImp.listaEstados());

    }

    @ApiOperation("Registrar estado")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/registrar")
    public ResponseEntity<?> registrarEstado(@RequestBody Estado estado) {

        estadoServiceImp.registrarEstado(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("estado registrado correctamente"));
    }

    @ApiOperation("Eliminar un estado por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/eliminar", produces = "application/json")
    public ResponseEntity<?> eliminarEstado(@RequestParam("id") Long id) {

        estadoServiceImp.eliminarEstado(id);
        return ResponseEntity.ok(new Mensaje("Estado eliminado"));
    }

    @ApiOperation("Actualizar distintos campos de un estado")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/actualizar/{id}", produces = "application/json")
    public ResponseEntity<?> actualizarEstado(@PathVariable("id") Long id, @RequestBody Estado estado) {

        estadoServiceImp.actualizarEstado(id, estado);
        return ResponseEntity.accepted().body(new Mensaje("estado actualizado"));
    }
}












