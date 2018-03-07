package fr.eni.clinique.bll.manager;


import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.exception.DaoException;

public interface PersonnelManager extends GenericManager<Personnel, Integer>{
	
	Personnel connexion(Personnel personnel) throws ManagerException;
	
	List<Personnel> selectByRole(String role) throws ManagerException;
	
	public void updateByCode(Integer codePersonnel, String mdp) throws ManagerException;

}
