package fr.eni.clinique.dal.dao.impl;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.exception.DaoException;

public class AnimalJDBCDAOImpl implements AnimalDAO{
	
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Animaux WHERE codeAnimal = ?";
	private static final String SELECT_ALL = "SELECT * FROM Animaux";
	private static final String UPDATE_QUERY = "UPDATE Animaux SET NomAnimal=?, Sexe=?, Couleur=?, Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archives=? WHERE codeAnimal=?";
    private static final String INSERT_QUERY = "INSERT INTo Animaux(NomAnimal=?, Sexe=?, Couleur=?, Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archives=?) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM Animaux WHERE codeAnimal=?";
	public static AnimalDAO getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animal insert(Animal element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Animal element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Animal selectById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Animal> selectAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Animal> selectByNom(String marque) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
