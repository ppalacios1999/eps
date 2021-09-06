package com.soaint.eps.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "tbl_medico" )
public class Medico {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	@Column( name = "nombre", length = 50 )
	private String nombre;
	@Column( name = "numero_cedula", length = 10 )
	private Long numeroCedula;
	@Column( name = "celular", length = 10 )
	private Long celular;
	@Column( name = "estado", length = 50 )
	private String estado;
	
	@ManyToOne( cascade = CascadeType.ALL )
	private Eps eps;
	

}
