package com.isima.session.isima.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isima.session.isima.entities.Calendrier;
import com.isima.session.isima.entities.Examen;
import com.isima.session.isima.entities.Matiere;
import com.isima.session.isima.entities.Salle;
import com.isima.session.isima.service.CalendrierService;

@Controller
@RequestMapping("/calendrier")
public class CalendrierController {

	private final CalendrierService calendrierService;
	
	private final Logger logger = LoggerFactory.getLogger(CalendrierController.class);

	public CalendrierController(CalendrierService calendrierService) {
		super();
		this.calendrierService = calendrierService;
	}
	
	@RequestMapping(value="/fichierCalendrier")
	public String findAllCalendrier(Model model) {
		List<Calendrier> listCalendrier = this.calendrierService.getAllCalendrier();
		model.addAttribute("listCalendrier", listCalendrier);
		return "fichierCalendriers";
	}
	
	@RequestMapping(value="/form" ,method=RequestMethod.GET)
	public String formCalendrier (Model model, @RequestParam(required = true) Long calendrierId) {
		Calendrier calendrier = this.calendrierService.findById(calendrierId);
		this.logger.info("nom du calendrier: {}", calendrier.getNom());
		model.addAttribute("calendrier", calendrier);
		
		return "detailCalendrier";
	}
	
	@RequestMapping(value="/getCalendrierByNom")
	@ResponseBody
	public Calendrier getCalendrierByNom(@RequestBody String nom) {
		return this.calendrierService.findByNom(nom);
	}
	
	@RequestMapping(value="/getCalendrierById/{calendrierId}")
	@ResponseBody
	public Calendrier getCalendrierById(@PathVariable("calendrierId") Long calendrierId) {
		return this.calendrierService.findById(calendrierId);
	}
	
	
}
