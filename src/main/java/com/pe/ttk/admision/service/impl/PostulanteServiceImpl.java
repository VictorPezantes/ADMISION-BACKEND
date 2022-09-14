package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.PostulanteDto;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import com.pe.ttk.admision.dto.entity.admision.PostulanteEntity;
import com.pe.ttk.admision.dto.entity.master.HistorialEntity;
import com.pe.ttk.admision.repository.HistorialRepository;
import com.pe.ttk.admision.repository.OfertaRepository;
import com.pe.ttk.admision.repository.PostulanteRepository;
import com.pe.ttk.admision.util.mapper.security.service.EmailService;
import com.pe.ttk.admision.service.PostulanteService;
import com.pe.ttk.admision.util.Constantes;
import com.pe.ttk.admision.util.ConvertirFechas;
import com.pe.ttk.admision.util.GuardarArchivos;
import com.pe.ttk.admision.util.mapper.PostulanteMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostulanteServiceImpl implements PostulanteService {

    @Autowired
    private PostulanteRepository postulanteRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private HistorialRepository historialRepository;


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


    public Mensaje registrarPostulante(PostulanteDto postulanteDto, MultipartFile curriculum, MultipartFile dniFrontal,
                                       MultipartFile dniPosterior, MultipartFile foto) {

        String dni = postulanteDto.getDni();

        if(postulanteRepository.existsByDniAndEstado(dni, Constantes.ESTADO_ACTIVO)){
            return new Mensaje("Ya existe una postulación, solo puede postular una sola vez");
        }

        String nombreCurriculum = "";
        String nombreDniFrontal = "";
        String nombreDniPosterior = "";
        String nombreFoto = "";
        if(!curriculum.isEmpty()){
            nombreCurriculum = dni+Constantes.CURRICULUM+"."+FilenameUtils.getExtension(curriculum.getOriginalFilename());
            guardarArchivos.guardarArchivo(curriculum, nombreCurriculum);
        }
        if(!dniFrontal.isEmpty()){
            nombreDniFrontal = dni+Constantes.DNI_FRONTAL+"."+FilenameUtils.getExtension(dniFrontal.getOriginalFilename());
            guardarArchivos.guardarArchivo(dniFrontal, nombreDniFrontal);
        }
        if(!dniPosterior.isEmpty()){
            nombreDniPosterior = dni+Constantes.DNI_POSTERIOR+"."+FilenameUtils.getExtension(dniPosterior.getOriginalFilename());
            guardarArchivos.guardarArchivo(dniPosterior, nombreDniPosterior);
        }
        if(!foto.isEmpty()){
            nombreFoto = dni+Constantes.FOTO+"."+FilenameUtils.getExtension(foto.getOriginalFilename());
            guardarArchivos.guardarArchivo(foto, nombreFoto);
        }

        PostulanteEntity postulanteEntity = PostulanteMapper.INSTANCE.toPostulanteEntity(postulanteDto);
        postulanteEntity.setFechaPostulacion(ConvertirFechas.getInstance().obtenerFecha(new java.util.Date()));
        postulanteEntity.setCurriculum(nombreCurriculum);
        postulanteEntity.setDniFrontal(nombreDniFrontal);
        postulanteEntity.setDniPosterior(nombreDniPosterior);
        postulanteEntity.setFoto(nombreFoto);

        Optional<OfertaEntity> ofertaOp = ofertaRepository.findByIdAndEstado(postulanteDto.getIdOferta(), Constantes.ESTADO_ACTIVO);
        if(ofertaOp.isEmpty()){
            return new Mensaje("No existe la oferta");
        }
        OfertaEntity ofertaDb = ofertaOp.get();
        ofertaDb.setCantidadPostulantes(ofertaDb.getCantidadPostulantes() + 1);
        postulanteEntity.setOfertaPostulada(ofertaDb.getTitulo());

        int anioActual = ConvertirFechas.getInstance().obtenerAnioMesDia(Calendar.YEAR);
        int mesActual = ConvertirFechas.getInstance().obtenerAnioMesDia(Calendar.MONTH);
        int diaActual = ConvertirFechas.getInstance().obtenerAnioMesDia(Calendar.DAY_OF_MONTH);

        int anioNac = ConvertirFechas.getInstance().obtenerAnioMesDia(Calendar.YEAR, postulanteEntity.getFechaNacimiento());
        int mesNac = ConvertirFechas.getInstance().obtenerAnioMesDia(Calendar.MONTH,postulanteEntity.getFechaNacimiento());
        int diaNac = ConvertirFechas.getInstance().obtenerAnioMesDia(Calendar.DAY_OF_MONTH,postulanteEntity.getFechaNacimiento());

        String mensaje = "";
        String asunto = "Postulación a " + ofertaDb.getTitulo() + " - Technotankers";
        boolean verificarEdad = false;
        int edad = anioActual - anioNac;
        if(ofertaDb.getCargoOferta().getNombreCargo().equalsIgnoreCase(Constantes.CARGO_TECNICO) ||
            ofertaDb.getCargoOferta().getNombreCargo().equalsIgnoreCase(Constantes.CARGO_SUPERVISOR)){
            postulanteEntity.setProcedencia(Constantes.PROC_MILITAR);
            if(anioActual - anioNac == Constantes.EDAD_TECNICO_SUPERVISOR){
                if(mesActual == mesNac && diaActual >= diaNac) verificarEdad = true;
                if(mesActual > mesNac) verificarEdad = true;
                edad = Constantes.EDAD_TECNICO_SUPERVISOR;
            }
            if(anioActual - anioNac < Constantes.EDAD_TECNICO_SUPERVISOR ||
                    anioActual - anioNac > Constantes.EDAD_TECNICO_SUPERVISOR) verificarEdad = false;
        }
        else if(ofertaDb.getCargoOferta().getNombreCargo().equalsIgnoreCase(Constantes.CARGO_OPERARIO)){
            postulanteEntity.setProcedencia(Constantes.PROC_CIVIL);
            if(anioActual - anioNac == Constantes.EDAD_OPERARIO){
                if(mesActual == mesNac && diaActual >= diaNac) verificarEdad = true;
                if(mesActual > mesNac) verificarEdad = true;
                edad = Constantes.EDAD_OPERARIO;
            }
            if(anioActual - anioNac < Constantes.EDAD_OPERARIO || anioActual - anioNac > Constantes.EDAD_OPERARIO) verificarEdad = false;
        }
        if(verificarEdad){
            mensaje = Constantes.MENSAJE_CUMPLE_EDAD;
        }else{
            mensaje = Constantes.MENSAJE_NO_CUMPLE_EDAD;
        }

        postulanteEntity.setEstadoPostulacion(Constantes.ESTADO_EVALUACION_INGRESADO);
        postulanteEntity.setEstado(Constantes.ESTADO_ACTIVO);

        ofertaRepository.save(ofertaDb);
        postulanteEntity = postulanteRepository.save(postulanteEntity);
        registrarHistorial(postulanteEntity, edad, mensaje);

        emailService.enviarEmailPostulante(postulanteDto.getEmail(), mensaje, asunto, postulanteDto.getPrimerNombre());
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

    @Override
    public Page<PostulanteDto> listarPostulantes(Integer numPagina, Integer tamPagina) {
        Pageable pageable = PageRequest.of(numPagina, tamPagina);
        List<PostulanteEntity> lista = postulanteRepository.findByEstado(Constantes.ESTADO_ACTIVO, pageable);
        List<PostulanteDto> listaPostulante = lista.stream().map(PostulanteMapper.INSTANCE::toPostulante).collect(Collectors.toList());
        if(!listaPostulante.isEmpty()){
            return new PageImpl<>(listaPostulante, pageable, lista.size());
        }

        return null;
    }

    private void registrarHistorial(PostulanteEntity postulanteEntity, Integer edad, String mensaje){
        HistorialEntity historialEntity = new HistorialEntity();
        historialEntity.setIdPostulante(postulanteEntity.getId());
        historialEntity.setPrimerNombre(postulanteEntity.getPrimerNombre());
        historialEntity.setSegundoNombre(postulanteEntity.getSegundoNombre());
        historialEntity.setApellidoPaterno(postulanteEntity.getApellidoPaterno());
        historialEntity.setApellidoMaterno(postulanteEntity.getApellidoMaterno());
        historialEntity.setFechaCambioEstado(ConvertirFechas.getInstance().obtenerFecha(new java.util.Date()));
        historialEntity.setEstadoPostulacion(postulanteEntity.getEstadoPostulacion());
        historialEntity.setCantidadPostulaciones(1);
        historialEntity.setMensajeEnviado(mensaje);
        historialEntity.setEdadPostulante(edad);
        historialRepository.save(historialEntity);
    }

}
