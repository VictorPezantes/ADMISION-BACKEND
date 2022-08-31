package com.pe.ttk.admision.controller;

import com.pe.ttk.admision.service.DireccionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/direccion")
@CrossOrigin(origins = "http://localhost:4200")
public class DireccionController {

    @Autowired
    DireccionService direccionService;

    @ApiOperation("Listar los departamentos")
    @GetMapping("/listar-departamentos")
    public ResponseEntity<?> listarDepartamentos() {

        return ResponseEntity.ok(direccionService.listarDepartamentos());
    }

    @ApiOperation("Listar las provincias de un departamento")
    @GetMapping("/listar-provincias/{idDepartamento}")
    public ResponseEntity<?> listarDepartamentos(@PathVariable Long idDepartamento) {

        return ResponseEntity.ok(direccionService.listarProvincias(idDepartamento));
    }

    @ApiOperation("Listar los distritos de una provincia")
    @GetMapping("/listar-distritos/{idProvincia}")
    public ResponseEntity<?> listarDistritos(@PathVariable Long idProvincia) {

        return ResponseEntity.ok(direccionService.listarDistritos(idProvincia));
    }

}












