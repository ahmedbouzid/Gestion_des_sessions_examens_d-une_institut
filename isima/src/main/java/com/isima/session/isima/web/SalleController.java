package com.isima.session.isima.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isima.session.isima.entities.Salle;
import com.isima.session.isima.service.ExamenService;
import com.isima.session.isima.service.SalleService;

@Controller
@RequestMapping(value="/salle")
public class SalleController {
	private final SalleService salleService;
	
	private final ExamenService examenService;
	
	private final Logger logger = LoggerFactory.getLogger(SalleController.class);

	public SalleController(SalleService salleService, ExamenService examenService) {
		this.salleService = salleService;
		this.examenService = examenService;
	}
	
@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formSalle (Model model) {
	model.addAttribute("salle", new Salle());
	return "ajoutSalle";
}
@RequestMapping(value="/saveSalle" , method=RequestMethod.POST)
	public String saveSalle (Model model , @Valid Salle salle) {
	try {
		this.salleService.addSalle(salle);;
		
	} catch (Exception e) {
		model.addAttribute("error",e);
		return "redirect:/salle/fichierSalle"+"&error="+e.getMessage();
	}
	
	return "redirect:/salle/fichierSalle";
}
@RequestMapping(value="/fichierSalle")
public String fichierSalle(Model model, @RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="size",
	defaultValue="10") int size, @RequestParam(name="erreurSalle", required=false) Boolean erreurSalle)  {
	Page<Salle> pageSalle = this.salleService.listSalle(page, size);
	model.addAttribute("pageSalle", pageSalle);
	int []pages=new int[pageSalle.getTotalPages()];
	model.addAttribute("pages",pages);
	if(erreurSalle != null)
		model.addAttribute("erreurSalle", Boolean.TRUE);
	return "fichierSalle";
}

@RequestMapping(value="/supprimer", method=RequestMethod.GET)
public String supprimer(@RequestParam ("id") Long id) {
	if (this.examenService.testIfSalleAffected(id) == Boolean.FALSE) { 
		this.salleService.delete(id);
		return "redirect:/salle/fichierSalle";
	} else 
		return "redirect:/salle/fichierSalle?erreurSalle=true";
	
	
}

@GetMapping("/getAllSallesNotReserved")
@ResponseBody
public List<Salle> getAllSallesNotReserved(@RequestParam(value="horaireRes", required = true) String horaireRes ) {
	this.logger.info("horaire est : {}; nombre de salle non réservés: {}",horaireRes, salleService.getAllSalleNotReserved(horaireRes).size());
	return salleService.getAllSalleNotReserved(horaireRes);
}

}
