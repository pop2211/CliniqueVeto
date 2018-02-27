package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.JdbcTools;

public class RaceJDBCDAOImpl implements RaceDAO{
	
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Races";
	private static final String UPDATE_QUERY = "UPDATE Races SET Race=?, Espece=? WHERE Race = ? AND Espece = ?";
    private static final String INSERT_QUERY = "INSERT INTO Races(Race, Espece) VALUES (?,?)";
    private static final String DELETE_QUERY = "DELETE FROM Races WHERE Race = ? AND Espece = ?";
    //private static final String TRUNCATE_QUERY = "TRUNCATE TABLE Races";
    private static final String TRUNCATE_QUERY = "DELETE FROM Races";
    
    private static RaceJDBCDAOImpl instance;
    
	public static RaceDAO getInstance() {
		if(instance == null) {
            instance = new RaceJDBCDAOImpl();
        }
        return instance;
	}
	
	@Override
	public Race insert(Race race) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcTools.get();
            
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, race.getRace());
            statement.setString(2, race.getEspece());
            
            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                /*if (resultSet.next()) {
                	race.setRace(resultSet.getString(1));
                	race.setEspece(resultSet.getString(2));
                	
                }*/
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
		return race;
	}

	@Override
	public void update(Race race) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        try {
	    	connection = JdbcTools.get();
	         
	        statement = connection.prepareStatement(UPDATE_QUERY);
	        
            statement.setString(1, race.getRace());
            statement.setString(2, race.getEspece());
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

	@Override
	public void deleteRace(String race, String espece) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = JdbcTools.get();
            
            // l'intégrité référentielle s'occupe d'invalider la suppression
            // si l'article est référencé dans une ligne de commande
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setString(1, race);
            statement.setString(2, espece);
            statement.executeUpdate();
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

	@Override
	public List<Race> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Race> liste = new ArrayList<Race>();
        
        try {
            connection = JdbcTools.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()) { 
				liste.add(resultSetEntryToRace(resultSet));	
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        
        return liste;
	}
	
	private Race resultSetEntryToRace(ResultSet resultSet) throws SQLException{
		Race race = new Race();

		race.setRace(resultSet.getString("Race"));
		race.setEspece(resultSet.getString("Espece"));
        
        return race;
	}
	
	
	//useless
	@Override
	//select race by espece
	public Race selectById(String id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//useless
	@Override
	public void delete(String id) throws DaoException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        try {
            connection = JdbcTools.get();
            statement = connection.createStatement();
            statement.executeUpdate(TRUNCATE_QUERY);
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

}
