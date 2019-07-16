package com.isima.session.isima.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.isima.session.isima.entities.Enseignant;

public interface EnseignantService {

	public void addEnseignant(Enseignant enseigant);
	
	public Page<Enseignant> listEnseignant(int page, int size);
	
	public List<Enseignant> listEnseignantAll();
	
	public void delete(Long id);
	
	public Enseignant findOne (Long id);

	public Page<Object> listEnseignantRecherche(String nomEnseignant, int page, int size);
	
	
}
