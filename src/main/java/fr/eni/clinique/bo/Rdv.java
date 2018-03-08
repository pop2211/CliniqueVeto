package fr.eni.clinique.bo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Rdv {
	
	private Personnel veto; 
	private Timestamp dateRdv; //Rendez-vous tous les quarts d'heures : 00 ; 15 ; 30 ; 45
	private Animal animal;
	
	public Rdv() {
		super();
	}
	
	public Rdv(Personnel veto, Timestamp dateRdv, Animal animal) {
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

	public Timestamp getDateRdv() {
		return dateRdv;
	}
	
	public String getDate() {
		return new SimpleDateFormat("dd/MM/YYYY").format(dateRdv);
	}
	
	public String getTime() {
		return new SimpleDateFormat("HH:ss").format(dateRdv);
	}
	
	public void setDateRdv(Timestamp timestamp) {
		this.dateRdv = timestamp;
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
