package com.pe.ttk.admision.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.PostulanteDto;
import com.pe.ttk.admision.dto.entity.admision.PostulanteEntity;
import com.pe.ttk.admision.exceptions.TTKDataException;
import com.pe.ttk.admision.service.PostulanteService;
import com.pe.ttk.admision.service.impl.PostulanteServiceImpl;
import com.pe.ttk.admision.util.FilterParam;
import com.pe.ttk.admision.util.PaginationUtils;
import com.pe.ttk.admision.util.SearchCriteria;
import com.pe.ttk.admision.util.input.data.PostulanteFindInputData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/postulante")
@CrossOrigin(origins = "http://localhost:4200")
public class PostulanteController {

    @Autowired
    PostulanteService postulanteService;

    @ApiOperation("Lista todos los postulantes")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<?> listarPostulantes(@RequestParam(defaultValue = "0") Integer numPagina,
                                    @RequestParam(defaultValue = "10") Integer tamPagina) {
        return ResponseEntity.ok(postulanteService.listarPostulantes(numPagina, tamPagina));
    }

    @ApiOperation("Lista filtrada por datos del postulante")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista/filtrada")
    public String obtenerOfertaPorEstado(@RequestParam(value = "search") String query,
                                         @RequestParam(value = "numpagina") Integer page,
                                         @RequestParam(value = "size") Integer size,
                                         Model model) throws TTKDataException {
        /*List<SearchCriteria> params = FilterParam.filter(query);
        PostulanteFindInputData input = new PostulanteFindInputData();
        input.fillData(params);
        List<PostulanteDto> listaPostulanteDto = postulanteService.findByQueryString(input.getEstadoPostulacion(), input.getDistrito(), input.getProvincia(), input.getDepartamento(), input.getProfesion(),
                input.getResponsableAsignado(), input.getProcedencia(), input.getApellidoPaterno());
        return PaginationUtils.getPaginationedResults(listaPostulanteDto, page, size, model);*/
        return "";
    }

    @ApiOperation("Registrar un nuevo postulante")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPostulante(@RequestParam(name = "curriculum", required = false) MultipartFile curriculum,
                                                 @RequestParam(name = "dniFrontal", required = false) MultipartFile dniFrontal,
                                                 @RequestParam(name = "dniPosterior", required = false) MultipartFile dniPosterior,
                                                 @RequestParam(name = "foto", required = false) MultipartFile foto,
                                                 @RequestParam String postulante) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PostulanteDto postulanteDto = objectMapper.readValue(postulante, PostulanteDto.class);
        /*if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new Mensaje("Por favor ingrese los campos correctamente"));*/
        return ResponseEntity.status(HttpStatus.CREATED).body(postulanteService.registrarPostulante(postulanteDto, curriculum,
                dniFrontal, dniPosterior, foto));
    }

    @ApiOperation("Actualizar distintos campos de un postulante")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/")
    public ResponseEntity<?> actualizarPostulante(@RequestParam("id") int id,
                                                  @RequestParam(name = "dnifrontal", required = false) MultipartFile dnifrontal,
                                                  @RequestParam(name = "foto", required = false) MultipartFile foto,
                                                  @RequestParam(name = "dniposterior", required = false) MultipartFile dniposterior,
                                                  PostulanteDto postulanteDto) {

        PostulanteEntity postulanteEntity = postulanteService.getOne(id).get();
        postulanteService.UpdatePostulante(postulanteEntity, postulanteDto, dnifrontal, dniposterior, foto);

        return new ResponseEntity(new Mensaje("Datos del postulante actualizados correctamente"), HttpStatus.ACCEPTED);
    }


}
