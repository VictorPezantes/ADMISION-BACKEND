package com.pe.ttk.admision.repository;


import com.pe.ttk.admision.dto.entity.master.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {


}
