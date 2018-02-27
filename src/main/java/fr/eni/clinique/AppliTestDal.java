package fr.eni.clinique;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.impl.AnimalJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.ClientJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RaceJDBCDAOImpl;
import fr.eni.clinique.dal.exception.DaoException;

public class AppliTestDal {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestDal.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
            //TEST serialisation
            RaceDAO raceDAO = new RaceJDBCDAOImpl();
            ClientDAO clientDAO = new ClientJDBCDAOImpl();
            AnimalDAO animalDAO = new AnimalJDBCDAOImpl();
            
            Race race = new Race("pasderace", "bambie");
            Race race2 = new Race("lapin", "civet");
            
            Animal animal = new Animal("MamandeBambi", "h", "bleu", "symbole", "alcoolique", false , race , 1);

            Client client = new Client("lagaffe", "vincent", "8 rue de lala", "bis", "49000", "Angers", "0606060606", "maf", "aze@rty.fr", "ne paye pas", false);
            
            /*
            LOGGER.info("Check inserting Race : {}", raceDAO.insert(race));
    		LOGGER.info("Check inserting Race : {}", raceDAO.insert(race2));
            LOGGER.info("Check reading Race : {}", raceDAO.selectAll());
            */
            
            
            LOGGER.info("Check inserting Client : {}", clientDAO.insert(client));
            LOGGER.info("Check reading Client: {}", clientDAO.selectById(client.getCodeClient()));
            LOGGER.info("Check reading all Client : {}", clientDAO.selectAll());
            
            
            /*
            LOGGER.info("Check inserting Eleve : {}", animalDAO.insert(animal));
            LOGGER.info("Check reading Eleve 001: {}", animalDAO.selectById(animal.getCodeAnimal()));
            LOGGER.info("Check reading all Eleve : {}", animalDAO.selectAll());
            */
          
            /*
            animalDAO.delete(1);
            clientDAO.delete(1);
            raceDAO.deleteRace(race.getRace(),race.getEspece());
            */
            
        } catch (DaoException e) {
            LOGGER.error("ERROR", e);
            e.printStackTrace();
        }
		
	}

}
