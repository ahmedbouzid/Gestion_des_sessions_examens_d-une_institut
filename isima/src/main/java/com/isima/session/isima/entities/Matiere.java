
 package com.isima.session.isima.entities;

import java.io.Serializable;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="matiere")
public class Matiere  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(unique=true)
	private String nomMatiere;
	
	@Column
	private Double coe;
	
	@Column
	private  String filiere;
	
	@NonNull
	private int niveau;
	
	@ManyToOne
	@JsonIgnoreProperties("matieres")
	private Enseignant enseignant;
	
	@OneToMany(mappedBy="matiere")
	private Set<Examen> examens = new HashSet<>();
	

	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	





	public Matiere(Long id, @NotEmpty String nomMatiere, Double coe, String filiere, int niveau,
			Enseignant enseignant, Set<Examen> examens) {
		super();
		this.id = id;
		this.nomMatiere = nomMatiere;
		this.coe = coe;
		this.filiere = filiere;
		this.niveau = niveau;
		this.enseignant = enseignant;
		this.examens = examens;
	}







	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomMatiere() {
		return nomMatiere;
	}


	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}


	public Double getCoe() {
		return coe;
	}


	public void setCoe(Double coe) {
		this.coe = coe;
	}
	
	


	public String getFiliere() {
		return filiere;
	}


	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public int getNiveau() {
		return niveau;
	}


	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}


	public Enseignant getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}


	public Set<Examen> getExamens() {
		return examens;
	}
	
	public Matiere addExamen(Examen examen) {
		this.examens.add(examen);
		examen.setMatiere(this);
		return this;
	}

	public Matiere removeExamen(Examen examen) {
		this.examens.remove(examen);
		examen.setMatiere(null);
		return this;
	}
	
	public void setExamens(Set<Examen> examens) {
		this.examens = examens;
	}
	
	
}
