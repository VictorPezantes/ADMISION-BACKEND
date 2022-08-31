package com.pe.ttk.admision.service;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.dto.PostulanteDto;
import com.pe.ttk.admision.dto.entity.admision.PostulanteEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface PostulanteService {



    List<PostulanteEntity> list();
    List<PostulanteDto> findByQueryString(String estadoPostulacion,String distrito, String provincia, String departamento,String profesion,String responsableAsignado,String procedencia,
                                                        String apellidoPaterno);

    Mensaje registrarPostulante(PostulanteDto postulanteDto, MultipartFile cv, MultipartFile dniF, MultipartFile dniP, MultipartFile foto);
    void  UpdatePostulante(PostulanteEntity postulanteEntity, PostulanteDto postulanteDto, MultipartFile dnifrontal, MultipartFile dniposterior, MultipartFile foto);

    void delete(int id);
    Optional<PostulanteEntity> getOne(int id);
}
