package fr.eni.clinique.ihm.controller;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.RdvManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.ihm.model.AnimalModel;
import fr.eni.clinique.ihm.model.RdvModel;

public class RdvController {

    private RdvModel model;
    private RdvManager rdvManager = ManagerFactory.RdvManager();
	
    public RdvController(RdvModel model) {
        super();
        this.model = model;
    }
    
    public void newRdv(Rdv rdv) throws ManagerException{
    	rdvManager.insert(rdv);
	}
    
    public List<Rdv> loadRdvVetJour(Integer codeAnimal, String date) throws ManagerException {
		return rdvManager.selectByVetAndDate(codeAnimal, date);
	}

	public void remove(Rdv rdv) {
		try {
			rdvManager.deleteRdv(rdv);
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}

}
