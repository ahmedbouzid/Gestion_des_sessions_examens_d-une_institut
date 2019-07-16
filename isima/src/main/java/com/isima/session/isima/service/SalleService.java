package com.isima.session.isima.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.isima.session.isima.entities.Salle;

public interface SalleService {
	public void addSalle( Salle salle);
	

	public Page<Salle> listSalle(int page , int size);
	
	// public List<SalleDTO> listSalleAll();
	
	public List<Salle> listSalleAllWithoutDTO();
	
	
	public void delete(Long id);
	
	public List<Salle> getAllSalleNotReserved(String horaireRes);
		


}
