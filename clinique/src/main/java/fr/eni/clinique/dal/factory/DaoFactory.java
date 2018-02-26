package main.java.fr.eni.clinique.dal.factory;


import main.java.fr.eni.clinique.dal.dao.AnimalDAO;
import main.java.fr.eni.clinique.dal.dao.impl.AnimalJDBCDAOImpl;

public class DaoFactory {
    
    public static AnimalDAO articleDAO() {
        return AnimalJDBCDAOImpl.getInstance();
    }
}
