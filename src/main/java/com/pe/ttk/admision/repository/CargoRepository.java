package com.pe.ttk.admision.repository;


import com.pe.ttk.admision.dto.entity.master.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Optional<Cargo> findByIdAndEstado(Long id, Integer estado);

    List<Cargo> findByEstado(Integer estado);

}
