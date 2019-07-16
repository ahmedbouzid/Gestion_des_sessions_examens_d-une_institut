package com.isima.session.isima.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tomcat.jni.Time;


@Entity
@Table(name = "calendrier")
public class Calendrier implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@OneToMany
	@JoinColumn(name = "calendrier_id")
	private Set<Examen> examens = new HashSet<>();
	
	public Calendrier() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public Calendrier addExamen(Examen examen) {
		this.examens.add(examen);
		examen.setCalendrier(this);
		return this;
	}
	
	public Calendrier removeExamen(Examen examen) {
		this.examens.remove(examen);
		examen.setCalendrier(null);
		return this;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

	
}
