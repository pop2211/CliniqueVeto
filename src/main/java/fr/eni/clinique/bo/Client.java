package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private Integer codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private List<Animal> animaux = new ArrayList<Animal>();
	private Boolean archive;

	public Client() {
		super();
	}

	public Client(Integer codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			List<Animal> animaux, Boolean archive) {
		super();
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.animaux = animaux;
		this.archive = archive;
	}
	
		public Client(String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			Boolean archive) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = archive;
	}
		
	public Integer getCodeClient() {
		return codeClient;
	}


	public void setCodeClient(Integer codeClient) {
		this.codeClient = codeClient;
	}


	public String getNomClient() {
		return nomClient;
	}


	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}


	public String getPrenomClient() {
		return prenomClient;
	}


	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}


	public String getAdresse1() {
		return adresse1;
	}


	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}


	public String getAdresse2() {
		return adresse2;
	}


	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getNumTel() {
		return numTel;
	}


	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}


	public String getAssurance() {
		return assurance;
	}


	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRemarque() {
		return remarque;
	}


	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	
	public List<Animal> getAnimaux() {
		return animaux;
	}

	public void setAnimaux(List<Animal> animaux) {
		this.animaux = animaux;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [codeClient=");
		builder.append(codeClient);
		builder.append(", nomClient=");
		builder.append(nomClient);
		builder.append(", prenomClient=");
		builder.append(prenomClient);
		builder.append(", adresse1=");
		builder.append(adresse1);
		builder.append(", adresse2=");
		builder.append(adresse2);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append(", numTel=");
		builder.append(numTel);
		builder.append(", assurance=");
		builder.append(assurance);
		builder.append(", email=");
		builder.append(email);
		builder.append(", remarque=");
		builder.append(remarque);
		builder.append(", animaux=");
		builder.append(animaux);
		builder.append(", archive=");
		builder.append(archive);
		builder.append("]");
		return builder.toString();
	}
	
}
