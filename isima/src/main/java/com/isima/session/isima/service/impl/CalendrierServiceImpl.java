package com.isima.session.isima.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isima.session.isima.entities.Calendrier;
import com.isima.session.isima.repository.CalendrierRepository;
import com.isima.session.isima.service.CalendrierService;

@Service
public class CalendrierServiceImpl implements CalendrierService {

	private final CalendrierRepository calendrierRepository;
	
	
	public CalendrierServiceImpl(CalendrierRepository calendrierRepository) {
		super();
		this.calendrierRepository = calendrierRepository;
	}


	@Override
	public Calendrier findByNom(String nom) {
		return this.calendrierRepository.findByNom(nom);
	}


	@Override
	public Calendrier findById(Long id) {
		return this.calendrierRepository.findById(id).get();
	}


	@Override
	public List<Calendrier> getAllCalendrier() {
		return this.calendrierRepository.findAll();
	}

}
