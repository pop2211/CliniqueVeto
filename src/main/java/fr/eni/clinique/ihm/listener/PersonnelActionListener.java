package fr.eni.clinique.ihm.listener;

import fr.eni.clinique.ihm.event.PersonnelActionEvent;

public interface PersonnelActionListener {

    /**
     * Initialize.
     * 
     */
    void init() throws Exception;
    
    
    /**
     * New Personnel Notification
     */
    void newPersonnel();
    
    /**
     * Connect Personnel Notification.
     * 
     * @param event
     */
    void connectPersonnel(PersonnelActionEvent event) throws Exception;
    
    /**
     * Delete Personnel Notification.
     * 
     * @param event
     */
    void deletePersonnel(PersonnelActionEvent event) throws Exception;
    
    /**
     * Save Personnel Notification.
     * 
     * @param event
     */
    void savePersonnel(PersonnelActionEvent event) throws Exception;
	
}
