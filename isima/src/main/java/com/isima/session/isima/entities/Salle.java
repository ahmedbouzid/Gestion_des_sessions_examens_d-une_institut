package com.isima.session.isima.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "salle")
public class Salle implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numSalle;
	
	private int capacite;
	
	private int nbrEnseignant;
	
	@Column(name="horaire_res")
	private String horaireRes = "";

	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Salle(String numSalle, int capacite, int nbrEnseignant, String horaireRes) {
		super();
		this.numSalle = numSalle;
		this.capacite = capacite;
		this.nbrEnseignant = nbrEnseignant;
		this.horaireRes = horaireRes;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumSalle() {
		return numSalle;
	}


	public void setNumSalle(String numSalle) {
		this.numSalle = numSalle;
	}
	
	
	public int getNbrEnseignant() {
		return nbrEnseignant;
	}


	public void setNbrEnseignant(int nbrEnseignant) {
		this.nbrEnseignant = nbrEnseignant;
	}


	public int getCapacite() {
		return capacite;
	}


	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}


	public String getHoraireRes() {
		return horaireRes;
	}


	public void setHoraireRes(String horaireRes) {
		this.horaireRes = horaireRes;
	}
}
