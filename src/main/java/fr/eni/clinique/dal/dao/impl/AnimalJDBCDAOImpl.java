package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.MSSQLConnectionFactory;

public class AnimalJDBCDAOImpl implements AnimalDAO{
	
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Animaux WHERE codeAnimal = ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Animaux";
	private static final String UPDATE_QUERY = "UPDATE Animaux SET NomAnimal=?, Sexe=?, Couleur=?, Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archives=? WHERE codeAnimal=?";
    private static final String INSERT_QUERY = "INSERT INTo Animaux(NomAnimal=?, Sexe=?, Couleur=?, Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archives=?) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM Animaux WHERE codeAnimal=?";
	public static AnimalDAO getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Animal selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Animal animal = null;
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	try {
					animal = resultSetEntryToAnimal(resultSet);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        return animal;
	}

	private Animal resultSetEntryToAnimal(ResultSet resultSet) throws Exception {
		Animal animal = new Animal();

		animal.setNomAnimal(resultSet.getString("Designation"));
		animal.setSexe(resultSet.getString("Sexe"));
		animal.setCouleur(resultSet.getString("Couleur"));
		animal.setRace(new Race(resultSet.getString("Race"),resultSet.getString("Espece")));
		animal.setCodeClient(resultSet.getInt("CodeClient"));
		animal.setTatouage(resultSet.getString("Tatouage"));
		animal.setAntecedants(resultSet.getString("Antecedants"));
		animal.setArchive(resultSet.getByte("Archive"));
        
        
        return animal;
	}

	@Override
	public Animal insert(Animal animal) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, animal.getNomAnimal());
            statement.setString(2, animal.getSexe());
            statement.setString(3, animal.getCouleur());
            statement.setString(4, animal.getRace().getRace());
            statement.setString(5, animal.getRace().getEspece());
            statement.setInt(6, animal.getCodeClient());
            statement.setString(7, animal.getTatouage());
            statement.setString(8, animal.getAntecedants());
            statement.setByte(9, animal.getArchive());
            
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
	    	connection = MSSQLConnectionFactory.get();
	         
	        statement = connection.prepareStatement(UPDATE_QUERY);
	        
            statement.setString(1, animal.getNomAnimal());
            statement.setString(2, animal.getSexe());
            statement.setString(3, animal.getCouleur());
            statement.setString(4, animal.getRace().getRace());
            statement.setString(5, animal.getRace().getEspece());
            statement.setInt(6, animal.getCodeClient());
            statement.setString(7, animal.getTatouage());
            statement.setString(8, animal.getAntecedants());
            statement.setByte(9, animal.getArchive());
            
            
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
            connection = MSSQLConnectionFactory.get();
            
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
            connection = MSSQLConnectionFactory.get();
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


}
