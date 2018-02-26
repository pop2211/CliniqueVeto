package main.java.fr.eni.clinique.dal.dao;

import java.util.List;

import main.java.fr.eni.clinique.bo.Animal;
import main.java.fr.eni.clinique.dal.exception.DaoException;

public interface AnimalDAO extends GenericDAO<Animal, Integer> {
	
	List<Animal> selectByNom(String marque) throws DaoException;
    

}
