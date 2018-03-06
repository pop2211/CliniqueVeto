package fr.eni.clinique.ihm.controller;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.model.ClientModel;

public class ClientController{

    private ClientModel model;
    private ClientManager clientManager = ManagerFactory.ClientManager();

    /**
     * @param model
     */
    public ClientController(ClientModel model) {
        super();
        this.model = model;
    }
	
	public void saveClient(Client client) throws Exception {
		if(client.getCodeClient() == null){
			clientManager.insert(client);
		}else{
			clientManager.update(client);
		}
	}
	
	public Client loadClient(Integer codeCli) throws ManagerException {
		return clientManager.selectById(codeCli);
	}
	
	public List<Client> loadAllClient() throws ManagerException {
		return clientManager.selectAll();
	}
	
	public List<Client> loadSearchClient(String search) throws ManagerException {
		return clientManager.selectSearch(search);
	}

	public void removeClient(Client rmCli) throws ManagerException {
		Integer rmIdCli = rmCli.getCodeClient();
		clientManager.delete(rmIdCli);
	}
	
}
