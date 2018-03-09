package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.exception.DaoException;

public interface RaceDAO extends GenericDAO<Race, String> {
	
	void deleteRace(String race, String espece) throws DaoException;
	
	List<String> selectByEspece(String espece) throws DaoException;
	
	List<String> selectEspeceDistinct() throws DaoException;

}
