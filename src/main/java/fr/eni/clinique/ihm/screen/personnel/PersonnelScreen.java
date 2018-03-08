package fr.eni.clinique.ihm.screen.personnel;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.model.TableModelPersonnel;
import fr.eni.clinique.ihm.screen.client.AddClientScreen;
import fr.eni.clinique.ihm.screen.common.GenericScreen;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;

public class PersonnelScreen extends GenericScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -840620127348344756L;

	private TableModelPersonnel modelTable;
	private AddPersonnelScreen frameAdd;
	private RebootPasswordPersonnelScreen frameChange;
	private PersonnelController controller;
	private PersonnelModel model;
	
	private JTable table;


	/**
	 * Create the frame.
	 */
	public PersonnelScreen(PersonnelController controller, PersonnelModel model) {
		super("Gestion du personnel", true, true, true,true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.controller = controller; 
		this.model = model;
			
		modelTable = new TableModelPersonnel();
		
		setBounds(100, 100, 450, 486);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 238, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 80, 81, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton ajouterBtn = new JButton("");
		ajouterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAdd = getFrameAdd();
				frameAdd.setVisible(true);
			}
		});
		
		ajouterBtn.setContentAreaFilled(false);
		ajouterBtn.setBorderPainted(false);
		ajouterBtn.setOpaque(false);
		ajouterBtn.setIcon(new ImageIcon(PersonnelScreen.class.getResource("/images/ico/add_27p.png")));
		GridBagConstraints gbc_ajouterBtn = new GridBagConstraints();
		gbc_ajouterBtn.insets = new Insets(5, 5, 5, 5);
		gbc_ajouterBtn.gridx = 0;
		gbc_ajouterBtn.gridy = 0;
		panel.add(ajouterBtn, gbc_ajouterBtn);
		
		JButton supprimerBtn = new JButton("");
		
		
		supprimerBtn.setBorderPainted(false);
		supprimerBtn.setIcon(new ImageIcon(PersonnelScreen.class.getResource("/images/ico/remove_27p.png")));
		supprimerBtn.setOpaque(false);
		supprimerBtn.setContentAreaFilled(false);
		GridBagConstraints gbc_supprimerBtn = new GridBagConstraints();
		gbc_supprimerBtn.insets = new Insets(5, 5, 5, 5);
		gbc_supprimerBtn.gridx = 1;
		gbc_supprimerBtn.gridy = 0;
		panel.add(supprimerBtn, gbc_supprimerBtn);
		
		JButton reinitialiserBtn = new JButton("");
		reinitialiserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer personnelId = getCurrentPersonnelId();
				if(personnelId == -1){
					showFailureMessage("Aucun utilisateur séléctionné");
				}else{
					frameChange = getFrameChange(personnelId);
					frameChange.setVisible(true);
				}
			}

			
		});
		reinitialiserBtn.setBorderPainted(false);
		reinitialiserBtn.setContentAreaFilled(false);
		reinitialiserBtn.setOpaque(false);
		reinitialiserBtn.setIcon(new ImageIcon(PersonnelScreen.class.getResource("/images/ico/unlock_27p.png")));
		GridBagConstraints gbc_reinitialiserBtn = new GridBagConstraints();
		gbc_reinitialiserBtn.insets = new Insets(5, 5, 5, 0);
		gbc_reinitialiserBtn.gridx = 2;
		gbc_reinitialiserBtn.gridy = 0;
		panel.add(reinitialiserBtn, gbc_reinitialiserBtn);
		
		JLabel lblNewLabel = new JLabel("Ajouter");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Supprimer");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblReinitialiser = new JLabel("Reinitialiser");
		lblReinitialiser.setHorizontalAlignment(SwingConstants.CENTER);
		lblReinitialiser.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReinitialiser.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblReinitialiser = new GridBagConstraints();
		gbc_lblReinitialiser.gridx = 2;
		gbc_lblReinitialiser.gridy = 1;
		panel.add(lblReinitialiser, gbc_lblReinitialiser);
		
		table = new JTable(modelTable);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 2;
		getContentPane().add(new JScrollPane(table), gbc_table);
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		supprimerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer personnelId = getCurrentPersonnelId();
					if(personnelId == -1){
						throw new Exception("Aucun personnel séléctionné");
					}
					controller.deletePersonnel(personnelId);
					modelTable.refresh();
					showSuccessMessage("Personnel archivé !");
				} catch (Exception e1) {
					errorOccured(e1);
				}
			}

			
		});

		
		pack();
	}
	
	public AddPersonnelScreen getFrameAdd() {
		if (frameAdd == null) {
			frameAdd = new AddPersonnelScreen(this,this.model,this.controller);
			getMainScreen().getDesktopPane().add(frameAdd);
		}
		return frameAdd;
	}
	
	private Integer getCurrentPersonnelId() {
		int column = 0;
		int row = table.getSelectedRow();
		Integer codePersonnel = -1;
		if(row >= 0){
			codePersonnel = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
		}
		return codePersonnel;
	}

	public TableModelPersonnel getTableModel(){
		return modelTable;
	}

	private RebootPasswordPersonnelScreen getFrameChange(Integer CodePerso) {
		if (frameChange == null) {
			frameChange = new RebootPasswordPersonnelScreen(this,this.model,this.controller, CodePerso);
			getMainScreen().getDesktopPane().add(frameChange);
		}
		return frameChange;
	}
	
	
	@Override
	public void processEvent(String eventName, Object eventParam) {
		// TODO Auto-generated method stub

	}
}
