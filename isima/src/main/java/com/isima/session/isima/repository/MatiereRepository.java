package com.isima.session.isima.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.entities.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long>{
	
	@Query("select m from Matiere m ")
	Page<Matiere> listMatiere (Pageable pageable);
	
	
	@Query("select matiere from Matiere matiere where LOWER(matiere.nomMatiere) LIKE LOWER(concat('%',concat(:nomMatiere,'%')))")
	Page<Object> listMatiereRecherche(Pageable pageable,@Param("nomMatiere") String nomMatiere);

}
