package com.pe.ttk.admision.util;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class GuardarArchivos {

    private static final Logger logger = LoggerFactory.getLogger(GuardarArchivos.class);
    private final Path rootFolder = Paths.get("archivos/Postulante");
    private final String directorio = new File("archivos/Postulante").getAbsolutePath();
    public void init(Path path) {
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void guardarArchivo(MultipartFile curriculum, MultipartFile dniFrontal, MultipartFile dniPosterior, MultipartFile foto,
                               String nombreCurriculum, String nombreDniFrontal, String nombreDniPosterior, String nombreFoto){

        try {
            File file = new File(directorio);
            if(!file.isDirectory()){
                init(this.rootFolder);
            }
            Files.copy(curriculum.getInputStream(), this.rootFolder.resolve(nombreCurriculum), StandardCopyOption.REPLACE_EXISTING);

            Files.copy(dniFrontal.getInputStream(), this.rootFolder.resolve(nombreDniFrontal), StandardCopyOption.REPLACE_EXISTING);

            Files.copy(dniPosterior.getInputStream(), this.rootFolder.resolve(nombreDniPosterior), StandardCopyOption.REPLACE_EXISTING);

            Files.copy(foto.getInputStream(), this.rootFolder.resolve(nombreFoto), StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            logger.error(e.getMessage());
        }

    }

    public void guardarArchivo(MultipartFile archivo, String nombreArchivo, String folder){

        String carpeta = new File(folder).getAbsolutePath();
        try {
            Path path = Paths.get(folder);
            File file = new File(carpeta);
            if(!file.isDirectory()){
                init(path);
            }
            Files.copy(archivo.getInputStream(), path.resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);

        }catch(IOException e){
            logger.error(e.getMessage());
        }

    }

    public void guardarArchivo(MultipartFile archivo, String nombreArchivo){

        try {
            File file = new File(directorio);
            if(!file.isDirectory()){
                init(this.rootFolder);
            }
            Files.copy(archivo.getInputStream(), this.rootFolder.resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);

        }catch(IOException e){
            logger.error(e.getMessage());
        }

    }

    public void actualizarArchivo( MultipartFile dnifrontal, MultipartFile dniposterior, MultipartFile foto){

        try {

            Files.copy(dnifrontal.getInputStream(), this.rootFolder.resolve(dnifrontal.getOriginalFilename()));
            Files.copy(dniposterior.getInputStream(), this.rootFolder.resolve(dniposterior.getOriginalFilename()));
            Files.copy(foto.getInputStream(), this.rootFolder.resolve(foto.getOriginalFilename()));
        }catch(IOException e){
            logger.error(e.getMessage());
        }

    }
}
