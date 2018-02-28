package fr.eni.clinique.bll.manager;


import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Personnel;

public interface PersonnelManager extends GenericManager<Personnel, Integer>{
	
	void connexion(Personnel personnel) throws ManagerException;

}
