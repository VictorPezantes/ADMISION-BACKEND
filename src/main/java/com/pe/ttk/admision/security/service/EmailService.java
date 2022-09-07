package com.pe.ttk.admision.security.service;

public interface EmailService {

    void enviarEmailPostulante(String toEmail, String mensaje, String asunto, String nombre);

}
