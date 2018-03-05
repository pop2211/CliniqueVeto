package fr.eni.clinique.bll.manager.impl;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.DaoFactory;

public class ClientManagerImpl implements ClientManager{
	
	
	private ClientDAO clientDAO = DaoFactory.clientDAO();
    
	private static ClientManagerImpl instance;
	 
	public static ClientManagerImpl getInstance() {
		if(instance == null) {
			instance = new ClientManagerImpl();
		}
		return instance;
	}
	
	@Override
	public List<Client> selectAll() throws ManagerException {
		List<Client> clients = null;
        
        try {
        	clients = clientDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste du client", e);
        }
        
        return clients;
	}
	
	@Override
	public Client selectById(Integer id) throws ManagerException {
		Client client = new Client();
		
		try {
			client = clientDAO.selectById(id);
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste du client", e);
        }
		return client;
	}

	@Override
	public Client insert(Client newClient) throws ManagerException {
		if(newClient.getCodeClient() != null) {
            throw new ManagerException("Le client est deja existante.");
        }
        
        try {
        	validerClient(newClient);
            
        	newClient = clientDAO.insert(newClient);
            
        } catch (DaoException e) {
            throw new ManagerException("Echec addClient", e);
        }
        return newClient;
	}

	@Override
	public void update(Client client) throws ManagerException {
		try {
			validerClient(client);
            
        	clientDAO.update(client);
            
        } catch (DaoException e) {
            throw new ManagerException(String.format("Echec updateClient-client: %s", client), e);
        }
	}

	@Override
	public void delete(Integer codeClient) throws ManagerException {
		try {
			clientDAO.delete(codeClient);
            
        } catch (DaoException e) {
            throw new ManagerException("Echec de suppression du client - ", e);
        }
	}

	private void validerClient(Client client) throws ManagerException {

        try {
            ObjectUtil.checkNotNullWithMessage(client, "Une erreur technique est survenue");
            ObjectUtil.checkNotBlankWithMessage(client.getNomClient(), "Le Nom est obligatoire");
            ObjectUtil.checkNotBlankWithMessage(client.getPrenomClient(), "Le Prenom est obligatoire");
            //ObjectUtil.checkNotBlankWithMessage(client.getAdresse1(), "La première adresse est obligatoire");
            //ObjectUtil.checkNotNullWithMessage(client.getCodePostal(), "Le Code Postal est obligatoire");
            //ObjectUtil.checkNotBlankWithMessage(client.getVille(), "La Ville est obligatoire");
            //ObjectUtil.checkNotBlankWithMessage(client.getNumTel(), "Le numéro de téléphone est obligatoire");
            //ObjectUtil.checkNotBlankWithMessage(client.getAssurance(), "L'assurance est obligatoire");
            //ObjectUtil.checkNotBlankWithMessage(client.getEmail(), "L'Email est obligatoire");
            //ObjectUtil.checkNotBlankWithMessage(client.getRemarque(), "La Remarque est obligatoire");
            ObjectUtil.checkNotNullWithMessage(client.getArchive(), "L'Archive est obligatoire");

        } catch (IllegalArgumentException e) {
            throw new ManagerException(String.format("Erreur de validation : %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }
	

}
