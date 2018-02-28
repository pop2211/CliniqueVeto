package fr.eni.clinique;

import javax.swing.SwingUtilities;

import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.MainScreen;

public class AppliTestIHM {

    public static void main(String[] args) {
      try {
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    
                    // Create A Clinique Model.
                    PersonnelModel personnelModel = new PersonnelModel();
                    
                    // Create A Clinique controller
                    PersonnelController personnelController = new PersonnelController(personnelModel);
                    
                    // Create A Clinique View
                    MainScreen screen = new MainScreen(AppConstants.APP_NAME, personnelModel, personnelController);
                    
                    // The Controller listen to the View
                    //screen.setActionListener(personnelController);
                    
                    screen.setVisible(true); // Fenetre visible
                    screen.setLocationRelativeTo(null); // Centré sur l'ecran
                }
            });
        } catch (Exception e) {
            throw new TechnicalException("Erreur Technique", e);
        }
    }
}




















