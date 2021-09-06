package com.soaint.eps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soaint.eps.entities.Eps;
import com.soaint.eps.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
	
	public List<Medico> findByEstado( String estado );
	
	public List<Medico> findByEps( Eps eps );

}
