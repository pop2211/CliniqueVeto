package fr.eni.clinique.ihm.event;

import fr.eni.clinique.bo.Personnel;

public class PersonnelActionEvent {
	
    private Personnel personnel;

    /**
     * @param article
     */
    public PersonnelActionEvent(Personnel personnel) {
        super();
        this.personnel = personnel;
    }

    /**
     * @return the article
     */
    public Personnel getPersonnel() {
        return personnel;
    }
}
