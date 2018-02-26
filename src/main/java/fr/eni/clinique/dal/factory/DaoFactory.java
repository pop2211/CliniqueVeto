package fr.eni.clinique.dal.factory;


import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.impl.AnimalJDBCDAOImpl;

public class DaoFactory {
    
    public static AnimalDAO articleDAO() {
        return AnimalJDBCDAOImpl.getInstance();
    }
}
