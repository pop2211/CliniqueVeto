package fr.eni.clinique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bo.Animal;
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
		
		
		

	}
	
}
