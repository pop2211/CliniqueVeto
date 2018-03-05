package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.exception.DaoException;

public interface ClientDAO extends GenericDAO<Client, Integer> {
	
	/**
	 * Get all elements.
	 * @param search 
	 * 
	 * @return The Elements found.
	 * 
	 * @throws DaoException
	 */
	List<Client> selectSearch(String search) throws DaoException;
	
}
