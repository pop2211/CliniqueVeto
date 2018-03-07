package fr.eni.clinique.bll.manager.impl;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.AnimalManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.DaoFactory;

public class AnimalManagerImpl implements AnimalManager{
	
	
	private AnimalDAO animalDAO = DaoFactory.animalDAO();
    
	private static AnimalManagerImpl instance;
	 
	public static AnimalManagerImpl getInstance() {
		if(instance == null) {
			instance = new AnimalManagerImpl();
		}
		return instance;
	}
	
	@Override
	public Animal selectById(Integer id) throws ManagerException {
		Animal animal = new Animal();
		
		try {
			animal = animalDAO.selectById(id);
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste Animaux", e);
        }
		return animal;
	}

	@Override
	public List<Animal> selectAll() throws ManagerException {
		List<Animal> animal = null;
        
        try {
        	animal = animalDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste animaux", e);
        }
        
        return animal;
	}

	@Override
	public Animal insert(Animal newAnimal) throws ManagerException {
		if(newAnimal.getCodeAnimal() != null) {
            throw new ManagerException("L'animal est deja existante.");
        }
        
        try {
        	validerAnimal(newAnimal);
            
        	newAnimal = animalDAO.insert(newAnimal);
            
        } catch (DaoException e) {
            throw new ManagerException("Echec addAnimal", e);
        }
        return newAnimal;
	}

	@Override
	public void update(Animal newAnimal) throws ManagerException {
		try {
			validerAnimal(newAnimal);
            
        	animalDAO.update(newAnimal);
            
        } catch (DaoException e) {
            throw new ManagerException(String.format("Echec updateAnimal-animal: %s", newAnimal), e);
        }
	}

	@Override
	public void delete(Integer codeAnimal) throws ManagerException {
		try {
			animalDAO.delete(codeAnimal);
            
        } catch (DaoException e) {
            throw new ManagerException("Echec de suppression de l'animal - ", e);
        }
	}
	
	private void validerAnimal(Animal animal) throws ManagerException {

        try {
            ObjectUtil.checkNotNullWithMessage(animal, "Une erreur technique st survenue");
            ObjectUtil.checkNotNullWithMessage(animal.getNomAnimal(), "Le Nom est obligatoire");
            ObjectUtil.checkNotNullWithMessage(animal.getCouleur(), "Le MDP est obligatoire");
            ObjectUtil.checkNotBlankWithMessage(animal.getSexe(), "Le Sexe est obligatoire");
            ObjectUtil.checkNotNullWithMessage(animal.getCodeClient(), "Le CodeClient est obligatoire");
            ObjectUtil.checkNotNullWithMessage(animal.getRace(), "La Race est obligatoire");

        } catch (IllegalArgumentException e) {
            throw new ManagerException(String.format("Erreur de validation : %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

	@Override
	public List<Animal> selectByMaitre(Integer codeClient) throws ManagerException {
		List<Animal> animal = null;
        
        try {
        	animal = animalDAO.selectByMaitre(codeClient);
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste animaux par maitre", e);
        }
        
        return animal;
	}

	

}
