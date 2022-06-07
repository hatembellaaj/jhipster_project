package com.csf.gaf.web.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.csf.gaf.domain.entity.Facture;
import com.csf.gaf.service.FactureService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/factures")
public class FactureController {

	@Autowired
	private FactureService factureService;

	
	@GetMapping("/hello")
	public String allAccess() {
		return "HELLO from facture :) !.";
	}
	
	
	
	@GetMapping("/findAll")
	public List<Facture> getAllFacture() {
		return factureService.listAll();
	}

	
	@GetMapping("/find/{idArticle}")
	public Facture getFactureById(@PathVariable(value = "idFacture") String id) {
		return factureService.get(id);
	}

	
	@PostMapping("/save")
	public Facture save(@RequestBody Facture facture) throws Exception {
		return factureService.save(facture);
	}

	
	@PostMapping("/update")
	public Facture update(@RequestBody Facture facture) throws Exception {
		return factureService.save(facture);
	}


	@DeleteMapping("delete/{idFacture}")
	public String deleteFacture(@PathVariable("idFacture") String id) {
		factureService.delete(id);
		return  " Facture is deleted";
	}


}

