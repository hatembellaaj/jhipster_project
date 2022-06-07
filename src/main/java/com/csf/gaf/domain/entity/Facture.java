package com.csf.gaf.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;


public class Facture {
	String idFacture;
	String idClient;
	String idBC;
	String DateFacturation;
	Float Montant_HTVA;
	Float Montant_TTC;
	
	
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
	public String getIdClient() {
		return idClient;
	}
	
	@Column
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	@Column
	public String getIdBC() {
		return idBC;
	}
	public void setIdBC(String idBC) {
		this.idBC = idBC;
	}
	
	@Column
	public String getDateFacturation() {
		return DateFacturation;
	}
	
	@Column
	public void setDateFacturation(String dateFacturation) {
		DateFacturation = dateFacturation;
	}
	
	@Column
	public Float getMontant_HTVAFloat() {
		return Montant_HTVA;
	}
	
	@Column
	public void setMontant_HTVAFloat(Float montant_HTVA) {
		Montant_HTVA = montant_HTVA;
	}
	
	@Column
	public Float getMontant_TTC() {
		return Montant_TTC;
	}
	
	@Column
	public void setMontant_TTC(Float montant_TTC) {
		Montant_TTC = montant_TTC;
	}

	
	@Column

	public Float getMontant_HTVA() {
		return Montant_HTVA;
	}

	public void setMontant_HTVA(Float montant_HTVA) {
		Montant_HTVA = montant_HTVA;
	}

	@Override
	public String toString() {
		return "Facture [idFacture=" + idFacture + ", idClient=" + idClient + ", idBC=" + idBC + ", DateFacturation="
				+ DateFacturation + ", Montant_HTVA=" + Montant_HTVA + ", Montant_TTC=" + Montant_TTC + "]";
	}
}
