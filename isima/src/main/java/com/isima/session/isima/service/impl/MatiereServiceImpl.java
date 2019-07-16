package com.isima.session.isima.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.isima.session.isima.entities.Matiere;
import com.isima.session.isima.repository.MatiereRepository;
import com.isima.session.isima.service.MatiereService;
@Service
@Transactional
public class MatiereServiceImpl  implements MatiereService{

	private final MatiereRepository matiereRepository;
	
	public MatiereServiceImpl(MatiereRepository matiereRepository) {
			super();
			this.matiereRepository = matiereRepository;
		}
	
	@Override
	public List<Matiere> listMatiereAll() {
		return this.matiereRepository.findAll();
	}

	@Override
	public Matiere findOne(Long matiereId) {
		return this.matiereRepository.getOne(matiereId);
	}

	@Override
	public void addMatiere(Matiere matiere) {
		this.matiereRepository.save(matiere);
		
	}

	@Override
	public void supprimer(Long matiereId) {
		this.matiereRepository.deleteById(matiereId);
		
	}

	@Override
	public Page<Matiere> listMatiere(int page, int size) {
		return matiereRepository.listMatiere(new PageRequest(page, size));
	}

	@Override
	public Page<Object> listMatiereRecherche(String nomMatiere, int page, int size) {
		return this.matiereRepository.listMatiereRecherche(new PageRequest(page, size), nomMatiere);

	}
 

}
