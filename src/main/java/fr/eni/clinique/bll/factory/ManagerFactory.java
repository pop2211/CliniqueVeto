package fr.eni.clinique.bll.factory;

public class ManagerFactory extends Exception {

    private static final long serialVersionUID = 5373358650308469523L;

    public ManagerFactory(String message) {
        super(message);
    }

    public ManagerFactory(String message, Throwable cause) {
        super(message, cause);
    }
    
}
