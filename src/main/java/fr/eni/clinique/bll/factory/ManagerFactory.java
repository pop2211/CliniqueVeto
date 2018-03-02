package fr.eni.clinique.bll.factory;

import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bll.manager.ClientManager;
import fr.eni.clinique.bll.manager.impl.ClientManagerImpl;
import fr.eni.clinique.bll.manager.impl.PersonnelManagerImpl;

public class ManagerFactory {
   
    public static PersonnelManager PersonnelManager() {
        return PersonnelManagerImpl.getInstance();
    }
    
    public static ClientManager ClientManager() {
        return ClientManagerImpl.getInstance();
    }
    
}
