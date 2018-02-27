package fr.eni.clinique.bll.factory;

import fr.eni.clinique.bll.manager.PersonnelManager;
import fr.eni.clinique.bll.manager.impl.PersonnelManagerImpl;

public class ManagerFactory extends Exception {

    private static final long serialVersionUID = 5373358650308469523L;

    public static PersonnelManager PersonnelManager() {
        return PersonnelManagerImpl.getInstance();
    }
    
}
