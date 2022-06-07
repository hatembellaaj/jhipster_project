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
import com.csf.gaf.domain.entity.BC;
import com.csf.gaf.service.BCService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bcs")
public class BCController {

	@Autowired
	private BCService bcService;

	
	@GetMapping("/hello")
	public String allAccess() {
		return "HELLO from bc :) !.";
	}
	
	
	// get all BC
	@GetMapping("/findAll")
	public List<BC> getAllBC() {
		return bcService.listAll();
	}

	// get BC by id
	@GetMapping("/find/{idBC}")
	public BC getBCById(@PathVariable(value = "idBC") String id) {
		return bcService.get(id);
	}

	// create BC
	@PostMapping("/save")
	public BC save(@RequestBody BC bc) throws Exception {
		return bcService.save(bc);
	}

	// update BC
	@PostMapping("/update")
	public BC update(@RequestBody BC bc) throws Exception {
		return bcService.save(bc);
	}

	// delete BC by id
	@DeleteMapping("delete/{idBC}")
	public String deleteBC(@PathVariable("idBC") String id) {
		bcService.delete(id);
		return  " BC is deleted";
	}


}