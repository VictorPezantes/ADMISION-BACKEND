package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.master.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long> {

    List<EstadoCivil> findByEstado(Integer estado);

}
