package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.common.util.StringUtil;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.JdbcTools;



public class RdvJDBCDAOImpl implements RdvDAO{
	
	//Il est imp�ratif de respecter la structure des tables et le sch�ma de la base fournis en annexe.
	//=> pas d'id rendez vous
	
	//private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Agendas WHERE CodeRdv = ?";
	private static final String SELECT_BY_CODEVETO_DATERDV_CODEANIMAL_QUERY = "SELECT * FROM Agendas WHERE CodeVeto=? AND DateRdv=? AND CodeAnimal=?";
	private static final String SELECT_BY_VET_AND_DATE = "SELECT * FROM Agendas WHERE CodeVeto=? AND datediff(day, DateRdv, ?) = 0";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Agendas";
	private static final String UPDATE_QUERY = "UPDATE Agendas SET CodeVeto=?, DateRdv=?, CodeAnimal=? WHERE CodeVeto=? AND DateRdv=? AND CodeAnimal=?";
    private static final String INSERT_QUERY = "INSERT INTO Agendas(CodeVeto, DateRdv, CodeAnimal) VALUES (?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM Agendas WHERE CodeVeto=? AND DateRdv=? AND CodeAnimal=?";
    //private static final String TRUNCATE_QUERY = "TRUNCATE TABLE Agendas";
    private static final String TRUNCATE_QUERY = "DELETE FROM Agendas";
    
    private static RdvJDBCDAOImpl instance;
    private static PersonnelJDBCDAOImpl PersonnelDAO;
    private static AnimalJDBCDAOImpl AnimalDAO;
    
	public static RdvDAO getInstance() {
		if(instance == null) {
            instance = new RdvJDBCDAOImpl();
            PersonnelDAO = new PersonnelJDBCDAOImpl();
            AnimalDAO = new AnimalJDBCDAOImpl(); 
        }
        return instance;
	}
    
	
	@Override
	public Rdv selectById(Rdv rdv) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Rdv rendezVous = null;
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_CODEVETO_DATERDV_CODEANIMAL_QUERY);
            
            statement.setInt(1, rdv.getVeto().getCodePers()); 
            statement.setTimestamp(2, rdv.getDateRdv());
            statement.setInt(3, rdv.getAnimal().getCodeAnimal());
            

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	try {
            		rendezVous = resultSetEntryToRdv(resultSet);
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
        return rendezVous;
	}

	private Rdv resultSetEntryToRdv(ResultSet resultSet) throws Exception {
		Rdv rdv = new Rdv();
		
	    Timestamp calendar = resultSet.getTimestamp("DateRdv");
		Integer codeVeto = resultSet.getInt("CodeVeto");
		Integer codeAnimal = resultSet.getInt("CodeAnimal");
		
		Personnel veto = PersonnelDAO.selectById(codeVeto);
		Animal animal = AnimalDAO.selectById(codeAnimal);
		
		rdv.setVeto(veto);
		rdv.setDateRdv(calendar);
		rdv.setAnimal(animal);
        
        return rdv;
	}

	@Override
	public Rdv insert(Rdv rdv) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcTools.get();
            
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
	        statement.setInt(1, rdv.getVeto().getCodePers());
			statement.setTimestamp(2, rdv.getDateRdv());
			statement.setInt(3, rdv.getAnimal().getCodeAnimal());

            if (statement.executeUpdate() == 0) {
            	throw new DaoException("insert fail");
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
		return rdv;
	}

	@Override
	public void update(Rdv rdv) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        try {
	    	connection = JdbcTools.get();
	        
	        statement = connection.prepareStatement(UPDATE_QUERY);
	        
	        statement.setInt(1, rdv.getVeto().getCodePers());
			statement.setTimestamp(2, rdv.getDateRdv());
			statement.setInt(3, rdv.getAnimal().getCodeAnimal());
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

	@Override
	public void delete(Rdv rdv) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = JdbcTools.get();
            
            // l'integrite referentielle s'occupe d'invalider la suppression
            // si l'element est reference dans une ligne de commande
            statement = connection.prepareStatement(DELETE_QUERY);
            
	        statement.setInt(1, rdv.getVeto().getCodePers());
			statement.setTimestamp(2, rdv.getDateRdv());
			statement.setInt(3, rdv.getAnimal().getCodeAnimal());
            
            statement.executeUpdate();
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
		
	}

	

	@Override
	public List<Rdv> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Rdv> liste = new ArrayList<Rdv>();
        
        try {
            connection = JdbcTools.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()) { 
				liste.add(resultSetEntryToRdv(resultSet));	
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


	@Override
	public List<Rdv> selectByVetAndDate(Integer codePersonne, String date) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Rdv> listeRendezVous = new ArrayList<Rdv>();
        try {
            connection = JdbcTools.get();
            statement = connection.prepareStatement(SELECT_BY_VET_AND_DATE);
            
            statement.setInt(1, codePersonne); 
            statement.setString(2, date);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	try {
            		listeRendezVous.add(resultSetEntryToRdv(resultSet));
				} catch (Exception e) {
					e.printStackTrace();
				}
            }

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement, resultSet);
        }
        return listeRendezVous;
	}


}
