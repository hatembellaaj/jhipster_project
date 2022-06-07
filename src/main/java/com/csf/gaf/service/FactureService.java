package com.csf.gaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.gaf.domain.entity.Facture;
import com.csf.gaf.repository.FactureRepository;


@Service
public class FactureService {
	@Autowired
	private FactureRepository factureRepository;

	public List<Facture> listAll() {
		return factureRepository.findAll();	
	}
	
	public Facture save(Facture facture){
		factureRepository.save(facture);
		return facture;
	}
	
	public Facture get(String id) {
		return factureRepository.getById(id);
	}
	
	public void delete(String id) {
		factureRepository.deleteById(id);
	}

}