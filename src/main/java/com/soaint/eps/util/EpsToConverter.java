package com.soaint.eps.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soaint.eps.dto.EpsResponse;
import com.soaint.eps.entities.Eps;

@Component
public class EpsToConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EpsResponse convertirEntidad( Eps eps) {
		
		return modelMapper.map(eps, EpsResponse.class);
		
	}
	
	public List<EpsResponse> convertirEntidad( List<Eps> epsList) {
		
		return epsList.stream().map(eps -> convertirEntidad(eps)).collect(Collectors.toList());
		
	}

}
