package com.isima.session.isima.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.entities.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Long > {
	
	@Query("select e from Examen e")
	Page<Examen> listExamen(Pageable pageable);
	
	@Query("select count(*) from Examen examen where examen.salle.id = :salleId")
	Long testIfSalleAffected(@Param("salleId") Long salleId);
	
	@Query("select count(*) from Examen examen where examen.matiere.enseignant.id = :enseignantId")
	Long testIfEnseignantAffected(@Param("enseignantId") Long enseignantId);
	
	@Query("select count (*) from Examen examen where examen.matiere.id = :matiereId")
	Long testIfMatiereAffected(@Param("matiereId") Long matiereId);
	
	@Query("select count(*) from Examen examen where examen.horaire = :horaire and examen.salle.id = :salleId")
	Long testIfSalleUsedInHoraire(@Param("horaire") String horaire, @Param("salleId") Long salleId);
	
	List<Examen> findAllByDateAndHoraire(LocalDate date, String horaire);

}
