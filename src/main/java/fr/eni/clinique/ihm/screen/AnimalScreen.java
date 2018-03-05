package fr.eni.clinique.ihm.screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.impl.ClientJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RaceJDBCDAOImpl;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.model.AnimalModel;


import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.client.MainClientScreen;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.io.Console;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class AnimalScreen extends JInternalFrame {
	
	private static final long serialVersionUID = -1179642960437851179L;
	
	private AnimalModel model;
	private AnimalController controller;
	
	private JTextField tatouageTbx;
	private JTextField nomTbx;
	private JTextField couleurTbx;
	private JComboBox<String> especeCbx;
	private JComboBox<String> raceCbx;
	private JComboBox sexeCbx;
	private JLabel recupLblCli;
	private JLabel recupLblAnimal;
	private JTextField antecedentsTbx;
	private Integer codeClient = null;
	RaceDAO raceDAO = new RaceJDBCDAOImpl();
	ClientDAO clientDAO = new ClientJDBCDAOImpl();
	
	public AnimalScreen(AnimalModel model, AnimalController controller) {
		super("Gestion des Animaux", true, true, true,true);
		setBackground(Color.WHITE);
		this.controller = controller;
		this.model = model;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//Animal animal = new Animal("MamandeBambi", "h", "bleu", "symbole", "alcoolique", false , new Race("Worchair", "Chat") , 2);
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 0, 0, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 0, 20, 0, 0, 0, 0, 0, 10, 0, 20, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel labelCli = new JLabel("Client :");
		labelCli.setHorizontalAlignment(SwingConstants.TRAILING);
		labelCli.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_labelCli = new GridBagConstraints();
		gbc_labelCli.anchor = GridBagConstraints.EAST;
		gbc_labelCli.insets = new Insets(0, 0, 5, 5);
		gbc_labelCli.gridx = 2;
		gbc_labelCli.gridy = 2;
		getContentPane().add(labelCli, gbc_labelCli);
		
		recupLblCli = new JLabel("");
		recupLblCli.setHorizontalAlignment(SwingConstants.LEFT);
		recupLblCli.setFont(new Font("Tahoma", Font.BOLD, 12));
		recupLblCli.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_recupLabelClient = new GridBagConstraints();
		gbc_recupLabelClient.insets = new Insets(0, 0, 5, 5);
		gbc_recupLabelClient.gridx = 3;
		gbc_recupLabelClient.gridy = 2;
		getContentPane().add(recupLblCli, gbc_recupLabelClient);
		
		JLabel lblNewLabel = new JLabel("Code");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		recupLblAnimal = new JLabel("");
		recupLblAnimal.setForeground(new Color(124, 252, 0));
		GridBagConstraints gbc_recupLblAnimal = new GridBagConstraints();
		gbc_recupLblAnimal.insets = new Insets(0, 0, 5, 5);
		gbc_recupLblAnimal.gridx = 2;
		gbc_recupLblAnimal.gridy = 4;
		getContentPane().add(recupLblAnimal, gbc_recupLblAnimal);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		nomTbx = new JTextField();
		GridBagConstraints gbc_nomTbx = new GridBagConstraints();
		gbc_nomTbx.insets = new Insets(0, 0, 5, 5);
		gbc_nomTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomTbx.gridx = 2;
		gbc_nomTbx.gridy = 5;
		getContentPane().add(nomTbx, gbc_nomTbx);
		nomTbx.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Couleur");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		couleurTbx = new JTextField();
		GridBagConstraints gbc_couleurTbx = new GridBagConstraints();
		gbc_couleurTbx.insets = new Insets(0, 0, 5, 5);
		gbc_couleurTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_couleurTbx.gridx = 2;
		gbc_couleurTbx.gridy = 6;
		getContentPane().add(couleurTbx, gbc_couleurTbx);
		couleurTbx.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Espèce");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 7;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		especeCbx = new JComboBox();
		
				
		GridBagConstraints gbc_especeCbx = new GridBagConstraints();
		gbc_especeCbx.insets = new Insets(0, 0, 5, 5);
		gbc_especeCbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_especeCbx.gridx = 2;
		gbc_especeCbx.gridy = 7;
		fillEspece(null);
		getContentPane().add(especeCbx, gbc_especeCbx);
		
		JLabel lblNewLabel_4 = new JLabel("Race");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 7;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		raceCbx = new JComboBox();
		GridBagConstraints gbc_raceCbx = new GridBagConstraints();
		gbc_raceCbx.insets = new Insets(0, 0, 5, 5);
		gbc_raceCbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_raceCbx.gridx = 4;
		gbc_raceCbx.gridy = 7;
		fillRace(especeCbx.getItemAt(0),null);
		especeCbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillRace(especeCbx.getSelectedItem().toString(), null);

			}
		});
		getContentPane().add(raceCbx, gbc_raceCbx);
		
		JLabel lblNewLabel_5 = new JLabel("Tatouage");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 8;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		tatouageTbx = new JTextField();
		GridBagConstraints gbc_tatouageTbx = new GridBagConstraints();
		gbc_tatouageTbx.insets = new Insets(0, 0, 5, 5);
		gbc_tatouageTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_tatouageTbx.gridx = 2;
		gbc_tatouageTbx.gridy = 8;
		getContentPane().add(tatouageTbx, gbc_tatouageTbx);
		tatouageTbx.setColumns(10);
		
		JButton validerBtn = new JButton("Valider");

		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if("".equals(recupLblCli.getText()) || recupLblCli.getText() == null){
					try {
						System.out.println(recupLblCli.getText());
						controller.newAnimal(readAnimal());
						showSuccessMessage("Animal ajouté !");
					} catch (Exception e1) {
						showFailureMessage(e1.getMessage());
					}
					
				}else{
					try {
						controller.saveAnimal(readAnimal());
						showSuccessMessage("Animal enregistré !");
					} catch (Exception e1) {
						showFailureMessage(e1.getMessage());
					}
					
				}
			}
		});
		
		JLabel lblAntcd = new JLabel("Antécédents");
		lblAntcd.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAntcd = new GridBagConstraints();
		gbc_lblAntcd.anchor = GridBagConstraints.EAST;
		gbc_lblAntcd.insets = new Insets(0, 0, 5, 5);
		gbc_lblAntcd.gridx = 3;
		gbc_lblAntcd.gridy = 8;
		getContentPane().add(lblAntcd, gbc_lblAntcd);
		
		antecedentsTbx = new JTextField();
		GridBagConstraints gbc_antecedentsTbx = new GridBagConstraints();
		gbc_antecedentsTbx.insets = new Insets(0, 0, 5, 5);
		gbc_antecedentsTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_antecedentsTbx.gridx = 4;
		gbc_antecedentsTbx.gridy = 8;
		getContentPane().add(antecedentsTbx, gbc_antecedentsTbx);
		antecedentsTbx.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblSexe = new GridBagConstraints();
		gbc_lblSexe.anchor = GridBagConstraints.EAST;
		gbc_lblSexe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSexe.gridx = 1;
		gbc_lblSexe.gridy = 9;
		getContentPane().add(lblSexe, gbc_lblSexe);
		
		sexeCbx = new JComboBox();
		sexeCbx.setModel(new DefaultComboBoxModel(new String[] {"M", "F", "H"}));
		GridBagConstraints gbc_sexeCbx = new GridBagConstraints();
		gbc_sexeCbx.insets = new Insets(0, 0, 5, 5);
		gbc_sexeCbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_sexeCbx.gridx = 2;
		gbc_sexeCbx.gridy = 9;
		
		getContentPane().add(sexeCbx, gbc_sexeCbx);
		

		validerBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/done_32p.png")));

		GridBagConstraints gbc_validerBtn = new GridBagConstraints();
		gbc_validerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_validerBtn.gridx = 2;
		gbc_validerBtn.gridy = 10;
		getContentPane().add(validerBtn, gbc_validerBtn);
		
		JButton annulerBtn = new JButton("Annuler");

		annulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Animal currentAnimal = readAnimal();
					Animal reloadedAnimal = controller.loadAnimal(currentAnimal.getCodeAnimal());
					showAnimal(reloadedAnimal);
					showSuccessMessage("Client rechargé !");
				} catch (Exception e1) {
					showFailureMessage(e1.getMessage());
				}
			}
		});

		annulerBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/undo_27p.png")));

		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 4;
		gbc_annulerBtn.gridy = 10;
		getContentPane().add(annulerBtn, gbc_annulerBtn);

	    
		//showAnimal(null);
		
		
		this.pack();
	}
	
	public void fillEspece(String currentEspece){
		try {
			List<String> especes = raceDAO.selectEspeceDistinct();
			for(String espece : especes){
				especeCbx.addItem(espece);
			}
			//modif
			if(currentEspece != null){
				System.out.println(currentEspece);
				especeCbx.setSelectedItem(currentEspece);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillRace(String espece, String currentRace){
		try {
			raceCbx.removeAllItems();
			List<String> races = raceDAO.selectByEspece(espece);
			for(String race : races){
				
				raceCbx.addItem(race);
			}
			if(currentRace != null){
				raceCbx.setSelectedItem(currentRace);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public String recupLabelClient(Integer id){
		String result = null;
		try {
			Client clients = clientDAO.selectById(id);
			result = clients.getNomClient();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
    }
	
	public void showAnimal(Animal animal) {

		if(animal == null){
			animal = new Animal();
		}
		// Rempli les champs de l'ihm :

			recupLblAnimal.setText(String.valueOf(animal.getCodeAnimal()));
			nomTbx.setText(ObjectUtil.nullToBlank(animal.getNomAnimal()).trim());
			couleurTbx.setText(ObjectUtil.nullToBlank(animal.getCouleur()).trim());
			tatouageTbx.setText(ObjectUtil.nullToBlank(animal.getTatouage()).trim());
			antecedentsTbx.setText(ObjectUtil.nullToBlank(animal.getAntecedents()).trim());
			fillEspece(animal.getRace().getEspece());
			fillRace(animal.getRace().getEspece(),animal.getRace().getRace());
			sexeCbx.setSelectedItem(animal.getSexe());
	}

	/**
	 * Read Client From the UI.
	 * 
	 * @return
	 */
	private Animal readAnimal() {
		
		

		Animal animal = new Animal();

		//Recupère les champs de l'ihm :
		
		if(recupLblCli.getText() != "" || recupLblCli.getText() != null){

			System.out.println(recupLblCli.getText());		
			animal.setCodeAnimal(1);
		}

		animal.setNomAnimal(nomTbx.getText().trim());
		animal.setCouleur(couleurTbx.getText().trim());
		Race race = new Race();
		race.setRace(raceCbx.getSelectedItem().toString());
		race.setEspece(especeCbx.getSelectedItem().toString());
		animal.setRace(race);
		animal.setTatouage(tatouageTbx.getText().trim());
		animal.setAntecedents(antecedentsTbx.getText().trim());
		animal.setSexe(sexeCbx.getSelectedItem().toString());
		animal.setArchive(false);
		animal.setCodeClient(1);

		return animal;
	}
	
	/**
	 * Show TechnicalError.
	 * 
	 * @param message
	 */
	public void showFailureMessage(String message) {
		JOptionPane.showMessageDialog(AnimalScreen.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Show Success Message.
	 * 
	 * @param message
	 */
	public void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(AnimalScreen.this, message);
	}

}
