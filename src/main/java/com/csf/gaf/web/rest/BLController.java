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
import com.csf.gaf.domain.entity.BL;
import com.csf.gaf.service.BLService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bcs")
public class BLController {

	@Autowired
	private BLService blService;

	
	@GetMapping("/hello")
	public String allAccess() {
		return "HELLO from bl :) !.";
	}
	
	
	// get all BL
	@GetMapping("/findAll")
	public List<BL> getAllBC() {
		return blService.listAll();
	}

	// get BL by id
	@GetMapping("/find/{idBC}")
	public BL getBLById(@PathVariable(value = "idBL") String id) {
		return blService.get(id);
	}

	// create BL
	@PostMapping("/save")
	public BL save(@RequestBody BL bc) throws Exception {
		return blService.save(bc);
	}

	// update BL
	@PostMapping("/update")
	public BL update(@RequestBody BL bc) throws Exception {
		return blService.save(bc);
	}

	// delete BL by id
	@DeleteMapping("delete/{idBL}")
	public String deleteBL(@PathVariable("idBL") String id) {
		blService.delete(id);
		return  " BL is deleted";
	}


}