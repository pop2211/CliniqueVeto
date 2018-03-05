package fr.eni.clinique.ihm.screen.common;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.ClientModel;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.MainScreen;


public class GenericScreen extends JInternalFrame {


	protected MainScreen mainScreen;
	
	
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
		showFailureMessage(e.getMessage());
		e.printStackTrace();
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

}
