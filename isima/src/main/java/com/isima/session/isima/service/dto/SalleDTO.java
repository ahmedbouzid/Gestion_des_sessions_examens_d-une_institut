package com.isima.session.isima.service.dto;

import java.io.Serializable;

public class SalleDTO implements Serializable {

	private Long id;
	
	private String numSalle;
	
	private int capacite;
	
	private int nbrEnseignant;
	
	private Long examenId;

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

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getNbrEnseignant() {
		return nbrEnseignant;
	}

	public void setNbrEnseignant(int nbrEnseignant) {
		this.nbrEnseignant = nbrEnseignant;
	}

	public Long getExamenId() {
		return examenId;
	}

	public void setExamenId(Long examenId) {
		this.examenId = examenId;
	}
	
	
	
	
}
