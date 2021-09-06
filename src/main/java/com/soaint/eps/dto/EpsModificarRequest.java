package com.soaint.eps.dto;

import lombok.Data;

@Data
public class EpsModificarRequest {
	
	private String nombre;
	private Long nit;
	private Integer tipoEps;
	private String direccion;
	private Long telefono;

}
