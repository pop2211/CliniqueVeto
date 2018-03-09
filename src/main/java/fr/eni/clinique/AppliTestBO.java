package fr.eni.clinique;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.bo.Rdv;

public class AppliTestBO {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestBO.class);
    
	public static void main(String[] args) {

		Race race = new Race("pasderace", "bambie");
		Race race2 = new Race("lapin", "civet");
		
		Client client = new Client(5, "lagaffe", "vincent", "8 rue de lala", "bis", "49000", "Angers", "0606060606", "maf", "aze@rty.fr", "ne paye pas", 0, false);
		
		Animal animal = new Animal(1, "MamandeBambi", "h", "bleu", "symbole", "alcoolique", false , race , client);
		Animal animal2 = new Animal(2, "Mamandepanpan", "h", "vert", "flingue", "suicidaire", false, race2 , client);

		List<Animal> animaux = new ArrayList<Animal>();
		animaux.add(animal);
		animaux.add(animal2);
		
		LOGGER.info("Test class Animal et Race");
		LOGGER.info(animal.toString());
		LOGGER.info(animal.getCouleur());
		animal.setCouleur("rose");
		LOGGER.info(animal.getCouleur());
		
		LOGGER.info("Test class Client");
		LOGGER.info(client.toString());
		
		LOGGER.info("Test class Personel");
		Personnel personel = new Personnel(1, "soignetoutou", "bambie", "veto", false );
		LOGGER.info(personel.toString());
		
		LOGGER.info("Test class Rdv");
		java.util.Date date= new java.util.Date();
        Timestamp dateRdv = new Timestamp(date.getTime());
		Rdv rdv = new Rdv(personel, dateRdv, animal);
		LOGGER.info(rdv.toString());

	}
	
}
