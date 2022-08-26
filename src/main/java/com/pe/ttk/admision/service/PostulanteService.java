package com.pe.ttk.admision.service;

import com.pe.ttk.admision.dto.PostulanteDto;
import com.pe.ttk.admision.dto.entity.admision.Postulante;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface PostulanteService {



    public List<Postulante> list();
    public List<PostulanteDto> findByQueryString(String estadoPostulacion,String distrito, String provincia, String departamento,String profesion,String responsableAsignado,String procedencia,
                                                        String apellidoPaterno);

    public void  savePostulante(PostulanteDto postulanteDto, MultipartFile cv, MultipartFile dniF, MultipartFile dniP, MultipartFile foto);
    public void  UpdatePostulante(Postulante postulante,PostulanteDto postulanteDto,MultipartFile dnifrontal,MultipartFile dniposterior,MultipartFile foto);

    public void delete(int id);
    public Optional<Postulante> getOne(int id);
}
