package fr.eni.clinique.ihm.screen.client;

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
import fr.eni.clinique.ihm.screen.common.GenericScreen;


public class GenericClientScreen extends GenericScreen {

	//code du client en cours d'edition
	protected Integer currentCodeClient;
	
	//champs pour editer un client
	protected JTextField codeTbx;
	protected JTextField nomTbx;
	protected JTextField prenomTbx;
	protected JTextField adresse1Tbx;
	protected JTextField adresse2Tbx;
	protected JTextField codePostalTbx;
	protected JTextField villeTbx;
	protected JTextField numTelTbx;
	protected JTextField assuranceTbx;
	protected JTextField emailTbx;
	protected JTextField remarqueTbx;
	
	final int MAXLENGTH_NOM = 20;
	final int MAXLENGTH_PRENOM = 20;
	final int MAXLENGTH_ADRESSE1 = 30;
	final int MAXLENGTH_ADRESSE2 = 30;
	final int MAXLENGTH_CODEPOSTAL = 5;
	final int MAXLENGTH_VILLE = 25;
	final int MAXLENGTH_NUMTEL = 15;
	final int MAXLENGTH_ASSURANCE = 30;
	final int MAXLENGTH_EMAIL = 20;
	
	public GenericClientScreen(String title, Boolean b1, Boolean b2, Boolean b3, Boolean b4) {
		super(title, b1, b2, b3, b4);
	}
	
	
	
	/**
	 * Show Client on the UI.
	 * 
	 * @param article
	 */
	public void showClient(Client client) {

		//Active les champs si un client est selectionne
		if (client == null) {
			client = new Client();
		}
		currentCodeClient = client.getCodeClient();
		Boolean enableFields = (currentCodeClient != null);
		
		codeTbx.setEnabled(false);	//(code always disabled)
		nomTbx.setEnabled(enableFields);
		prenomTbx.setEnabled(enableFields);
		adresse1Tbx.setEnabled(enableFields);
		adresse2Tbx.setEnabled(enableFields);
		codePostalTbx.setEnabled(enableFields);
		villeTbx.setEnabled(enableFields);
		numTelTbx.setEnabled(enableFields);
		assuranceTbx.setEnabled(enableFields);
		emailTbx.setEnabled(enableFields);
		remarqueTbx.setEnabled(enableFields);
		
		// Rempli les champs de l'ihm :
		codeTbx.setText(ObjectUtil.nullToBlank(client.getCodeClient()).trim());
		nomTbx.setText(ObjectUtil.nullToBlank(client.getNomClient()).trim());
		prenomTbx.setText(ObjectUtil.nullToBlank(client.getPrenomClient()).trim());
		adresse1Tbx.setText(ObjectUtil.nullToBlank(client.getAdresse1()).trim());
		adresse2Tbx.setText(ObjectUtil.nullToBlank(client.getAdresse2()).trim());
		codePostalTbx.setText(ObjectUtil.nullToBlank(client.getCodePostal()).trim());
		villeTbx.setText(ObjectUtil.nullToBlank(client.getVille()).trim());
		numTelTbx.setText(ObjectUtil.nullToBlank(client.getNumTel()).trim());
		assuranceTbx.setText(ObjectUtil.nullToBlank(client.getAssurance()).trim());
		emailTbx.setText(ObjectUtil.nullToBlank(client.getEmail()).trim());
		remarqueTbx.setText(ObjectUtil.nullToBlank(client.getRemarque()).trim());
	}

	/**
	 * Read Client From the UI.
	 * 
	 * @return
	 */
	public Client readClient() {

		Client client = new Client();
		
		Integer codeClient = null;
		if(codeTbx != null){
			codeClient = Integer.parseInt(codeTbx.getText().trim());
		}

		// Recupère les champs de l'ihm :
		client.setCodeClient(codeClient);
		client.setNomClient(nomTbx.getText().trim());
		client.setPrenomClient(prenomTbx.getText().trim());
		client.setAdresse1(adresse1Tbx.getText().trim());
		client.setAdresse2(adresse2Tbx.getText().trim());
		client.setCodePostal(codePostalTbx.getText().trim());
		client.setVille(villeTbx.getText().trim());
		client.setNumTel(numTelTbx.getText().trim());
		client.setAssurance(assuranceTbx.getText().trim());
		client.setEmail(emailTbx.getText().trim());
		client.setRemarque(remarqueTbx.getText().trim());

		return client;
	}
	
	@Override
	public void processEvent(String eventName, Object eventParam){
	}
	
	public Integer getCurrentCodeClient(){
		return currentCodeClient;
	}
}
