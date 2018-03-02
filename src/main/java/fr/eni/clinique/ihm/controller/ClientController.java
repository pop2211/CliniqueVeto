package fr.eni.clinique.ihm.controller;

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

	
	public void init() throws Exception {
		System.out.println("Client Controler: init(), load first client");
		
        Client firstClient = clientManager.selectById(1);
        model.loadClient(firstClient);
	}

	
	public void newClient(Client client) throws ManagerException {
		clientManager.insert(client);
	}
	
	
	public void saveClient(Client client) throws Exception {
		clientManager.update(client);
	}
	
}
