package fr.eni.clinique.bo;

import java.util.Date;

public class Agenda {

	
	private Personel veto; 
	private Date dateRdv; //Rendez-vous tous les quarts d’heures : 00 ; 15 ; 30 ; 45
	private Animal animal;
	
	
	public Agenda() {
		super();
	}
	public Agenda(Personel veto, Date dateRdv, Animal animal) {
		super();
		this.veto = veto;
		this.dateRdv = dateRdv;
		this.animal = animal;
	}


	public Personel getVeto() {
		return veto;
	}
	public void setVeto(Personel veto) {
		this.veto = veto;
	}

	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Date getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agenda [veto=").append(veto).append(", dateRdv=").append(dateRdv).append(", animal=")
				.append(animal).append("]");
		return builder.toString();
	}
	
	
	
}
