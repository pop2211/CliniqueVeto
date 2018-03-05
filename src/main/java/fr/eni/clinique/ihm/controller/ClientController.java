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
		System.out.println("Client Controler: init()");
		
	}

	
//	public void newClient(Client client) throws ManagerException {
//		clientManager.insert(client);
//	}
	
	
	public void saveClient(Client client) throws Exception {
		System.out.println(client.getCodeClient());
		if(client.getCodeClient() == null){
			System.out.println("clientManager.insert");
			clientManager.insert(client);
		}else{
			System.out.println("clientManager.update");
			clientManager.update(client);
		}
	}
	
	public Client loadClient(Integer codeCli) throws ManagerException {
		return clientManager.selectById(codeCli);
	}
	
}
