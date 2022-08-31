package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.master.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Long> {

    List<Distrito> findByIdProvinciaAndEstado(Long idProvincia, Integer estado);

}
