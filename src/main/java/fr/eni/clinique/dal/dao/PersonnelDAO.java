package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.exception.DaoException;

public interface PersonnelDAO extends GenericDAO<Personnel, Integer> {

	List<Personnel> selectByNom(String nom) throws DaoException;
	
	List<Personnel> selectByNomMdp(String nom,String mdp) throws DaoException;
	
	List<Personnel> selectByRole(String role) throws DaoException;
	
}
