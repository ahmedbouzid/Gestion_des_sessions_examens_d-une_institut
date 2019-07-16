package com.isima.session.isima.web;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.repository.ExamenRepository;
import com.isima.session.isima.service.EnseignantService;
import com.isima.session.isima.service.ExamenService;
import com.isima.session.isima.service.MatiereService;


@Controller
@RequestMapping("/enseignant")
public class EnseignantController {
	private static Logger logger = LoggerFactory.getLogger(EnseignantController.class);
	
	private final EnseignantService enseignantService;
	
	private final MatiereService matiereService;
	private final ExamenService examenService;
 
	public EnseignantController(EnseignantService enseignantService, MatiereService matiereService,ExamenService examenService) {
		super();
		this.enseignantService = enseignantService;
		this.matiereService = matiereService;
		this.examenService=examenService;
	}
	
	@RequestMapping(value="/form" ,method=RequestMethod.GET)
	public String formEnseignant (Model model, @RequestParam(required = false) Long enseignantId) {
		
		if (enseignantId != null) {
			Enseignant enseignant = this.enseignantService.findOne(enseignantId);
			model.addAttribute("enseignant", enseignant);
			model.addAttribute("modif", Boolean.TRUE);
		}
		else {
			model.addAttribute("enseignant", new Enseignant());
			model.addAttribute("modif", Boolean.FALSE);

		}
		
		
		return "ajoutEnsei";
	}
	
	
	@RequestMapping(value="/saveEnseignant", method=RequestMethod.POST)
	public String saveEnseignant(Model model, @Valid Enseignant enseignant) {
		try {
			this.enseignantService.addEnseignant(enseignant);
			
		} catch (Exception e) {
			model.addAttribute("error",e);
			return "redirect:/enseignant/fichierEnsei"+"&error="+e.getMessage();
		}
		
		return "redirect:/enseignant/fichierEnsei";
	}
	
	@RequestMapping(value="/fichierEnsei")
	public String fichierEnseignant(Model model, @RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="size",defaultValue="10") int size,
			@RequestParam(name="erreurEnseignant", required=false) Boolean erreurEnseignant)  {
		Page<Enseignant> pageEnseignants = this.enseignantService.listEnseignant(page, size);
		int pageCount=pageEnseignants.getTotalPages();
		int []pages=new int[pageCount];
		model.addAttribute("pages",pages);
		model.addAttribute("pageCourante",page);
		model.addAttribute("pageEnseigants", pageEnseignants);
		if(erreurEnseignant != null)
			model.addAttribute("erreurEnseignant", Boolean.TRUE);
		return "fichierEnseignants";
	}
	
	@RequestMapping(value="/supprimer", method=RequestMethod.GET)
	public String supprimer(@RequestParam("id") Long id) {
		if (this.examenService.testIfEnseignantAffected(id) == Boolean.FALSE) {
		this.enseignantService.delete(id);
		return "redirect:/enseignant/fichierEnsei";
		} else 
			return "redirect:/enseignant/fichierEnsei?erreurEnseignant=true";
		
	}
	
	@RequestMapping(value="/recherche", method=RequestMethod.GET, produces = "application/json")
	public String getListRecherche(Model model,@RequestParam("nom") String nom,
										@RequestParam(name="page",defaultValue="0") int page,
										@RequestParam(name="size",defaultValue="10") int size
										) {
		Page<Object> pageEnseignants = this.enseignantService.listEnseignantRecherche(nom, page, size);
		if (pageEnseignants.getContent().size() > 0) {
			int pageCount=pageEnseignants.getTotalPages();
			int []pages=new int[pageCount];
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",page);
			model.addAttribute("pageEnseignants", pageEnseignants);
			
		}
		else {
			Page<Object> pageMatieres = this.matiereService.listMatiereRecherche(nom, page, size);
			if (pageMatieres.getContent().size() > 0) {
				int pageCount=pageMatieres.getTotalPages();
				int []pages=new int[pageCount];
				model.addAttribute("pages",pages);
				model.addAttribute("pageCourante",page);
				model.addAttribute("pageMatieres", pageMatieres);
			}
		}
		return "accueil";
}
}
