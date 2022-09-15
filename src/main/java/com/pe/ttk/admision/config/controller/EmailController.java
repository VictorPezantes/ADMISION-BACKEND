package com.pe.ttk.admision.config.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.pe.ttk.admision.dto.Mensaje;
import com.pe.ttk.admision.security.dto.ChangePasswordDto;
import com.pe.ttk.admision.security.dto.EmailValuesDto;
import com.pe.ttk.admision.security.entity.Usuario;
import com.pe.ttk.admision.security.service.UsuarioService;
import com.pe.ttk.admision.security.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/email-service")
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

	@PostMapping("/enviar-cambio-password")
	public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDto dto) {

		Optional<Usuario> usuarioOpt = usuarioService.getByNombreUsuarioOrEmail(dto.getMailTo());
		if (usuarioOpt.isEmpty())
			return ResponseEntity.badRequest().body(new Mensaje("No existe ningún usuario con esas credenciales"));

		Usuario usuario = usuarioOpt.get();

		dto.setMailFrom(mailFrom);
		dto.setMailTo(usuario.getEmail());
		dto.setSubject("Cambio de Contraseña");
		dto.setUserName(usuario.getNombre());
		UUID uuid = UUID.randomUUID();
		String tokenPassword = uuid.toString();
		dto.setToken(tokenPassword);
		usuario.setTokenPassword(tokenPassword);
		usuarioService.save(usuario);
		emailServiceImpl.sendMailTemplate(dto);
		return ResponseEntity.ok(new Mensaje("correo para recuperar contraseña enviado correctamente"));
	}

	@PostMapping("/cambiar-password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto dto, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return ResponseEntity.badRequest().body(new Mensaje("Campos mal ingresados"));

		if (!dto.getPassword().equals(dto.getConfirmPassword()))
			return ResponseEntity.badRequest().body(new Mensaje("Las Contraseñas no coinciden"));

		Optional<Usuario> usuarioOpt = usuarioService.getByTokenPassword(dto.getTokenPassword());
		if (usuarioOpt.isEmpty())
			return ResponseEntity.badRequest().body(new Mensaje("No existe usuario para recuperar contraseña"));

		Usuario usuario = usuarioOpt.get();

		String newPassword = passwordEncoder.encode(dto.getPassword());
		usuario.setPassword(newPassword);
		usuario.setTokenPassword(null);
		usuarioService.save(usuario);

		return ResponseEntity.ok(new Mensaje("Contraseña actualizada correctamente"));

	}

}
