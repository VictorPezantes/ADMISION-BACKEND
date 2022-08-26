package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.PostulanteDto;
import com.pe.ttk.admision.dto.entity.admision.Postulante;
import com.pe.ttk.admision.dto.entity.admision.PostulanteMapping;
import com.pe.ttk.admision.repository.PostulanteRepository;
import com.pe.ttk.admision.service.PostulanteService;
import com.pe.ttk.admision.util.ConvertirFechas;
import com.pe.ttk.admision.util.GuardarArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostulanteServiceImpl implements PostulanteService {

    @Autowired
    PostulanteRepository postulanteRepository;

    Postulante postulante = new Postulante();
    private Date fechaNacimiento;
    private Date fechaIngresoTrabajoReciente;
    private Date fechaSalidaTrabajoreciente;
    ConvertirFechas convertirFechas = new ConvertirFechas();
    GuardarArchivos guardarArchivos = new GuardarArchivos();


    public List<Postulante> list() {
        return postulanteRepository.findAll();
    }

    @Override
    public List<PostulanteDto> findByQueryString(String estadoPostulacion, String distrito, String provincia, String departamento, String profesion,
                                                        String responsableAsignado, String procedencia, String apellidoPaterno) {
        PostulanteDto postulanteDto = new PostulanteDto();
        List<PostulanteDto> listaPostulantesDto = new ArrayList<>();
        List<PostulanteMapping> listaPostulantesMapping = postulanteRepository.findByQueryString(estadoPostulacion,distrito, provincia,departamento);
       if(!listaPostulantesMapping.isEmpty()){
           for (PostulanteMapping p:listaPostulantesMapping) {
              // postulanteDto.setFechaIngresoTrabajoReciente(p.getFechaIngresoTrabajoReciente());
              // postulanteDto.setFechaSalidaTrabajoreciente(p.getFechaSalidaTrabajoreciente());
               postulanteDto.setApellidoMaterno(p.getApellidoMaterno());
               postulanteDto.setApellidoPaterno(p.getApellidoPaterno());
               postulanteDto.setCelularFamiliar(p.getCelularFamiliar());
               postulanteDto.setCelularPrincipal(p.getCelularPrincipal());
               postulanteDto.setDepartamento(p.getDepartamento());
               postulanteDto.setCurriculumVitae(p.getCurriculumVitae());
               postulanteDto.setDistrito(p.getDistrito());
               postulanteDto.setProvincia(p.getProvincia());
               postulanteDto.setDniFrontal(p.getDniFrontal());
               postulanteDto.setDniPosterior(p.getDniPosterior());
               postulanteDto.setEmailPrincipal(p.getEmailPrincipal());
               postulanteDto.setEmailSecundario(p.getEmailSecundario());
               postulanteDto.setEmpresaCurso(p.getEmpresaCurso());
               postulanteDto.setDireccionPrincipal(p.getDireccionPrincipal());
               postulanteDto.setEmpresaTrabajoReciente(p.getEmpresaTrabajoReciente());
               postulanteDto.setEstadoCivil(p.getEstadoCivil());
               postulanteDto.setEstadoPostulacion(p.getEstadoPostulacion());
               postulanteDto.setFotografia(p.getFotografia());
               postulanteDto.setTelefonoFijo(p.getTelefonoFijo());
               postulanteDto.setRespuestaDisponibilidadViajar(p.getRespuestaDisponibilidadViajar());
               postulanteDto.setRespuestaExperienciaMantencion(p.getRespuestaExperienciaMantencion());
               //postulanteDto.setFechaNacimiento(p.getFechaNacimiento());
               postulanteDto.setPrimerNombre(p.getPrimerNombre());
               postulanteDto.setSegundoNombre(p.getSegundoNombre());
               postulanteDto.setSubEstadoPostulacion(p.getSubEstadoPostulacion());
               postulanteDto.setUltimoCursoRealizado(p.getUltimoCursoRealizado());
               postulanteDto.setTrabajoReciente(p.getTrabajoReciente());
               postulanteDto.setLugarEstudios(p.getLugarEstudios());
               postulanteDto.setProfesion(p.getProfesion());
               postulanteDto.setResponsableAsignado(p.getResponsableAsignado());
               postulanteDto.setOfertaPostulada(p.getOfertaPostulada());
               postulanteDto.setProcedencia(p.getProcedencia());
               postulanteDto.setCargoPostulante(p.getCargoPostulante());
               postulanteDto.setMotivoSalidaTrabajoReciente(p.getMotivoSalidaTrabajoReciente());
               postulanteDto.setDni(p.getDni());


               listaPostulantesDto.add(postulanteDto);
           }
       }else {
           return listaPostulantesDto;
       }

        return listaPostulantesDto;
    }


    public void savePostulante(PostulanteDto postulanteDto, MultipartFile curriculum, MultipartFile dnifrontal, MultipartFile dniposterior, MultipartFile foto) {


        guardarArchivos.guardarArchivo(curriculum, dnifrontal, dniposterior, foto);
        fechaNacimiento = Date.valueOf(postulanteDto.getFechaNacimiento());
        if (postulanteDto.getFechaIngresoTrabajoReciente().isEmpty() || postulanteDto.getFechaSalidaTrabajoreciente().isEmpty()) {
            fechaIngresoTrabajoReciente = null;
            fechaSalidaTrabajoreciente = null;
        } else {
            fechaIngresoTrabajoReciente = Date.valueOf(postulanteDto.getFechaIngresoTrabajoReciente());
            fechaSalidaTrabajoreciente = Date.valueOf(postulanteDto.getFechaSalidaTrabajoreciente());
        }
        postulante.setFechaIngresoTrabajoReciente(fechaIngresoTrabajoReciente);
        postulante.setFechaSalidaTrabajoReciente(fechaSalidaTrabajoreciente);
        postulante.setApellidoMaterno(postulanteDto.getApellidoMaterno());
        postulante.setApellidoPaterno(postulanteDto.getApellidoPaterno());
        postulante.setCelularFamiliar(postulanteDto.getCelularFamiliar());
        postulante.setCelularPrincipal(postulanteDto.getCelularPrincipal());
        postulante.setDepartamento(postulanteDto.getDepartamento());
        postulante.setProvincia(postulanteDto.getProvincia());
        postulante.setCurriculumVitae(curriculum.getOriginalFilename());
        postulante.setDistrito(postulanteDto.getDistrito());
        postulante.setDniFrontal(dnifrontal.getOriginalFilename());
        postulante.setDniPosterior(dniposterior.getOriginalFilename());
        postulante.setEmailPrincipal(postulanteDto.getEmailPrincipal());
        postulante.setEmailSecundario(postulanteDto.getEmailSecundario());
        postulante.setEmpresaCurso(postulanteDto.getEmpresaCurso());
        postulante.setDireccionPrincipal(postulanteDto.getDireccionPrincipal());
        postulante.setEmpresaTrabajoReciente(postulanteDto.getEmpresaTrabajoReciente());
        postulante.setEstadoCivil(postulanteDto.getEstadoCivil());
        postulante.setEstadoPostulacion(postulanteDto.getEstadoPostulacion());
        postulante.setFotografia(foto.getOriginalFilename());
        postulante.setTelefonoFijo(postulanteDto.getTelefonoFijo());
        postulante.setRespuestaDisponibilidadViajar(postulanteDto.getRespuestaDisponibilidadViajar());
        postulante.setRespuestaExperienciaMantencion(postulanteDto.getRespuestaExperienciaMantencion());
        postulante.setFechaNacimiento(fechaNacimiento);
        postulante.setFechaPostulacion(convertirFechas.stringToDateSql());
        postulante.setPrimerNombre(postulanteDto.getPrimerNombre());
        postulante.setSegundoNombre(postulanteDto.getSegundoNombre());
        postulante.setSubEstadoPostulacion(postulanteDto.getSubEstadoPostulacion());
        postulante.setTrabajoReciente(postulanteDto.getTrabajoReciente());
        postulante.setLugarEstudios(postulanteDto.getLugarEstudios());
        postulante.setProfesion(postulanteDto.getProfesion());
        postulante.setUltimoCursoRealizado(postulanteDto.getUltimoCursoRealizado());
        postulante.setMotivoSalidaTrabajoReciente(postulanteDto.getMotivoSalidaTrabajoReciente());
        postulante.setProcedencia(postulanteDto.getProcedencia());
        postulante.setCargoPostulante(postulanteDto.getCargoPostulante());
        postulante.setOfertaPostulada(postulanteDto.getOfertaPostulada());
        postulante.setCantidadPostulaciones(postulanteDto.getCantidadPostulaciones());
        postulante.setResponsableAsignado(postulanteDto.getResponsableAsignado());
        postulante.setDni(postulanteDto.getDni());
        postulanteRepository.save(postulante);
    }

    @Override
    public void UpdatePostulante(Postulante postulante,PostulanteDto postulanteDto, MultipartFile dnifrontal,MultipartFile dniposterior,MultipartFile foto) {

        guardarArchivos.actualizarArchivo(dnifrontal, dniposterior, foto);
        postulante.setCelularFamiliar(postulanteDto.getCelularFamiliar());
        postulante.setCelularPrincipal(postulanteDto.getCelularPrincipal());
        postulante.setDepartamento(postulanteDto.getDepartamento());
        postulante.setDistrito(postulanteDto.getDistrito());
        postulante.setProvincia(postulanteDto.getProvincia());
        postulante.setDniFrontal(dnifrontal.getOriginalFilename());
        postulante.setDniPosterior(dniposterior.getOriginalFilename());
        postulante.setFotografia(foto.getOriginalFilename());
        postulante.setEmailPrincipal(postulanteDto.getEmailPrincipal());
        postulante.setDireccionPrincipal(postulanteDto.getDireccionPrincipal());
        postulante.setEstadoCivil(postulanteDto.getEstadoCivil());
        postulante.setEstadoPostulacion(postulanteDto.getEstadoPostulacion());
        postulante.setFotografia(postulanteDto.getFotografia());
        postulante.setTelefonoFijo(postulanteDto.getTelefonoFijo());
        postulante.setSubEstadoPostulacion(postulanteDto.getSubEstadoPostulacion());
        postulante.setResponsableAsignado(postulanteDto.getResponsableAsignado());
        postulante.setProcedencia(postulanteDto.getProcedencia());
        postulanteRepository.save(postulante);
    }

    public void delete(int id) {
        postulanteRepository.deleteById(id);
    }

    public Optional<Postulante> getOne(int id) {
        return postulanteRepository.findById(id);
    }
}
