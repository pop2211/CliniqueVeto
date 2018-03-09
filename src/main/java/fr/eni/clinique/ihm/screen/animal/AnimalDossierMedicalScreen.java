package fr.eni.clinique.ihm.screen.animal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.impl.ClientJDBCDAOImpl;
import fr.eni.clinique.dal.dao.impl.RaceJDBCDAOImpl;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.model.AnimalModel;
import fr.eni.clinique.ihm.screen.common.GenericScreen;

public class AnimalDossierMedicalScreen extends GenericScreen {
	
	//Screen to edit animal comments

	private static final long serialVersionUID = -4328796218453001155L;
	
	private JLabel lblCodeClient; //(OLD recupLblCli)
	private JTextArea antecedentsTbx;
	
	private Integer codeClient = null;
	private Integer codeAnimal = null;
	private Animal currentAnimal;
	
	private RaceDAO raceDAO = new RaceJDBCDAOImpl();
	private ClientDAO clientDAO = new ClientJDBCDAOImpl();
	
	private JLabel labelCli;
	private JLabel lblCodeAnimal;
	
	private JLabel labelAntecedents;
	private JLabel lblAnimal;
	private JLabel lblNom;
	private JLabel lblCouleur;
	private JLabel lblSexe;
	private JLabel lblRace;
	private JLabel lblEspece;
	private JLabel lblTatouage;
	
	
	public AnimalDossierMedicalScreen(GenericScreen parentScreen, Integer CodeClient, Integer CodeAnimal) {
		super("Modification de l'animal", true, true, true,true);
		
		setBounds(100, 100, 609, 452);
		
		this.parentScreen = parentScreen;
		
        this.modelAnimal = new AnimalModel();
		this.controllerAnimal = new AnimalController(this.modelAnimal);
		codeClient = CodeClient;
		codeAnimal = CodeAnimal;
		
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 107, 0, 0, 300, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 85, 0, 0, 24, 0, 0, 0, 0, 10, 150, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		
		JButton validerBtn = new JButton("Valider");
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String antecedents =antecedentsTbx.getText().trim();
					currentAnimal.setAntecedents(antecedents);
					controllerAnimal.saveAnimal(currentAnimal);
					
					parentScreen.processEvent("UpdateAnimal", null);
					setVisible(false);
					showSuccessMessage("Animal mise à jour !");
				} catch (Exception e) {
					errorOccured(e);
				}
			}
		});
		validerBtn.setIcon(new ImageIcon(AnimalDossierMedicalScreen.class.getResource("/images/ico/done_32p.png")));
		validerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		validerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		validerBtn.setBounds(354, 11, 86, 59);
		panel.add(validerBtn);
		
		JButton annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Animal reloadedAnimal = controllerAnimal.loadAnimal(currentAnimal.getCodeAnimal());
					showAnimal(reloadedAnimal);
					setVisible(false);
				} catch (Exception e1) {
					errorOccured(e1);
				}
			}
		});
		annulerBtn.setIcon(new ImageIcon(AnimalDossierMedicalScreen.class.getResource("/images/ico/undo_27p.png")));
		annulerBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		annulerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		annulerBtn.setBounds(450, 13, 92, 58);
		panel.add(annulerBtn);
		
		lblCodeClient = new JLabel("");
		lblCodeClient.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodeClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodeClient.setForeground(new Color(255, 0, 0));
		setLabelClient(codeClient);
		
		labelCli = new JLabel("Client :");
		labelCli.setHorizontalAlignment(SwingConstants.TRAILING);
		labelCli.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_labelCli = new GridBagConstraints();
		gbc_labelCli.insets = new Insets(0, 0, 5, 5);
		gbc_labelCli.gridx = 1;
		gbc_labelCli.gridy = 3;
		getContentPane().add(labelCli, gbc_labelCli);
		GridBagConstraints gbc_recupLabelClient = new GridBagConstraints();
		gbc_recupLabelClient.anchor = GridBagConstraints.WEST;
		gbc_recupLabelClient.insets = new Insets(0, 0, 5, 5);
		gbc_recupLabelClient.gridx = 2;
		gbc_recupLabelClient.gridy = 3;
		getContentPane().add(lblCodeClient, gbc_recupLabelClient);
		
		labelAntecedents = new JLabel("Antécédents/Consultations :");
		labelAntecedents.setHorizontalAlignment(SwingConstants.TRAILING);
		labelAntecedents.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_labelAntecedents = new GridBagConstraints();
		gbc_labelAntecedents.insets = new Insets(0, 0, 5, 5);
		gbc_labelAntecedents.gridx = 4;
		gbc_labelAntecedents.gridy = 3;
		getContentPane().add(labelAntecedents, gbc_labelAntecedents);
		
		antecedentsTbx = new JTextArea();
		GridBagConstraints gbc_antecedentsTbx = new GridBagConstraints();
		gbc_antecedentsTbx.gridheight = 7;
		gbc_antecedentsTbx.insets = new Insets(0, 0, 5, 5);
		gbc_antecedentsTbx.fill = GridBagConstraints.BOTH;
		gbc_antecedentsTbx.gridx = 4;
		gbc_antecedentsTbx.gridy = 4;
		getContentPane().add(antecedentsTbx, gbc_antecedentsTbx);
		
		lblAnimal = new JLabel("Animal :");
		lblAnimal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblAnimal = new GridBagConstraints();
		gbc_lblAnimal.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnimal.gridx = 1;
		gbc_lblAnimal.gridy = 5;
		getContentPane().add(lblAnimal, gbc_lblAnimal);
		
		lblCodeAnimal = new JLabel("<dynamic>");
		lblCodeAnimal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodeAnimal.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCodeAnimal = new GridBagConstraints();
		gbc_lblCodeAnimal.anchor = GridBagConstraints.WEST;
		gbc_lblCodeAnimal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeAnimal.gridx = 2;
		gbc_lblCodeAnimal.gridy = 5;
		getContentPane().add(lblCodeAnimal, gbc_lblCodeAnimal);
		
		lblNom = new JLabel("<dynamic>");
		lblNom.setHorizontalAlignment(SwingConstants.LEFT);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.WEST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 2;
		gbc_lblNom.gridy = 6;
		getContentPane().add(lblNom, gbc_lblNom);
		
		lblCouleur = new JLabel("<dynamic>");
		lblCouleur.setHorizontalAlignment(SwingConstants.LEFT);
		lblCouleur.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCouleur = new GridBagConstraints();
		gbc_lblCouleur.anchor = GridBagConstraints.WEST;
		gbc_lblCouleur.insets = new Insets(0, 0, 5, 5);
		gbc_lblCouleur.gridx = 2;
		gbc_lblCouleur.gridy = 7;
		getContentPane().add(lblCouleur, gbc_lblCouleur);
		
		lblSexe = new JLabel("<dynamic>");
		lblSexe.setHorizontalAlignment(SwingConstants.LEFT);
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblSexe = new GridBagConstraints();
		gbc_lblSexe.anchor = GridBagConstraints.WEST;
		gbc_lblSexe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSexe.gridx = 3;
		gbc_lblSexe.gridy = 7;
		getContentPane().add(lblSexe, gbc_lblSexe);
		
		lblRace = new JLabel("<dynamic>");
		lblRace.setHorizontalAlignment(SwingConstants.LEFT);
		lblRace.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblRace = new GridBagConstraints();
		gbc_lblRace.anchor = GridBagConstraints.WEST;
		gbc_lblRace.insets = new Insets(0, 0, 5, 5);
		gbc_lblRace.gridx = 2;
		gbc_lblRace.gridy = 8;
		getContentPane().add(lblRace, gbc_lblRace);
		
		lblEspece = new JLabel("<dynamic>");
		lblEspece.setHorizontalAlignment(SwingConstants.LEFT);
		lblEspece.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblEspece = new GridBagConstraints();
		gbc_lblEspece.anchor = GridBagConstraints.WEST;
		gbc_lblEspece.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspece.gridx = 3;
		gbc_lblEspece.gridy = 8;
		getContentPane().add(lblEspece, gbc_lblEspece);
		
		lblTatouage = new JLabel("<dynamic>");
		lblTatouage.setHorizontalAlignment(SwingConstants.LEFT);
		lblTatouage.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTatouage = new GridBagConstraints();
		gbc_lblTatouage.anchor = GridBagConstraints.WEST;
		gbc_lblTatouage.insets = new Insets(0, 0, 5, 5);
		gbc_lblTatouage.gridx = 2;
		gbc_lblTatouage.gridy = 9;
		getContentPane().add(lblTatouage, gbc_lblTatouage);
		
		if (codeClient == null){
			labelCli.setVisible(false);	//"Client: "
			lblCodeAnimal.setVisible(false);
		}
		
		currentAnimal = new Animal();
		try {
			currentAnimal = controllerAnimal.loadAnimal(codeAnimal);
		} catch (ManagerException e) {
			errorOccured(e);
		}
		showAnimal(currentAnimal);
		
		this.pack();
	}
	

	
	public void setLabelClient(Integer id){
		String result = null;
		try {
			Client clients = clientDAO.selectById(id);
			result = clients.getFullname();
			lblCodeClient.setText(result);
		} catch (DaoException e) {
			errorOccured(e);
		}
    }
	
	public void showAnimal(Animal animal) {

		if(animal == null){
			animal = new Animal();
		}
		// Rempli les champs de l'ihm :

		lblCodeAnimal.setText(String.valueOf(animal.getCodeAnimal()));
		lblNom.setText(ObjectUtil.nullToBlank(animal.getNomAnimal()).trim());
		lblCouleur.setText(ObjectUtil.nullToBlank(animal.getCouleur()).trim());
		lblTatouage.setText(ObjectUtil.nullToBlank(animal.getTatouage()).trim());
		antecedentsTbx.setText(ObjectUtil.nullToBlank(animal.getAntecedents()).trim());
		lblEspece.setText(animal.getRace().getEspece());
		lblRace.setText(animal.getRace().getRace());
		lblSexe.setText(animal.getSexe());
	}

	@Override
	public void processEvent(String eventName, Object eventParam) {
		// TODO Auto-generated method stub
		
	}

}
