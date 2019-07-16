package com.isima.session.isima.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isima.session.isima.entities.FicheDeSuivie;
import com.isima.session.isima.repository.FichSuivieRepository;
import com.isima.session.isima.service.FichSuivieService;

@Service
public class FichSuivieServiceImpl implements FichSuivieService {
	
	private final FichSuivieRepository fichSuivieRepository;
	
	

	public FichSuivieServiceImpl(FichSuivieRepository fichSuivieRepository) {
		super();
		this.fichSuivieRepository = fichSuivieRepository;
	}



	@Override
	public List<FicheDeSuivie> getAllFichSuivie() {
		return this.fichSuivieRepository.findAll();
	}



	@Override
	public FicheDeSuivie findById(Long id) {
		return this.fichSuivieRepository.findById(id).get();
	}

}
