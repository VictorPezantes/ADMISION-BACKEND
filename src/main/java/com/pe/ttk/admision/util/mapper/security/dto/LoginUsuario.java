package com.pe.ttk.admision.util.mapper.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginUsuario {
	
	@NotBlank
	private String password;
	private String email;
	//@NotBlank
	private String nombreUsuario;


}
