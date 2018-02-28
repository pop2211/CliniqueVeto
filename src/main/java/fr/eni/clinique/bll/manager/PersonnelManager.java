package fr.eni.clinique.bll.manager;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Personnel;

public interface PersonnelManager {
	
	List<Personnel> getList() throws ManagerException;
    
	Personnel addPersonnel(Personnel newPersonnel) throws ManagerException;
    
    void removePersonnel(Personnel personnel) throws ManagerException;

	void connexion(Personnel personnel) throws ManagerException;

	void updatePersonnel(Personnel personnel) throws ManagerException;
}
