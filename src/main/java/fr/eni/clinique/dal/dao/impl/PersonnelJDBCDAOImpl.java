package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.JdbcTools;

public class PersonnelJDBCDAOImpl implements PersonnelDAO{

	private static final String NOT_ARCHIVE = " AND Archive<>1";
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Personnels WHERE CodePers = ?"+ NOT_ARCHIVE;
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Personnels WHERE 1=1"+ NOT_ARCHIVE;
	private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM Personnels WHERE Nom = ?"+ NOT_ARCHIVE;
	private static final String SELECT_BY_NAME_PSW_QUERY = "SELECT * FROM Personnels WHERE Nom = ? AND MotPasse = ?"+ NOT_ARCHIVE;
	private static final String UPDATE_QUERY = "UPDATE Personnels SET Nom=?, MotPasse=?, Role=?, Archive=? WHERE CodePers=?";
    private static final String INSERT_QUERY = "INSERT INTO Personnels(Nom, MotPasse, Role, Archive) VALUES (?,?,?,?)";
    private static final String DELETE_QUERY = "UPDATE Personnels SET Archive=1 WHERE CodePers=?";
    private static final String TRUNCATE_QUERY = "DELETE FROM Personnels; DBCC CHECKIDENT(Personnels, RESEED, 0);";
    private static final String SELECT_BY_ROLE = "SELECT * FROM Personnels WHERE Role = ?"+ NOT_ARCHIVE;
	private static final String UPDATE_PSW_QUERY = "UPDATE Personnels Set MotPasse=? WHERE CodePers=?";
    
    private static PersonnelJDBCDAOImpl instance;
    
	public static PersonnelDAO getInstance() {
		if(instance == null) {
            instance = new PersonnelJDBCDAOImpl();
        }
        return instance;
	}
	
	@Override
	public Personnel selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Personnel personnel = null;
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	personnel = resultSetEntryToPersonnel(resultSet);
				// TODO Auto-generated catch block
            }

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        return personnel;
	}

	private Personnel resultSetEntryToPersonnel(ResultSet resultSet) throws SQLException{
		Personnel personnel = new Personnel();

		personnel.setCodePers(resultSet.getInt("CodePers"));
		personnel.setNom(resultSet.getString("Nom"));
		personnel.setMdp(resultSet.getString("MotPasse"));
		personnel.setRole(resultSet.getString("Role"));
		personnel.setArchive(resultSet.getBoolean("Archive"));
        
        return personnel;
	}

	@Override
	public Personnel insert(Personnel personnel) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcTools.get();
            
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, personnel.getNom());
            statement.setString(2, personnel.getMdp());
            statement.setString(3, personnel.getRole());
            statement.setBoolean(4, personnel.isArchive());
            
            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                	personnel.setCodePers(resultSet.getInt(1));
                }
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
		return personnel;
	}

	@Override
	public void update(Personnel personnel) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        try {
	    	connection = JdbcTools.get();
	         
	        statement = connection.prepareStatement(UPDATE_QUERY);
	        
            statement.setString(1, personnel.getNom());
            statement.setString(2, personnel.getMdp());
            statement.setString(3, personnel.getRole());
            statement.setBoolean(4, personnel.isArchive());
            
            statement.setInt(5, personnel.getCodePers());
			if (statement.executeUpdate() == 0) {
				throw new DaoException("Erreur update personnel");
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
	
	public void updateByCode(Integer codePersonnel, String mdp) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        try {
	    	connection = JdbcTools.get();
	         
	        statement = connection.prepareStatement(UPDATE_PSW_QUERY);
	        
	        statement.setString(1, mdp);
            statement.setInt(2, codePersonnel);
	        
            
			if (statement.executeUpdate() == 0) {
				throw new DaoException("Erreur update personnel");
			}
            
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

	@Override
	public List<Personnel> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Personnel> liste = new ArrayList<Personnel>();
        
        try {
            connection = JdbcTools.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()) { 
				liste.add(resultSetEntryToPersonnel(resultSet));	
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        
        return liste;
	}
	

	@Override
	public List<Personnel> selectByNom(String nom) throws DaoException {
		
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Personnel> liste = new ArrayList<Personnel>();
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_NAME_QUERY);
            statement.setString(1, nom);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                liste.add(resultSetEntryToPersonnel(resultSet));
            }
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
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

	@Override
	public List<Personnel> selectByNomMdp(String nom, String mdp) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Personnel> liste = new ArrayList<Personnel>();
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_NAME_PSW_QUERY);
            statement.setString(1, nom);
            statement.setString(2, mdp);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                liste.add(resultSetEntryToPersonnel(resultSet));
            }
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
        
        return liste;
	}

	@Override
	public List<Personnel> selectByRole(String role) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Personnel> liste = new ArrayList<Personnel>();
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_ROLE);
            statement.setString(1, role);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                liste.add(resultSetEntryToPersonnel(resultSet));
            }
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
        
        return liste;
	}


}
