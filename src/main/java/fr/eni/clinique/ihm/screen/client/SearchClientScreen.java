package fr.eni.clinique.ihm.screen.client;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.common.util.Item;
import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.model.ClientModel;
import fr.eni.clinique.ihm.screen.common.GenericScreen;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class SearchClientScreen extends GenericClientScreen {


	private static final long serialVersionUID = 6599224006738672027L;

	
	private JTextField searchTbx;
	private JList resultsLst;
	private Vector<Item> resultsLstModel;
	private JPanel panel;
	private JScrollPane scrollPane;
	
	private Boolean loadingResultsLst = false;
	
	
	/**
	 * Create the frame.
	 */
	public SearchClientScreen(GenericScreen parentScreen) {
		
		super("Recherche", true, true, true, true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.parentScreen = parentScreen;
		this.modelClient = parentScreen.getModelClient();
		this.controllerClient = parentScreen.getControllerClient();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 200, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 10, 198, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		

		resultsLstModel = new Vector<Item>();
		
		panel = new JPanel();
		panel.setToolTipText("Pour");
		panel.setMinimumSize(new Dimension(200, 90));
		panel.setMaximumSize(new Dimension(200, 90));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 85, 0, 0, 20, 0};
		gbl_panel.rowHeights = new int[]{90, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		searchTbx = new JTextField();
		GridBagConstraints gbc_searchTbx = new GridBagConstraints();
		gbc_searchTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTbx.insets = new Insets(0, 0, 0, 5);
		gbc_searchTbx.gridx = 1;
		gbc_searchTbx.gridy = 0;
		panel.add(searchTbx, gbc_searchTbx);
		searchTbx.setColumns(10);
		
		JButton searchBtn = new JButton("Rechercher");
		GridBagConstraints gbc_searchBtn = new GridBagConstraints();
		gbc_searchBtn.insets = new Insets(0, 0, 0, 5);
		gbc_searchBtn.gridx = 3;
		gbc_searchBtn.gridy = 0;
		panel.add(searchBtn, gbc_searchBtn);
		searchBtn.setIcon(new ImageIcon(SearchClientScreen.class.getResource("/images/ico/search_27p.png")));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchSearch();
			}
		});
		searchBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		searchBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		getContentPane().add(scrollPane, gbc_scrollPane);
		resultsLst = new JList<Item<Integer>>();
		scrollPane.setViewportView(resultsLst);
		resultsLst.setListData(resultsLstModel);
		
		resultsLst.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting() && !loadingResultsLst){
		            JList source = (JList)event.getSource();
		            Integer found = source.getSelectedIndex();
		            if(found > -1){
			            Item selectedItem = resultsLstModel.get(source.getSelectedIndex());
			            Integer selectedCodeCli = (Integer) selectedItem.getId();
			            parentScreen.processEvent("AddClient", selectedCodeCli);
		            }
		            setVisible(false);
		        }
		    }
		});
		
		
		this.pack();  //remplace: setBounds(100, 100, 598, 440)

	}
	
	public void launchSearch(){
		loadingResultsLst = true;
		String searchValue = searchTbx.getText().trim();
		try {
			List<Client> clients = controllerClient.loadSearchClient(searchValue);
			resultsLstModel.removeAllElements();
			if(!clients.isEmpty()) {
				for (Client client : clients) {
					String resultLabel = client.getFullname();
					if(client.getNbAnimaux() != null && client.getNbAnimaux() > 0){
						resultLabel += " ("+ client.getNbAnimaux();
						if(client.getNbAnimaux()==1) resultLabel += " animal)";
						else resultLabel += " animaux)";
					}
					resultsLstModel.addElement( new Item(client.getCodeClient(), resultLabel));
				}
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		resultsLst.setListData(resultsLstModel);
		loadingResultsLst = false;
	}
	
	
}
