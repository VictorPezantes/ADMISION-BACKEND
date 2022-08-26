package com.pe.ttk.admision.security.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.pe.ttk.admision.security.enums.RolNombre;
import lombok.Data;

@Entity
@Data
@Table(name = "rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;

	public Rol() {
		super();
	}

	public Rol(@NotNull RolNombre rolNombre) {
		super();
		this.rolNombre = rolNombre;
	}

}
