package fr.eni.clinique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

public class AppliTestBLL {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestBLL.class);
	
	PersonnelManager personnelManager = PersonnelManager.catalogManager();
	
	public static void main(String[] args) {

		try {
			
		Personnel c = new Personnel("jaja", "mdp");
		
			
		} catch (ManagerException e) {
            LOGGER.error("ERROR", e);
            e.printStackTrace();
        }
		}
}
