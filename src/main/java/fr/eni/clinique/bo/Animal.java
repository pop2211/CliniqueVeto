package fr.eni.clinique.bo;

public class Animal {

	private Integer codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String tatouage;
	private String antecedants;
	private byte archive;
	private Race race;
	private Integer codeClient;
	
	public Animal() {
		super();
	}
	public Animal(Integer codeAnimal, String nomAnimal, String sexe, String couleur, String tatouage,
			String antecedants, byte archive, Race race, Integer codeClient) {
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
	public byte isArchive() {
		return archive;
	}
	public void setArchive(byte archive) {
		this.archive = archive;
	}
	
	public byte getArchive() {
		return archive;
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
		return "Animal [codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal + ", sexe=" + sexe + ", couleur="
				+ couleur + ", tatouage=" + tatouage + ", antecedants=" + antecedants + ", archive=" + archive
				+ ", race=" + race.toString() + ", codeClient=" + codeClient + "]";
	}

	
	
	
}
