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

public class InternalFrameSearchClient extends JInternalFrame {

	
	private static final long serialVersionUID = -7503557272432439802L;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable rechercherTable;
	private JTextField rechercherTbx;

	/**
	 * Create the frame.
	 */
	public InternalFrameSearchClient(ClientModel model, ClientController controller) {
		//setBounds(100, 100, 598, 440);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton rechercherBtn = new JButton("Rechercher");
		rechercherBtn.setIcon(new ImageIcon(InternalFrameSearchClient.class.getResource("/images/ico/search_27p.png")));
		rechercherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		rechercherTbx = new JTextField();
		GridBagConstraints gbc_rechercherTbx = new GridBagConstraints();
		gbc_rechercherTbx.gridwidth = 10;
		gbc_rechercherTbx.insets = new Insets(0, 0, 5, 5);
		gbc_rechercherTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_rechercherTbx.gridx = 1;
		gbc_rechercherTbx.gridy = 1;
		getContentPane().add(rechercherTbx, gbc_rechercherTbx);
		rechercherTbx.setColumns(10);
		rechercherBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		rechercherBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		
		GridBagConstraints gbc_rechercherBtn = new GridBagConstraints();
		gbc_rechercherBtn.insets = new Insets(0, 0, 5, 5);
		gbc_rechercherBtn.gridx = 12;
		gbc_rechercherBtn.gridy = 1;
		getContentPane().add(rechercherBtn, gbc_rechercherBtn);
		
		rechercherTable = new JTable();
		rechercherTable.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_rechercherTable = new GridBagConstraints();
		gbc_rechercherTable.gridheight = 7;
		gbc_rechercherTable.gridwidth = 12;
		gbc_rechercherTable.insets = new Insets(0, 0, 5, 5);
		gbc_rechercherTable.fill = GridBagConstraints.BOTH;
		gbc_rechercherTable.gridx = 1;
		gbc_rechercherTable.gridy = 3;
		getContentPane().add(rechercherTable, gbc_rechercherTable);
		
		this.pack();

	}
}
