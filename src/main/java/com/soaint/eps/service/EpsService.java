package com.soaint.eps.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.eps.dto.EpsModificarRequest;
import com.soaint.eps.dto.EpsRegistrarRequest;
import com.soaint.eps.entities.Eps;
import com.soaint.eps.entities.Medico;
import com.soaint.eps.repository.EpsRepository;
import com.soaint.eps.util.MedicoEstado;

@Service
public class EpsService {
	
	@Autowired
	private EpsRepository epsRepository;
	
	private final Integer REFERENCIA_TIPO_EPS_CONSULTA = 2;
	
	
	public Eps registrarEps( EpsRegistrarRequest payload ) {
		
		Eps eps = new Eps();
		
		eps.setNombre(payload.getNombre());
		eps.setDireccion(payload.getDireccion());
		eps.setNit(payload.getNit());
		eps.setTelefono(payload.getTelefono());
		eps.setTipoEps(payload.getTipoEps());
		
		List<Medico> medicos = payload.getMedicos().stream().map( medico -> Medico.builder().
				nombre(medico.getNombre()).numeroCedula(medico.getNumeroCedula()).eps(eps).
				celular(medico.getCelular()).estado(MedicoEstado.ACTIVO.getEstado()).build() ).collect(Collectors.toList());
		eps.setMedicos(medicos);
		
		epsRepository.save(eps);
		return eps;
		
	}
	
	public List<Eps> consultarEps(){
		
		List<Eps> epsList = epsRepository.findAll();
		
		return epsList;
		
	}
	
	public Eps consultarEpsPorId( Long id) {
		
		Eps eps = new Eps();
		
		if(epsRepository.existsById(id)){
			eps = epsRepository.findById(id).get();
		}
		
		return eps;
		
	}
	
	public List<Eps> consultarEpsPorTipo(){
		
		List<Eps> epsList = epsRepository.findByTipoEpsGreaterThan(REFERENCIA_TIPO_EPS_CONSULTA);
		
		return epsList;
		
	}
	
	public Boolean modificarEps(Long id,  EpsModificarRequest payload ) {
		
		Boolean bandera = false;
		
		if(epsRepository.existsById(id)) {
			
			Eps eps = epsRepository.findById(id).get();
			
			eps.setNombre(payload.getNombre());
			eps.setNit(payload.getNit());
			eps.setTipoEps(payload.getTipoEps());
			eps.setDireccion(payload.getDireccion());
			eps.setTelefono(payload.getTelefono());
			epsRepository.save(eps);
			bandera = true;
			
		}
		return bandera;	
	}
	
	public Boolean eliminarEps( Long id ) {
		
		Boolean bandera = false;
		
		if(epsRepository.existsById(id)) {
			
			Eps eps = epsRepository.findById(id).get();
			epsRepository.delete(eps);
			bandera = true;
			
		}
		return bandera;
	}
	
	

}
