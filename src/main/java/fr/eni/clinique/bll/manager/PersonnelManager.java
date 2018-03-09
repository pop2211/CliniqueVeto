package fr.eni.clinique.bll.manager;


import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Personnel;

public interface PersonnelManager extends GenericManager<Personnel, Integer>{
	
	Personnel connexion(Personnel personnel) throws ManagerException;
	
	List<Personnel> selectByRole(String role) throws ManagerException;

	public void updatePasswordByCode(Integer codePersonnel, String mdp) throws ManagerException;

}
