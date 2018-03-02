package fr.eni.clinique.bll.manager.impl;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.RaceManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.DaoFactory;

public class RaceManagerImpl implements RaceManager{

	private RaceDAO raceDAO = DaoFactory.raceDAO();
    
	private static RaceManagerImpl instance;
	 
	public static RaceManagerImpl getInstance() {
		if(instance == null) {
			instance = new RaceManagerImpl();
		}
		return instance;
	}

	
	private void validerRace(Race race) throws ManagerException {

        try {
            ObjectUtil.checkNotNullWithMessage(race, "Une erreur technique st survenue");
            ObjectUtil.checkNotNullWithMessage(race.getRace(), "Le Nom est obligatoire");
            ObjectUtil.checkNotNullWithMessage(race.getEspece(), "Le Prenom est obligatoire");

        } catch (IllegalArgumentException e) {
            throw new ManagerException(String.format("Erreur de validation : %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

	@Override
	public void update(Race race) throws ManagerException {
		try {
			validerRace(race);
            
			raceDAO.update(race);
            
        } catch (DaoException e) {
            throw new ManagerException(String.format("Echec updateRace-race: %s", race), e);
        }
	}

	@Override
	public void delete(String id) throws ManagerException {
		// TODO Auto-generated method stub
		return;
	}
	
	@Override
	public List<Race> selectAll() throws ManagerException {
		List<Race> races = null;
        
        try {
        	races = raceDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste de la race", e);
        }
        
        return races;
	}

	@Override
	public List<String> selectByEspece(String espece) {
		List<String> race = null;
		try {
			race = raceDAO.selectByEspece(espece);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return race;
	}

	@Override
	public Race selectById(String id) throws ManagerException {
		return null;
	}


	@Override
	public Race insert(Race element) throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
}
