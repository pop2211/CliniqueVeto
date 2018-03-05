package fr.eni.clinique.bll.manager;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Race;

public interface RaceManager extends GenericManager<Race, String>{

	List<String> selectByEspece(String espece) throws ManagerException;;

}
