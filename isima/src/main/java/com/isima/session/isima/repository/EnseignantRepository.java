package com.isima.session.isima.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isima.session.isima.entities.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
	
	@Query("select e from Enseignant e")
	Page<Enseignant> listEnseigant(Pageable pageable);
	
	@Query("select e from Enseignant e where LOWER(e.nom) LIKE LOWER(concat('%',concat(:nomEnseignant,'%')))")
	Page<Object> listEnseignantRecherche(Pageable pageable, @Param("nomEnseignant")String nomEnseignant);

}
