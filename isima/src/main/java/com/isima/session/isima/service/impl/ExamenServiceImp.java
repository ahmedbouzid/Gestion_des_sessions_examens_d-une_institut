package com.isima.session.isima.service.impl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.entities.Examen;
import com.isima.session.isima.entities.Salle;
import com.isima.session.isima.repository.ExamenRepository;
import com.isima.session.isima.repository.SalleRepository;
import com.isima.session.isima.service.ExamenService;

@Service
@Transactional
public class ExamenServiceImp  implements ExamenService{
	private final ExamenRepository examenRepository;
	private final SalleRepository salleRepository;

	public ExamenServiceImp(ExamenRepository examenRepository, SalleRepository salleRepository) {
		super();
		this.examenRepository = examenRepository;
		this.salleRepository = salleRepository;
	}

	@Override
	public void addExamen(Examen examen) {
		Set<Enseignant> listEnseignants = new HashSet();
		for (Enseignant enseignantElement: examen.getEnseignants()) {
			enseignantElement.setExamen(examen);
			listEnseignants.add(enseignantElement);
		}
		examen.setEnseignants(listEnseignants);
		examen.getSalle().setHoraireRes(examen.getHoraire());
		this.salleRepository.save(examen.getSalle());
		this.examenRepository.save(examen);
		
	}

	@Override
	public void delete(Long id) {
		this.examenRepository.deleteById(id);
	}

	@Override
	public Page<Examen> listExamen(int page, int size) {
		
		return examenRepository.listExamen(new PageRequest(page, size));
	}

	@Override
	public Examen findOne(Long id) {
		return examenRepository.getOne(id) ;
	}

	@Override
	public Boolean testIfSalleAffected(Long salleId) {
		Long verif = this.examenRepository.testIfSalleAffected(salleId);
		return verif > 0;
		
	}

	@Override
	public Boolean testIfEnseignantAffected(Long enseignantId) {
		Long verif = this.examenRepository.testIfEnseignantAffected(enseignantId);
		return verif > 0;
	}

	@Override
	public Boolean testIfMatiereAffected(Long matiereId) {
		Long verif = this.examenRepository.testIfMatiereAffected(matiereId);
		return verif > 0;
	}

	@Override
	public Boolean testIfSalleUsedInHoraire(String horaire, Long salleId) {
		Long verif = this.examenRepository.testIfSalleUsedInHoraire(horaire, salleId);
		return verif > 0;
	}

	@Override
	public List<Examen> findAllByDateAndHoraire(LocalDate date, String horaire) {
		return this.examenRepository.findAllByDateAndHoraire(date, horaire);
	}
	

}
