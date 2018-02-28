package fr.eni.clinique.ihm.controller;

import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.event.PersonnelActionEvent;
import fr.eni.clinique.ihm.listener.PersonnelActionListener;
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

	public void deletePersonnel(PersonnelActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void savePersonnel(PersonnelActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public Boolean connectPersonnel(Personnel personnel) throws Exception {
		return personnelManager.connexion(personnel);
	}
	
}
