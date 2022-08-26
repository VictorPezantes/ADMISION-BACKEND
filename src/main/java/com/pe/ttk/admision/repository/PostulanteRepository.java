package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.admision.Postulante;
import com.pe.ttk.admision.dto.entity.admision.PostulanteMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulanteRepository extends JpaRepository<Postulante,Integer> {


    List<Postulante> findAll();
    List<PostulanteMapping> findByQueryString(@Param("dni") String dni,@Param("distrito") String distrito,
                                              @Param("provincia") String provincia, @Param("departamento") String departamento);

}
