package com.pe.ttk.admision.security.dto;

import java.util.Collection;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class JwtDto {

	private String token;
	private String nombreUsuario;
	private String email;
	private Collection<? extends  GrantedAuthority> authorities;

	public JwtDto(String token) {

		this.token = token;

	}

	public JwtDto(String token, String nombreUsuario, String email, Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.authorities = authorities;
	}

	public JwtDto() {
	}

	/*public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}*/
}
