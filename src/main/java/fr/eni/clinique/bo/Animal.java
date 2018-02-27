package fr.eni.clinique.bo;

public class Animal {

	private Integer codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String tatouage;
	private String antecedants;
	private Boolean archive;
	private Race race;
	private Integer codeClient;
	
	public Animal() {
		super();
	}
	public Animal(Integer codeAnimal, String nomAnimal, String sexe, String couleur, String tatouage,
			String antecedants, Boolean archive, Race race, Integer codeClient) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.tatouage = tatouage;
		this.antecedants = antecedants;
		this.archive = archive;
		this.race = race;
		this.codeClient = codeClient;
	}
	
	public Animal(String nomAnimal, String sexe, String couleur, String tatouage, String antecedants, Boolean archive,
			Race race, Integer codeClient) {
		super();
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.tatouage = tatouage;
		this.antecedants = antecedants;
		this.archive = archive;
		this.race = race;
		this.codeClient = codeClient;
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
	
	public String getAntecedants() {
		return antecedants;
	}
	
	public void setAntecedants(String antecedants) {
		this.antecedants = antecedants;
	}
	
	public boolean isArchive() {
		return archive;
	}
	
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	
	public Integer getCodeClient() {
		return codeClient;
	}
	
	public void setCodeClient(Integer codeClient) {
		this.codeClient = codeClient;
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
		builder.append(", antecedants=");
		builder.append(antecedants);
		builder.append(", archive=");
		builder.append(archive);
		builder.append(", race=");
		builder.append(race);
		builder.append(", codeClient=");
		builder.append(codeClient);
		builder.append("]");
		return builder.toString();
	}
	
}
