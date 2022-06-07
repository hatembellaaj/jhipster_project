package com.csf.gaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.gaf.domain.entity.Client;
import com.csf.gaf.repository.ClientRepository;


@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public List<Client> listAll() {
		return clientRepository.findAll();	
	}
	
	public Client save(Client client){
		clientRepository.save(client);
		return client;
	}
	
	public Client get(String id) {
		return clientRepository.getById(id);
	}
	
	public void delete(String id) {
		clientRepository.deleteById(id);
	}

}
