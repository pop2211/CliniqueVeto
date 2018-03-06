package fr.eni.clinique.bll.manager.impl;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.DaoFactory;


public class PersonnelManagerImpl implements PersonnelManager {
	
	private PersonnelDAO personnelDAO = DaoFactory.personelDAO();
	    
	private static PersonnelManagerImpl instance;
	 
	public static PersonnelManagerImpl getInstance() {
		if(instance == null) {
			instance = new PersonnelManagerImpl();
		}
		return instance;
	}

	@Override
	public List<Personnel> selectAll() throws ManagerException {
		List<Personnel> personnels = null;
        
        try {
        	personnels = personnelDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste du personnel", e);
        }
        
        return personnels;
	}

	@Override
	public Personnel insert(Personnel newPersonnel) throws ManagerException {
		if(newPersonnel.getCodePers() != null) {
            throw new ManagerException("La personne est deja existante.");
        }
        
        try {
        	validerPersonnel(newPersonnel);
            
        	newPersonnel = personnelDAO.insert(newPersonnel);
            
        } catch (DaoException e) {
            throw new ManagerException("Echec addPersonnel", e);
        }
        return newPersonnel;
	}
	
	 @Override
	    public void update(Personnel personnel) throws ManagerException {
	        
	        try {
	        	validerPersonnel(personnel);
	            
	        	personnelDAO.update(personnel);
	            
	        } catch (DaoException e) {
	            throw new ManagerException(String.format("Echec updatePersonnel-personnel: %s", personnel), e);
	        }
	    }

	

	@Override
	public void delete(Integer codePers) throws ManagerException {
		 try {
	            personnelDAO.delete(codePers);
	            
	        } catch (DaoException e) {
	            throw new ManagerException("Echec de suppression de la personne - ", e);
	        }
		
	}
	
	public Boolean connexion(Personnel personnel) throws ManagerException {
		Boolean testCo = false;
        try {
        	
        	ObjectUtil.checkNotBlankWithMessage(personnel.getNom(), "Le login est obligatoire");
            ObjectUtil.checkNotBlankWithMessage(personnel.getMdp(), "Le mot de passe est obligatoire");
            
            List<Personnel> personnel_nom = personnelDAO.selectByNom(personnel.getNom());
            if(!personnel_nom.isEmpty()) {
            	List<Personnel> personnel_nom_mdp = personnelDAO.selectByNomMdp(personnel.getNom(), personnel.getMdp());
            	if(personnel_nom_mdp.isEmpty() || personnel_nom_mdp.size() != 1) {
            		throw new IllegalArgumentException("Le mot de passe est incorrecte");
            	}
            	else {
            		testCo = true;
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
        return testCo;
     }
	
	private void validerPersonnel(Personnel personnel) throws ManagerException {

        try {
            ObjectUtil.checkNotNullWithMessage(personnel, "Une erreur technique st survenue");
            ObjectUtil.checkNotNullWithMessage(personnel.getNom(), "Le Nom est obligatoire");
            ObjectUtil.checkNotNullWithMessage(personnel.getMdp(), "Le MDP est obligatoire");
            ObjectUtil.checkNotBlankWithMessage(personnel.getRole(), "Le Rôle est obligatoire");
            ObjectUtil.checkNotNullWithMessage(personnel.isArchive(), "L'Archive est obligatoire");

        } catch (IllegalArgumentException e) {
            throw new ManagerException(String.format("Erreur de validation : %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

	@Override
	public Personnel selectById(Integer id) throws ManagerException {
		
		Personnel personne = new Personnel();
		
		try {
			personne = personnelDAO.selectById(id);
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste du personnel", e);
        }
		return personne;
        
	}

	@Override
	public List<Personnel> selectByRole(String role) throws ManagerException {
		List<Personnel> personnels = null;
		
		try {
			personnels = personnelDAO.selectByRole(role);
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération liste du personnel par Role", e);
        }
		return personnels;
	}



}
