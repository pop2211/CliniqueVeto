package fr.eni.clinique.bll.manager;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Client;

public interface ClientManager extends GenericManager<Client, Integer>{

	List<Client> selectSearch(String search) throws ManagerException;

}
