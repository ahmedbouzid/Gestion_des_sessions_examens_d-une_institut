package com.isima.session.isima.service;

import java.util.List;

import com.isima.session.isima.entities.Calendrier;

public interface CalendrierService {

	public Calendrier findByNom(String nom);
	
	public Calendrier findById(Long id);
	
	public List<Calendrier> getAllCalendrier();
}
