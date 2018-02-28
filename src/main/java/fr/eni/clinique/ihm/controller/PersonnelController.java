package fr.eni.clinique.ihm.controller;

import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.ihm.event.PersonnelActionEvent;
import fr.eni.clinique.ihm.listener.PersonnelActionListener;
import fr.eni.clinique.ihm.model.PersonnelModel;

public class PersonnelController implements PersonnelActionListener {

    private PersonnelModel model;
    private PersonnelManager personnelManager = ManagerFactory.PersonnelManager();

    /**
     * @param model
     */
    public PersonnelController(PersonnelModel model) {
        super();
        this.model = model;
    }

    /*
    @Override
    public void init() throws ManagerException {

        List<Personnel> personnels = personnelManager.getPersonnels();
        model.loadPersonnels(personnels);
    }

    @Override
    public void newPersonnel() {

        model.addPersonnel(new Personnel());
    }

    @Override
    public void deletePersonnel(PersonnelActionEvent event) throws ManagerException {

        if (event.getPersonnel().getCodePers() != null) {
            cataloguePersonnel.removePersonnel(event.getPersonnel());
        }

        model.removeCurrentArticle();
    }
    
    @Override
    public void savePersonnel(PersonnelActionEvent event) throws ManagerException {

        if (event.getPersonnel().getCodePersonnel() == null) { // Create

        	personnelManager.addPersonnel(event.getPersonnel());

        } else { // Update

        	personnelManager.updatePersonnel(event.getCodePers());
        }
        model.changeCurrentArticle(event.getArticle());
    }
    */

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newPersonnel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonnel(PersonnelActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePersonnel(PersonnelActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void connectPersonnel(PersonnelActionEvent event) throws Exception {
		System.out.println("Personnel Controler: connectPersonnel");
		
	}
	
}
