package com.pe.ttk.admision.repository;

import com.pe.ttk.admision.dto.entity.admision.PostulanteEntity;
import com.pe.ttk.admision.dto.entity.admision.PostulanteMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulanteRepository extends JpaRepository<PostulanteEntity,Integer> {


    List<PostulanteEntity> findAll();
    /*List<PostulanteMapping> findByQueryString(@Param("dni") String dni,@Param("distrito") String distrito,
                                              @Param("provincia") String provincia, @Param("departamento") String departamento);
*/
    List<PostulanteEntity> findByEstado(Integer estado, Pageable pageable);

    boolean existsByDniAndEstado(String dni, Integer estado);

}
