package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfertaRepository extends PagingAndSortingRepository<OfertaEntity, Long>, OfertaRepositoryPersonalizado {


    @Query(" select o from OfertaEntity o"
            + " where o.titulo like %:titulo% ")
    List<OfertaEntity> findByTitulo(@Param("titulo") String titulo);

    List<OfertaEntity> findByEstadoOferta(Estado estado);

    List<OfertaEntity> findByCreadorOferta(Encargado encargado);
    List<OfertaEntity> findByfechaPublicacion(Date fechaPublicacion);

    Optional<OfertaEntity> findByIdAndEstado(Long id, Integer estado);


}
