package com.isima.session.isima.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isima.session.isima.entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long>{
	
	@Query("select s from Salle s")
	Page<Salle> listSalle(Pageable pageable);
	
	@Query("select distinct salle from Salle salle where salle.horaireRes <> ?1")
	List<Salle> findAllNotReserverd(String horaireRes);
	

}
