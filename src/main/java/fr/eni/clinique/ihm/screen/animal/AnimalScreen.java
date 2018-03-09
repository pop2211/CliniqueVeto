package fr.eni.clinique.ihm.screen.animal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.common.util.StringUtil;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.impl.ClientJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RaceJDBCDAOImpl;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.model.AnimalModel;
import fr.eni.clinique.ihm.screen.client.MainClientScreen;
import fr.eni.clinique.ihm.screen.common.GenericScreen;
import fr.eni.clinique.ihm.screen.common.JTextFieldLimit;

public class AnimalScreen extends GenericScreen {
	
	//Screen to create new animal or edit existing one
	
	private static final long serialVersionUID = -1179642960437851179L;
	
	private JTextField tatouageTbx;
	private JTextField nomTbx;
	private JTextField couleurTbx;
	private JComboBox<String> especeCbx;
	private JComboBox<String> raceCbx;
	private JComboBox sexeCbx;
	private JLabel recupLblCli;
	private JLabel recupLblAnimal;
	private JLabel lblNewLabel;
	private JTextField antecedentsTbx;
	private Integer codeClient = null;
	RaceDAO raceDAO = new RaceJDBCDAOImpl();
	ClientDAO clientDAO = new ClientJDBCDAOImpl();
	
	
	
