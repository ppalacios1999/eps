package com.soaint.eps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.eps.dto.EpsModificarRequest;
import com.soaint.eps.dto.EpsRegistrarRequest;
import com.soaint.eps.dto.EpsResponse;
import com.soaint.eps.entities.Eps;
import com.soaint.eps.service.EpsService;
import com.soaint.eps.util.EpsToConverter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@ApiModel( description = "Es el controlador de la eps" )
public class EpsController {
	
	@ApiModelProperty( notes = "Es un servicio para la eps", 
			example = "Registrar eps")
	@Autowired
	private EpsService epsService;
	
	@ApiModelProperty( notes = "Es un conversor para la clase Eps")
	@Autowired
	private EpsToConverter epsToConverter;
	
	@ApiOperation( value = "Registrar eps", notes = "Registra una eps ")
	@PostMapping( value = "eps" )
	public ResponseEntity<EpsResponse> registrarEps( @RequestBody EpsRegistrarRequest payload ){
		
		Eps eps = epsService.registrarEps(payload);
		
		return new ResponseEntity<>( epsToConverter.convertirEntidad(eps), HttpStatus.CREATED );
		
	}
	
	@ApiOperation( value = "Consultar eps", notes = "Consulta las eps registradas")
	@GetMapping( value = "eps" )
	public ResponseEntity<List<EpsResponse>> consultarEps(){
		
		List<Eps> epsList = epsService.consultarEps();
		
		if(epsList.isEmpty()) {
			return new ResponseEntity<>( epsToConverter.convertirEntidad(epsList), HttpStatus.NOT_FOUND );
		}else {
			return new ResponseEntity<>( epsToConverter.convertirEntidad(epsList), HttpStatus.OK );
		}
		
	}
	
	@ApiOperation( value = "Consultar eps por id", notes = "Consulta una eps registrada por id")
	@GetMapping( value = "eps/{id}" )
	public ResponseEntity<EpsResponse> consultarEpsPorId( @PathVariable Long id ){
		
		Eps eps = epsService.consultarEpsPorId(id);
		
		if(eps.getId() == null) {
			return new ResponseEntity<>( epsToConverter.convertirEntidad(eps), HttpStatus.NOT_FOUND );
		}else {
			return new ResponseEntity<>( epsToConverter.convertirEntidad(eps), HttpStatus.OK );
		}
		
		
	}
	
	@ApiOperation( value = "Consultar eps por tipo", notes = "Consulta las eps registradas cuyo tipo"
			+ " es mayor que 2")
	@GetMapping( value = "epsPorTipo" )
	public ResponseEntity<List<EpsResponse>> consultarEpsPorTipo(){
		
		List<Eps> epsList = epsService.consultarEpsPorTipo();
		
		if(epsList.isEmpty()) {
			return new ResponseEntity<>( epsToConverter.convertirEntidad(epsList), HttpStatus.NOT_FOUND );
		}else {
			return new ResponseEntity<>( epsToConverter.convertirEntidad(epsList), HttpStatus.OK );
		}
		
		
	}
	
	@ApiOperation( value = "Modificar eps", notes = "Modifica una eps registrada")
	@PutMapping( value = "eps/{id}" )
	public ResponseEntity<Boolean> modificarEps( @PathVariable Long id, 
			@RequestBody EpsModificarRequest payload ){
		
		if(epsService.modificarEps(id, payload)) {
			return new ResponseEntity<>( true, HttpStatus.OK );
		}else {
			return new ResponseEntity<>( false, HttpStatus.NOT_FOUND );
		}
		
	}
	
	@ApiOperation( value = "Eliminar eps", notes = "Elimina una eps registrada")
	@DeleteMapping( value = "eps/{id}" )
	public ResponseEntity<Boolean> eliminarEps( @PathVariable Long id){
		if(epsService.eliminarEps(id)) {
			return new ResponseEntity<>( true, HttpStatus.OK );
		}else {
			return new ResponseEntity<>( false, HttpStatus.NOT_FOUND );
		}
	}
	
	

}
