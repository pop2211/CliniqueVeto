package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

public class ClientModel {

	 private List<Client> clients = new ArrayList<>();
	 private int currentIndex;
	    
	 
	 public void loadPersonnel(List<Client> clients) {
	        this.clients.clear();
	        this.clients.addAll(clients);
	    }
	 
	 public void addPersonnel(Client client) {
		 clients.add(client);
	 }
	 
	 
	 public void removeCurrentClient() {

	        if (!clients.isEmpty()) {
	        	clients.remove(currentIndex);

	            if (currentIndex > clients.size() - 1) {
	                currentIndex = 0;
	            }
	        }
	  }
	 
	 
	 public Client currentClient() {
	        
		 Client client = null;
	        
	        if(!clients.isEmpty()) {
	        	client = clients.get(currentIndex);
	        }
	        return client;
	    }
	    
	
}
