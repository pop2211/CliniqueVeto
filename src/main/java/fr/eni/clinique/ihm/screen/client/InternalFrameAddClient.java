package fr.eni.clinique.ihm.screen.client;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;

import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.model.ClientModel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;

public class InternalFrameAddClient extends JInternalFrame {
	private JTextField codeTbx;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField nomTbx;
	private JTextField prenomTbx;
	private JTextField adresse1Tbx;
	private JTextField adresse2Tbx;
	private JTextField codePostalTbx;
	private JTextField villeTbx;


	/**
	 * Create the frame.
	 */
	public InternalFrameAddClient(ClientModel model, ClientController controller) {
		setBounds(100, 100, 350, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton validerBtn = new JButton("Valider");
		validerBtn.setIcon(new ImageIcon(InternalFrameAddClient.class.getResource("/images/ico/done_32p.png")));
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		annulerBtn.setIcon(new ImageIcon(InternalFrameAddClient.class.getResource("/images/ico/undo_27p.png")));
		annulerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		annulerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 5;
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
		GridBagConstraints gbc_codeTbx = new GridBagConstraints();
		gbc_codeTbx.gridwidth = 5;
		gbc_codeTbx.insets = new Insets(0, 0, 5, 5);
		gbc_codeTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTbx.gridx = 2;
		gbc_codeTbx.gridy = 3;
		getContentPane().add(codeTbx, gbc_codeTbx);
		codeTbx.setColumns(10);
		
		
		
		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.WEST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 4;
		getContentPane().add(lblNom, gbc_lblNom);
		
		nomTbx = new JTextField();
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
		adresse1Tbx.setColumns(10);
		GridBagConstraints gbc_adresse1Tbx = new GridBagConstraints();
		gbc_adresse1Tbx.gridwidth = 5;
		gbc_adresse1Tbx.insets = new Insets(0, 0, 5, 5);
		gbc_adresse1Tbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresse1Tbx.gridx = 2;
		gbc_adresse1Tbx.gridy = 6;
		getContentPane().add(adresse1Tbx, gbc_adresse1Tbx);
		
		adresse2Tbx = new JTextField();
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
		villeTbx.setColumns(10);
		GridBagConstraints gbc_villeTbx = new GridBagConstraints();
		gbc_villeTbx.gridwidth = 5;
		gbc_villeTbx.insets = new Insets(0, 0, 5, 5);
		gbc_villeTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_villeTbx.gridx = 2;
		gbc_villeTbx.gridy = 9;
		getContentPane().add(villeTbx, gbc_villeTbx);

	}
}
