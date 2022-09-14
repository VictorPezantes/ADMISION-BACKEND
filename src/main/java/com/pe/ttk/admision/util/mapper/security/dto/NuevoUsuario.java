package com.pe.ttk.admision.util.mapper.security.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class NuevoUsuario {
	
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellidos;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;

	private String nombreUsuario;

	private String fotografia;

	private Set<String> roles = new HashSet<>();

}
