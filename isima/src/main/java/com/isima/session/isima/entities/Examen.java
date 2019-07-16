package com.isima.session.isima.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "examen")
public class Examen implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate date;
	private String duree;
	private String filiere;
	private String horaire;
	private Character groupe;
	
	private String typeSession;
	
	@ManyToOne
	@JsonIgnoreProperties("examens")
	private Matiere matiere;
	
	@OneToMany(mappedBy="examen")
	private Set<Enseignant> enseignants = new HashSet<>();
	
	@ManyToOne
	@JsonIgnoreProperties("examens")
	private Calendrier calendrier;
	
	@OneToOne
	@JoinColumn(unique=false)
	private Salle salle;
	
	@ManyToOne
	@JoinColumn(name="examen_id")
	@JsonIgnoreProperties("examens")
	private FicheDeSuivie ficheDeSuivie;

	public Examen() {
		super();
	}

	public Examen(LocalDate date, String duree, String horaire, String numSalle, Matiere matiere, String filiere, Character groupe) {
		super();
		this.date = date;
		this.duree = duree;
		this.horaire = horaire;
		this.matiere = matiere;
		this.filiere = filiere;
		this.groupe = groupe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Character getGroupe() {
		return groupe;
	}

	public void setGroupe(Character groupe) {
		this.groupe = groupe;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	

	public Calendrier getCalendrier() {
		return calendrier;
	}

	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}
	

	public String getTypeSession() {
		return typeSession;
	}

	public void setTypeSession(String typeSession) {
		this.typeSession = typeSession;
	}
	
	

	public Set<Enseignant> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(Set<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}
	
	

	public FicheDeSuivie getFicheDeSuivie() {
		return ficheDeSuivie;
	}

	public void setFicheDeSuivie(FicheDeSuivie ficheDeSuivie) {
		this.ficheDeSuivie = ficheDeSuivie;
	}

	@Override
	public String toString() {
		return "Examen [id=" + id + ", date=" + date + ", duree=" + duree + ", filiere=" + filiere + ", horaire="
				+ horaire + ", groupe=" + groupe + ", salle=" + salle.getId() + "]";
	}
	
	

}
