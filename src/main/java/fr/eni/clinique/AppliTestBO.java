package fr.eni.clinique;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personel;
import fr.eni.clinique.bo.Race;

public class AppliTestBO {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestBO.class);
    
	public static void main(String[] args) {

		Race r = new Race("papsderace", "bambie");
		Animal a = new Animal(1, "MamandeBambi", "h", "bleu", "symboledelinfini", "alcoolique", (byte) 0, r , 5);

		LOGGER.info("Test class Animal et Race");
		LOGGER.info(a.toString());
		LOGGER.info(a.getCouleur());
		a.setCouleur("rose");
		LOGGER.info(a.getCouleur());
		
		LOGGER.info("Test class Client");
		Client c = new Client(5, "lagaffe", "vincent", "8 rue de lala", "bis", "49000", "Angers", "0606060606", "maf", "aze@rty.fr", "ne paye pas", (byte)0);
		LOGGER.info(c.toString());
		
		LOGGER.info("Test class Personel");
		Personel p = new Personel(1, "soignetoutou", "bambie", "veto", (byte) 0 );
		LOGGER.info(p.toString());
		
		LOGGER.info("Test class Agenda");
		GregorianCalendar d = new GregorianCalendar(2018, 2, 25, 14, 15);
		Agenda a = new Agenda(p, d, a);
		

	}
	
}
