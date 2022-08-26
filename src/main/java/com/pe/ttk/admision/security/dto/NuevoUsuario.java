package com.pe.ttk.admision.security.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class NuevoUsuario {
	
	@NotBlank
	private String nombre;
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String nombreUsuario;

	private String fotografia;

	private Set<String> roles = new HashSet<>();

}
