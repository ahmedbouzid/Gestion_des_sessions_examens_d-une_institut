package com.isima.session.isima.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "fichesuivie")
public class FicheDeSuivie implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String horaire;
	
	private LocalDate dateFich;
	
	@OneToMany(mappedBy="ficheDeSuivie")
	private Set<Examen> examens = new HashSet<>();
	
	
	public FicheDeSuivie() {
		super();
	}
	

	




	public FicheDeSuivie(Long id, String horaire, LocalDate date, Set<Examen> examens) {
		super();
		this.id = id;
		this.horaire = horaire;
		this.dateFich = date;
		this.examens = examens;
	}







	public LocalDate getDate() {
		return dateFich;
	}







	public void setDate(LocalDate date) {
		this.dateFich = date;
	}







	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Set<Examen> getExamens() {
		return examens;
	}


	public void setExamens(Set<Examen> examens) {
		this.examens = examens;
	}


	public String getHoraire() {
		return horaire;
	}


	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}
	
	

	
	
	
	
}


