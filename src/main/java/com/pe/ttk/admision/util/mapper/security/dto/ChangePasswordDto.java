package com.pe.ttk.admision.util.mapper.security.dto;

import javax.validation.constraints.NotBlank;

public class ChangePasswordDto {
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	@NotBlank
	private String tokenPassword;
	public ChangePasswordDto(String password, String confirmPassword, String tokenPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.tokenPassword = tokenPassword;
	}
	public ChangePasswordDto() {
		super();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getTokenPassword() {
		return tokenPassword;
	}
	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}
	
	

}
