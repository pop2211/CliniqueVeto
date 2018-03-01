package fr.eni.clinique.ihm.screen;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.impl.AnimalJDBCDAOImpl;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalScreen extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1179642960437851179L;
	
	private JTextField tatouageTbx;
	private JTextField codeTbx;
	private JTextField nomTbx;
	private JTextField couleurTbx;
	AnimalDAO animalDAO = new AnimalJDBCDAOImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalScreen frame = new AnimalScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AnimalScreen(PersonnelModel model, PersonnelController controller) {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel labelCli = new JLabel("Client");
		GridBagConstraints gbc_labelCli = new GridBagConstraints();
		gbc_labelCli.insets = new Insets(0, 0, 5, 5);
		gbc_labelCli.gridx = 2;
		gbc_labelCli.gridy = 1;
		getContentPane().add(labelCli, gbc_labelCli);
		
		JLabel recupLabelClient = new JLabel("");
		recupLabelClient.setForeground(SystemColor.activeCaption);
		GridBagConstraints gbc_recupLabelClient = new GridBagConstraints();
		gbc_recupLabelClient.insets = new Insets(0, 0, 5, 5);
		gbc_recupLabelClient.gridx = 2;
		gbc_recupLabelClient.gridy = 2;
		getContentPane().add(recupLabelClient, gbc_recupLabelClient);
		
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
		
		JComboBox especeCbx = new JComboBox();
		GridBagConstraints gbc_especeCbx = new GridBagConstraints();
		gbc_especeCbx.insets = new Insets(0, 0, 5, 5);
		gbc_especeCbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_especeCbx.gridx = 2;
		gbc_especeCbx.gridy = 7;
		especeCbx.addItem("Chat");
		getContentPane().add(especeCbx, gbc_especeCbx);
		
		JLabel lblNewLabel_4 = new JLabel("Race");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 7;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JComboBox raceCbx = new JComboBox();
		GridBagConstraints gbc_raceCbx = new GridBagConstraints();
		gbc_raceCbx.insets = new Insets(0, 0, 5, 5);
		gbc_raceCbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_raceCbx.gridx = 4;
		gbc_raceCbx.gridy = 7;
		try {
			raceCbx.addItem(animalDAO.selectByEspece(especeCbx.getSelectedItem().toString()));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		GridBagConstraints gbc_validerBtn = new GridBagConstraints();
		gbc_validerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_validerBtn.gridx = 2;
		gbc_validerBtn.gridy = 9;
		getContentPane().add(validerBtn, gbc_validerBtn);
		
		JButton annulerBtn = new JButton("Annuler");
		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 4;
		gbc_annulerBtn.gridy = 9;
		getContentPane().add(annulerBtn, gbc_annulerBtn);

	}

}
