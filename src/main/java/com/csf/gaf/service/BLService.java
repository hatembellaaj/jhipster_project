package com.csf.gaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.gaf.domain.entity.BL;
import com.csf.gaf.repository.BLRepository;


@Service
public class BLService {
	@Autowired
	private BLRepository blRepository;

	public List<BL> listAll() {
		return blRepository.findAll();	
	}
	
	public BL save(BL bc){
		blRepository.save(bc);
		return bc;
	}
	
	public BL get(String id) {
		return blRepository.getById(id);
	}
	
	public void delete(String id) {
		blRepository.deleteById(id);
	}

}