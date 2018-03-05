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
import fr.eni.clinique.ihm.screen.common.JTextFieldLimit;

public class MainClientScreen extends GenericClientScreen {

	private static final long serialVersionUID = -9075041539974261255L;

	private MainScreen mainScreen;
	
	private AddClientScreen frameAdd;
	private SearchClientScreen frameSearch;


	public MainClientScreen(ClientModel model, ClientController controller) {
		super("Gestion des Clients", true, true, true, true);
		
		this.controller = controller;
		this.model = model;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 0, 0, 0, 0, 147, 0, 0, 0, 0, 0, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JButton rechercherBtn = new JButton("Rechercher");
		rechercherBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/search_27p.png")));
		rechercherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("getFrameSearch");
				frameSearch = getFrameSearch(model, controller);
				frameSearch.setVisible(true);
			}
		});
		rechercherBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		rechercherBtn.setHorizontalTextPosition(SwingConstants.CENTER);

		GridBagConstraints gbc_rechercherBtn = new GridBagConstraints();
		gbc_rechercherBtn.insets = new Insets(0, 0, 5, 5);
		gbc_rechercherBtn.gridx = 1;
		gbc_rechercherBtn.gridy = 1;
		getContentPane().add(rechercherBtn, gbc_rechercherBtn);

		JButton validerBtn = new JButton("Valider");
		validerBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/done_32p.png")));
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client saveIt = readClient();
					System.out.println( saveIt );
					
					controller.saveClient(saveIt);
					showSuccessMessage("Client enregistré !");
				} catch (Exception e1) {
					showFailureMessage(e1.getMessage());
				}
			}
		});

		JButton ajouterBtn = new JButton("Ajouter");
		ajouterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAdd = getFrameAdd(model, controller);
				frameAdd.setVisible(true);
			}
		});
		ajouterBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/add_27p.png")));
		ajouterBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		ajouterBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_ajouterBtn = new GridBagConstraints();
		gbc_ajouterBtn.insets = new Insets(0, 0, 5, 5);
		gbc_ajouterBtn.gridx = 6;
		gbc_ajouterBtn.gridy = 1;
		getContentPane().add(ajouterBtn, gbc_ajouterBtn);

		JButton supprimerBtn = new JButton("Supprimer");
		supprimerBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/remove_27p.png")));
		supprimerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		supprimerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		supprimerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_supprimerBtn = new GridBagConstraints();
		gbc_supprimerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_supprimerBtn.gridx = 7;
		gbc_supprimerBtn.gridy = 1;
		getContentPane().add(supprimerBtn, gbc_supprimerBtn);
		validerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		validerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_validerBtn = new GridBagConstraints();
		gbc_validerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_validerBtn.gridx = 11;
		gbc_validerBtn.gridy = 1;
		getContentPane().add(validerBtn, gbc_validerBtn);

		JButton annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client currentCli = readClient();
					Client reloadedCli = controller.loadClient(currentCli.getCodeClient());
					showClient(reloadedCli);
					showSuccessMessage("Client rechargé !");
				} catch (Exception e1) {
					showFailureMessage(e1.getMessage());
				}
			}
		});
		annulerBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/undo_27p.png")));
		annulerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		annulerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 12;
		gbc_annulerBtn.gridy = 1;
		getContentPane().add(annulerBtn, gbc_annulerBtn);

		JLabel lblNewLabel = new JLabel("Code");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		codeTbx = new JTextField();
		codeTbx.setEnabled(false);
		GridBagConstraints gbc_codeTbx = new GridBagConstraints();
		gbc_codeTbx.gridwidth = 5;
		gbc_codeTbx.insets = new Insets(0, 0, 5, 5);
		gbc_codeTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTbx.gridx = 2;
		gbc_codeTbx.gridy = 3;
		getContentPane().add(codeTbx, gbc_codeTbx);
		codeTbx.setColumns(10);

		animauxTable = new JTable();
		animauxTable.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_animauxTable = new GridBagConstraints();
		gbc_animauxTable.gridheight = 9;
		gbc_animauxTable.gridwidth = 6;
		gbc_animauxTable.insets = new Insets(0, 0, 5, 5);
		gbc_animauxTable.fill = GridBagConstraints.BOTH;
		gbc_animauxTable.gridx = 7;
		gbc_animauxTable.gridy = 3;
		getContentPane().add(animauxTable, gbc_animauxTable);

		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.WEST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 4;
		getContentPane().add(lblNom, gbc_lblNom);

		nomTbx = new JTextField();
		nomTbx.setDocument(new JTextFieldLimit(20));
		nomTbx.setColumns(10);
		GridBagConstraints gbc_nomTbx = new GridBagConstraints();
		gbc_nomTbx.gridwidth = 5;
		gbc_nomTbx.insets = new Insets(0, 0, 5, 5);
		gbc_nomTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomTbx.gridx = 2;
		gbc_nomTbx.gridy = 4;
		getContentPane().add(nomTbx, gbc_nomTbx);

		JLabel lblPrnom = new JLabel("Prénom");
		GridBagConstraints gbc_lblPrnom = new GridBagConstraints();
		gbc_lblPrnom.anchor = GridBagConstraints.WEST;
		gbc_lblPrnom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrnom.gridx = 1;
		gbc_lblPrnom.gridy = 5;
		getContentPane().add(lblPrnom, gbc_lblPrnom);

		prenomTbx = new JTextField();
		prenomTbx.setColumns(10);
		GridBagConstraints gbc_prenomTbx = new GridBagConstraints();
		gbc_prenomTbx.gridwidth = 5;
		gbc_prenomTbx.insets = new Insets(0, 0, 5, 5);
		gbc_prenomTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_prenomTbx.gridx = 2;
		gbc_prenomTbx.gridy = 5;
		getContentPane().add(prenomTbx, gbc_prenomTbx);

		JLabel lblAdresse = new JLabel("Adresse");
		GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
		gbc_lblAdresse.anchor = GridBagConstraints.WEST;
		gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdresse.gridx = 1;
		gbc_lblAdresse.gridy = 6;
		getContentPane().add(lblAdresse, gbc_lblAdresse);

		adresse1Tbx = new JTextField();
		adresse1Tbx.setDocument(new JTextFieldLimit(30));
		adresse1Tbx.setColumns(10);
		GridBagConstraints gbc_adresse1Tbx = new GridBagConstraints();
		gbc_adresse1Tbx.gridwidth = 5;
		gbc_adresse1Tbx.insets = new Insets(0, 0, 5, 5);
		gbc_adresse1Tbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse1Tbx.gridx = 2;
		gbc_adresse1Tbx.gridy = 6;
		getContentPane().add(adresse1Tbx, gbc_adresse1Tbx);

		adresse2Tbx = new JTextField();
		adresse2Tbx.setDocument(new JTextFieldLimit(5));
		adresse2Tbx.setColumns(10);
		GridBagConstraints gbc_adresse2Tbx = new GridBagConstraints();
		gbc_adresse2Tbx.gridwidth = 5;
		gbc_adresse2Tbx.insets = new Insets(0, 0, 5, 5);
		gbc_adresse2Tbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse2Tbx.gridx = 2;
		gbc_adresse2Tbx.gridy = 7;
		getContentPane().add(adresse2Tbx, gbc_adresse2Tbx);

		JLabel lblCodePostal = new JLabel("Code postal");
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.WEST;
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 1;
		gbc_lblCodePostal.gridy = 8;
		getContentPane().add(lblCodePostal, gbc_lblCodePostal);

		codePostalTbx = new JTextField();
		codePostalTbx.setDocument(new JTextFieldLimit(5));
		codePostalTbx.setColumns(10);
		GridBagConstraints gbc_codePostalTbx = new GridBagConstraints();
		gbc_codePostalTbx.gridwidth = 5;
		gbc_codePostalTbx.insets = new Insets(0, 0, 5, 5);
		gbc_codePostalTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_codePostalTbx.gridx = 2;
		gbc_codePostalTbx.gridy = 8;
		getContentPane().add(codePostalTbx, gbc_codePostalTbx);

		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.WEST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 1;
		gbc_lblVille.gridy = 9;
		getContentPane().add(lblVille, gbc_lblVille);

		villeTbx = new JTextField();
		villeTbx.setDocument(new JTextFieldLimit(25));
		villeTbx.setColumns(10);
		GridBagConstraints gbc_villeTbx = new GridBagConstraints();
		gbc_villeTbx.gridwidth = 5;
		gbc_villeTbx.insets = new Insets(0, 0, 5, 5);
		gbc_villeTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_villeTbx.gridx = 2;
		gbc_villeTbx.gridy = 9;
		getContentPane().add(villeTbx, gbc_villeTbx);

		JLabel lblNumTel = new JLabel("Téléphone");
		GridBagConstraints gbc_lblNumTel = new GridBagConstraints();
		gbc_lblNumTel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumTel.anchor = GridBagConstraints.WEST;
		gbc_lblNumTel.gridx = 1;
		gbc_lblNumTel.gridy = 10;
		getContentPane().add(lblNumTel, gbc_lblNumTel);

		numTelTbx = new JTextField();
		numTelTbx.setDocument(new JTextFieldLimit(15));
		numTelTbx.setText("<dynamic>");
		numTelTbx.setColumns(10);
		GridBagConstraints gbc_numTelTbx = new GridBagConstraints();
		gbc_numTelTbx.gridwidth = 5;
		gbc_numTelTbx.insets = new Insets(0, 0, 5, 5);
		gbc_numTelTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_numTelTbx.gridx = 2;
		gbc_numTelTbx.gridy = 10;
		getContentPane().add(numTelTbx, gbc_numTelTbx);

		JLabel lblAssurance = new JLabel("Assurance");
		GridBagConstraints gbc_lblAssurance = new GridBagConstraints();
		gbc_lblAssurance.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssurance.anchor = GridBagConstraints.WEST;
		gbc_lblAssurance.gridx = 1;
		gbc_lblAssurance.gridy = 11;
		getContentPane().add(lblAssurance, gbc_lblAssurance);

		assuranceTbx = new JTextField();
		assuranceTbx.setDocument(new JTextFieldLimit(30));
		assuranceTbx.setText("<dynamic>");
		assuranceTbx.setColumns(10);
		GridBagConstraints gbc_assuranceTbx = new GridBagConstraints();
		gbc_assuranceTbx.gridwidth = 5;
		gbc_assuranceTbx.insets = new Insets(0, 0, 5, 5);
		gbc_assuranceTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_assuranceTbx.gridx = 2;
		gbc_assuranceTbx.gridy = 11;
		getContentPane().add(assuranceTbx, gbc_assuranceTbx);

		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 12;
		getContentPane().add(lblEmail, gbc_lblEmail);

		emailTbx = new JTextField();
		emailTbx.setDocument(new JTextFieldLimit(20));
		emailTbx.setText("<dynamic>");
		emailTbx.setColumns(10);
		GridBagConstraints gbc_emailTbx = new GridBagConstraints();
		gbc_emailTbx.gridwidth = 5;
		gbc_emailTbx.insets = new Insets(0, 0, 5, 5);
		gbc_emailTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTbx.gridx = 2;
		gbc_emailTbx.gridy = 12;
		getContentPane().add(emailTbx, gbc_emailTbx);

		JButton ajouterAnimalBtn = new JButton("Ajouter");
		ajouterAnimalBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/add_18p.png")));
		ajouterAnimalBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		ajouterAnimalBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_ajouterAnimalBtn = new GridBagConstraints();
		gbc_ajouterAnimalBtn.insets = new Insets(0, 0, 5, 5);
		gbc_ajouterAnimalBtn.gridx = 10;
		gbc_ajouterAnimalBtn.gridy = 12;
		getContentPane().add(ajouterAnimalBtn, gbc_ajouterAnimalBtn);

		JButton supprimerAnimalBtn = new JButton("Supprimer");
		supprimerAnimalBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/remove_18p.png")));
		supprimerAnimalBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		supprimerAnimalBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_supprimerAnimalBtn = new GridBagConstraints();
		gbc_supprimerAnimalBtn.insets = new Insets(0, 0, 5, 5);
		gbc_supprimerAnimalBtn.gridx = 11;
		gbc_supprimerAnimalBtn.gridy = 12;
		getContentPane().add(supprimerAnimalBtn, gbc_supprimerAnimalBtn);

		JButton editerAnimalBtn = new JButton("Editer");
		editerAnimalBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/edit_18p.png")));
		editerAnimalBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		editerAnimalBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_editerAnimalBtn = new GridBagConstraints();
		gbc_editerAnimalBtn.insets = new Insets(0, 0, 5, 5);
		gbc_editerAnimalBtn.gridx = 12;
		gbc_editerAnimalBtn.gridy = 12;
		getContentPane().add(editerAnimalBtn, gbc_editerAnimalBtn);

		JLabel lblRemarque = new JLabel("Remarque");
		GridBagConstraints gbc_lblRemarque = new GridBagConstraints();
		gbc_lblRemarque.anchor = GridBagConstraints.WEST;
		gbc_lblRemarque.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemarque.gridx = 1;
		gbc_lblRemarque.gridy = 13;
		getContentPane().add(lblRemarque, gbc_lblRemarque);

		remarqueTbx = new JTextField();
		remarqueTbx.setText("<dynamic>");
		remarqueTbx.setColumns(10);
		GridBagConstraints gbc_remarqueTbx = new GridBagConstraints();
		gbc_remarqueTbx.gridwidth = 4;
		gbc_remarqueTbx.insets = new Insets(0, 0, 5, 5);
		gbc_remarqueTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_remarqueTbx.gridx = 3;
		gbc_remarqueTbx.gridy = 13;
		getContentPane().add(remarqueTbx, gbc_remarqueTbx);

		

		// test chargement premier client a l'ouverture fenetre
		Client firstClient;
		try {
			firstClient = controller.loadClient(1);
			showClient(firstClient);
		} catch (ManagerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// INIT CONTROLLER
		try {
			controller.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.pack();
		System.out.println("getMainScreen() null at end constructor: "+ getMainScreen());

	}
	
	
	public SearchClientScreen getFrameSearch(ClientModel model, ClientController controller) {
		if (frameSearch == null) {
			frameSearch = new SearchClientScreen(model, controller);
			getMainScreen().getDesktopPane().add(frameSearch);
		}
		return frameSearch;
	}
	
	
	public AddClientScreen getFrameAdd(ClientModel model, ClientController controller) {
		if (frameAdd == null) {
			frameAdd = new AddClientScreen(model, controller);
			getMainScreen().getDesktopPane().add(frameAdd);
		}
		return frameAdd;
	}
	

}