package com.csf.gaf.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bc")
public class BC {
	String idFacture;
	String idBC;
	String idClient;
	String DateBC;
	float Montant_HTVA;
	float Montant_TTC;
	
	
	@Id  
	@Column
	public String getIdFacture() {
		return idFacture;
	}
	
	@Column
	public void setIdFacture(String idFacture) {
		this.idFacture = idFacture;
	}
	
	@Column
	public String getIdBC() {
		return idBC;
	}
	public void setIdBC(String idBC) {
		this.idBC = idBC;
	}
	
	@Column
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	@Column
	public String getDateBC() {
		return DateBC;
	}
	public void setDateBC(String dateBC) {
		DateBC = dateBC;
	}
	
	@Column
	public float getMontant_HTVA() {
		return Montant_HTVA;
	}
	
	@Column
	public void setMontant_HTVA(float montant_HTVA) {
		Montant_HTVA = montant_HTVA;
	}
	
	@Column
	public float getMontant_TTC() {
		return Montant_TTC;
	}
	
	@Column
	public void setMontant_TTC(float montant_TTC) {
		Montant_TTC = montant_TTC;
	}

	@Override
	public String toString() {
		return "BC [idFacture=" + idFacture + ", idBC=" + idBC + ", idClient=" + idClient + ", DateBC=" + DateBC
				+ ", Montant_HTVA=" + Montant_HTVA + ", Montant_TTC=" + Montant_TTC + "]";
	}
}
