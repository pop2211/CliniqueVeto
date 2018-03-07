package fr.eni.clinique.bll.factory;

import fr.eni.clinique.bll.manager.AnimalManager;
import fr.eni.clinique.bll.manager.ClientManager;
import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bll.manager.RaceManager;
import fr.eni.clinique.bll.manager.RdvManager;
import fr.eni.clinique.bll.manager.impl.AnimalManagerImpl;
import fr.eni.clinique.bll.manager.impl.ClientManagerImpl;
import fr.eni.clinique.bll.manager.impl.PersonnelManagerImpl;
import fr.eni.clinique.bll.manager.impl.RaceManagerImpl;
import fr.eni.clinique.bll.manager.impl.RdvManagerImpl;

public class ManagerFactory {
   
    public static PersonnelManager PersonnelManager() {
        return PersonnelManagerImpl.getInstance();
    }
    
    public static ClientManager ClientManager() {
        return ClientManagerImpl.getInstance();
    }

	public static AnimalManager AnimalManager() {
		return AnimalManagerImpl.getInstance();
	}

	public static RaceManager RaceManager() {
		return RaceManagerImpl.getInstance();
	}
	
	public static RdvManager RdvManager() {
		return RdvManagerImpl.getInstance();
	}
    
}
