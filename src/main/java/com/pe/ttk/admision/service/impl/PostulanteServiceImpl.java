package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.PostulanteDto;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import com.pe.ttk.admision.dto.entity.admision.PostulanteEntity;
import com.pe.ttk.admision.dto.entity.admision.PostulanteMapping;
import com.pe.ttk.admision.repository.OfertaRepository;
import com.pe.ttk.admision.repository.PostulanteRepository;
import com.pe.ttk.admision.service.PostulanteService;
import com.pe.ttk.admision.util.Constantes;
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

    @Autowired
    OfertaRepository ofertaRepository;

    PostulanteEntity postulanteEntity = new PostulanteEntity();
    private Date fechaNacimiento;
    private Date fechaIngresoTrabajoReciente;
    private Date fechaSalidaTrabajoreciente;
    ConvertirFechas convertirFechas = new ConvertirFechas();
    GuardarArchivos guardarArchivos = new GuardarArchivos();


    public List<PostulanteEntity> list() {
        return postulanteRepository.findAll();
    }

    @Override
    public List<PostulanteDto> findByQueryString(String estadoPostulacion, String distrito, String provincia, String departamento, String profesion,
                                                        String responsableAsignado, String procedencia, String apellidoPaterno) {
        PostulanteDto postulanteDto = new PostulanteDto();
        List<PostulanteDto> listaPostulantesDto = new ArrayList<>();
        /*List<PostulanteMapping> listaPostulantesMapping = postulanteRepository.findByQueryString(estadoPostulacion,distrito, provincia,departamento);
        if(!listaPostulantesMapping.isEmpty()){for (PostulanteMapping p:listaPostulantesMapping) {
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

       }else {
           return listaPostulantesDto;
       }
       */
        return listaPostulantesDto;
    }


    public Mensaje registrarPostulante(PostulanteDto postulanteDto, MultipartFile curriculum, MultipartFile dnifrontal, MultipartFile dniposterior, MultipartFile foto) {


        //guardarArchivos.guardarArchivo(curriculum, dnifrontal, dniposterior, foto);
        /*fechaNacimiento = postulanteDto.getFechaNacimiento();
        if (postulanteDto.getFechaIngresoTrabajoReciente() == null || postulanteDto.getFechaSalidaTrabajoReciente() == null) {
            fechaIngresoTrabajoReciente = null;
            fechaSalidaTrabajoreciente = null;
        } else {
            fechaIngresoTrabajoReciente = postulanteDto.getFechaIngresoTrabajoReciente();
            fechaSalidaTrabajoreciente = Date.valueOf(postulanteDto.getFechaSalidaTrabajoreciente());
        }*/
        postulanteEntity.setFechaIngresoTrabajoReciente(fechaIngresoTrabajoReciente);
        postulanteEntity.setFechaSalidaTrabajoReciente(fechaSalidaTrabajoreciente);
        postulanteEntity.setApellidoMaterno(postulanteDto.getApellidoMaterno());
        postulanteEntity.setApellidoPaterno(postulanteDto.getApellidoPaterno());
        postulanteEntity.setCelularFamiliar(postulanteDto.getCelularFamiliar());
        postulanteEntity.setCelular(postulanteDto.getCelular());
        postulanteEntity.setIdDepartamento(postulanteDto.getIdDepartamento());
        postulanteEntity.setIdProvincia(postulanteDto.getIdProvincia());
        //postulanteEntity.setCurriculumVitae(curriculum.getOriginalFilename());
        postulanteEntity.setIdDistrito(postulanteDto.getIdDistrito());
        //postulanteEntity.setDniFrontal(dnifrontal.getOriginalFilename());
        //postulanteEntity.setDniPosterior(dniposterior.getOriginalFilename());
        postulanteEntity.setEmail(postulanteDto.getEmail());
        postulanteEntity.setEmailSecundario(postulanteDto.getEmailSecundario());
        postulanteEntity.setEmpresaCurso(postulanteDto.getEmpresaCurso());
        postulanteEntity.setDireccion(postulanteDto.getDireccion());
        postulanteEntity.setEmpresaTrabajoReciente(postulanteDto.getEmpresaTrabajoReciente());
        postulanteEntity.setIdEstadoCivil(postulanteDto.getIdEstadoCivil());
        postulanteEntity.setEstadoPostulacion(postulanteDto.getEstadoPostulacion());
        //postulanteEntity.setFotografia(foto.getOriginalFilename());
        postulanteEntity.setTelefonoFijo(postulanteDto.getTelefonoFijo());
        postulanteEntity.setDisponibilidadViajar(postulanteDto.getDisponibilidadViajar());
        postulanteEntity.setExperienciaRubro(postulanteDto.getExperienciaRubro());
        postulanteEntity.setFechaNacimiento(postulanteDto.getFechaNacimiento());
        postulanteEntity.setDni(postulanteDto.getDni());
        postulanteEntity.setFechaPostulacion(ConvertirFechas.getInstance().obtenerFechaActual());
        postulanteEntity.setPrimerNombre(postulanteDto.getPrimerNombre());
        postulanteEntity.setSegundoNombre(postulanteDto.getSegundoNombre());
        //postulanteEntity.setSubEstadoPostulacion(postulanteDto.getSubEstadoPostulacion());
        postulanteEntity.setTrabajoReciente(postulanteDto.getTrabajoReciente());
        postulanteEntity.setLugarEstudios(postulanteDto.getLugarEstudios());
        postulanteEntity.setProfesion(postulanteDto.getProfesion());
        postulanteEntity.setUltimoCursoRealizado(postulanteDto.getUltimoCursoRealizado());
        postulanteEntity.setMotivoSalidaTrabajoReciente(postulanteDto.getMotivoSalidaTrabajoReciente());
        postulanteEntity.setProcedencia(postulanteDto.getProcedencia());
        postulanteEntity.setIdOferta(postulanteDto.getIdOferta());

        Optional<OfertaEntity> ofertaOp = ofertaRepository.findByIdAndEstado(postulanteDto.getIdOferta(), Constantes.ESTADO_ACTIVO);
        if(ofertaOp.isEmpty()){
            return new Mensaje("No existe la oferta");
        }
        OfertaEntity ofertaDb = ofertaOp.get();
        postulanteEntity.setOfertaPostulada(ofertaDb.getTitulo());
        //postulanteEntity.setCantidadPostulaciones(postulanteDto.getCantidadPostulaciones());
        //postulanteEntity.setResponsableAsignado(postulanteDto.getResponsableAsignado());
        postulanteEntity.setEstado(Constantes.ESTADO_ACTIVO);

        postulanteRepository.save(postulanteEntity);
        return new Mensaje("Postulante registrado correctamente");
    }

    @Override
    public void UpdatePostulante(PostulanteEntity postulanteEntity, PostulanteDto postulanteDto, MultipartFile dnifrontal, MultipartFile dniposterior, MultipartFile foto) {

        guardarArchivos.actualizarArchivo(dnifrontal, dniposterior, foto);
        postulanteEntity.setCelularFamiliar(postulanteDto.getCelularFamiliar());
        /*postulanteEntity.setCelularPrincipal(postulanteDto.getCelularPrincipal());
        postulanteEntity.setDepartamento(postulanteDto.getDepartamento());
        postulanteEntity.setDistrito(postulanteDto.getDistrito());
        postulanteEntity.setProvincia(postulanteDto.getProvincia());
        postulanteEntity.setDniFrontal(dnifrontal.getOriginalFilename());
        postulanteEntity.setDniPosterior(dniposterior.getOriginalFilename());
        postulanteEntity.setFotografia(foto.getOriginalFilename());
        postulanteEntity.setEmailPrincipal(postulanteDto.getEmailPrincipal());
        postulanteEntity.setDireccionPrincipal(postulanteDto.getDireccionPrincipal());
        postulanteEntity.setEstadoCivil(postulanteDto.getEstadoCivil());
        postulanteEntity.setEstadoPostulacion(postulanteDto.getEstadoPostulacion());
        postulanteEntity.setFotografia(postulanteDto.getFotografia());
        postulanteEntity.setTelefonoFijo(postulanteDto.getTelefonoFijo());
        postulanteEntity.setSubEstadoPostulacion(postulanteDto.getSubEstadoPostulacion());
        postulanteEntity.setResponsableAsignado(postulanteDto.getResponsableAsignado());
        postulanteEntity.setProcedencia(postulanteDto.getProcedencia());
        postulanteRepository.save(postulanteEntity);*/
    }

    public void delete(int id) {
        postulanteRepository.deleteById(id);
    }

    public Optional<PostulanteEntity> getOne(int id) {
        return postulanteRepository.findById(id);
    }
}
