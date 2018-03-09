package fr.eni.clinique.bo;

public class Animal {

	private Integer codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String tatouage;
	private String antecedents;
	private Boolean archive;
	private Race race;
	private Client client;
	
	public Animal() {
		super();
		this.setArchive(false);
	}
	public Animal(Integer codeAnimal, String nomAnimal, String sexe, String couleur, String tatouage,
			String antecedents, Boolean archive, Race race, Client client) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
		this.race = race;
		this.client = client;
	}
	
	public Animal(String nomAnimal, String sexe, String couleur, String tatouage, String antecedents, Boolean archive,
			Race race, Client client) {
		super();
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
		this.race = race;
		this.client = client;
	}
	public Integer getCodeAnimal() {
		return codeAnimal;
	}
	
	public void setCodeAnimal(Integer codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	
	public String getNomAnimal() {
		return nomAnimal;
	}
	
	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}
	
	public String getSexe() {
		return sexe;
	}
	
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public String getTatouage() {
		return tatouage;
	}
	
	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}
	
	public String getAntecedents() {
		return antecedents;
	}
	
	public void setAntecedents(String antecedants) {
		this.antecedents = antecedants;
	}
	
	public boolean isArchive() {
		return archive;
	}
	
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client codeClient) {
		this.client = codeClient;
	}
	
	public Race getRace() {
		return race;
	}
	
	public void setRace(Race race) {
		this.race = race;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Animal [codeAnimal=");
		builder.append(codeAnimal);
		builder.append(", nomAnimal=");
		builder.append(nomAnimal);
		builder.append(", sexe=");
		builder.append(sexe);
		builder.append(", couleur=");
		builder.append(couleur);
		builder.append(", tatouage=");
		builder.append(tatouage);
		builder.append(", antecedents=");
		builder.append(antecedents);
		builder.append(", archive=");
		builder.append(archive);
		builder.append(", race=");
		builder.append(race);
		builder.append(", codeClient=");
		builder.append(client);
		builder.append("]");
		return builder.toString();
	}
	
}
