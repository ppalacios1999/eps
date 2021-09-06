package com.soaint.eps.dto;

import java.util.List;

import lombok.Data;

@Data
public class EpsRegistrarRequest {
	
	private String nombre;
	private Long nit;
	private Integer tipoEps;
	private String direccion;
	private Long telefono;
	private List<MedicoRegistrarRequest> medicos;

}
