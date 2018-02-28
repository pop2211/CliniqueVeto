package fr.eni.clinique;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.MainScreen;
import javax.swing.JCheckBox;

public class AppliTestIHM_WindowBuilder {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppliTestIHM_WindowBuilder window = new AppliTestIHM_WindowBuilder();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppliTestIHM_WindowBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

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
                    screen.setLocationRelativeTo(null);
                    
                    JCheckBox chckbxTestaddcheckbox = new JCheckBox("testAddCheckbox");
                    chckbxTestaddcheckbox.setBounds(32, 25, 211, 23);
                    screen.getContentPane().add(chckbxTestaddcheckbox);
                }
            });
            
        } catch (Exception e) {
            throw new TechnicalException("Erreur Technique", e);
        }
	}
}
