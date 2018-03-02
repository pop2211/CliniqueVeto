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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.impl.ClientJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RaceJDBCDAOImpl;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.AnimalModel;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.client.ClientScreen;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.io.Console;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AnimalScreen extends JInternalFrame {
	
	private static final long serialVersionUID = -1179642960437851179L;
	
	private AnimalModel model;
	private AnimalController controller;
	
	private JTextField tatouageTbx;
	private JTextField codeTbx;
	private JTextField nomTbx;
	private JTextField couleurTbx;
	private JComboBox<String> especeCbx;
	private JComboBox<String> raceCbx;
	private JLabel recupLblCli;
	private Integer codeClient = null;
	RaceDAO raceDAO = new RaceJDBCDAOImpl();
	ClientDAO clientDAO = new ClientJDBCDAOImpl();
	
	public AnimalScreen(AnimalModel model, AnimalController controller) {
		super("Gestion des Animaux", true, true, true,true);
		setBackground(Color.WHITE);
		this.controller = controller;
		this.model = model;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
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
		
		recupLblCli = new JLabel(recupLabelClient(2));
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
		
		codeTbx = new JTextField();
		GridBagConstraints gbc_codeTbx = new GridBagConstraints();
		gbc_codeTbx.insets = new Insets(0, 0, 5, 5);
		gbc_codeTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTbx.gridx = 2;
		gbc_codeTbx.gridy = 4;
		getContentPane().add(codeTbx, gbc_codeTbx);
		codeTbx.setColumns(10);
		
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
		fillEspece();
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
		fillRace(especeCbx.getItemAt(0));
		especeCbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillRace(especeCbx.getSelectedItem().toString());

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
		validerBtn.setIcon(new ImageIcon(ClientScreen.class.getResource("/images/ico/done_32p.png")));
		GridBagConstraints gbc_validerBtn = new GridBagConstraints();
		gbc_validerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_validerBtn.gridx = 2;
		gbc_validerBtn.gridy = 10;
		getContentPane().add(validerBtn, gbc_validerBtn);
		
		JButton annulerBtn = new JButton("Annuler");
		annulerBtn.setIcon(new ImageIcon(ClientScreen.class.getResource("/images/ico/undo_27p.png")));
		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 4;
		gbc_annulerBtn.gridy = 10;
		getContentPane().add(annulerBtn, gbc_annulerBtn);

	    
		
		
		
		this.pack();
	}
	
	public void fillEspece(){
		try {
			List<String> especes = raceDAO.selectEspeceDistinct();
			for(String espece : especes){
				especeCbx.addItem(espece);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillRace(String espece){
		try {
			raceCbx.removeAllItems();
			List<String> races = raceDAO.selectByEspece(espece);
			for(String race : races){
				
				raceCbx.addItem(race);
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

}
