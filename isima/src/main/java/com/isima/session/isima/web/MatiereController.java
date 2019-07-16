package com.isima.session.isima.web;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isima.session.isima.entities.Enseignant;
import com.isima.session.isima.entities.Filiere;
import com.isima.session.isima.entities.Matiere;
import com.isima.session.isima.repository.FiliereRepository;
import com.isima.session.isima.service.EnseignantService;
import com.isima.session.isima.service.ExamenService;
import com.isima.session.isima.service.MatiereService;

@Controller
@RequestMapping(value="/matiere")
public class MatiereController {
	private final MatiereService matiereService;
	private final EnseignantService enseignantService;
	private final ExamenService examenService;
	private final FiliereRepository filiereRepository;
	
	public MatiereController(MatiereService matiereService, EnseignantService enseignantService, ExamenService examenService,
			FiliereRepository filiereRepository) {
		super();
		this.matiereService = matiereService;
		this.enseignantService = enseignantService;
		this.examenService= examenService;
		this.filiereRepository = filiereRepository;
	}
@RequestMapping(value="/form" , method=RequestMethod.GET)
public String formMatiere(Model model , @RequestParam(required = false ) Long matiereId ) {
	List<Filiere> filieres = this.filiereRepository.findAll();
	model.addAttribute("filieres", filieres);
	if (matiereId != null) {
	Matiere matiere = this.matiereService.findOne(matiereId);
	model.addAttribute("matiere", matiere);
	model.addAttribute("modif", Boolean.TRUE);
	
	}
	else {
	model.addAttribute("matiere", new Matiere());
	model.addAttribute("modif", Boolean.FALSE);
	}
	
	List<Enseignant> listEnseignants = this.enseignantService.listEnseignantAll();
	model.addAttribute("listEnseignants", listEnseignants);
	
	return "ajoutMat";
	
}
@RequestMapping(value="/fichierMatiere")
public String fichierEnseignant(Model model, @RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="size",defaultValue="10") 
int size, @RequestParam(name="erreurMatiere", required=false) Boolean erreurMatiere)  {
	Page<Matiere> pageMatieres= this.matiereService.listMatiere(page, size);
	int pageCount=pageMatieres.getTotalPages();
	int []pages=new int[pageCount];
	model.addAttribute("pages",pages);
	model.addAttribute("pageCourante",page);
	model.addAttribute("pageMatieres", pageMatieres);
	if(erreurMatiere != null)
		model.addAttribute("erreurMatiere", Boolean.TRUE);
	return "fichierMatiere";
}

@RequestMapping(value="saveMatiere" , method= RequestMethod.POST)
public String SaveMatiere (Model model , @Valid Matiere matiere ) {
	try {
		this.matiereService.addMatiere(matiere);
	}catch (Exception e) {
		model.addAttribute("error", e);
		return "redirect:/matiere/ficheMatiere"+"&error="+e.getMessage();
		
	}
	return "redirect:/matiere/fichierMatiere";
}
@RequestMapping(value="/supprimer" , method=RequestMethod.GET)
public String supprimer (@RequestParam("id") Long id ) {
	if (this.examenService.testIfMatiereAffected(id) == Boolean.FALSE) {
		this.matiereService.supprimer(id);
		return "redirect:/matiere/fichierMatiere";
	} else
		return "redirect:/matiere/fichierMatiere?erreurMatiere=true";
}

}
