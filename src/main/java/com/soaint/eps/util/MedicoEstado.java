package com.soaint.eps.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedicoEstado {
	
	ACTIVO("activo"),
	INACTIVO("inactivo");
	
	private String estado;
}
