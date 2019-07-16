package com.isima.session.isima.web;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isima.session.isima.entities.Calendrier;
import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.entities.Examen;
import com.isima.session.isima.entities.Matiere;
import com.isima.session.isima.entities.Salle;
import com.isima.session.isima.service.CalendrierService;
import com.isima.session.isima.service.EnseignantService;
import com.isima.session.isima.service.ExamenService;
import com.isima.session.isima.service.MatiereService;
import com.isima.session.isima.service.SalleService;

@Controller
@RequestMapping("/examen")
public class ExamenController {
	private final ExamenService examenService;
	
	private final SalleService salleService;
	
	private final MatiereService matiereService;
	
	private final CalendrierService calendrierService;
	private final EnseignantService enseignantService;
	
	private Logger logger = LoggerFactory.getLogger(ExamenController.class);

	public ExamenController(ExamenService examenService, SalleService salleService, 
			MatiereService matiereService, CalendrierService calendrierService,
			EnseignantService enseignantService) {
		this.examenService = examenService;
		this.salleService = salleService;
		this.matiereService = matiereService;
		this.calendrierService = calendrierService;
		this.enseignantService = enseignantService;
	}
	
	@RequestMapping(value="/form" ,method=RequestMethod.GET)
	public String formExamen (Model model, @RequestParam(required = false) Long examenId,
			@RequestParam(value="erreurSalleUsedInHoraire", required = false)Boolean erreurSalleUsedInHoraire) {
		if (examenId != null) {
			Examen examen = this.examenService.findOne(examenId);
			this.logger.info("examen est : {}", examen);
			model.addAttribute("examen", examen);
			model.addAttribute("modif", Boolean.TRUE);
			model.addAttribute("salleModal", examen.getSalle());
		} else {
			model.addAttribute("examen", new Examen());
			model.addAttribute("modif", Boolean.FALSE);
			model.addAttribute("listEnseignants", this.enseignantService.listEnseignantAll());
		}
		List<Salle> listSalles = this.salleService.listSalleAllWithoutDTO();
		model.addAttribute("listSalles", listSalles);
		List<Matiere> listMatieres = this.matiereService.listMatiereAll();
		model.addAttribute("listMatieres", listMatieres);
		
		if(erreurSalleUsedInHoraire != null) 
			model.addAttribute("erreurSalleUsedInHoraire", Boolean.TRUE);
		
		return "ajoutExamen";
	}
	
	@RequestMapping(value="/saveExamen", method=RequestMethod.POST)
	public String saveExamen(Model model, @Valid Examen examen) {
		try {
			this.logger.info("Examen : {}", examen.toString());
			String filiaire = "";
			Calendrier calendrier;
			String queryString;
			if (examen.getFiliere().equals("LAI")) {
				filiaire = "Licence Appliquée en Sciences de l'Informatique";
			} else if (examen.getFiliere().equals("LFI")) {
				filiaire = "Licence Fondamentale en Sciences de l'Informatique";
			} else {
				filiaire = "Licence Appliquée en Administration de Réseaux et sécurité";
			}
			
			String DSOrExamen = examen.getTypeSession();
			if (DSOrExamen.equals("DS")) {
				queryString = "Calendrier des DS : " + filiaire + " niveau : " + examen.getMatiere().getNiveau();
			} else {
				queryString = "Calendrier des Examens : " + filiaire + " niveau : " + examen.getMatiere().getNiveau();
			}
			
			calendrier = this.calendrierService.findByNom(queryString);
			this.logger.info("queryString : {}", queryString);
			examen.setCalendrier(calendrier);
			if (this.examenService.testIfSalleUsedInHoraire(examen.getHoraire(), examen.getSalle().getId()) == Boolean.FALSE) {
				this.examenService.addExamen(examen);
			} else 
				return "redirect:/examen/form?erreurSalleUsedInHoraire=true";
			
			
		} catch (Exception e) {
			model.addAttribute("error",e);
			return "redirect:/examen/fichierExamens"+"&error="+e.getMessage();
		}
		
		return "redirect:/examen/fichierExamen";
		
			
	}
	
	@RequestMapping(value="/fichierExamen")
	public String fichierExamen(Model model, @RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="size",defaultValue="10") int size) {
		Page<Examen> pageExamens = this.examenService.listExamen(page, size);
		int pageCount=pageExamens.getTotalPages();
		int [] pages =new int[pageCount];
		model.addAttribute("pages",pages);
		model.addAttribute("pageCourante",page);
		model.addAttribute("pageExamens", pageExamens);
		return "fichierExamens";
	}
	
	@RequestMapping(value="/supprimer", method=RequestMethod.GET)
	public String supprimer(@RequestParam("id") Long id) {
		this.examenService.delete(id);
		return "redirect:/examen/fichierExamen";
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
	}
	
	@RequestMapping(value="/getExamenById/{examenId}")
	@ResponseBody
	public Examen getExamenById(@PathVariable("examenId") Long examenId) {
		return this.examenService.findOne(examenId);
	}
	
	
	@GetMapping("/listHoraire")
	@ResponseBody
	public List<String> getListofHoraire1H(@RequestParam("dsOrExamen") String dsOrExamen) {
		this.logger.info("hello I shall be called by the jquery code");
		List<String> listHoraire = new ArrayList<>();
		if (dsOrExamen.equals("DS")) {
			listHoraire.add("du 09:00 à 10:00");
			listHoraire.add("du 10:30 à 11:30");
			listHoraire.add("du 13:00 à 14:00");
			listHoraire.add("du 14:30 à 15:30");
		} else if (dsOrExamen.equals("EXAMEN")) {
			listHoraire.add("du 09:00 à 10:30");
			listHoraire.add("du 11:00 à 12:30");
			listHoraire.add("du 14:00 à 15:30");
			listHoraire.add("du 16:00 à 17:30");
		}
		
		return listHoraire;
	}
	
	@GetMapping("/getAllEnseignantByDateAndByHoraire")
	@ResponseBody
	public List<Examen> getListEnseignant(@RequestParam("date") LocalDate date, @RequestParam("horaire") String horaire) {
		return this.examenService.findAllByDateAndHoraire(date, horaire);
	}
	
	
}
