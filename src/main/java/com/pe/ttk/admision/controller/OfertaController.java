package com.pe.ttk.admision.controller;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.OfertaDto;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import com.pe.ttk.admision.exceptions.TTKDataException;
import com.pe.ttk.admision.service.impl.OfertaServiceImpl;
import com.pe.ttk.admision.util.FilterParam;
import com.pe.ttk.admision.util.PaginationUtils;
import com.pe.ttk.admision.util.SearchCriteria;
import com.pe.ttk.admision.util.input.data.OfertaFindInputData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/oferta")
@CrossOrigin(origins = "http://localhost:4200")
public class OfertaController {

    @Autowired
    OfertaServiceImpl ofertaService;

    @ApiOperation("Listar las ofertas activas")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<?> listarOfertas(@RequestParam(defaultValue = "0") Integer numPagina,
                                @RequestParam(defaultValue = "10") Integer tamPagina,
                                @RequestParam(defaultValue = "") String titulo,
                                @RequestParam(defaultValue = "") List estado,
                                @RequestParam(defaultValue = "")String fechaPublicacion,
                                @RequestParam(defaultValue = "")List creador) {

        return ResponseEntity.ok(ofertaService.listarOfertas(numPagina, tamPagina, titulo, estado,
                fechaPublicacion, creador));
    }

    @ApiOperation("Listar las ofertas activadas para landing")
    @GetMapping("/listar-landing")
    public ResponseEntity<?> listarOfertasLanding(@RequestParam(defaultValue = "0") Integer numPagina,
                                           @RequestParam(defaultValue = "10") Integer tamPagina) {

        return ResponseEntity.ok(ofertaService.listarOfertasLanding(numPagina, tamPagina));
    }

    @ApiOperation("Obtener oferta para actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtenerOferta(@PathVariable Long id, Authentication auth) {
        if(!auth.isAuthenticated()){
            return ResponseEntity.badRequest().body(new Mensaje("No está logueado en el sistema"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaService.obtenerOferta(id));
    }

    @ApiOperation("Registrar oferta")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/registrar")
    public ResponseEntity<?> registrarOferta(@RequestBody OfertaDto ofertaDto, Authentication auth) {
        if(!auth.isAuthenticated()){
           return ResponseEntity.badRequest().body(new Mensaje("No está logueado en el sistema"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaService.registrarOferta(ofertaDto, auth));
    }

    @ApiOperation("Actualizar distintos campos de una oferta")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/actualizar")
    public ResponseEntity<?> actualizarOferta(@RequestBody OfertaDto ofertaDto) {

        return ResponseEntity.accepted().body(ofertaService.actualizarOferta(ofertaDto));
    }

    @ApiOperation("Actualizar estado de una oferta")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/actualizar-estado")
    public ResponseEntity<?> actualizarEstadoOferta(@RequestBody OfertaDto ofertaDto) {

        return ResponseEntity.accepted().body(ofertaService.actualizarEstadoOferta(ofertaDto));
    }

    @ApiOperation("Eliminar una oferta por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOferta(@RequestBody OfertaDto ofertaDto) {

        return ResponseEntity.ok(ofertaService.eliminarOferta(ofertaDto));
    }

    @ApiOperation("Lista filtrada por datos del postulante")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista/filtrada")
    public String obtenerOfertaPorEstado(@RequestParam(value = "search") String query,
                                         @RequestParam(value = "numpagina") Integer page,
                                         @RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "estadoOferta") Estado estado,
                                         @RequestParam(value = "creadorOferta") Encargado creador,
                                         @RequestParam(value = "fechaPublicacion") String fechaPublicacion,
                                         Model model) throws TTKDataException {

        List<OfertaEntity> listaOfertaEntities = new ArrayList<>();

        if(!query.isEmpty()) {
            List<SearchCriteria> params = FilterParam.filter(query);
            OfertaFindInputData input = new OfertaFindInputData();
            input.fillData(params);
            listaOfertaEntities = ofertaService.findOfertaByQueryString(input.getTitulo());
        }
        if(estado  != null)
            listaOfertaEntities = ofertaService.findByEstadoOferta(estado);
        if(creador != null)
            listaOfertaEntities = ofertaService.findByCreadorOferta(creador);

        if(!fechaPublicacion.isEmpty()){

            Date fecha = Date.valueOf(fechaPublicacion);;
            listaOfertaEntities = ofertaService.findByfechaPublicacion(fecha);}
        return PaginationUtils.getPaginationedResults(listaOfertaEntities, page, size, model);
    }



}