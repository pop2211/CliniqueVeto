package fr.eni.clinique.ihm.controller;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.model.PersonnelModel;

public class PersonnelController{

    private PersonnelModel model;
    private PersonnelManager personnelManager = ManagerFactory.PersonnelManager();

    /**
     * @param model
     */
    public PersonnelController(PersonnelModel model) {
        super();
        this.model = model;
    }

	
	public void init() throws Exception {
		System.out.println("Personnel Controler: init()");
		
	}

	
	public void newPersonnel() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Boolean connectPersonnel(Personnel personnel) throws Exception {
		return personnelManager.connexion(personnel);
	}
	
	public List<Personnel> loadPersonnelByRole(String role) throws ManagerException {
		return personnelManager.selectByRole(role);
	} 
	
}
