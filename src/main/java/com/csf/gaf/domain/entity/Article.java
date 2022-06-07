package com.csf.gaf.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "article")
public class Article {
	
	@Id
	@Column(name="id_article")
	private String idArticle;


	@Column(name="designation",length = 50)
	private String Designation;

	@Column(name="qte")
	private float Qte;

	@Column(name="puhtva")
	private float PUHTVA;
	
	@Column(name="tva")
	private float TVA;
	
	@Column(name="puttc")
	private float PUTTC;
	
	@Column(name="id_fournisseur")
	private String idFournisseur;

	public String getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public float getQte() {
		return Qte;
	}

	public void setQte(float qte) {
		Qte = qte;
	}

	public float getPUHTVA() {
		return PUHTVA;
	}

	public void setPUHTVA(float pUHTVA) {
		PUHTVA = pUHTVA;
	}

	public float getTVA() {
		return TVA;
	}

	public void setTVA(float tVA) {
		TVA = tVA;
	}

	public float getPUTTC() {
		return PUTTC;
	}

	public void setPUTTC(float pUTTC) {
		PUTTC = pUTTC;
	}

	public String getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(String idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", Designation=" + Designation + ", Qte=" + Qte + ", PUHTVA="
				+ PUHTVA + ", TVA=" + TVA + ", PUTTC=" + PUTTC + ", idFournisseur=" + idFournisseur + "]";
	}
	
	
	
}
