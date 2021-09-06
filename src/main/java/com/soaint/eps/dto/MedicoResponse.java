package com.soaint.eps.dto;

import lombok.Data;

@Data
public class MedicoResponse {
	
	private Long id;
	private String nombre;
	private Long numeroCedula;
	private Long celular;
	private String estado;

}
