package com.isima.session.isima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isima.session.isima.entities.Calendrier;
public interface CalendrierRepository extends JpaRepository<Calendrier, Long> {

	// @Query("select calendrier from Calendrier calendrier where calendrier.nom like %:nom%")
	Calendrier findByNom(@Param("nom")String nom);
}
