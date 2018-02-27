package fr.eni.clinique.dal.factory;


import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.impl.AnimalJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.ClientJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.PersonnelJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RaceJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RdvJDBCDAOImpl;

public class DaoFactory {
    
    public static AnimalDAO animalDAO() {
        return AnimalJDBCDAOImpl.getInstance();
    }
    
    public static ClientDAO clientDAO() {
        return ClientJDBCDAOImpl.getInstance();
    }
    
    public static PersonnelDAO personelDAO() {
        return PersonnelJDBCDAOImpl.getInstance();
    }
    
    public static RdvDAO RdvDAO() {
        return RdvJDBCDAOImpl.getInstance();
    }
    
    public static RaceDAO RaceDAO() {
        return RaceJDBCDAOImpl.getInstance();
    }
}
