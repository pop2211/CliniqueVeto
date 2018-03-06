package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Personnel;

public class PersonnelModel {

	 private List<Personnel> personnels = new ArrayList<>();
	 private int currentIndex;
	    
	 
	 public void loadPersonnel(List<Personnel> personnels) {
	        this.personnels.clear();
	        this.personnels.addAll(personnels);
	    }
	 
	 public void addPersonnel(Personnel personnel) {
		 	personnels.add(personnel);
	    }
	 
	 
	
	    
	
}
