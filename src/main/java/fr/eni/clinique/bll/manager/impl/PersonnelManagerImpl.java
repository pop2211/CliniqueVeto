package fr.eni.clinique.bll.manager.impl;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.factory.DaoFactory;

public class PersonnelManagerImpl implements PersonnelManager{
	
	private PersonnelDAO personnelDAO = DaoFactory.personelDAO();
	    
	private static PersonnelManagerImpl instance;
	 
	public static PersonnelManagerImpl getInstance() {
		if(instance == null) {
			instance = new PersonnelManagerImpl();
		}
		return instance;
	}

	@Override
	public List<Personnel> getList() throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnel addPersonnel(Personnel newPersonnel)
			throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Personnel(Personnel personnel) throws ManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePersonnel(Personnel personnel) throws ManagerException {
		// TODO Auto-generated method stub
		
	}
	
	public void Connexion(Personnel personnel) throws ManagerException {
		
        try {
        	
        	ObjectUtil.checkNotBlankWithMessage(personnel.getNom(), "Le login est obligatoire");
            ObjectUtil.checkNotBlankWithMessage(personnel.getMdp(), "Le mot de passe est obligatoire");
            
            List<Personnel> personnel_nom = personnelDAO.selectByNom(personnel.getNom());
            if(!personnel_nom.isEmpty()) {
            	List<Personnel> personnel_nom_mdp = personnelDAO.selectByNomMdp(personnel.getNom(), personnel.getMdp());
            	if(personnel_nom_mdp.isEmpty() || personnel_nom_mdp.size() != 1) {
            		throw new IllegalArgumentException("Le mot de passe est incorrecte");
            	}
            }
            else{
            	throw new IllegalArgumentException("Le nom d'utilisateur n'existe pas");
            }
        	
        } catch (IllegalArgumentException e) {
            throw new ManagerException(String.format("Erreur de Connexion : %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage(), e);
        }
     }

}
