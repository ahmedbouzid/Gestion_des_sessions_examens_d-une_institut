package com.isima.session.isima.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.repository.EnseignantRepository;
import com.isima.session.isima.service.EnseignantService;

@Service
@Transactional
public class EnseignantServiceImpl implements EnseignantService {
	
	private final EnseignantRepository enseignantRepository;
	
	public EnseignantServiceImpl(EnseignantRepository enseignantRepository) {
		super();
		this.enseignantRepository = enseignantRepository;
	}

	@Override
	public void addEnseignant(Enseignant enseigant) {
		this.enseignantRepository.save(enseigant);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Enseignant> listEnseignant(int page, int size) {
		return enseignantRepository.listEnseigant(new PageRequest(page, size));
	}

	@Override
	public void delete(Long id) {
		this.enseignantRepository.deleteById(id);
		
	}

	@Override
	public Enseignant findOne(Long id ) {
        return this.enseignantRepository.getOne(id);
	}

	@Override
	public List<Enseignant> listEnseignantAll() {
		return this.enseignantRepository.findAll();
	}

	@Override
	public Page<Object> listEnseignantRecherche(String nomEnseignant, int page, int size) {
		return this.enseignantRepository.listEnseignantRecherche(new PageRequest(page, size), nomEnseignant);
	}

}
