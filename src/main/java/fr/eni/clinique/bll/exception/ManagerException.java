package fr.eni.clinique.bll.exception;

public class ManagerException extends Exception {

    private static final long serialVersionUID = 5373358650308469523L;

    public ManagerException(String message) {
        super(message);
    }

    public ManagerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
