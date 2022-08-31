package com.pe.ttk.admision.controller;

import com.pe.ttk.admision.service.DireccionService;
import com.pe.ttk.admision.service.EstadoCivilService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estado-civil")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoCivilController {

    @Autowired
    EstadoCivilService estadoCivilService;

    @ApiOperation("Listar los estado civiles")
    @GetMapping("/listar")
    public ResponseEntity<?> listarEstadoCiviles() {

        return ResponseEntity.ok(estadoCivilService.listarEstadoCivil());
    }

}












