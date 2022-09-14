package com.pe.ttk.admision.util.mapper.security.service;

public interface EmailService {

    void enviarEmailPostulante(String toEmail, String mensaje, String asunto, String nombre);

}
