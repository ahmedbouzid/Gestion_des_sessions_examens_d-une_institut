package com.isima.session.isima.service;

import java.util.List;

import com.isima.session.isima.entities.FicheDeSuivie;

public interface FichSuivieService {

	public List<FicheDeSuivie> getAllFichSuivie();
	
	public FicheDeSuivie findById(Long id);
	}
