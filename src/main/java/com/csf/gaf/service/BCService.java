package com.csf.gaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.gaf.domain.entity.BC;
import com.csf.gaf.repository.BCRepository;


@Service
public class BCService {
	@Autowired
	private BCRepository bcRepository;

	public List<BC> listAll() {
		return bcRepository.findAll();	
	}
	
	public BC save(BC bc){
		bcRepository.save(bc);
		return bc;
	}
	
	public BC get(String id) {
		return bcRepository.getById(id);
	}
	
	public void delete(String id) {
		bcRepository.deleteById(id);
	}

}