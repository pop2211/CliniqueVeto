package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.exception.DaoException;

public interface RaceDAO extends GenericDAO<Race, String> {
	
	void deleteRace(String race, String espece) throws DaoException;
	
	List<Race> selectByEspece(String espece) throws DaoException;

}
