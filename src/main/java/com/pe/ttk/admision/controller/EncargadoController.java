package com.pe.ttk.admision.controller;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.service.impl.EncargadoServieImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encargado")
@CrossOrigin(origins = "http://localhost:4200")
public class EncargadoController {

    @Autowired
    EncargadoServieImp encargadoServieImp;

    @ApiOperation("Lista todos los encargados registrado y paginacion")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<?>   listarEncargado() {
        List<Encargado> listaEncargados = encargadoServieImp.listaEncargados();

        return ResponseEntity.ok(listaEncargados);

    }
        @ApiOperation("Registrar encargado")
        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/registrar")
        public ResponseEntity<?>  registrarEncargado(@RequestBody Encargado encargado,
                                                     BindingResult bindingResult){
            if (bindingResult.hasErrors())
                return ResponseEntity.badRequest().body(new Mensaje("campos mal puestos o email inválido"));

            boolean  ok = encargadoServieImp.existsByEmail(encargado.getEmail());
            if (ok) return ResponseEntity.badRequest().body(new Mensaje("El email ya existe"));
            encargadoServieImp.registrarEncargado(encargado);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Encargado registrado correctamente"));
        }

    @ApiOperation("Eliminar un encargado por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarEncargado(@RequestParam("id") Long id) {

        encargadoServieImp.eliminarEncargado(id);
        return ResponseEntity.ok(new Mensaje("Encargado eliminado"));
    }

    @ApiOperation("Actualizar distintos campos de un encargado")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEncargado(@PathVariable("id") Long id,
                                                 @RequestBody Encargado encargado) {
        encargadoServieImp.actualizarEncargado(id, encargado);
        return ResponseEntity.accepted().body(new Mensaje("encargado actualizado"));
    }
    }












