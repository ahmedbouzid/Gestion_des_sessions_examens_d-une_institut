package com.isima.session.isima.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isima.session.isima.entities.Calendrier;
import com.isima.session.isima.entities.Examen;
import com.isima.session.isima.entities.FicheDeSuivie;
import com.isima.session.isima.service.ExamenService;
import com.isima.session.isima.service.FichSuivieService;

@Controller
@RequestMapping("/fichSuivie")
public class FichSuivieController {

	private final FichSuivieService fichSuivieService;
	
	private final ExamenService examenService;

	public FichSuivieController(FichSuivieService fichSuivieService, ExamenService examenService) {
		super();
		this.fichSuivieService = fichSuivieService;
		this.examenService = examenService;
	}
	
	
	@RequestMapping(value="/fichierFichSuivie")
	public String findAllFichSuivie(Model model) {
		List<FicheDeSuivie> listFichSuivie = this.fichSuivieService.getAllFichSuivie();
		model.addAttribute("listFichSuivie", listFichSuivie);
		return "fichierFichSuivies";
	}
	
	@RequestMapping(value="/form" ,method=RequestMethod.GET)
	public String formFichSuivie (Model model, @RequestParam(required = true) Long fichSuivieId) {
		FicheDeSuivie ficheDeSuivie = this.fichSuivieService.findById(fichSuivieId);
		model.addAttribute("fichSuivie", ficheDeSuivie);
		
		List<Examen> listExamen = this.examenService.findAllByDateAndHoraire(ficheDeSuivie.getDate(),
				ficheDeSuivie.getHoraire());
		model.addAttribute("listExamen", listExamen);
		return "detailFichSuivie";
	}
	
}