	public AnimalScreen(GenericScreen parentScreen, Integer CodeCli) {
		super("Gestion des Animaux", true, true, true,true);
		setBackground(Color.WHITE);
		
		this.parentScreen = parentScreen;
		
        this.modelAnimal = new AnimalModel();
		this.controllerAnimal = new AnimalController(this.modelAnimal);
		codeClient = CodeCli;
		
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
		
		recupLblCli = new JLabel("");
		recupLblCli.setHorizontalAlignment(SwingConstants.LEFT);
		recupLblCli.setFont(new Font("Tahoma", Font.BOLD, 12));
		recupLblCli.setForeground(new Color(255, 0, 0));
		setLabelClient(codeClient);
		GridBagConstraints gbc_recupLabelClient = new GridBagConstraints();
		gbc_recupLabelClient.insets = new Insets(0, 0, 5, 5);
		gbc_recupLabelClient.gridx = 3;
		gbc_recupLabelClient.gridy = 2;
		getContentPane().add(recupLblCli, gbc_recupLabelClient);
		
		lblNewLabel = new JLabel("Code");
		lblNewLabel.setVisible(false);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	
		recupLblAnimal = new JLabel("");
		recupLblAnimal.setHorizontalAlignment(SwingConstants.LEFT);
		recupLblAnimal.setForeground(new Color(163,163,163));
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
		nomTbx.setDocument(new JTextFieldLimit(30));
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
		couleurTbx.setDocument(new JTextFieldLimit(20));
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
				String selectedEspece = null;
				if(especeCbx.getSelectedItem() != null){
					selectedEspece = especeCbx.getSelectedItem().toString();
				}
				fillRace(selectedEspece, null);
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
		tatouageTbx.setDocument(new JTextFieldLimit(10));
		GridBagConstraints gbc_tatouageTbx = new GridBagConstraints();
		gbc_tatouageTbx.insets = new Insets(0, 0, 5, 5);
		gbc_tatouageTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_tatouageTbx.gridx = 2;
		gbc_tatouageTbx.gridy = 8;
		getContentPane().add(tatouageTbx, gbc_tatouageTbx);
		tatouageTbx.setColumns(10);
		
		// Validation de l'ajout ou de la modification
		
		JButton validerBtn = new JButton("Valider");
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(StringUtil.isNull(recupLblAnimal.getText())) {
						controllerAnimal.newAnimal(readAnimal());
						parentScreen.processEvent("AddAnimal", null);
						setVisible(false);
						showSuccessMessage("Animal enregistré !");
					}
					else {
						controllerAnimal.saveAnimal(readAnimal());
						
						parentScreen.processEvent("UpdateAnimal", null);
						setVisible(false);
						showSuccessMessage("Animal mise à jour !");
					}
				} catch (Exception e) {
					errorOccured(e);
				}
			}
		});
		
		/*JLabel lblAntcd = new JLabel("Antécédents");
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
		antecedentsTbx.setColumns(10);*/
		
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
		
		//Ferme la fenetre en cas de nouvel animal, reviens à l'animal d'origine en cas de modification
		
		JButton annulerBtn = new JButton("Annuler");

		annulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codeAnimal = recupLblAnimal.getText();
				if(StringUtil.isNull(codeAnimal)){
					//annule ajout animal
					setVisible(false);
				}else{
					try {
						//annule edition animal existant et recharge les champs
						Animal currentAnimal = readAnimal();
						Animal reloadedAnimal = controllerAnimal.loadAnimal(currentAnimal.getCodeAnimal());
						showAnimal(reloadedAnimal);
						showSuccessMessage("Animal rechargé !");
					} catch (Exception e1) {
						errorOccured(e1);
					}
				}
			}
		});

		annulerBtn.setIcon(new ImageIcon(MainClientScreen.class.getResource("/images/ico/undo_27p.png")));

		GridBagConstraints gbc_annulerBtn = new GridBagConstraints();
		gbc_annulerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_annulerBtn.gridx = 4;
		gbc_annulerBtn.gridy = 10;
		getContentPane().add(annulerBtn, gbc_annulerBtn);
		
		this.pack();
	}
	
	public void fillEspece(String currentEspece){
		try {
			especeCbx.removeAllItems();
			List<String> especes = raceDAO.selectEspeceDistinct();
			for(String espece : especes){
				especeCbx.addItem(espece);
			}
			//modif
			if(currentEspece != null){
				especeCbx.setSelectedItem(currentEspece);
			}
		} catch (DaoException e) {
			errorOccured(e);
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
			errorOccured(e);
		}
    }
	
	public void setLabelClient(Integer id){
		String result = null;
		try {
			Client clients = clientDAO.selectById(id);
			result = clients.getFullname();
			recupLblCli.setText(result);
		} catch (DaoException e) {
			errorOccured(e);
		}
    }
	
	public void showAnimal(Animal animal) {

		if(animal == null){
			animal = new Animal();
		}
		lblNewLabel.setVisible(true);
		// Rempli les champs de l'ihm :
		recupLblAnimal.setText(String.valueOf(animal.getCodeAnimal()));
		nomTbx.setText(ObjectUtil.nullToBlank(animal.getNomAnimal()).trim());
		couleurTbx.setText(ObjectUtil.nullToBlank(animal.getCouleur()).trim());
		tatouageTbx.setText(ObjectUtil.nullToBlank(animal.getTatouage()).trim());
		//antecedentsTbx.setText(ObjectUtil.nullToBlank(animal.getAntecedents()).trim());
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
		
		Integer codeAnimal = null;
		if(!StringUtil.isNull(recupLblAnimal.getText())) {
			codeAnimal = Integer.parseInt(recupLblAnimal.getText());
		}
		
		try {
		// Recupère les champs de l'ihm :
		animal.setCodeAnimal(codeAnimal);
		animal.setNomAnimal(nomTbx.getText().trim());
		animal.setCouleur(couleurTbx.getText().trim());
		Race race = new Race();
		race.setRace(raceCbx.getSelectedItem().toString());
		race.setEspece(especeCbx.getSelectedItem().toString());
		animal.setRace(race);
		animal.setTatouage(tatouageTbx.getText().trim());
		//animal.setAntecedents(antecedentsTbx.getText().trim());
		animal.setSexe(sexeCbx.getSelectedItem().toString());
		animal.setArchive(false);
		Client client = controllerClient.loadClient(codeClient);
		animal.setClient(client);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return animal;
	}

	@Override
	public void processEvent(String eventName, Object eventParam) {
		// TODO Auto-generated method stub
		
	}

}
