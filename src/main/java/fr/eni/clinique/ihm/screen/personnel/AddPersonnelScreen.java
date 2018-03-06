package fr.eni.clinique.ihm.screen.personnel;


import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.util.ObjectUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AddPersonnelScreen extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5507847919355001252L;
	
	private JTextField nomTbx;
	private JTextField mdpTbx;
	private JTextField roleTbx;


	/**
	 * Create the frame.
	 */
	public AddPersonnelScreen() {
		
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Ajout Personnel");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNom = new JLabel("Nom :");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 3;
		getContentPane().add(lblNom, gbc_lblNom);
		
		nomTbx = new JTextField();
		GridBagConstraints gbc_nomTbx = new GridBagConstraints();
		gbc_nomTbx.insets = new Insets(0, 0, 5, 5);
		gbc_nomTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomTbx.gridx = 2;
		gbc_nomTbx.gridy = 3;
		getContentPane().add(nomTbx, gbc_nomTbx);
		nomTbx.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot De Passe :");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 1;
		gbc_lblMotDePasse.gridy = 5;
		getContentPane().add(lblMotDePasse, gbc_lblMotDePasse);
		
		mdpTbx = new JTextField();
		GridBagConstraints gbc_mdpTbx = new GridBagConstraints();
		gbc_mdpTbx.insets = new Insets(0, 0, 5, 5);
		gbc_mdpTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_mdpTbx.gridx = 2;
		gbc_mdpTbx.gridy = 5;
		getContentPane().add(mdpTbx, gbc_mdpTbx);
		mdpTbx.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Role :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 7;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		roleTbx = new JTextField();
		GridBagConstraints gbc_roleTbx = new GridBagConstraints();
		gbc_roleTbx.insets = new Insets(0, 0, 5, 5);
		gbc_roleTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_roleTbx.gridx = 2;
		gbc_roleTbx.gridy = 7;
		getContentPane().add(roleTbx, gbc_roleTbx);
		roleTbx.setColumns(10);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setIcon(new ImageIcon(AddPersonnelScreen.class.getResource("/images/ico/done_16p.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 9;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.setIcon(new ImageIcon(AddPersonnelScreen.class.getResource("/images/ico/undo_18p.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 9;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		
	}
	
	
	
	public void showPersonnel(Personnel personnnel) {

		if(personnnel == null){
			personnnel = new Personnel();
		}
		// Rempli les champs de l'ihm :
			nomTbx.setText(ObjectUtil.nullToBlank(personnnel.getNom()).trim());
			mdpTbx.setText(ObjectUtil.nullToBlank(personnnel.getMdp()).trim());
			roleTbx.setText(ObjectUtil.nullToBlank(personnnel.getRole()).trim());		
			
	}

	/**
	 * Read Client From the UI.
	 * 
	 * @return
	 */
	private Personnel readPersonnel() {
		
		

		Personnel personnel = new Personnel();



		personnel.setNom(nomTbx.getText().trim());
		personnel.setMdp(mdpTbx.getText().trim());
		personnel.setRole(roleTbx.getText().trim());
		personnel.setArchive(false);
		
		return personnel;
	}
	

}
