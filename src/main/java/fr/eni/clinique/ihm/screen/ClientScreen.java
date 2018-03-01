package fr.eni.clinique.ihm.screen;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;
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

public class ClientScreen extends JInternalFrame {
	private JTextField codeTbx;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientScreen frame = new ClientScreen();
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
	public ClientScreen() {
		setBounds(100, 100, 598, 440);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton rechercherBtn = new JButton("Rechercher");
		rechercherBtn.setIcon(new ImageIcon(ClientScreen.class.getResource("/images/ico/search_27p.png")));
		rechercherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		validerBtn.setIcon(new ImageIcon(ClientScreen.class.getResource("/images/ico/done_32p.png")));
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton ajouterBtn = new JButton("Ajouter");
		ajouterBtn.setIcon(new ImageIcon(ClientScreen.class.getResource("/images/ico/add_27p.png")));
		ajouterBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		ajouterBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_ajouterBtn = new GridBagConstraints();
		gbc_ajouterBtn.insets = new Insets(0, 0, 5, 5);
		gbc_ajouterBtn.gridx = 6;
		gbc_ajouterBtn.gridy = 1;
		getContentPane().add(ajouterBtn, gbc_ajouterBtn);
		
		JButton supprimerBtn = new JButton("Supprimer");
		supprimerBtn.setIcon(new ImageIcon(ClientScreen.class.getResource("/images/ico/remove_27p.png")));
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
			}
		});
		annulerBtn.setIcon(new ImageIcon(ClientScreen.class.getResource("/images/ico/undo_27p.png")));
		annulerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		annulerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 12;
		gbc_annulerBtn.gridy = 1;
		getContentPane().add(annulerBtn, gbc_annulerBtn);
		
		JLabel lblNewLabel = new JLabel("Code");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		codeTbx = new JTextField();
		GridBagConstraints gbc_codeTbx = new GridBagConstraints();
		gbc_codeTbx.gridwidth = 4;
		gbc_codeTbx.insets = new Insets(0, 0, 5, 5);
		gbc_codeTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTbx.gridx = 3;
		gbc_codeTbx.gridy = 4;
		getContentPane().add(codeTbx, gbc_codeTbx);
		codeTbx.setColumns(10);
		
		
		
		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 5;
		getContentPane().add(lblNom, gbc_lblNom);
		
		JLabel lblPrnom = new JLabel("Prénom");
		GridBagConstraints gbc_lblPrnom = new GridBagConstraints();
		gbc_lblPrnom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrnom.gridx = 1;
		gbc_lblPrnom.gridy = 6;
		getContentPane().add(lblPrnom, gbc_lblPrnom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
		gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdresse.gridx = 1;
		gbc_lblAdresse.gridy = 7;
		getContentPane().add(lblAdresse, gbc_lblAdresse);
		
		JLabel lblCodePostal = new JLabel("Code postal");
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 1;
		gbc_lblCodePostal.gridy = 9;
		getContentPane().add(lblCodePostal, gbc_lblCodePostal);
		
		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.insets = new Insets(0, 0, 0, 5);
		gbc_lblVille.gridx = 1;
		gbc_lblVille.gridy = 10;
		getContentPane().add(lblVille, gbc_lblVille);

	}
}
