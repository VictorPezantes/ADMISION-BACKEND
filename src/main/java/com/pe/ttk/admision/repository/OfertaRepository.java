package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.master.Encargado;
import com.pe.ttk.admision.dto.entity.master.Estado;
import com.pe.ttk.admision.dto.entity.admision.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {


    @Query(" select o from Oferta o"
            + " where o.titulo like %?1% ")
    List<Oferta> findOfertaByQueryString(@Param("titulo") String titulo);



    List<Oferta> findByEstadoOferta(Estado estado);

    List<Oferta> findByCreadorOferta(Encargado encargado);
    List<Oferta> findByfechaPublicacion(Date fechaPublicacion);


}
