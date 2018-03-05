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

public class SearchClientScreen extends GenericScreen {


	private static final long serialVersionUID = -5415116920585998148L;
	
	private ClientController controllerClient;
	private ClientModel Modelclient;
	private MainClientScreen parentScreen;
	
	private JTextField searchTbx;
	private JList resultsLst;
	private Vector<Item> resultsLstModel;
	private GridBagConstraints gbc_resultsLst;
	
	
	/**
	 * Create the frame.
	 */
	public SearchClientScreen(MainClientScreen parentScreen) {
		
		super("Recherche", true, true, true, true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.parentScreen = parentScreen;
		this.controllerClient = new ClientController(Modelclient);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		searchTbx = new JTextField();
		GridBagConstraints gbc_searchTbx = new GridBagConstraints();
		gbc_searchTbx.insets = new Insets(0, 0, 5, 5);
		gbc_searchTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTbx.gridx = 1;
		gbc_searchTbx.gridy = 2;
		getContentPane().add(searchTbx, gbc_searchTbx);
		searchTbx.setColumns(10);
		
		JButton searchBtn = new JButton("Rechercher");
		searchBtn.setIcon(new ImageIcon(SearchClientScreen.class.getResource("/images/ico/search_27p.png")));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchValue = searchTbx.getText().trim();
				try {
					List<Client> clients = controllerClient.loadSearchClient(searchValue);
					resultsLstModel.removeAllElements();
					if(!clients.isEmpty()) {
						for (Client client : clients) {
							resultsLstModel.addElement( new Item(client.getCodeClient(), client.getFullname()));
							System.out.println("rempli modelLstClient: "+ client.getCodeClient() +""+ client.getFullname());
						}
					}
				} catch (ManagerException e) {
					e.printStackTrace();
				}
				resultsLst.setListData(resultsLstModel);
			}
		});
		searchBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		searchBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		
		GridBagConstraints gbc_searchBtn = new GridBagConstraints();
		gbc_searchBtn.insets = new Insets(0, 0, 5, 5);
		gbc_searchBtn.gridx = 2;
		gbc_searchBtn.gridy = 2;
		getContentPane().add(searchBtn, gbc_searchBtn);
		

		resultsLstModel = new Vector<Item>();
		resultsLst = new JList<Item>();
		resultsLst.setListData(resultsLstModel);
		
		resultsLst.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		            JList source = (JList)event.getSource();
		            Item selectedItem = resultsLstModel.get(source.getSelectedIndex());
		            Integer selectedCodeCli = selectedItem.getId();
		            //System.out.println("JList valueChanged"+ selectedItem.getId() + " : " + selectedItem.getDescription());
		            
		            parentScreen.showClientById(selectedCodeCli);
		            setVisible(false);
		        }
		    }
		});
		
		gbc_resultsLst = new GridBagConstraints();
		gbc_resultsLst.gridwidth = 2;
		gbc_resultsLst.gridheight = 6;
		gbc_resultsLst.insets = new Insets(0, 0, 5, 5);
		gbc_resultsLst.fill = GridBagConstraints.BOTH;
		gbc_resultsLst.gridx = 1;
		gbc_resultsLst.gridy = 3;
		getContentPane().add(resultsLst, gbc_resultsLst);
		
		
		this.pack();  //remplace: setBounds(100, 100, 598, 440)

	}
	
	
}
