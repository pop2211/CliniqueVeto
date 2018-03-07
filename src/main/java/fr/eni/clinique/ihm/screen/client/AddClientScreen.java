package fr.eni.clinique.ihm.screen.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.screen.common.GenericScreen;
import fr.eni.clinique.ihm.screen.common.JTextFieldLimit;

public class AddClientScreen extends GenericClientScreen {

	private static final long serialVersionUID = 4425015574384790305L;
	
	
	/**
	 * Create the frame.
	 */
	public AddClientScreen(GenericScreen parentScreen) {
		super("Ajout", true, true, true, true);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.parentScreen = parentScreen;
		this.modelClient = parentScreen.getModelClient();
		this.controllerClient = parentScreen.getControllerClient();
		
		
		setBounds(100, 100, 350, 416);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 0, 0, 0, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton validerBtn = new JButton("Valider");
		validerBtn.setIcon(new ImageIcon(AddClientScreen.class.getResource("/images/ico/done_32p.png")));
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client saveIt = readClient();
				try {
					controllerClient.saveClient(saveIt);
					showSuccessMessage("Client ajouté !");
					parentScreen.processEvent("AddClient", saveIt.getCodeClient());
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					errorOccured(e1);
				}
			}
		});
		validerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		validerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_validerBtn = new GridBagConstraints();
		gbc_validerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_validerBtn.gridx = 4;
		gbc_validerBtn.gridy = 1;
		getContentPane().add(validerBtn, gbc_validerBtn);
		
		JButton annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showClient(new Client());	//reset fields
				setVisible(false);
			}
		});
		annulerBtn.setIcon(new ImageIcon(AddClientScreen.class.getResource("/images/ico/undo_27p.png")));
		annulerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		annulerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 5;
		gbc_annulerBtn.gridy = 1;
		getContentPane().add(annulerBtn, gbc_annulerBtn);
		
		
		
		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.WEST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 3;
		getContentPane().add(lblNom, gbc_lblNom);
		
		nomTbx = new JTextField();
		nomTbx.setDocument(new JTextFieldLimit(MAXLENGTH_NOM));
		nomTbx.setColumns(10);
		GridBagConstraints gbc_nomTbx = new GridBagConstraints();
		gbc_nomTbx.gridwidth = 5;
		gbc_nomTbx.insets = new Insets(0, 0, 5, 5);
		gbc_nomTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomTbx.gridx = 2;
		gbc_nomTbx.gridy = 3;
		getContentPane().add(nomTbx, gbc_nomTbx);
		
		JLabel lblPrnom = new JLabel("Prénom");
		GridBagConstraints gbc_lblPrnom = new GridBagConstraints();
		gbc_lblPrnom.anchor = GridBagConstraints.WEST;
		gbc_lblPrnom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrnom.gridx = 1;
		gbc_lblPrnom.gridy = 4;
		getContentPane().add(lblPrnom, gbc_lblPrnom);
		
		prenomTbx = new JTextField();
		prenomTbx.setDocument(new JTextFieldLimit(MAXLENGTH_PRENOM));
		prenomTbx.setColumns(10);
		GridBagConstraints gbc_prenomTbx = new GridBagConstraints();
		gbc_prenomTbx.gridwidth = 5;
		gbc_prenomTbx.insets = new Insets(0, 0, 5, 5);
		gbc_prenomTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_prenomTbx.gridx = 2;
		gbc_prenomTbx.gridy = 4;
		getContentPane().add(prenomTbx, gbc_prenomTbx);
		
		JLabel lblAdresse = new JLabel("Adresse");
		GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
		gbc_lblAdresse.anchor = GridBagConstraints.WEST;
		gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdresse.gridx = 1;
		gbc_lblAdresse.gridy = 5;
		getContentPane().add(lblAdresse, gbc_lblAdresse);
		
		adresse1Tbx = new JTextField();
		adresse1Tbx.setDocument(new JTextFieldLimit(MAXLENGTH_ADRESSE1));
		adresse1Tbx.setColumns(10);
		GridBagConstraints gbc_adresse1Tbx = new GridBagConstraints();
		gbc_adresse1Tbx.gridwidth = 5;
		gbc_adresse1Tbx.insets = new Insets(0, 0, 5, 5);
		gbc_adresse1Tbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse1Tbx.gridx = 2;
		gbc_adresse1Tbx.gridy = 5;
		getContentPane().add(adresse1Tbx, gbc_adresse1Tbx);
		
		adresse2Tbx = new JTextField();
		adresse2Tbx.setDocument(new JTextFieldLimit(MAXLENGTH_ADRESSE2));
		adresse2Tbx.setColumns(10);
		GridBagConstraints gbc_adresse2Tbx = new GridBagConstraints();
		gbc_adresse2Tbx.gridwidth = 5;
		gbc_adresse2Tbx.insets = new Insets(0, 0, 5, 5);
		gbc_adresse2Tbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse2Tbx.gridx = 2;
		gbc_adresse2Tbx.gridy = 6;
		getContentPane().add(adresse2Tbx, gbc_adresse2Tbx);
		
		JLabel lblCodePostal = new JLabel("Code postal");
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.WEST;
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 1;
		gbc_lblCodePostal.gridy = 7;
		getContentPane().add(lblCodePostal, gbc_lblCodePostal);
		
		codePostalTbx = new JTextField();
		codePostalTbx.setDocument(new JTextFieldLimit(MAXLENGTH_CODEPOSTAL));
		codePostalTbx.setColumns(10);
		GridBagConstraints gbc_codePostalTbx = new GridBagConstraints();
		gbc_codePostalTbx.gridwidth = 5;
		gbc_codePostalTbx.insets = new Insets(0, 0, 5, 5);
		gbc_codePostalTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_codePostalTbx.gridx = 2;
		gbc_codePostalTbx.gridy = 7;
		getContentPane().add(codePostalTbx, gbc_codePostalTbx);
		
		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.WEST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 1;
		gbc_lblVille.gridy = 8;
		getContentPane().add(lblVille, gbc_lblVille);
		
		villeTbx = new JTextField();
		villeTbx.setDocument(new JTextFieldLimit(MAXLENGTH_VILLE));
		villeTbx.setColumns(10);
		GridBagConstraints gbc_villeTbx = new GridBagConstraints();
		gbc_villeTbx.gridwidth = 5;
		gbc_villeTbx.insets = new Insets(0, 0, 5, 5);
		gbc_villeTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_villeTbx.gridx = 2;
		gbc_villeTbx.gridy = 8;
		getContentPane().add(villeTbx, gbc_villeTbx);
		
		JLabel lblTlphone = new JLabel("Téléphone");
		GridBagConstraints gbc_lblTlphone = new GridBagConstraints();
		gbc_lblTlphone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTlphone.anchor = GridBagConstraints.WEST;
		gbc_lblTlphone.gridx = 1;
		gbc_lblTlphone.gridy = 9;
		getContentPane().add(lblTlphone, gbc_lblTlphone);
		
		numTelTbx = new JTextField();
		numTelTbx.setDocument(new JTextFieldLimit(MAXLENGTH_NUMTEL));
		numTelTbx.setColumns(10);
		GridBagConstraints gbc_numTelTbx = new GridBagConstraints();
		gbc_numTelTbx.gridwidth = 5;
		gbc_numTelTbx.insets = new Insets(0, 0, 5, 5);
		gbc_numTelTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_numTelTbx.gridx = 2;
		gbc_numTelTbx.gridy = 9;
		getContentPane().add(numTelTbx, gbc_numTelTbx);
		
		JLabel lblAssurance = new JLabel("Assurance");
		GridBagConstraints gbc_lblAssurance = new GridBagConstraints();
		gbc_lblAssurance.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssurance.anchor = GridBagConstraints.WEST;
		gbc_lblAssurance.gridx = 1;
		gbc_lblAssurance.gridy = 10;
		getContentPane().add(lblAssurance, gbc_lblAssurance);
		
		assuranceTbx = new JTextField();
		assuranceTbx.setDocument(new JTextFieldLimit(MAXLENGTH_ASSURANCE));
		assuranceTbx.setColumns(10);
		GridBagConstraints gbc_assuranceTbx = new GridBagConstraints();
		gbc_assuranceTbx.gridwidth = 5;
		gbc_assuranceTbx.insets = new Insets(0, 0, 5, 5);
		gbc_assuranceTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_assuranceTbx.gridx = 2;
		gbc_assuranceTbx.gridy = 10;
		getContentPane().add(assuranceTbx, gbc_assuranceTbx);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 11;
		getContentPane().add(lblEmail, gbc_lblEmail);
		
		emailTbx = new JTextField();
		emailTbx.setDocument(new JTextFieldLimit(MAXLENGTH_EMAIL));
		emailTbx.setColumns(10);
		GridBagConstraints gbc_emailTbx = new GridBagConstraints();
		gbc_emailTbx.gridwidth = 5;
		gbc_emailTbx.insets = new Insets(0, 0, 5, 5);
		gbc_emailTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTbx.gridx = 2;
		gbc_emailTbx.gridy = 11;
		getContentPane().add(emailTbx, gbc_emailTbx);
		
		JLabel lblRemarque = new JLabel("Remarque");
		GridBagConstraints gbc_lblRemarque = new GridBagConstraints();
		gbc_lblRemarque.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemarque.anchor = GridBagConstraints.WEST;
		gbc_lblRemarque.gridx = 1;
		gbc_lblRemarque.gridy = 12;
		getContentPane().add(lblRemarque, gbc_lblRemarque);
		
		remarqueTbx = new JTextField();
		remarqueTbx.setColumns(10);
		GridBagConstraints gbc_remarqueTbx = new GridBagConstraints();
		gbc_remarqueTbx.gridwidth = 5;
		gbc_remarqueTbx.insets = new Insets(0, 0, 5, 5);
		gbc_remarqueTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_remarqueTbx.gridx = 2;
		gbc_remarqueTbx.gridy = 12;
		getContentPane().add(remarqueTbx, gbc_remarqueTbx);

	}
}
