package com.soaint.eps.dto;


import lombok.Data;

@Data
public class MedicoRegistrarRequest {
	
	private String nombre;
	private Long numeroCedula;
	private Long celular;

}
