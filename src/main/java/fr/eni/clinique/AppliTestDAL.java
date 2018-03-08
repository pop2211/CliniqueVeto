package fr.eni.clinique;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

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

public class AppliTestDAL {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestDAL.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
            //TEST DAL
			
            RaceDAO raceDAO = new RaceJDBCDAOImpl();
            ClientDAO clientDAO = new ClientJDBCDAOImpl();
            AnimalDAO animalDAO = new AnimalJDBCDAOImpl();
            PersonnelDAO personnelDAO = new PersonnelJDBCDAOImpl();
            RdvDAO rdvDAO = new RdvJDBCDAOImpl();
            
            LOGGER.info("Check delete strat : {}");
            rdvDAO.deleteAll();
            personnelDAO.deleteAll();
            animalDAO.deleteAll();
            clientDAO.deleteAll();
            raceDAO.deleteAll();
            
            
            
            Race race = new Race("pasderace", "bambie");
            Race race2 = new Race("lapin", "civet");
            LOGGER.info("Check inserting Race : {}", raceDAO.insert(race));
    		LOGGER.info("Check inserting Race : {}", raceDAO.insert(race2));
    		Race race3 = new Race("Worchair", "Chat");
            LOGGER.info("Check inserting Race : {}", raceDAO.insert(race3));
            Race race4 = new Race("Chinoix", "Chat");
            LOGGER.info("Check inserting Race : {}", raceDAO.insert(race4));
            LOGGER.info("Check reading Race : {}", raceDAO.selectAll());
            
            Client client = new Client("lagaffe", "vincent", "8 rue de lala", "bis", "49000", "Angers", "0606060606", "maf", "aze@rty.fr", "ne pas", false);
            LOGGER.info("Check inserting Client : {}", clientDAO.insert(client));
            LOGGER.info("Check reading Client: {}", clientDAO.selectById(client.getCodeClient()));
            LOGGER.info("Check reading all Client : {}", clientDAO.selectAll());
            
            Client client2 = new Client("cli2", "nomcli2", "9 rue de lala", "bis", "75000", "Paris", "0707070707", "maf", "aze@rty.com", "remarque", false);
            LOGGER.info("Check inserting Client2 : {}", clientDAO.insert(client2));
            
            Animal animal = new Animal("MamandeBambi", "h", "bleu", "symbole", "alcoolique", false , race , client.getCodeClient());
    		LOGGER.info("Check inserting Animal : {}", animalDAO.insert(animal));
            LOGGER.info("Check reading Animal : {}", animalDAO.selectAll());
            

            
            Personnel u = new Personnel("u", "u", EnumRole.VET, false);
            LOGGER.info("Check inserting u : {}");
            personnelDAO.insert(u);
            
            
            
            Personnel admin = new Personnel("admin", "1", EnumRole.ADM, false);
            LOGGER.info("Check inserting admin : {}");
            personnelDAO.insert(admin); //new Personnel inserted, now code
            
            Personnel dev = new Personnel("dev", "1", EnumRole.DEV, false);
            LOGGER.info("Check inserting dev : {}");
            personnelDAO.insert(dev);
            
            Personnel secretaire = new Personnel("secretaire", "1", EnumRole.SEC, false);
            LOGGER.info("Check inserting secretaire : {}");
            personnelDAO.insert(secretaire);
            
            Personnel directeurtech = new Personnel("directeurtech", "1", EnumRole.SUP, false);
            LOGGER.info("Check inserting directeurtech : {}");
            personnelDAO.insert(directeurtech);
            
            Personnel assistant = new Personnel("assistant", "1", EnumRole.USR, false);
            LOGGER.info("Check inserting assistant : {}");
            personnelDAO.insert(assistant);
            Personnel stagiaire = new Personnel("stagiaire", "1", EnumRole.USR, false);
            LOGGER.info("Check inserting stagiaire : {}");
            personnelDAO.insert(stagiaire);
            
            Personnel veterinaire = new Personnel("veterinaire", "1", EnumRole.VET, false);
            LOGGER.info("Check inserting Veto : {}");
            personnelDAO.insert(veterinaire);	
            

            

            

            
            java.util.Date date= new java.util.Date();
            Timestamp dateRdv = new Timestamp(date.getTime());
            Rdv rdv = new Rdv(veterinaire, dateRdv, animal);
            LOGGER.info("Check inserting Rdv : {}", rdvDAO.insert(rdv));
            LOGGER.info("Check reading Rdv: {}", rdvDAO.selectById(rdv));
            LOGGER.info("Check reading all Rdv : {}", rdvDAO.selectAll());
            
            
            
            
//            LOGGER.info("Check delete end : {}");
//            rdvDAO.delete(rdv);
//            personnelDAO.delete(veto.getCodePers());
//            animalDAO.delete(animal.getCodeAnimal());
//            clientDAO.delete(client.getCodeClient());
//            clientDAO.delete(client.getCodeClient());
//            raceDAO.deleteRace(race.getRace(),race.getEspece());
//            raceDAO.deleteRace(race2.getRace(),race2.getEspece());
            
            
            
        } catch (DaoException e) {
            LOGGER.error("ERROR", e);
            e.printStackTrace();
        }
		
	}

}
