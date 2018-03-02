package fr.eni.clinique.bll.manager;

import fr.eni.clinique.bo.Race;

public interface RaceManager extends GenericManager<Race, String>{

	Race selectByEspece(String espece);

}
