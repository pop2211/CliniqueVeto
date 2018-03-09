package fr.eni.clinique.ihm.controller;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.PersonnelManager;
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
		
	}
	
	public Personnel selectPersonnel(Integer codePers) throws ManagerException{
		return personnelManager.selectById(codePers);
	}

	public void newPersonnel(Personnel personnel) throws ManagerException{
		personnelManager.insert(personnel);
	}
	
	public void deletePersonnel(Integer codePers) throws ManagerException{
		personnelManager.delete(codePers);
	}
	
	public void changePswPersonnel(Integer codePerso ,String mdp) throws ManagerException{
		personnelManager.updatePasswordByCode(codePerso, mdp);
	}
	
	
	public void savePersonnel(Personnel personnel) throws ManagerException{
		personnelManager.update(personnel);
	}
	
	public Personnel connectPersonnel(Personnel personnel) throws Exception {
		return personnelManager.connexion(personnel);
	}
	
	public List<Personnel> loadPersonnelByRole(String role) throws ManagerException {
		return personnelManager.selectByRole(role);
	}


	
	
}
