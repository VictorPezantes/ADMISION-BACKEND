package com.pe.ttk.admision.repository;


import com.pe.ttk.admision.dto.entity.master.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {


}
