package fr.eni.clinique;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.EnumRole;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.dao.impl.AnimalJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.ClientJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.PersonnelJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RaceJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RdvJDBCDAOImpl;
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
            PersonnelDAO personnelDAO = new PersonnelJDBCDAOImpl();
            RdvDAO rdvDAO = new RdvJDBCDAOImpl();
            
            Race race = new Race("pasderace", "bambie");
            Race race2 = new Race("lapin", "civet");
            
            Animal animal = new Animal("MamandeBambi", "h", "bleu", "symbole", "alcoolique", false , race , 1);

            Client client = new Client("lagaffe", "vincent", "8 rue de lala", "bis", "49000", "Angers", "0606060606", "maf", "aze@rty.fr", "ne paye pas", false);
            
            Personnel veto = new Personnel("leveterinaire", "123456", EnumRole.VETERINAIRE.getCode(), false);
            
            GregorianCalendar dateRdv = new GregorianCalendar();
            Rdv rdv = new Rdv(veto, dateRdv, animal);
            
            /*
            LOGGER.info("Check inserting Race : {}", raceDAO.insert(race));
    		LOGGER.info("Check inserting Race : {}", raceDAO.insert(race2));
            LOGGER.info("Check reading Race : {}", raceDAO.selectAll());
            */
            
            /*
            LOGGER.info("Check inserting Client : {}", clientDAO.insert(client));
            LOGGER.info("Check reading Client: {}", clientDAO.selectById(client.getCodeClient()));
            LOGGER.info("Check reading all Client : {}", clientDAO.selectAll());
            */
            
            
            LOGGER.info("Check inserting Rdv : {}", rdvDAO.insert(rdv));
            //LOGGER.info("Check reading Rdv: {}", rdvDAO.selectById(rendezVous));
            LOGGER.info("Check reading all Rdv : {}", rdvDAO.selectAll());
            
          
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
