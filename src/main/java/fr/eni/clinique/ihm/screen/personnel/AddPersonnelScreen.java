package fr.eni.clinique.ihm.screen.personnel;


import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextField;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.EnumRole;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.util.Item;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.common.GenericScreen;
import fr.eni.clinique.ihm.screen.common.JTextFieldLimit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class AddPersonnelScreen extends GenericScreen {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5507847919355001252L;
	
	private JTextField nomTbx;
	private JTextField mdpTbx;
	private PersonnelController personnelController;
	private PersonnelModel personnelModel;
	private JComboBox<Item> comboBox;


	/**
	 * Create the frame.
	 * @param model 
	 */
	public AddPersonnelScreen(PersonnelScreen personnelScreen, PersonnelModel model, PersonnelController controller) {
		super("Ajout du personnel", true, true, true,true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		personnelController = controller;
		personnelModel = model;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_lblNom.gridy = 2;
		getContentPane().add(lblNom, gbc_lblNom);
		
		nomTbx = new JTextField();
		nomTbx.setDocument(new JTextFieldLimit(20));
		GridBagConstraints gbc_nomTbx = new GridBagConstraints();
		gbc_nomTbx.insets = new Insets(0, 0, 5, 5);
		gbc_nomTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomTbx.gridx = 2;
		gbc_nomTbx.gridy = 2;
		getContentPane().add(nomTbx, gbc_nomTbx);
		nomTbx.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot De Passe :");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 1;
		gbc_lblMotDePasse.gridy = 4;
		getContentPane().add(lblMotDePasse, gbc_lblMotDePasse);
		
		mdpTbx = new JTextField();
		mdpTbx.setDocument(new JTextFieldLimit(10));
		GridBagConstraints gbc_mdpTbx = new GridBagConstraints();
		gbc_mdpTbx.insets = new Insets(0, 0, 5, 5);
		gbc_mdpTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_mdpTbx.gridx = 2;
		gbc_mdpTbx.gridy = 4;
		getContentPane().add(mdpTbx, gbc_mdpTbx);
		mdpTbx.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Role :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 6;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JButton validerBtn = new JButton("Valider");
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.newPersonnel(readPersonnel());
					setVisible(false);
					personnelScreen.getTableModel().refresh();
					showSuccessMessage("Personnel ajouté !");
				} catch (ManagerException e) {
					errorOccured(e);
				}		
			}			
		});
		
		
		
		comboBox = new JComboBox<Item>();
		for(EnumRole role : EnumRole.getList()){
			comboBox.addItem(new Item(role.getCode(), role.getLibelle()));
			
		}
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 6;
		getContentPane().add(comboBox, gbc_comboBox);
		
		
		
		
		validerBtn.setIcon(new ImageIcon(AddPersonnelScreen.class.getResource("/images/ico/done_16p.png")));
		GridBagConstraints gbc_validerBtn = new GridBagConstraints();
		gbc_validerBtn.insets = new Insets(0, 0, 0, 5);
		gbc_validerBtn.gridx = 1;
		gbc_validerBtn.gridy = 8;
		getContentPane().add(validerBtn, gbc_validerBtn);
		
		JButton annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					setVisible(false);
			}
		});
		annulerBtn.setIcon(new ImageIcon(AddPersonnelScreen.class.getResource("/images/ico/undo_18p.png")));
		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 0, 5);
		gbc_annulerBtn.gridx = 2;
		gbc_annulerBtn.gridy = 8;
		getContentPane().add(annulerBtn, gbc_annulerBtn);

		pack();
	}
	


	public void showPersonnel(Personnel personnnel) {

		if(personnnel == null){
			personnnel = new Personnel();
		}
		// Rempli les champs de l'ihm :
			nomTbx.setText(ObjectUtil.nullToBlank(personnnel.getNom()).trim());
			mdpTbx.setText(ObjectUtil.nullToBlank(personnnel.getMdp()).trim());
			String libelle = EnumRole.libelleByCode(personnnel.getRole());
			comboBox.setSelectedItem(libelle);		
			
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
		Item selectedItem = (Item) comboBox.getSelectedItem();
		personnel.setRole((String) selectedItem.getId());
		personnel.setArchive(false);
		
		return personnel;
	}



	@Override
	public void processEvent(String eventName, Object eventParam) {
		// TODO Auto-generated method stub
		
	}
	

}
