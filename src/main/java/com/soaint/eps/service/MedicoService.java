package com.soaint.eps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.eps.dto.MedicoModificarRequest;
import com.soaint.eps.entities.Eps;
import com.soaint.eps.entities.Medico;
import com.soaint.eps.repository.MedicoRepository;
import com.soaint.eps.util.MedicoEstado;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private EpsService epsService;
	
	
	
	public List<Medico> consultarMedicos(){
		
		List<Medico> medicos = medicoRepository.findAll();
		
		return medicos;
		
	}
	
	public Medico consultarMedicoPorId( Long id) {
		
		Medico medico = new Medico();
		
		if(medicoRepository.existsById(id)){
			medico = medicoRepository.findById(id).get();
		}
		
		return medico;
		
	}
	
	public Boolean modificarMedico(Long id,  MedicoModificarRequest payload ) {
		
		Boolean bandera = false;
		
		if(medicoRepository.existsById(id)) {
			
			Medico medico = medicoRepository.findById(id).get();
			
			medico.setNombre(payload.getNombre());
			medico.setCelular(payload.getCelular());
			medico.setNumeroCedula(payload.getNumeroCedula());
			medicoRepository.save(medico);
			bandera = true;
			
		}
		return bandera;	
	}
	
	public Boolean eliminarMedico(Long id) {
		
		Boolean bandera = false;
		
		if(medicoRepository.existsById(id)) {
			
			Medico medico = medicoRepository.findById(id).get();
			medico.setEstado(MedicoEstado.INACTIVO.getEstado());
			medicoRepository.save(medico);
			bandera = true;
			
		}
		return bandera;	
	}
	
	public List<Medico> consultarMedicosActivos(){
		
		List<Medico> medicos = medicoRepository.findByEstado( MedicoEstado.ACTIVO.getEstado() );
		
		return medicos;
		
	}
	
	public List<Medico> consultarMedicosPorEps( Long idEps ){
		
		List<Medico> medicos = new ArrayList<>();
		
		Eps eps = epsService.consultarEpsPorId(idEps);
		
		if(eps.getId()!=null) {
			
			medicos = medicoRepository.findByEps(eps);
			
		}
				
		return medicos;
		
	}

}
