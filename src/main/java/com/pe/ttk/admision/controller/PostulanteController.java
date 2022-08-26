package com.pe.ttk.admision.controller;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.PostulanteDto;
import com.pe.ttk.admision.dto.entity.admision.Postulante;
import com.pe.ttk.admision.exceptions.TTKDataException;
import com.pe.ttk.admision.service.impl.PostulanteServiceImpl;
import com.pe.ttk.admision.util.FilterParam;
import com.pe.ttk.admision.util.PaginationUtils;
import com.pe.ttk.admision.util.SearchCriteria;
import com.pe.ttk.admision.util.input.data.PostulanteFindInputData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/postulante")
@CrossOrigin(origins = "http://localhost:4200")
public class PostulanteController {

    @Autowired
    PostulanteServiceImpl postulanteService;

    @ApiOperation("Lista todos los postulantes")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar-postulantes")
    public String listarPostulantes(@RequestParam(value = "numpagina") Integer page,
                                    @RequestParam(value = "size") Integer size,
                                    Model model) {
        List<Postulante> listaPostulantes = postulanteService.list();
        return PaginationUtils.getPaginationedResults(listaPostulantes, page, size, model);
    }

    @ApiOperation("Lista filtrada por datos del postulante")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista/filtrada")
    public String obtenerOfertaPorEstado(@RequestParam(value = "search") String query,
                                         @RequestParam(value = "numpagina") Integer page,
                                         @RequestParam(value = "size") Integer size,
                                         Model model) throws TTKDataException {
        List<SearchCriteria> params = FilterParam.filter(query);
        PostulanteFindInputData input = new PostulanteFindInputData();
        input.fillData(params);
        List<PostulanteDto> listaPostulanteDto = postulanteService.findByQueryString(input.getEstadoPostulacion(), input.getDistrito(), input.getProvincia(), input.getDepartamento(), input.getProfesion(),
                input.getResponsableAsignado(), input.getProcedencia(), input.getApellidoPaterno());
        return PaginationUtils.getPaginationedResults(listaPostulanteDto, page, size, model);
    }

    @ApiOperation("Registrar un nuevo postulante")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPostulante(@RequestParam(name = "curriculum", required = false) MultipartFile curriculum,
                                                 @RequestParam(name = "dnifrontal", required = false) MultipartFile dnifrontal,
                                                 @RequestParam(name = "foto", required = false) MultipartFile foto,
                                                 @RequestParam(name = "dniposterior", required = false) MultipartFile dniposterior,
                                                 PostulanteDto postulanteDto) {
        postulanteService.savePostulante(postulanteDto, curriculum, dnifrontal, dniposterior, foto);
        return new ResponseEntity(new Mensaje("Postulante registrado correctamente"), HttpStatus.CREATED);
    }

    @ApiOperation("Actualizar distintos campos de un postulante")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/")
    public ResponseEntity<?> actualizarPostulante(@RequestParam("id") int id,
                                                  @RequestParam(name = "dnifrontal", required = false) MultipartFile dnifrontal,
                                                  @RequestParam(name = "foto", required = false) MultipartFile foto,
                                                  @RequestParam(name = "dniposterior", required = false) MultipartFile dniposterior,
                                                  PostulanteDto postulanteDto) {

        Postulante postulante = postulanteService.getOne(id).get();
        postulanteService.UpdatePostulante(postulante, postulanteDto, dnifrontal, dniposterior, foto);

        return new ResponseEntity(new Mensaje("Datos del postulante actualizados correctamente"), HttpStatus.ACCEPTED);
    }


}
