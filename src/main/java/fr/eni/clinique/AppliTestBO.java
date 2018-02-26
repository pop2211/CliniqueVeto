package fr.eni.clinique;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.bo.Rdv;

public class AppliTestBO {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestBO.class);
    
	public static void main(String[] args) {

		Race race = new Race("pasderace", "bambie");
		Race race2 = new Race("lapin", "civet");
		Animal animal = new Animal(1, "MamandeBambi", "h", "bleu", "symboledelinfini", "alcoolique", (byte) 0, race , 5);
		Animal animal2 = new Animal(2, "Mamandepanpan", "h", "vert", "flingue", "suicidaire", (byte) 0, race2 , 5);

		List<Animal> animaux = new ArrayList<Animal>();
		animaux.add(animal);
		animaux.add(animal2);
		
		LOGGER.info("Test class Animal et Race");
		LOGGER.info(animal.toString());
		LOGGER.info(animal.getCouleur());
		animal.setCouleur("rose");
		LOGGER.info(animal.getCouleur());
		
		LOGGER.info("Test class Client");
		Client client = new Client(5, "lagaffe", "vincent", "8 rue de lala", "bis", "49000", "Angers", "0606060606", "maf", "aze@rty.fr", "ne paye pas", animaux, (byte)0);
		LOGGER.info(client.toString());
		
		LOGGER.info("Test class Personel");
		Personel personel = new Personel(1, "soignetoutou", "bambie", "veto", (byte) 0 );
		LOGGER.info(personel.toString());
		
		LOGGER.info("Test class Rdv");
		GregorianCalendar date = new GregorianCalendar(2018, 2, 25, 14, 15);
		Rdv rdv = new Rdv(personel, date, animal);
		LOGGER.info(rdv.toString());
		

	}
	
}
