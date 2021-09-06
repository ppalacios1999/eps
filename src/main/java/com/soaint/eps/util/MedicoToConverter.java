package com.soaint.eps.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soaint.eps.dto.MedicoResponse;
import com.soaint.eps.entities.Medico;

@Component
public class MedicoToConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public MedicoResponse convertirEntidad( Medico medico) {
		
		return modelMapper.map(medico, MedicoResponse.class);
		
	}
	
	public List<MedicoResponse> convertirEntidad( List<Medico> medicos) {
		
		return medicos.stream().map(medico -> convertirEntidad(medico)).collect(Collectors.toList());
		
	}

}
