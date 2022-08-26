package com.pe.ttk.admision.util;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GuardarArchivos {

    private final Path rootFolder = Paths.get("archivos/Postulante");

    public void guardarArchivo(MultipartFile curriculum, MultipartFile dnifrontal, MultipartFile dniposterior, MultipartFile foto){

        try {
            Files.copy(curriculum.getInputStream(), this.rootFolder.resolve(curriculum.getOriginalFilename()));
            Files.copy(dnifrontal.getInputStream(), this.rootFolder.resolve(dnifrontal.getOriginalFilename()));
            Files.copy(dniposterior.getInputStream(), this.rootFolder.resolve(dniposterior.getOriginalFilename()));
            Files.copy(foto.getInputStream(), this.rootFolder.resolve(foto.getOriginalFilename()));
        }catch(Exception e){

        }

    }

    public void actualizarArchivo( MultipartFile dnifrontal, MultipartFile dniposterior, MultipartFile foto){

        try {

            Files.copy(dnifrontal.getInputStream(), this.rootFolder.resolve(dnifrontal.getOriginalFilename()));
            Files.copy(dniposterior.getInputStream(), this.rootFolder.resolve(dniposterior.getOriginalFilename()));
            Files.copy(foto.getInputStream(), this.rootFolder.resolve(foto.getOriginalFilename()));
        }catch(Exception e){

        }

    }
}
