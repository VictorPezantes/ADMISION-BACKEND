package com.pe.ttk.admision.config.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.security.dto.ChangePasswordDto;
import com.pe.ttk.admision.security.dto.EmailValuesDto;
import com.pe.ttk.admision.security.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.ttk.admision.security.service.UsuarioService;
import com.pe.ttk.admision.security.service.impl.EmailServiceImpl;

@RestController
@RequestMapping("/email-password")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

	@Autowired
	EmailServiceImpl emailServiceImpl;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UsuarioService usuarioService;

	@Value("${spring.mail.username}")
	private String mailFrom;

	private static final String mailSubject = "Cambio de Contraseña";

	@PostMapping("/send/email")
	public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDto dto) {

		Optional<Usuario> usuarioOpt = usuarioService.getByNombreUsuarioOrEmail(dto.getMailTo());
		if (!usuarioOpt.isPresent())
			return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"),
					(HttpStatus.NOT_FOUND));

		Usuario usuario = usuarioOpt.get();

		dto.setMailFrom(mailFrom);
		dto.setMailTo(usuario.getEmail());
		dto.setSubject(mailSubject);
		dto.setUserName(usuario.getNombre());
		UUID uuid = UUID.randomUUID();
		String tokenPassword = uuid.toString();
		dto.setToken(tokenPassword);
		usuario.setTokenPassword(tokenPassword);
		usuarioService.save(usuario);
		emailServiceImpl.sendMailTemplate(dto);
		return new ResponseEntity(new Mensaje("correo enviado con éxito"), HttpStatus.OK);
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto dto, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal ingresados"), (HttpStatus.BAD_REQUEST));

		if (!dto.getPassword().equals(dto.getConfirmPassword()))
			return new ResponseEntity(new Mensaje("Las Contraseñas no coinciden"), (HttpStatus.BAD_REQUEST));

		Optional<Usuario> usuarioOpt = usuarioService.getByTokenPassword(dto.getTokenPassword());
		if (!usuarioOpt.isPresent())
			return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"),
					(HttpStatus.BAD_REQUEST));

		Usuario usuario = usuarioOpt.get();

		String newPassword = passwordEncoder.encode(dto.getPassword());
		usuario.setPassword(newPassword);
		usuario.setTokenPassword(null);
		usuarioService.save(usuario);

		return new ResponseEntity(new Mensaje("contraseña actualizada"), (HttpStatus.OK));

	}

}
