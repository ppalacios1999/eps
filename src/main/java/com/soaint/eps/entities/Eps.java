package com.soaint.eps.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "tbl_eps" )
public class Eps {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY  )
	private Long id;
	@Column( name = "nombre", length = 50 )
	private String nombre;
	@Column( name = "nit", length = 10 )
	private Long nit;
	@Column( name = "tipo_eps", length = 10 )
	private Integer tipoEps;
	@Column( name = "direccion", length = 50 )
	private String direccion;
	@Column( name = "telefono", length = 10 )
	private Long telefono;
	
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "eps" )
	private List<Medico> medicos;

}
