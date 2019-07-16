package com.isima.session.isima.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.entities.Matiere;

public interface MatiereService {
	public void addMatiere(Matiere matiere);
	public void supprimer(Long matiereId);
	public Matiere findOne(Long matiereId);
	public List<Matiere> listMatiereAll();
	public Page<Matiere> listMatiere(int page, int size);
	public Page<Object> listMatiereRecherche(String nomMatiere, int page, int size);

	
}
