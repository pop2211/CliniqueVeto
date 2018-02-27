package fr.eni.clinique.bo;

import java.util.GregorianCalendar;

public class Rdv {

	
	private Personnel veto; 
	private GregorianCalendar dateRdv; //Rendez-vous tous les quarts d�heures : 00 ; 15 ; 30 ; 45
	private Animal animal;
	
	
	public Rdv() {
		super();
	}
	public Rdv(Personnel veto, GregorianCalendar dateRdv, Animal animal) {
		super();
		this.veto = veto;
		this.dateRdv = dateRdv;
		this.animal = animal;
	}

	public Personnel getVeto() {
		return veto;
	}
	public void setVeto(Personnel veto) {
		this.veto = veto;
	}

	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public GregorianCalendar getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(GregorianCalendar dateRdv) {
		this.dateRdv = dateRdv;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rdv [veto=");
		builder.append(veto);
		builder.append(", dateRdv=");
		builder.append(dateRdv);
		builder.append(", animal=");
		builder.append(animal);
		builder.append("]");
		return builder.toString();
	}
	
}
