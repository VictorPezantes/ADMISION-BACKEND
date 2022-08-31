package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.master.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    List<Provincia> findByIdDepartamentoAndEstado(Long idDepartamento, Integer estado);

}
