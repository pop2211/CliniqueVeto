package fr.eni.clinique;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

public class AppliTestBLL {

	
	
	public static void main(String[] args) {
		
		PersonnelManager persoManag = ManagerFactory.PersonnelManager();

		Personnel testLogin = new Personnel("jaja", "mdp");
		try {
			persoManag.connexion(testLogin);
		} catch (ManagerException e) {
		}
	}
	
	
}
