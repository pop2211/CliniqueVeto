package fr.eni.clinique.ihm.screen.Agenda;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.EnumRole;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.common.util.Item;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.controller.RdvController;
import fr.eni.clinique.ihm.model.TableModelAgenda;
import fr.eni.clinique.ihm.model.TableModelRdv;
import fr.eni.clinique.ihm.screen.animal.AnimalDossierMedicalScreen;
import fr.eni.clinique.ihm.screen.common.GenericScreen;
import fr.eni.clinique.ihm.screen.personnel.PersonnelScreen;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import org.jdatepicker.ComponentFormatDefaults;
import org.jdatepicker.JDatePicker;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTable;

public class AgendaScreen extends GenericScreen {


	private static final long serialVersionUID = -6638231117920115532L;
	private JDatePicker datePicker;
	private JTable table;
	private JComboBox CbxVeterinaire;
	TableModelAgenda tableModelAgenda;
	
	private AnimalDossierMedicalScreen frameAnimalDossierMedical;
	
	/**
	 * Create the frame.
	 */
	public AgendaScreen() {
		
		super("Gestion des Agendas", true, true, true, true);
		
		controllerAnimal = new AnimalController(modelAnimal);
		controllerPersonnel = new PersonnelController(modelPersonnel);
		controllerRdv = new RdvController(modelRdv);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{46, 187, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panelDe = new JPanel();
		panelDe.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "De", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelDe = new GridBagConstraints();
		gbc_panelDe.insets = new Insets(0, 0, 5, 0);
		gbc_panelDe.fill = GridBagConstraints.BOTH;
		gbc_panelDe.gridx = 0;
		gbc_panelDe.gridy = 0;
		getContentPane().add(panelDe, gbc_panelDe);
		GridBagLayout gbl_panelDe = new GridBagLayout();
		gbl_panelDe.columnWidths = new int[]{1, 39, 89, 109, 46, 133, 0};
		gbl_panelDe.rowHeights = new int[]{1, 0};
		gbl_panelDe.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDe.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDe.setLayout(gbl_panelDe);
		
		datePicker = new JDatePicker();
		datePicker.getFormattedTextField().setColumns(1);
		datePicker.getFormattedTextField().setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processEvent("ChangeDate", null);
			}
		});
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.insets = new Insets(0, 0, 0, 5);
		gbc_datePicker.gridx = 5;
		gbc_datePicker.gridy = 0;
		panelDe.add(datePicker, gbc_datePicker);
		gbc_datePicker.insets = new Insets(0, 10, 5, 0);
		
		JLabel lblNewLabel = new JLabel("Vétérinaire");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		panelDe.add(lblNewLabel, gbc_lblNewLabel);
		
		CbxVeterinaire = new JComboBox();
		try {
			List<Personnel> vetos = controllerPersonnel.loadPersonnelByRole(EnumRole.VETERINAIRE.getCode());
			
			if(!vetos.isEmpty()) {
				for (Personnel veto : vetos) {
					CbxVeterinaire.addItem( new Item<Integer>(veto.getCodePers(), veto.getNom()));
				}
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		CbxVeterinaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processEvent("ChangeVeto", null);
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 0;
		panelDe.add(CbxVeterinaire, gbc_comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 0;
		panelDe.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		Integer numVeto = ((Item<Integer>) CbxVeterinaire.getItemAt(0)).getId();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		tableModelAgenda = new TableModelAgenda(numVeto, date);
		table = new JTable(tableModelAgenda);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(Color.BLACK, 2, true));
		scrollPane.setPreferredSize(new Dimension(0, 0));
		getContentPane().add(scrollPane, gbc_table);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton dossierMedicalBtn = new JButton("Dossier Medical");
		dossierMedicalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer rowIndex = table.getSelectedRow();
					if(rowIndex == -1){
						throw new Exception("Aucun rendez-vous selectionné");
					}
					Rdv rdv = tableModelAgenda.getRdvs().get(rowIndex);
					Integer codeAnimal = rdv.getAnimal().getCodeAnimal();
					Integer codeClient = rdv.getAnimal().getCodeClient();
					frameAnimalDossierMedical = getFrameAnimalDossierMedical(codeClient, codeAnimal);
					frameAnimalDossierMedical.setVisible(true);
				} catch (Exception e1) {
					errorOccured(e1);
				}
			}
		});
		dossierMedicalBtn.setIcon(new ImageIcon(PersonnelScreen.class.getResource("/images/ico/folder_27p.png")));
		GridBagConstraints gbc_dossierMedicalBtn = new GridBagConstraints();
		gbc_dossierMedicalBtn.gridx = 12;
		gbc_dossierMedicalBtn.gridy = 0;
		gbc_dossierMedicalBtn.insets = new Insets(5, 5, 15, 5);
		panel.add(dossierMedicalBtn, gbc_dossierMedicalBtn);
		
		//Paramètre du Datepicker
		ComponentFormatDefaults defaults = ComponentFormatDefaults.getInstance();
		defaults.setFormat(ComponentFormatDefaults.Key.TODAY_SELECTOR, new SimpleDateFormat("EEE dd/MM/yyyy"));
        defaults.setFormat(ComponentFormatDefaults.Key.DOW_HEADER, new SimpleDateFormat("EEEE"));
        defaults.setFormat(ComponentFormatDefaults.Key.SELECTED_DATE_FIELD, new SimpleDateFormat("dd/MM/yyyy"));
        defaults.setFormat(ComponentFormatDefaults.Key.MONTH_SELECTOR, new SimpleDateFormat("MMM"));
    
	pack();
	}

	@Override
	public void processEvent(String eventName, Object eventParam) {
		switch(eventName){
			case "ChangeVeto":
			    tableModelAgenda.refresh(getActualVeto(), getActualDate());
			break;
			case "ChangeDate":
				tableModelAgenda.refresh(getActualVeto(), getActualDate());
			break;
		}
	}
	
	private Integer getActualVeto() {
		return ((Item<Integer>) CbxVeterinaire.getSelectedItem()).getId();
	}
	
	private String getActualDate() {
		String parsedDate = null;
		try {
			Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(datePicker.getFormattedTextField().getText());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    parsedDate = formatter.format(initDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return parsedDate;
	}
	
	public AnimalDossierMedicalScreen getFrameAnimalDossierMedical(Integer CodeCli, Integer CodeAnimal) {
		frameAnimalDossierMedical = new AnimalDossierMedicalScreen((GenericScreen)this, CodeCli, CodeAnimal);
		getMainScreen().getDesktopPane().add(frameAnimalDossierMedical);
		return frameAnimalDossierMedical;
	}
	
}
