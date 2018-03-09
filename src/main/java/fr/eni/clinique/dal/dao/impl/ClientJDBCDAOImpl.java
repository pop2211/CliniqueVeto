package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.common.util.SqlUtil;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.JdbcTools;

public class ClientJDBCDAOImpl implements ClientDAO {

	private static final String NOT_ARCHIVE = " AND Archive<>1";
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Clients WHERE codeClient=?"+ NOT_ARCHIVE;
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Clients WHERE 1=1"+ NOT_ARCHIVE;
	private static final String UPDATE_QUERY = "UPDATE Clients SET NomClient=?, PrenomClient=?, Adresse1=?, Adresse2=?, CodePostal=?, Ville=?, NumTel=?, Assurance=?, Email=?, Remarque=?, Archive=? WHERE codeClient=?";
	private static final String INSERT_QUERY = "INSERT INTO Clients(NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_QUERY = "UPDATE Clients SET Archive=1 WHERE codeClient=?; UPDATE Animaux SET Archive=1 FROM Animaux a JOIN Clients c ON a.CodeClient = c.CodeClient WHERE c.Archive=1;";
	private static final String TRUNCATE_QUERY = "DELETE FROM Clients; DBCC CHECKIDENT(Clients, RESEED, 0);";
	
	//private static final String SEARCH_QUERY = "SELECT * FROM Clients WHERE (NomClient LIKE ? OR PrenomClient LIKE ?)"+ NOT_ARCHIVE;
	private static final String SEARCH_QUERY = "SELECT c1.*, res.NbAnimaux FROM Clients c1"
	+" LEFT JOIN ("
		+" SELECT c2.CodeClient, COUNT(a.CodeAnimal) AS NbAnimaux FROM Clients c2"
		+" JOIN Animaux a ON a.CodeClient = c2.CodeClient"
		+" WHERE a.Archive<>1" //ANIMAL NOT ARCHIVE
		+" GROUP BY c2.CodeClient"
	+" ) res ON res.CodeClient = c1.CodeClient"
	+" WHERE (c1.NomClient LIKE ? OR c1.PrenomClient LIKE ?) AND c1.Archive<>1"; //CLIENT NOT ARCHIVE

	
	private static ClientJDBCDAOImpl instance;

	public static ClientDAO getInstance() {
		if (instance == null) {
			instance = new ClientJDBCDAOImpl();
		}
		return instance;
	}

	@Override
	public Client selectById(Integer id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Client client = null;
		try {
			connection = JdbcTools.get();
			statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
			statement.setInt(1, id);

			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				try {
					client = resultSetEntryToClient(resultSet);
				} catch (Exception e) {
					throw new DaoException("Erreur selection client");
				}
			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			ResourceUtil.safeClose(connection, statement, resultSet);
		}
		return client;
	}

	private Client resultSetEntryToClient(ResultSet resultSet) throws Exception {
		Client client = new Client();

		client.setCodeClient(resultSet.getInt("CodeClient"));
		client.setNomClient(resultSet.getString("NomClient"));
		client.setPrenomClient(resultSet.getString("PrenomClient"));
		client.setAdresse1(resultSet.getString("Adresse1"));
		client.setAdresse2(resultSet.getString("Adresse2"));
		client.setCodePostal(resultSet.getString("CodePostal"));
		client.setVille(resultSet.getString("Ville"));
		client.setNumTel(resultSet.getString("NumTel"));
		client.setAssurance(resultSet.getString("Assurance"));
		client.setEmail(resultSet.getString("Email"));
		client.setRemarque(resultSet.getString("Remarque"));
		client.setArchive(resultSet.getBoolean("Archive"));

		if(SqlUtil.resultSethasColumn(resultSet, "NbAnimaux")){
			client.setNbAnimaux(resultSet.getInt("NbAnimaux"));
			//only SEARCH_QUERY
		}
		
		
		return client;
	}

	@Override
	public Client insert(Client client) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcTools.get();

			statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, client.getNomClient());
			statement.setString(2, client.getPrenomClient());
			statement.setString(3, client.getAdresse1());
			statement.setString(4, client.getAdresse2());
			statement.setString(5, client.getCodePostal());
			statement.setString(6, client.getVille());
			statement.setString(7, client.getNumTel());
			statement.setString(8, client.getAssurance());
			statement.setString(9, client.getEmail());
			statement.setString(10, client.getRemarque());
			statement.setBoolean(11, client.getArchive());

			if (statement.executeUpdate() == 1) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					client.setCodeClient(resultSet.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			ResourceUtil.safeClose(connection, statement, resultSet);
		}
		return client;
	}

	@Override
	public void update(Client client) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = JdbcTools.get();

			statement = connection.prepareStatement(UPDATE_QUERY);

			statement.setString(1, client.getNomClient());
			statement.setString(2, client.getPrenomClient());
			statement.setString(3, client.getAdresse1());
			statement.setString(4, client.getAdresse2());
			statement.setString(5, client.getCodePostal());
			statement.setString(6, client.getVille());
			statement.setString(7, client.getNumTel());
			statement.setString(8, client.getAssurance());
			statement.setString(9, client.getEmail());
			statement.setString(10, client.getRemarque());
			statement.setBoolean(11, client.getArchive());
			
			statement.setInt(12, client.getCodeClient());
			
			//statement.executeQuery();
			//=> SQLServerException: L'instruction n'a pas renvoyé le jeu de résultat.
			
			if (statement.executeUpdate() == 0) {
				throw new DaoException("Erreur update client");
			}

		} catch (SQLException e) {
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

			// l'integrite referentielle s'occupe d'invalider la suppression
			// si l'element est reference dans une ligne de commande
			statement = connection.prepareStatement(DELETE_QUERY);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			ResourceUtil.safeClose(connection, statement);
		}

	}

	@Override
	public List<Client> selectAll() throws DaoException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Client> liste = new ArrayList<Client>();

		try {
			connection = JdbcTools.get();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_QUERY);

			while (resultSet.next()) {
				liste.add(resultSetEntryToClient(resultSet));
			}
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			ResourceUtil.safeClose(connection, statement, resultSet);
		}

		return liste;
	}
	
	@Override
	public List<Client> selectSearch(String search) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Client> liste = new ArrayList<Client>();
		
		try {
			connection = JdbcTools.get();
			statement = connection.prepareStatement(SEARCH_QUERY);
			//(SELECT_ALL resultSet not have nbAnimaux)
			statement.setString(1, '%'+search+'%');
			statement.setString(2, '%'+search+'%');
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Client cli = resultSetEntryToClient(resultSet);
				liste.add(cli);
			}
		} catch (Exception e) {
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
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			ResourceUtil.safeClose(connection, statement);
		}
	}

}
