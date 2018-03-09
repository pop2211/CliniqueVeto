package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.JdbcTools;

public class AnimalJDBCDAOImpl implements AnimalDAO{
	
	private static final String NOT_ARCHIVE = " AND Archive<>1";
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Animaux WHERE CodeAnimal=?"+ NOT_ARCHIVE;
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Animaux WHERE 1=1"+ NOT_ARCHIVE;
	private static final String UPDATE_QUERY = "UPDATE Animaux SET NomAnimal=?, Sexe=?, Couleur=?, Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archive=? WHERE CodeAnimal=?";
    private static final String INSERT_QUERY = "INSERT INTO Animaux(NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "UPDATE Animaux SET Archive=1 WHERE CodeAnimal=?";
    private static final String TRUNCATE_QUERY = "DELETE FROM Animaux; DBCC CHECKIDENT(Animaux, RESEED,0);";
    private static final String SELECT_BY_MAITRE = "SELECT * FROM Animaux WHERE CodeClient=?"+ NOT_ARCHIVE;;
    
    private static AnimalJDBCDAOImpl instance;
    private static ClientJDBCDAOImpl ClientDAO;
    
	public static AnimalDAO getInstance() {
		if(instance == null) {
            instance = new AnimalJDBCDAOImpl();
        }
        return instance;
	}
	
	@Override
	public Animal selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Animal animal = null;
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
				animal = resultSetEntryToAnimal(resultSet);
            }

        } catch(Exception e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        return animal;
	}

	private Animal resultSetEntryToAnimal(ResultSet resultSet) throws Exception{
		Animal animal = new Animal();
		
		Integer codeClient = resultSet.getInt("CodeClient");
		
		ClientDAO = new ClientJDBCDAOImpl();
		
		Client client = ClientDAO.selectById(codeClient);
		
		animal.setCodeAnimal(resultSet.getInt("CodeAnimal"));
		animal.setNomAnimal(resultSet.getString("NomAnimal"));
		animal.setSexe(resultSet.getString("Sexe"));
		animal.setCouleur(resultSet.getString("Couleur"));
		animal.setRace(new Race(resultSet.getString("Race"),resultSet.getString("Espece")));
		animal.setClient(client);
		animal.setTatouage(resultSet.getString("Tatouage"));
		animal.setAntecedents(resultSet.getString("Antecedents"));
		animal.setArchive(resultSet.getBoolean("Archive"));
        
        
        return animal;
	}

	@Override
	public Animal insert(Animal animal) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcTools.get();
            
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, animal.getNomAnimal());
            statement.setString(2, animal.getSexe());
            statement.setString(3, animal.getCouleur());
            statement.setString(4, animal.getRace().getRace());
            statement.setString(5, animal.getRace().getEspece());
            statement.setInt(6, animal.getClient().getCodeClient());
            statement.setString(7, animal.getTatouage());
            statement.setString(8, animal.getAntecedents());
            statement.setBoolean(9, animal.isArchive());
            
            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    animal.setCodeAnimal(resultSet.getInt(1));
                }
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
		return animal;
	}

	@Override
	public void update(Animal animal) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        try {
	    	connection = JdbcTools.get();
	         
	        statement = connection.prepareStatement(UPDATE_QUERY);
	        
            statement.setString(1, animal.getNomAnimal());
            statement.setString(2, animal.getSexe());
            statement.setString(3, animal.getCouleur());
            statement.setString(4, animal.getRace().getRace());
            statement.setString(5, animal.getRace().getEspece());
            statement.setInt(6, animal.getClient().getCodeClient());
            statement.setString(7, animal.getTatouage());
            statement.setString(8, animal.getAntecedents());
            statement.setBoolean(9, animal.isArchive());
            statement.setInt(10, animal.getCodeAnimal());
            
			if (statement.executeUpdate() == 0) {
				throw new DaoException("Erreur update animal");
			}
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

	@Override
	public void delete(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = JdbcTools.get();
            
            // l'intégrité référentielle s'occupe d'invalider la suppression
            // si l'article est référencé dans une ligne de commande
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
		
	}

	@Override
	public List<Animal> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Animal> liste = new ArrayList<Animal>();
        
        try {
            connection = JdbcTools.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()) { 
				liste.add(resultSetEntryToAnimal(resultSet));	
            }
        } catch(Exception e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        
        return liste;
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

	public List<Animal> selectByMaitre(Integer codeClient) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Animal> liste = new ArrayList<Animal>();
        
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_MAITRE);
            statement.setInt(1, codeClient);
            
            resultSet = statement.executeQuery();
            while (resultSet.next()) { 
				liste.add(resultSetEntryToAnimal(resultSet));	
            }
        } catch(Exception e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        return liste;
	}
	
}
