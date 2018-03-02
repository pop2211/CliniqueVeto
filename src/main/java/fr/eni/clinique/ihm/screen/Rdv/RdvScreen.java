package fr.eni.clinique.ihm.screen.Rdv;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.JDatePicker;

import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;

public class RdvScreen extends JInternalFrame {
	
	private static final long serialVersionUID = -6328443080174868948L;

	private PersonnelModel model;
	private PersonnelController controller;
	private JTextField textField;
	
	public RdvScreen(PersonnelModel model, PersonnelController controller) {
		super("Prise de rendez-vous", true, true, true,true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{225, 20, 225, 20, 225};
		gridBagLayout.rowHeights = new int[]{30, 100, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_Pour = new JPanel();
		panel_Pour.setToolTipText("Pour");
		panel_Pour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Pour", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_Pour = new GridBagConstraints();
		gbc_panel_Pour.insets = new Insets(0, 0, 5, 5);
		gbc_panel_Pour.fill = GridBagConstraints.BOTH;
		gbc_panel_Pour.gridx = 0;
		gbc_panel_Pour.gridy = 1;
		getContentPane().add(panel_Pour, gbc_panel_Pour);
		GridBagLayout gbl_panel_Pour = new GridBagLayout();
		gbl_panel_Pour.columnWidths = new int[]{10, 125, 36, 0};
		gbl_panel_Pour.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Pour.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Pour.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Pour.setLayout(gbl_panel_Pour);
		
		JLabel lblClient = new JLabel("Client");
		GridBagConstraints gbc_lblClient = new GridBagConstraints();
		gbc_lblClient.anchor = GridBagConstraints.WEST;
		gbc_lblClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblClient.gridx = 1;
		gbc_lblClient.gridy = 0;
		panel_Pour.add(lblClient, gbc_lblClient);
		
		JComboBox CbxClient = new JComboBox();
		GridBagConstraints gbc_CbxClient = new GridBagConstraints();
		gbc_CbxClient.insets = new Insets(0, 0, 5, 5);
		gbc_CbxClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_CbxClient.gridx = 1;
		gbc_CbxClient.gridy = 1;
		panel_Pour.add(CbxClient, gbc_CbxClient);
		
		JButton BtnAddClient = new JButton("");
		BtnAddClient.setBorderPainted(false);
		BtnAddClient.setContentAreaFilled(false);
		BtnAddClient.setIcon(new ImageIcon(RdvScreen.class.getResource("/images/ico/add_18p.png")));
		GridBagConstraints gbc_BtnAddClient = new GridBagConstraints();
		gbc_BtnAddClient.insets = new Insets(0, 0, 5, 0);
		gbc_BtnAddClient.gridx = 2;
		gbc_BtnAddClient.gridy = 1;
		panel_Pour.add(BtnAddClient, gbc_BtnAddClient);
		
		JLabel labelAnimal = new JLabel("Animal");
		GridBagConstraints gbc_labelAnimal = new GridBagConstraints();
		gbc_labelAnimal.anchor = GridBagConstraints.WEST;
		gbc_labelAnimal.insets = new Insets(0, 0, 5, 5);
		gbc_labelAnimal.gridx = 1;
		gbc_labelAnimal.gridy = 2;
		panel_Pour.add(labelAnimal, gbc_labelAnimal);
		
		JComboBox CbXAnimal = new JComboBox();
		GridBagConstraints gbc_CbXAnimal = new GridBagConstraints();
		gbc_CbXAnimal.insets = new Insets(0, 0, 0, 5);
		gbc_CbXAnimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_CbXAnimal.gridx = 1;
		gbc_CbXAnimal.gridy = 3;
		panel_Pour.add(CbXAnimal, gbc_CbXAnimal);
		
		JButton BtnAddAnimal = new JButton("");
		BtnAddAnimal.setBorderPainted(false);
		BtnAddAnimal.setContentAreaFilled(false);
		BtnAddAnimal.setIcon(new ImageIcon(RdvScreen.class.getResource("/images/ico/add_18p.png")));
		GridBagConstraints gbc_BtnAddAnimal = new GridBagConstraints();
		gbc_BtnAddAnimal.gridx = 2;
		gbc_BtnAddAnimal.gridy = 3;
		panel_Pour.add(BtnAddAnimal, gbc_BtnAddAnimal);
		
		JPanel panel_Par = new JPanel();
		panel_Par.setToolTipText("Par");
		panel_Par.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Par", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_Par = new GridBagConstraints();
		gbc_panel_Par.insets = new Insets(0, 0, 5, 5);
		gbc_panel_Par.fill = GridBagConstraints.BOTH;
		gbc_panel_Par.gridx = 2;
		gbc_panel_Par.gridy = 1;
		getContentPane().add(panel_Par, gbc_panel_Par);
		GridBagLayout gbl_panel_Par = new GridBagLayout();
		gbl_panel_Par.columnWidths = new int[]{25, 175, 25, 0};
		gbl_panel_Par.rowHeights = new int[]{0, 0, 0};
		gbl_panel_Par.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Par.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Par.setLayout(gbl_panel_Par);
		
		JLabel labelVeterinaire = new JLabel("Client");
		GridBagConstraints gbc_labelVeterinaire = new GridBagConstraints();
		gbc_labelVeterinaire.anchor = GridBagConstraints.WEST;
		gbc_labelVeterinaire.insets = new Insets(0, 0, 5, 5);
		gbc_labelVeterinaire.gridx = 1;
		gbc_labelVeterinaire.gridy = 0;
		panel_Par.add(labelVeterinaire, gbc_labelVeterinaire);
		
		JComboBox CbxVeterinaire = new JComboBox();
		GridBagConstraints gbc_CbxVeterinaire = new GridBagConstraints();
		gbc_CbxVeterinaire.insets = new Insets(0, 0, 0, 5);
		gbc_CbxVeterinaire.fill = GridBagConstraints.HORIZONTAL;
		gbc_CbxVeterinaire.gridx = 1;
		gbc_CbxVeterinaire.gridy = 1;
		panel_Par.add(CbxVeterinaire, gbc_CbxVeterinaire);
		
		JPanel panelQuand = new JPanel();
		panelQuand.setToolTipText("Quand");
		panelQuand.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Quand", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelQuand = new GridBagConstraints();
		gbc_panelQuand.insets = new Insets(0, 0, 5, 0);
		gbc_panelQuand.fill = GridBagConstraints.BOTH;
		gbc_panelQuand.gridx = 4;
		gbc_panelQuand.gridy = 1;
		getContentPane().add(panelQuand, gbc_panelQuand);
		GridBagLayout gbl_panelQuand = new GridBagLayout();
		gbl_panelQuand.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelQuand.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelQuand.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelQuand.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelQuand.setLayout(gbl_panelQuand);
		
		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 2;
		gbc_lblDate.gridy = 0;
		panelQuand.add(lblDate, gbc_lblDate);
		
		/*Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		JDatePanel datePanel = new JDatePanel();
		*/
		JDatePicker datePicker = new JDatePicker();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panelQuand.add(datePicker, gbc_textField);
		
		JLabel lblHeure = new JLabel("Heure");
		GridBagConstraints gbc_lblHeure = new GridBagConstraints();
		gbc_lblHeure.anchor = GridBagConstraints.WEST;
		gbc_lblHeure.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeure.gridx = 2;
		gbc_lblHeure.gridy = 2;
		panelQuand.add(lblHeure, gbc_lblHeure);
		
		JComboBox cbxHeure = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		panelQuand.add(cbxHeure, gbc_comboBox);
		
		JLabel labelH = new JLabel("h");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 3;
		panelQuand.add(labelH, gbc_label);
		
		JComboBox cbxMinute = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_1.gridx = 4;
		gbc_comboBox_1.gridy = 3;
		panelQuand.add(cbxMinute, gbc_comboBox_1);
		
		this.pack();

	}
}
