package com.pe.ttk.admision.service.impl;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.OfertaDto;
import com.pe.ttk.admision.dto.entity.master.Cargo;
import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import com.pe.ttk.admision.repository.*;
import com.pe.ttk.admision.security.entity.UsuarioPrincipal;
import com.pe.ttk.admision.security.repository.UsuarioRepository;
import com.pe.ttk.admision.service.OfertaService;
import com.pe.ttk.admision.util.Constantes;
import com.pe.ttk.admision.util.ConvertirFechas;
import com.pe.ttk.admision.util.mapper.OfertaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {


    @Autowired
    OfertaRepository ofertaRepository;

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EncargadoRepository encargadoRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    OfertaMapper ofertaMapper;

    ConvertirFechas convertirFechas = new ConvertirFechas();

    public Page<OfertaDto> listarOfertas(Integer numPagina, Integer tamPagina, String titulo, List estado,
                                         String fechaPublicacion, List creador) {

        Pageable pageable = PageRequest.of(numPagina, tamPagina, Sort.by("fechaPublicacion").descending());
        List<OfertaEntity> listaOfertas = ofertaRepository.listarOfertas(pageable, titulo, estado, fechaPublicacion, creador, Constantes.ESTADO_ACTIVO);
        List<OfertaDto> ofertas =listaOfertas.stream().map(ofertaMapper::toOferta)
                .collect(Collectors.toList());
        if(!listaOfertas.isEmpty()){
            return new PageImpl<>(ofertas, pageable, listaOfertas.size());
        }

        return null;
    }

    @Override
    public Page<OfertaDto> listarOfertasLanding(Integer numPagina, Integer tamPagina) {
        Pageable pageable = PageRequest.of(numPagina, tamPagina, Sort.by("fechaPublicacion").descending());
        List<OfertaEntity> listaOfertas = ofertaRepository.listarOfertasLanding(pageable, Constantes.ESTADO_ACTIVADA, Constantes.ESTADO_ACTIVO);
        List<OfertaDto> ofertas =listaOfertas.stream().map(ofertaMapper::toOfertaLanding)
                .collect(Collectors.toList());
        if(!listaOfertas.isEmpty()){
            return new PageImpl<>(ofertas, pageable, listaOfertas.size());
        }

        return null;
    }


    public Mensaje registrarOferta(OfertaDto ofertaDto, Authentication auth) {

        UsuarioPrincipal usuario = (UsuarioPrincipal) auth.getPrincipal();

        //String username = auth;
        //Optional<Usuario> usuarioDb = usuarioRepository.findByNombreUsuarioOrEmail(usuario.getNombreUsuario(), usuario.getEmail());
        String emailEncargado = usuario.getEmail();

        Optional<Encargado> encargadoOp = encargadoRepository.findByEmail(emailEncargado);
        if(encargadoOp==null){
            return new Mensaje(("No existe el encargado"));
        }
        Encargado encargadoDb = encargadoOp.get();

        Estado estado = new Estado();
        estado.setId(Constantes.ESTADO_CREADA);

        OfertaEntity ofertaEntity = new OfertaEntity();

        ofertaEntity.setEstadoOferta(estado);
        ofertaEntity.setCantidadPostulantes(Constantes.CERO);
        ofertaEntity.setCargoOferta(ofertaDto.getCargoOferta());
        ofertaEntity.setCreadorOferta(encargadoDb);
        ofertaEntity.setFechaPublicacion(null);
        ofertaEntity.setFechaCreacion(convertirFechas.stringToDateSql());
        ofertaEntity.setDescripcion(ofertaDto.getDescripcion());
        ofertaEntity.setRequisito(ofertaDto.getRequisito());
        ofertaEntity.setTitulo(ofertaDto.getTitulo());
        ofertaEntity.setFechaActualizacion(null);
        ofertaEntity.setFechaDesactivado(null);
        ofertaEntity.setEstado(Constantes.ESTADO_ACTIVO);
        ofertaRepository.save(ofertaEntity);

        return new Mensaje(("Oferta creada correctamente"));
    }

    public Mensaje actualizarOferta(OfertaDto ofertaDto) {

        Optional<OfertaEntity> ofertaOp = ofertaRepository.findByIdAndEstado(ofertaDto.getId(), Constantes.ESTADO_ACTIVO);
        if(ofertaOp.isPresent()){
            Long idCargo = ofertaDto.getCargoOferta().getId();
            Optional<Cargo> cargoOp = cargoRepository.findByIdAndEstado(idCargo, Constantes.ESTADO_ACTIVO);
            if(cargoOp==null){
                return new Mensaje("El cargo no existe");
            }

            OfertaEntity ofertaDb = ofertaOp.get();
            Cargo cargoDb = cargoOp.get();

            ofertaDb.setTitulo(ofertaDto.getTitulo());
            ofertaDb.setDescripcion(ofertaDto.getDescripcion());
            ofertaDb.setRequisito(ofertaDto.getRequisito());
            ofertaDb.setCargoOferta(cargoDb);
            ofertaDb.setFechaActualizacion(convertirFechas.stringToDateSql());
            ofertaRepository.save(ofertaDb);

            return new Mensaje("Oferta actualizada correctamente");
        }

        return new Mensaje("La oferta no existe");

    }


    @Override
    public Mensaje actualizarEstadoOferta(OfertaDto ofertaDto) {

        Optional<OfertaEntity> ofertaOp = ofertaRepository.findByIdAndEstado(ofertaDto.getId(), Constantes.ESTADO_ACTIVO);
        if(ofertaOp.isPresent()){
            OfertaEntity ofertaDb = ofertaOp.get();
            Long idEstado = ofertaDto.getEstadoOferta().getId();
            Optional<Estado> estadoOp = estadoRepository.findById(idEstado);
            if(estadoOp==null){
                return new Mensaje("No existe el estado");
            }
            Estado estadoDb = estadoOp.get();
            if(Objects.equals(estadoDb.getId(), Constantes.ESTADO_ACTIVADA)){
                ofertaDb.setFechaPublicacion(convertirFechas.stringToDateSql());
                ofertaDb.setEstadoOferta(estadoDb);
                ofertaRepository.save(ofertaDb);
            }
            if(Objects.equals(estadoDb.getId(), Constantes.ESTADO_DESACTIVADA)){
                ofertaDb.setFechaDesactivado(convertirFechas.stringToDateSql());
                ofertaDb.setEstadoOferta(estadoDb);
                ofertaRepository.save(ofertaDb);
            }
            return  new Mensaje("Estado de la oferta actualizado correctamente");
        }

        return new Mensaje("No existe la oferta");
    }

    public void delete(Long id) {
        ofertaRepository.deleteById(id);
    }

    public Optional<OfertaEntity> getOne(Long id) {
        return ofertaRepository.findById(id);
    }

    @Override
    public List<OfertaEntity> findOfertaByQueryString(String titulo) {

        return ofertaRepository.findByTitulo(titulo);
    }

    public List<OfertaEntity> findByCreadorOferta(Encargado creador) {

        return ofertaRepository.findByCreadorOferta(creador);
    }

    public List<OfertaEntity> findByEstadoOferta(Estado estado) {

        return ofertaRepository.findByEstadoOferta(estado);


    }

    public List<OfertaEntity> findByfechaPublicacion(Date fechaPublicacion) {

        return ofertaRepository.findByfechaPublicacion(fechaPublicacion);


    }

    @Override
    public OfertaDto obtenerOferta(Long id) {
        Optional<OfertaEntity> ofertaOp = ofertaRepository.findByIdAndEstado(id, Constantes.ESTADO_ACTIVO);
        if(ofertaOp.isPresent()){
            OfertaEntity ofertaDb = ofertaOp.get();
            return ofertaMapper.toOferta(ofertaDb);
        }
        return null;
    }

    @Override
    public Mensaje eliminarOferta(OfertaDto ofertaDto) {
        Optional<OfertaEntity> ofertaOp = ofertaRepository.findByIdAndEstado(ofertaDto.getId(), Constantes.ESTADO_ACTIVO);
        if(ofertaOp.isPresent()){
            OfertaEntity ofertaDb = ofertaOp.get();
            ofertaDb.setEstado(Constantes.ESTADO_INACTIVO);
            ofertaRepository.save(ofertaDb);

            return  new Mensaje("Oferta eliminada correctamente");
        }

        return new Mensaje("No existe la oferta");
    }
}