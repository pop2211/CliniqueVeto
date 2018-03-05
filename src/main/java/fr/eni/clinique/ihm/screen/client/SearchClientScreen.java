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

public class SearchClientScreen extends JInternalFrame {


	private static final long serialVersionUID = -5415116920585998148L;
	
	private JTextField rechercherTbx;

	/**
	 * Create the frame.
	 */
	public SearchClientScreen(ClientModel model, ClientController controller) {
		//setBounds(100, 100, 598, 440);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton rechercherBtn = new JButton("Rechercher");
		rechercherBtn.setIcon(new ImageIcon(SearchClientScreen.class.getResource("/images/ico/search_27p.png")));
		rechercherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		rechercherTbx = new JTextField();
		GridBagConstraints gbc_rechercherTbx = new GridBagConstraints();
		gbc_rechercherTbx.insets = new Insets(0, 0, 5, 5);
		gbc_rechercherTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_rechercherTbx.gridx = 1;
		gbc_rechercherTbx.gridy = 5;
		getContentPane().add(rechercherTbx, gbc_rechercherTbx);
		rechercherTbx.setColumns(10);
		rechercherBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		rechercherBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		
		GridBagConstraints gbc_rechercherBtn = new GridBagConstraints();
		gbc_rechercherBtn.insets = new Insets(0, 0, 5, 5);
		gbc_rechercherBtn.gridx = 2;
		gbc_rechercherBtn.gridy = 5;
		getContentPane().add(rechercherBtn, gbc_rechercherBtn);
		
		this.pack();

	}
}
