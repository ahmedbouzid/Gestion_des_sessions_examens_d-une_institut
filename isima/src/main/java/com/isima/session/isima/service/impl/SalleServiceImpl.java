package com.isima.session.isima.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.isima.session.isima.entities.Salle;
import com.isima.session.isima.repository.SalleRepository;
import com.isima.session.isima.service.SalleService;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SalleServiceImpl implements SalleService {
	
	private final SalleRepository salleRepository;
	

	public SalleServiceImpl(SalleRepository salleRepository) {
		this.salleRepository = salleRepository;
	}

	@Override
	public void addSalle(Salle salle) {
		this.salleRepository.save(salle);
	}
	@SuppressWarnings("deprecation")
	@Override
	public Page<Salle> listSalle(int page, int size) {
		return salleRepository.listSalle(new PageRequest(page,size));
	}

	@Override
	public void delete(Long id) {
		this.salleRepository.deleteById(id);
	}
	@Override
	public List<Salle> listSalleAllWithoutDTO() {
		return this.salleRepository.findAll();
	}

	@Override
	public List<Salle> getAllSalleNotReserved(String horaireRes) {
		return this.salleRepository.findAllNotReserverd(horaireRes);
	}

}
