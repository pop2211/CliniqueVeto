package fr.eni.clinique.bll.factory;

import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bll.manager.impl.PersonnelManagerImpl;

public class ManagerFactory {
   
    public static PersonnelManager PersonnelManager() {
        return PersonnelManagerImpl.getInstance();
    }
    
}
