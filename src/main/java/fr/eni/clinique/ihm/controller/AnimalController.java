package fr.eni.clinique.ihm.controller;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.AnimalManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.ihm.model.AnimalModel;

public class AnimalController {
	

    private AnimalModel model;
    private AnimalManager animalManager = ManagerFactory.AnimalManager();

    /**
     * @param model
     */
    public AnimalController(AnimalModel model) {
        super();
        this.model = model;
    }

	
	public void init() throws Exception {
		System.out.println("Animal Controler: init()");
		
	}

	
	public void newAnimal(Animal animal) throws ManagerException {
		animalManager.insert(animal);
	}
	
	
	public void saveAnimal(Animal animal) throws Exception {
		animalManager.update(animal);
	}
	
	
	public Animal loadAnimal(Integer codeAnimal) throws ManagerException {
		return animalManager.selectById(codeAnimal);
	}
	
	public List<Animal> loadAnimalByMaitre(Integer codeClient) throws ManagerException {
		return animalManager.selectByMaitre(codeClient);
	}

}
