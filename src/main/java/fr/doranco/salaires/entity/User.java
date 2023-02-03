package fr.doranco.salaires.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private int id;  
	private String genre;  
	private String nom; 
	private String prenom; 
	private String telephone; 
	private Date dateNaissance; 
	private Date dateEntree;
	private Date dateSortie;
	private String titre;
	private float salaireDeBase;
	private String email;
	private String statut;
	private List<Adresse> adresses;
	
	public User() {
	adresses = new ArrayList<Adresse>();	
	}
	

	
	public User(String genre, String nom, String prenom, String telephone, Date dateNaissance, Date dateEntree,
			Date dateSortie, String titre, float salaireDeBase,String email, String statut) {
		super();
		this.genre = genre;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.titre = titre;
		this.salaireDeBase = salaireDeBase;
		this.email = email;
		this.statut = statut;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public Date getDateNaissance() {
		return dateNaissance;
	}



	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}



	public Date getDateEntree() {
		return dateEntree;
	}



	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}



	public Date getDateSortie() {
		return dateSortie;
	}



	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}



	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public float getSalaireDeBase() {
		return salaireDeBase;
	}



	public void setSalaireDeBase(float salaireDeBase) {
		this.salaireDeBase = salaireDeBase;
	}


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}



	public List<Adresse> getAdresses() {
		return adresses;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", genre=" + genre + ", nom=" + nom + ", prenom=" + prenom + ", telephone="
				+ telephone + ", dateNaissance=" + dateNaissance + ", dateEntree=" + dateEntree + ", dateSortie="
				+ dateSortie + ", titre=" + titre + ", salaireDeBase=" + salaireDeBase + ", salaireReel=" 
				+ ", email=" + email + ", statut=" + statut + "]";
	}

	
	
}
