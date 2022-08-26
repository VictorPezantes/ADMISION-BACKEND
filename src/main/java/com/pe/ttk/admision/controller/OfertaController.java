package com.pe.ttk.admision.controller;

import com.google.gson.Gson;
import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.OfertaDto;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.dto.entity.admision.Oferta;
import com.pe.ttk.admision.exceptions.TTKDataException;
import com.pe.ttk.admision.service.impl.OfertaServiceImpl;
import com.pe.ttk.admision.util.FilterParam;
import com.pe.ttk.admision.util.PaginationUtils;
import com.pe.ttk.admision.util.SearchCriteria;
import com.pe.ttk.admision.util.input.data.OfertaFindInputData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/oferta")
@CrossOrigin(origins = "http://localhost:4200")
public class OfertaController {

    @Autowired
    OfertaServiceImpl ofertaService;

    @ApiOperation("Lista todas las ofertas creadas")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public String listarOfertas(@RequestParam(value = "numpagina") Integer page,
                                @RequestParam(value = "size") Integer size,
                                Model model) {

        List<Oferta> listaOfertas = ofertaService.listarOfertas();

        return PaginationUtils.getPaginationedResults(listaOfertas, page, size, model);
    }


    @ApiOperation("Crear ofertas")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/crear")
    public ResponseEntity<?> crearOferta(@RequestBody Oferta oferta, Authentication auth) {

        ofertaService.registrarOferta(oferta, auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaService.registrarOferta(oferta, auth));
    }

    @ApiOperation("Actualizar distintos campos de una oferta")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarOferta(@PathVariable("id") Long id, @RequestBody OfertaDto ofertaDto) {

        ofertaService.actualizarOferta(id, ofertaDto);
        return ResponseEntity.accepted().body(new Mensaje("oferta actualizada"));
    }

    @ApiOperation("Actualizar estado de una oferta")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/actualizar/estado/{id}")
    public ResponseEntity<?> actualizarEstadoOferta(@PathVariable("id") Long id, @RequestBody OfertaDto ofertaDto) {

        ofertaService.actualizarEstadoOferta(id, ofertaDto);
        return ResponseEntity.accepted().body(new Mensaje("oferta actualizada"));
    }

    @ApiOperation("Eliminar una oferta por id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOferta(@RequestParam("id") Long id) {

        ofertaService.delete(id);
        return ResponseEntity.ok(new Mensaje("Oferta eliminada"));
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

        List<Oferta> listaOfertas= new ArrayList<>();

        if(!query.isEmpty()) {
            List<SearchCriteria> params = FilterParam.filter(query);
            OfertaFindInputData input = new OfertaFindInputData();
            input.fillData(params);
            listaOfertas = ofertaService.findOfertaByQueryString(input.getTitulo());
        }
        if(estado  != null)
            listaOfertas = ofertaService.findByEstadoOferta(estado);
        if(creador != null)
            listaOfertas = ofertaService.findByCreadorOferta(creador);

        if(!fechaPublicacion.isEmpty()){

            Date fecha = Date.valueOf(fechaPublicacion);;
            listaOfertas = ofertaService.findByfechaPublicacion(fecha);}
        return PaginationUtils.getPaginationedResults(listaOfertas, page, size, model);
    }



}