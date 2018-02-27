package fr.eni.clinique.dal.dao.impl;

import java.util.List;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.exception.DaoException;

public class RaceJDBCDAOImpl implements RaceDAO{
    
    private static RaceJDBCDAOImpl instance;
    
	public static RaceDAO getInstance() {
		if(instance == null) {
            instance = new RaceJDBCDAOImpl();
        }
        return instance;
	}
	
	@Override
	public Race insert(Race element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Race element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	//select race by espece
	public Race selectById(String id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Race> selectAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
