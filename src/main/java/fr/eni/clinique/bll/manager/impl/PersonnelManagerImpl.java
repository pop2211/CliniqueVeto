package fr.eni.clinique.bll.manager.impl;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;

public class PersonnelManagerImpl implements PersonnelManager{

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
	
	public void Connexion(Personnel Personnel) throws ManagerException {

        try {
        	
        	ObjectUtil.checkNotBlankWithMessage(Personnel.getNom(), "Le login est obligatoire");
            ObjectUtil.checkNotBlankWithMessage(Personnel.getMdp(), "Le mot de passe est obligatoire");
        	
        } catch (IllegalArgumentException e) {
            throw new ManagerException(String.format("Erreur de Connexion : %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage(), e);
        }
     }

}
