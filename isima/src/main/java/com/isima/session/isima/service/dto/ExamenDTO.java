package com.isima.session.isima.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ExamenDTO implements Serializable {

	private Long id;
	
	private LocalDate date;
	private int duree;
	private LocalTime horaire;
	private String numSalle;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public LocalTime getHoraire() {
		return horaire;
	}
	public void setHoraire(LocalTime horaire) {
		this.horaire = horaire;
	}
	public String getNumSalle() {
		return numSalle;
	}
	public void setNumSalle(String numSalle) {
		this.numSalle = numSalle;
	}
	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExamenDTO examenDTO = (ExamenDTO) o;
        if (examenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), examenDTO.getId());
	}
	
}
