package fr.eni.clinique.ihm.screen.common;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.AppliTestDAL;
import fr.eni.clinique.bo.EnumRole;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.controller.RdvController;
import fr.eni.clinique.ihm.model.AnimalModel;
import fr.eni.clinique.ihm.model.ClientModel;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.model.RdvModel;
import fr.eni.clinique.ihm.model.TableModelAnimal;
import fr.eni.clinique.ihm.screen.MainScreen;


public abstract class GenericScreen extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	protected MainScreen mainScreen;
	protected GenericScreen parentScreen;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestDAL.class);
	
	public GenericScreen(String title, Boolean b1, Boolean b2,  Boolean b3,  Boolean b4){
		super(title, b1, b2, b3, b4);
		
		
	}
	
	public MainScreen getMainScreen(){
		if(this.mainScreen == null){
			this.mainScreen = (MainScreen) this.getTopLevelAncestor();
		}
		return this.mainScreen;
		//return null if executed at end of constructor
	}
	

	/**
	 * Show TechnicalError and print StackTrace.
	 * 
	 * @param e
	 */
	public void errorOccured(Exception e) {
		LOGGER.error("ERROR", e);
		//if user is dev show stack trace (before modal popup)
		if(getMainScreen().getProfil() == EnumRole.DEV){
			e.printStackTrace();
		}
		showFailureMessage(e.getMessage());
	}

	/**
	 * Show TechnicalError.
	 * 
	 * @param message
	 */
	public void showFailureMessage(String message) {
		JOptionPane.showMessageDialog(GenericScreen.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Show Success Message.
	 * 
	 * @param message
	 */
	public void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(GenericScreen.this, message);
	}
	
	/**
	 * Show Dialog Message and return result.
	 * 
	 * @param message
	 */
	public boolean showConfirmDialog(String message) {
		String title = "Confirmation";
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, message, title, dialogButton);
		return (dialogResult == JOptionPane.YES_OPTION);
	}


	//========== MODEL & CONTROLLERS ==============
	//Client
	protected ClientModel modelClient;
	protected ClientController controllerClient;
	//Animal
	protected AnimalModel modelAnimal;
	protected TableModelAnimal modelAnimalTable;
	protected AnimalController controllerAnimal;
	//Personnel
	protected PersonnelModel modelPersonnel;
	protected PersonnelController controllerPersonnel;
	//RDV
	protected RdvModel modelRdv;
	protected RdvController controllerRdv;
	
	public ClientModel getModelClient() {
		return modelClient;
	}
	public ClientController getControllerClient() {
		return controllerClient;
	}
	
	//=============================================
	
	
	public abstract void processEvent(String eventName, Object eventParam);
	

}
