package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.master.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    List<Departamento> findByEstado(Integer estado);

}
