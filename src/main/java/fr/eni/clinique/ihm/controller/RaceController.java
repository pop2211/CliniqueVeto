package fr.eni.clinique.ihm.controller;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.RaceManager;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.ihm.model.RaceModel;

public class RaceController {

	 	private RaceModel model;
	    private RaceManager raceManager = ManagerFactory.RaceManager();

	    /**
	     * @param model
	     */
	    public RaceController(RaceModel model) {
	        super();
	        this.model = model;
	    }

		
		public void init() throws Exception {
			
		}

		
		public void newRace(Race race) throws ManagerException {
			raceManager.insert(race);
		}
		
		
		public void saveRace(Race race) throws Exception {
			raceManager.update(race);
		}
		
		
		public List<String> loadRace(String espece) throws ManagerException {
			return raceManager.selectByEspece(espece);
		}
	
}
