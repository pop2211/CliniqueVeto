package fr.eni.clinique.ihm.screen.Agenda;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.ComponentFormatDefaults;
import org.jdatepicker.JDatePicker;

import fr.eni.clinique.ihm.screen.common.GenericScreen;

public class AgendaScreen extends GenericScreen {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6638231117920115532L;
	private JDatePicker datePicker;
	/**
	 * Create the frame.
	 */
	public AgendaScreen() {
		
		super("Gestion des Agendas", true, true, true, true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{46, 187, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbl_panelDe.columnWidths = new int[]{1, 39, 89, 85, 46, 133, 0};
		gbl_panelDe.rowHeights = new int[]{1, 0};
		gbl_panelDe.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDe.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDe.setLayout(gbl_panelDe);
		
		datePicker = new JDatePicker();
		datePicker.getFormattedTextField().setColumns(1);
		datePicker.getFormattedTextField().setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
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
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 0;
		panelDe.add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 0;
		panelDe.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
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
		// TODO Auto-generated method stub
		
	}
}
