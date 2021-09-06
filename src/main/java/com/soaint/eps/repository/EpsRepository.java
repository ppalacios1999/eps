package com.soaint.eps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soaint.eps.entities.Eps;

public interface EpsRepository extends JpaRepository<Eps, Long>{
	
	public List<Eps> findByTipoEpsGreaterThan( Integer tipoEps );

}
