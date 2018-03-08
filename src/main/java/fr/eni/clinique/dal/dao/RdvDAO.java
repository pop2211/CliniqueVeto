package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dal.exception.DaoException;

public interface RdvDAO extends GenericDAO<Rdv, Rdv> {
	
	List<Rdv> selectByVetAndDate(Integer codePersonne, String Date) throws DaoException;
}
