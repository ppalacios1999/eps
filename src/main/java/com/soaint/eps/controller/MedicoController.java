package com.soaint.eps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.eps.dto.MedicoModificarRequest;
import com.soaint.eps.dto.MedicoResponse;
import com.soaint.eps.entities.Medico;
import com.soaint.eps.service.MedicoService;
import com.soaint.eps.util.MedicoToConverter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@ApiModel( description = "Es el controlador del medico" )
public class MedicoController {
	
	@ApiModelProperty( notes = "Es un servicio para el medico", 
			example = "Registrar medico")
	@Autowired
	private MedicoService medicoService;
	
	@ApiModelProperty( notes = "Es un conversor para la clase Medico")
	@Autowired
	private MedicoToConverter medicoToConverter;
	
	@ApiOperation( value = "Consultar medicos", notes = "Consulta los medicos registrados ")
	@GetMapping( value = "medico" )
	public ResponseEntity<List<MedicoResponse>> consultarMedicos(){
		
		List<Medico> medicos = medicoService.consultarMedicos();
		
		if(medicos.isEmpty()) {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medicos), HttpStatus.NOT_FOUND );
		}else {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medicos), HttpStatus.OK );
		}
		
	}
	
	@ApiOperation( value = "Consultar medico por id", notes = "Consulta un medico por su id ")
	@GetMapping( value = "medico/{id}" )
	public ResponseEntity<MedicoResponse> consultarMedicoPorId( @PathVariable Long id ){
		
		Medico medico = medicoService.consultarMedicoPorId(id);
		
		if(medico.getId() == null) {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medico), HttpStatus.NOT_FOUND );
		}else {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medico), HttpStatus.OK );
		}
	}
	
	@ApiOperation( value = "Consultar medicos activos", notes = "Consulta los medicos registrados "
			+ "cuyo estado sea activo ")
	@GetMapping( value = "medicosActivos" )
	public ResponseEntity<List<MedicoResponse>> consultarMedicosActivos(){
		
		List<Medico> medicos = medicoService.consultarMedicosActivos();
		
		if(medicos.isEmpty()) {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medicos), HttpStatus.NOT_FOUND );
		}else {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medicos), HttpStatus.OK );
		}
		
	}
	
	@ApiOperation( value = "Consultar medico por eps", notes = "Consulta los medicos asociados a una eps ")
	@GetMapping( value = "medicosEps/{idEps}" )
	public ResponseEntity<List<MedicoResponse>> consultarMedicosPorEps( @PathVariable Long idEps ){
		
		List<Medico> medicos = medicoService.consultarMedicosPorEps(idEps);
		
		if(medicos.isEmpty()) {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medicos), HttpStatus.NOT_FOUND );
		}else {
			return new ResponseEntity<>( medicoToConverter.convertirEntidad(medicos), HttpStatus.OK );
		}
		
	}
	
	@ApiOperation( value = "Modificar medico", notes = "Modifica un medico registrado ")
	@PutMapping( value = "medico/{id}" )
	public ResponseEntity<Boolean> modificarMedico( @PathVariable Long id, 
			@RequestBody MedicoModificarRequest payload ){
		
		if(medicoService.modificarMedico(id, payload)) {
			return new ResponseEntity<>( true, HttpStatus.OK );
		}else {
			return new ResponseEntity<>( false, HttpStatus.NOT_FOUND );
		}
		
	}
	
	@ApiOperation( value = "Eliminar medico", notes = "Elimina un medico registrado ")
	@DeleteMapping( value = "medico/{id}" )
	public ResponseEntity<Boolean> eliminarMedico( @PathVariable Long id){
		if(medicoService.eliminarMedico(id)) {
			return new ResponseEntity<>( true, HttpStatus.OK );
		}else {
			return new ResponseEntity<>( false, HttpStatus.NOT_FOUND );
		}
	}

}
