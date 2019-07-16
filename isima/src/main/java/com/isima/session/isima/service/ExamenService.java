package com.isima.session.isima.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.isima.session.isima.entities.Examen;



public interface ExamenService {
	public void addExamen(Examen examen);
	
	public void delete (Long id) ;
	
	public Page<Examen> listExamen (int page , int size);
	
	public Examen findOne (Long id);
	
	public Boolean testIfSalleAffected(Long salleId);
	
	public Boolean testIfEnseignantAffected(Long enseignantId);
	
	public Boolean testIfMatiereAffected(Long matiereId);
	
	public Boolean testIfSalleUsedInHoraire(String horaire, Long salleId);
	
	public List<Examen> findAllByDateAndHoraire(LocalDate date, String horaire);

}
