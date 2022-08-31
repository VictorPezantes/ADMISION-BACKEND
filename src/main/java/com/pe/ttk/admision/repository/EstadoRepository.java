package com.pe.ttk.admision.repository;


import com.pe.ttk.admision.dto.entity.master.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findById(Long id);

}
