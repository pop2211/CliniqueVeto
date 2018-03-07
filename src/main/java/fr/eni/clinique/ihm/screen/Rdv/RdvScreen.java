package fr.eni.clinique.ihm.screen.Rdv;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.ComponentFormatDefaults;
import org.jdatepicker.JDatePicker;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.EnumRole;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.util.Item;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.screen.animal.AnimalScreen;
import fr.eni.clinique.ihm.screen.client.AddClientScreen;
import fr.eni.clinique.ihm.screen.common.GenericScreen;

public class RdvScreen extends GenericScreen {
	
	private static final long serialVersionUID = -6328443080174868948L;
	
	private AddClientScreen frameAddClient;
	private AnimalScreen frameAddAnimal;
	
	
	JComboBox<Item<Integer>> CbxClient;
	JComboBox<Item<Integer>> CbxAnimal;
	
	public RdvScreen() {
		super("Prise de rendez-vous", true, true, true, true);
		controllerClient = new ClientController(modelClient);
		controllerAnimal = new AnimalController(modelAnimal);
		controllerPersonnel = new PersonnelController(modelPersonnel);
		constructionFenetre();
	}
	
	private void constructionFenetre() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{220, 0, 220, 0, 110, 110, 0};
		gridBagLayout.rowHeights = new int[]{30, 130, 35, 191, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_Pour = new JPanel();
		panel_Pour.setMaximumSize(new Dimension(200, 125));
		panel_Pour.setMinimumSize(new Dimension(200, 125));
		panel_Pour.setToolTipText("Pour");
		panel_Pour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Pour", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_Pour = new GridBagConstraints();
		gbc_panel_Pour.fill = GridBagConstraints.BOTH;
		gbc_panel_Pour.insets = new Insets(0, 0, 5, 5);
		gbc_panel_Pour.gridx = 0;
		gbc_panel_Pour.gridy = 1;
		getContentPane().add(panel_Pour, gbc_panel_Pour);
		GridBagLayout gbl_panel_Pour = new GridBagLayout();
		gbl_panel_Pour.columnWidths = new int[]{150, 0, 0};
		gbl_panel_Pour.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Pour.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Pour.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Pour.setLayout(gbl_panel_Pour);
		
		JLabel lblClient = new JLabel("Client");
		GridBagConstraints gbc_lblClient = new GridBagConstraints();
		gbc_lblClient.anchor = GridBagConstraints.WEST;
		gbc_lblClient.insets = new Insets(10, 10, 5, 5);
		gbc_lblClient.gridx = 0;
		gbc_lblClient.gridy = 0;
		panel_Pour.add(lblClient, gbc_lblClient);
		
		CbxClient = new JComboBox<Item<Integer>>();
		chargeClient();
		
		GridBagConstraints gbc_CbxClient = new GridBagConstraints();
		gbc_CbxClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_CbxClient.insets = new Insets(0, 10, 5, 5);
		gbc_CbxClient.gridx = 0;
		gbc_CbxClient.gridy = 1;
		panel_Pour.add(CbxClient, gbc_CbxClient);
		
		JButton BtnAddClient = new JButton("");
		BtnAddClient.setBorderPainted(false);
		BtnAddClient.setContentAreaFilled(false);
		BtnAddClient.setIcon(new ImageIcon(RdvScreen.class.getResource("/images/ico/add_18p.png")));
		BtnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameAddClient = getFrameAddClient();
				frameAddClient.setVisible(true);
			}
		});
		GridBagConstraints gbc_BtnAddClient = new GridBagConstraints();
		gbc_BtnAddClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_BtnAddClient.insets = new Insets(0, 0, 5, 0);
		gbc_BtnAddClient.gridx = 1;
		gbc_BtnAddClient.gridy = 1;
		panel_Pour.add(BtnAddClient, gbc_BtnAddClient);
		
		JLabel labelAnimal = new JLabel("Animal");
		GridBagConstraints gbc_labelAnimal = new GridBagConstraints();
		gbc_labelAnimal.anchor = GridBagConstraints.WEST;
		gbc_labelAnimal.insets = new Insets(0, 10, 5, 5);
		gbc_labelAnimal.gridx = 0;
		gbc_labelAnimal.gridy = 2;
		panel_Pour.add(labelAnimal, gbc_labelAnimal);
		
		CbxAnimal = new JComboBox<Item<Integer>>();
		try {
			if(CbxClient.getItemCount() > 0 ){
				List<Animal> animaux = controllerAnimal.loadAnimalByMaitre(CbxClient.getItemAt(0).getId());
				
				if(!animaux.isEmpty()) {
					for (Animal animal : animaux) {
						CbxAnimal.addItem( new Item<Integer>(animal.getCodeAnimal(), animal.getNomAnimal()));
					}
				}
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		CbxAnimal.setMaximumSize(new Dimension(125, 20));
		CbxAnimal.setMinimumSize(new Dimension(125, 20));
		GridBagConstraints gbc_CbXAnimal = new GridBagConstraints();
		gbc_CbXAnimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_CbXAnimal.insets = new Insets(0, 10, 5, 5);
		gbc_CbXAnimal.gridx = 0;
		gbc_CbXAnimal.gridy = 3;
		panel_Pour.add(CbxAnimal, gbc_CbXAnimal);
		
		JButton BtnAddAnimal = new JButton("");
		BtnAddAnimal.setBorderPainted(false);
		BtnAddAnimal.setContentAreaFilled(false);
		BtnAddAnimal.setIcon(new ImageIcon(RdvScreen.class.getResource("/images/ico/add_18p.png")));
		BtnAddAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer CodeCli = null;
				if (CbxClient.getSelectedItem() != null) {
					CodeCli = ((Item<Integer>) CbxClient.getSelectedItem()).getId();
				}
				frameAddAnimal = getFrameAddAnimal(CodeCli);
				frameAddAnimal.setVisible(true);
			}
		});
		GridBagConstraints gbc_BtnAddAnimal = new GridBagConstraints();
		gbc_BtnAddAnimal.insets = new Insets(0, 0, 5, 0);
		gbc_BtnAddAnimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_BtnAddAnimal.gridx = 1;
		gbc_BtnAddAnimal.gridy = 3;
		panel_Pour.add(BtnAddAnimal, gbc_BtnAddAnimal);
		
		JPanel panel_Par = new JPanel();
		panel_Par.setMinimumSize(new Dimension(200, 125));
		panel_Par.setMaximumSize(new Dimension(200, 125));
		panel_Par.setToolTipText("Par");
		panel_Par.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Par", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_Par = new GridBagConstraints();
		gbc_panel_Par.fill = GridBagConstraints.BOTH;
		gbc_panel_Par.insets = new Insets(0, 0, 5, 5);
		gbc_panel_Par.gridx = 2;
		gbc_panel_Par.gridy = 1;
		getContentPane().add(panel_Par, gbc_panel_Par);
		GridBagLayout gbl_panel_Par = new GridBagLayout();
		gbl_panel_Par.columnWidths = new int[]{150, 0};
		gbl_panel_Par.rowHeights = new int[]{0, 0, 0};
		gbl_panel_Par.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_Par.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Par.setLayout(gbl_panel_Par);
		
		JLabel labelVeterinaire = new JLabel("Veterinaire");
		GridBagConstraints gbc_labelVeterinaire = new GridBagConstraints();
		gbc_labelVeterinaire.anchor = GridBagConstraints.WEST;
		gbc_labelVeterinaire.insets = new Insets(10, 10, 5, 0);
		gbc_labelVeterinaire.gridx = 0;
		gbc_labelVeterinaire.gridy = 0;
		panel_Par.add(labelVeterinaire, gbc_labelVeterinaire);
		
		JComboBox<Item<Integer>> CbxVeterinaire = new JComboBox<Item<Integer>>();
		try {
			List<Personnel> vetos = controllerPersonnel.loadPersonnelByRole(EnumRole.VETERINAIRE.getCode());
			
			if(!vetos.isEmpty()) {
				for (Personnel veto : vetos) {
					CbxVeterinaire.addItem( new Item<Integer>(veto.getCodePers(), veto.getNom()));
				}
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		CbxVeterinaire.setMaximumSize(new Dimension(125, 20));
		CbxVeterinaire.setMinimumSize(new Dimension(125, 20));
		
		CbxVeterinaire.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_CbxVeterinaire = new GridBagConstraints();
		gbc_CbxVeterinaire.fill = GridBagConstraints.HORIZONTAL;
		gbc_CbxVeterinaire.insets = new Insets(0, 10, 0, 0);
		gbc_CbxVeterinaire.gridx = 0;
		gbc_CbxVeterinaire.gridy = 1;
		panel_Par.add(CbxVeterinaire, gbc_CbxVeterinaire);
		
		JPanel panelQuand = new JPanel();
		panelQuand.setMinimumSize(new Dimension(200, 125));
		panelQuand.setMaximumSize(new Dimension(200, 125));
		panelQuand.setToolTipText("Quand");
		panelQuand.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Quand", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelQuand = new GridBagConstraints();
		gbc_panelQuand.gridwidth = 2;
		gbc_panelQuand.fill = GridBagConstraints.BOTH;
		gbc_panelQuand.insets = new Insets(0, 0, 5, 0);
		gbc_panelQuand.gridx = 4;
		gbc_panelQuand.gridy = 1;
		getContentPane().add(panelQuand, gbc_panelQuand);
		GridBagLayout gbl_panelQuand = new GridBagLayout();
		gbl_panelQuand.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelQuand.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelQuand.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelQuand.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelQuand.setLayout(gbl_panelQuand);
		
		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 10, 5, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 0;
		panelQuand.add(lblDate, gbc_lblDate);
		
		//Paramètre du Datepicker
		ComponentFormatDefaults defaults = ComponentFormatDefaults.getInstance();
		defaults.setFormat(ComponentFormatDefaults.Key.TODAY_SELECTOR, new SimpleDateFormat("EEE dd/MM/yyyy"));
        defaults.setFormat(ComponentFormatDefaults.Key.DOW_HEADER, new SimpleDateFormat("EEEE"));
        defaults.setFormat(ComponentFormatDefaults.Key.SELECTED_DATE_FIELD, new SimpleDateFormat("dd/MM/yyyy"));
        defaults.setFormat(ComponentFormatDefaults.Key.MONTH_SELECTOR, new SimpleDateFormat("MMM"));
        
        JDatePicker datePicker = new JDatePicker();
        datePicker.getFormattedTextField().setColumns(1);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 10, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panelQuand.add(datePicker, gbc_textField);
		
		JLabel lblHeure = new JLabel("Heure");
		GridBagConstraints gbc_lblHeure = new GridBagConstraints();
		gbc_lblHeure.anchor = GridBagConstraints.WEST;
		gbc_lblHeure.insets = new Insets(0, 10, 5, 5);
		gbc_lblHeure.gridx = 0;
		gbc_lblHeure.gridy = 2;
		panelQuand.add(lblHeure, gbc_lblHeure);
		
		JComboBox<String> cbxHeure = new JComboBox<String>();
		cbxHeure.setModel(new DefaultComboBoxModel<String>(new String[] {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 10, 0, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 3;
		panelQuand.add(cbxHeure, gbc_comboBox);
		
		JLabel labelH = new JLabel("h");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 3;
		panelQuand.add(labelH, gbc_label);
		
		JComboBox<String> cbxMinute = new JComboBox<String>();
		cbxMinute.setModel(new DefaultComboBoxModel<String>(new String[] {"0", "15", "30", "45"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.anchor = GridBagConstraints.WEST;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 3;
		panelQuand.add(cbxMinute, gbc_comboBox_1);
		
		JTable tableClient = new JTable();
		GridBagConstraints gbc_tableClient = new GridBagConstraints();
		gbc_tableClient.insets = new Insets(0, 0, 5, 5);
		gbc_tableClient.fill = GridBagConstraints.VERTICAL;
		gbc_tableClient.gridwidth = 6;
		gbc_tableClient.gridx = 0;
		gbc_tableClient.gridy = 3;
		getContentPane().add(tableClient, gbc_tableClient);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setMinimumSize(new Dimension(100, 23));
		btnSupprimer.setMaximumSize(new Dimension(100, 23));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnValider = new JButton("Valider");
		btnValider.setMinimumSize(new Dimension(100, 23));
		btnValider.setMaximumSize(new Dimension(100, 23));
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnValider.insets = new Insets(0, 0, 15, 5);
		gbc_btnValider.gridx = 4;
		gbc_btnValider.gridy = 4;
		getContentPane().add(btnValider, gbc_btnValider);
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.insets = new Insets(0, 0, 15, 10);
		gbc_btnSupprimer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSupprimer.gridx = 5;
		gbc_btnSupprimer.gridy = 4;
		getContentPane().add(btnSupprimer, gbc_btnSupprimer);
		
		this.pack();
		
	}
	
	private void chargeClient() {
		try {
			List<Client> clients = controllerClient.loadAllClient();
			
			if(!clients.isEmpty()) {
				CbxClient.removeAllItems();
				for (Client client : clients) {
					CbxClient.addItem( new Item<Integer>(client.getCodeClient(), client.getFullname()));
				}
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		CbxClient.setMaximumSize(new Dimension(125, 20));
		CbxClient.setMinimumSize(new Dimension(125, 20));
		CbxClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CbxClient.getItemCount() > 0) {
					chargeAnimaux(((Item<Integer>) CbxClient.getSelectedItem()).getId());
				}
			}
		});
	}

	private void chargeAnimaux(int CodeClient) {
		List<Animal> animaux;
		try {
			CbxAnimal.removeAllItems();
			animaux = controllerAnimal.loadAnimalByMaitre(CodeClient);
			if(!animaux.isEmpty()) {
				for (Animal animal : animaux) {
					CbxAnimal.addItem(new Item<Integer>(animal.getCodeAnimal(), animal.getNomAnimal()));
				}
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void processEvent(String eventName, Object eventParam) {
		switch(eventName){
		case "AddClient":
			chargeClient();
		break;
		case "AddAnimal":
			//chargeAnimaux();
		}
	}

	/*
	private Rdv readRdv() {

		Rdv rdv = new Rdv();
		
		//Recupère les champs de l'ihm :
		animal.setCodeAnimal((Integer.parseInt(recupLblAnimal.getText())));
		animal.setNomAnimal(nomTbx.getText().trim());
		animal.setCouleur(couleurTbx.getText().trim());
		Race race = new Race();
		race.setRace(raceCbx.getSelectedItem().toString());
		race.setEspece(especeCbx.getSelectedItem().toString());
		animal.setRace(race);
		animal.setTatouage(tatouageTbx.getText().trim());

		return animal;
	}
	 */
	public AddClientScreen getFrameAddClient() {
		if (frameAddClient == null) {
			frameAddClient = new AddClientScreen((GenericScreen)this);
			getMainScreen().getDesktopPane().add(frameAddClient);
		}
		return frameAddClient;
	}
	
	public AnimalScreen getFrameAddAnimal(Integer CodeCli) {
		if (frameAddAnimal == null) {
			
			frameAddAnimal = new AnimalScreen((GenericScreen)this, CodeCli);
			getMainScreen().getDesktopPane().add(frameAddAnimal);
		}
		return frameAddAnimal;
	}
}
