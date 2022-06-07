package com.csf.gaf.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	String idClient;
	String Designation;
	String Tel;
	String Adresse;
	
	
	@Id  
	@Column
	public String getIdClient() {
		return idClient;
	}
	
	@Column
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	@Column
	public String getDesignation() {
		return Designation;
	}
	
	@Column
	public void setDesignation(String designation) {
		Designation = designation;
	}
	
	@Column
	public String getTel() {
		return Tel;
	}
	
	@Column
	public void setTel(String tel) {
		Tel = tel;
	}
	
	@Column
	public String getAdresse() {
		return Adresse;
	}
	
	@Column
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", Designation=" + Designation + ", Tel=" + Tel + ", Adresse=" + Adresse
				+ "]";
	}
}
