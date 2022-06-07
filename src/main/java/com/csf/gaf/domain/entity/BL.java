package com.csf.gaf.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bl")
public class BL {
	String idBL;
	String idBC;
	String DateBL;
	String Remarques;
	
	
	@Id  
	@Column
	public String getIdBL() {
		return idBL;
	}
	
	@Column
	public void setIdBL(String idBL) {
		this.idBL = idBL;
	}
	
	@Column
	public String getIdBC() {
		return idBC;
	}
	
	@Column
	public void setIdBC(String idBC) {
		this.idBC = idBC;
	}
	public String getDateBL() {
		return DateBL;
	}
	
	@Column
	public void setDateBL(String dateBL) {
		DateBL = dateBL;
	}
	
	@Column
	public String getRemarques() {
		return Remarques;
	}
	
	@Column
	public void setRemarques(String remarques) {
		Remarques = remarques;
	}

	@Override
	public String toString() {
		return "BL [idBL=" + idBL + ", idBC=" + idBC + ", DateBL=" + DateBL + ", Remarques=" + Remarques + "]";
	}
}
