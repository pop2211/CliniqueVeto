package fr.eni.clinique.bll.manager;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Animal;

public interface AnimalManager extends GenericManager<Animal, Integer>{
	
	List<Animal> selectByMaitre(Integer codeClient) throws ManagerException;
}
