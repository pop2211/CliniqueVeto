package fr.eni.clinique.ihm.screen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.DesktopManager;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import fr.eni.clinique.bo.EnumRole;
import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.common.util.LogUtil;
import fr.eni.clinique.common.util.StringUtil;
import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.ClientModel;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.Agenda.AgendaScreen;
import fr.eni.clinique.ihm.screen.Rdv.RdvScreen;
import fr.eni.clinique.ihm.screen.animal.AnimalScreen;
import fr.eni.clinique.ihm.screen.client.MainClientScreen;
import fr.eni.clinique.ihm.screen.login.LoginScreen;
import fr.eni.clinique.ihm.screen.personnel.PersonnelScreen;

public class MainScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private PersonnelModel model;
	private PersonnelController controller;
	private String profil = "";

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
		MenuProfil(profil);
	}

	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private JMenu menuGestionDesRendezvous;
	private JMenuItem menuAgenda;
	private JMenuItem menuGestionDuPersonnel;
	private JMenuItem menuGestionDesAnimaux;
	
	private LoginScreen frameLogin;
	private MainClientScreen clientScreen;
	private AnimalScreen animalScreen;
	private PersonnelScreen personnelScreen;
	private RdvScreen rdvScreen;
	private AgendaScreen agendaScreen;
	
	

	public MainScreen(String title, PersonnelModel model, PersonnelController controller) {
		this.controller = controller;
		this.model = model;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int largeurFen = (int) Math.round(screenSize.width * 0.7);
		int hauteurFen = (int) Math.round(screenSize.height * 0.7);
		setBounds(0, 0, largeurFen, hauteurFen);
		setTitle(title);

		// initialiser l'ecran MDI
		desktopPane = new JDesktopPane();

		// Associer le JDesktopPane à la JFrame
		setContentPane(desktopPane);

		// Barre de menus
		setJMenuBar(getMenuBarre());
		
		// Frame interne login
		frameLogin = getFrameLogin(model, controller);
		desktopPane.add(frameLogin);
		frameLogin.setVisible(true);
		
	}

	public void createMenuBar() {
								
		// Menu Fichier
		JMenu menu = new JMenu("Fichier");
		menu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		menuBarre.add(menu);
			
			// Sous menu Connexion / Déconnexion
			JMenuItem menuItem = new JMenuItem("Connexion / Déconnexion");
			menuItem.setActionCommand("deco/reco");
			menuItem.addActionListener(this);
			menu.add(menuItem);
			
			// Sous menu fermer
			menuItem = new JMenuItem("Fermer");
			menuItem.setActionCommand("fermer");
			menuItem.addActionListener(this);
			menu.add(menuItem);
		
		//Menu Gestion Des Rendez-vous
		menuGestionDesRendezvous = new JMenu("Gestion des rendez-vous");
		menuGestionDesRendezvous.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		menuBarre.add(menuGestionDesRendezvous);
		
			// Sous menu Gestion Rdv
			menuItem = new JMenuItem("Prise de rendez-vous");
			menuItem.setActionCommand("gestionRDV");
			menuItem.addActionListener(this);
			menuGestionDesRendezvous.add(menuItem);
		
			// Sous menu Gestion clients
			menuItem = new JMenuItem("Gestion des clients");
			menuItem.setActionCommand("gestionClients");
			menuItem.addActionListener(this);
			menuGestionDesRendezvous.add(menuItem);
		
		//Menu Agenda
		menuAgenda = new JMenuItem("Agenda");
		menuAgenda.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		Integer menuAgendaWidth = 55;
		menuAgenda.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuAgenda.setPreferredSize(new Dimension(menuAgendaWidth, 22));
		menuAgenda.setMaximumSize(new Dimension(menuAgendaWidth, 22));
		menuAgenda.setMinimumSize(new Dimension(menuAgendaWidth, 22));
		
		menuAgenda.setActionCommand("gestionAgenda");
		menuAgenda.addActionListener(this);
		menuBarre.add(menuAgenda);
		
		//Menu GestionDuPersonnel
		menuGestionDuPersonnel = new JMenuItem("Gestion du Personnel");
		menuGestionDuPersonnel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		menuGestionDuPersonnel.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuGestionDuPersonnel.setPreferredSize(new Dimension(130, 22));
		menuGestionDuPersonnel.setMaximumSize(new Dimension(130, 22));
		menuGestionDuPersonnel.setMinimumSize(new Dimension(130, 22));
		menuGestionDuPersonnel.setActionCommand("gestionPersonnel");
		menuGestionDuPersonnel.addActionListener(this);
		menuBarre.add(menuGestionDuPersonnel);

		MenuProfil(getProfil());
	}
	
	public void MenuProfil(String profil) {

		switch (profil) {
		case EnumRole.ADM:
			menuGestionDesRendezvous.setVisible(false);
			menuAgenda.setVisible(false);
			menuGestionDuPersonnel.setVisible(true);
			break;
		case EnumRole.ASS:
			menuGestionDesRendezvous.setVisible(false);
			menuAgenda.setVisible(true);
			menuGestionDuPersonnel.setVisible(false);
			break;
		case EnumRole.SEC: 
			menuGestionDesRendezvous.setVisible(true);
			menuAgenda.setVisible(false);
			menuGestionDuPersonnel.setVisible(false);
			break;
		case EnumRole.VET:
			menuGestionDesRendezvous.setVisible(false);
			menuAgenda.setVisible(true);
			menuGestionDuPersonnel.setVisible(false);
			break;
		case EnumRole.SUP:
			menuGestionDesRendezvous.setVisible(false);
			menuAgenda.setVisible(true);
			menuGestionDuPersonnel.setVisible(false);
			break;
		case EnumRole.DEV:
			menuGestionDesRendezvous.setVisible(true);
			menuAgenda.setVisible(true);
			menuGestionDuPersonnel.setVisible(true);
			break;
		default:
			menuGestionDesRendezvous.setVisible(false);
			menuAgenda.setVisible(false);
			menuGestionDuPersonnel.setVisible(false);
		}
	}

	// Réagir aux clicks sur les menus
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "deco/reco":
			if(!StringUtil.isNull(profil)) {
				setProfil(AppConstants.EMPTY);
			}
			//close all previously oppened JInternalFrames :
			JInternalFrame frames[] = desktopPane.getAllFrames();
			DesktopManager dm = desktopPane.getDesktopManager();
			for (int i = 0 ; i < frames.length ; i ++)
			{
				if(frames[i] != frameLogin)
				{
					dm.closeFrame(frames[i]);
					try {
						frames[i].setClosed(false);
					} catch (PropertyVetoException e1) {
						errorOccured(e1);
					}
				}
			}
			//show login form
			frameLogin.setVisible(true);
			break;
		case "fermer":
			System.exit(0);
			break;
		case "gestionRDV": 
			//frame GestionRdv
			rdvScreen = getRdvScreen(model, controller);
			desktopPane.add(rdvScreen);
			rdvScreen.setVisible(true);
			break;
		case "gestionAgenda": 
			//frame GestionAgenda
			agendaScreen = getAgendaScreen();
			desktopPane.add(agendaScreen);
			agendaScreen.setVisible(true);
			break;
		case "gestionClients":
			//frame GestionCllient
            // Create client Model & Controller
            ClientModel clientModel = new ClientModel();
            ClientController clientController = new ClientController(clientModel);
			clientScreen = getClientScreen(clientModel, clientController);
			desktopPane.add(clientScreen);
			clientScreen.setVisible(true);
			break;
		case "gestionPersonnel":
			//frame GestionPersonnel

            PersonnelModel personnelModel = new PersonnelModel();
            PersonnelController personnelController = new PersonnelController(personnelModel);
			personnelScreen = getPersonnelScreen(personnelModel, personnelController);
			desktopPane.add(personnelScreen);
			personnelScreen.setVisible(true);
			break;
		default:
			LogUtil.LOGGER.error("ERROR", "MainScreen actionPerformed : "+ e);
		}

	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuBar getMenuBarre() {
		if (menuBarre == null) {
			menuBarre = new JMenuBar();

			createMenuBar();
		}
		return menuBarre;
	}

	public LoginScreen getFrameLogin(PersonnelModel model, PersonnelController controller) {
		if (frameLogin == null) {
			frameLogin = new LoginScreen(this, model, controller);
		}
		return frameLogin;
	}
	
	private PersonnelScreen getPersonnelScreen(PersonnelModel personnelModel, PersonnelController personnelController) {
		personnelScreen = new PersonnelScreen(personnelController, personnelModel);
		return personnelScreen;
	}
	
	public RdvScreen getRdvScreen(PersonnelModel model, PersonnelController controller) {
		RdvScreen rdvScreen = new RdvScreen();
		return rdvScreen;
	}
	
	public AgendaScreen getAgendaScreen() {
		AgendaScreen agendaScreen = new AgendaScreen();
		return agendaScreen;
	}

	public MainClientScreen getClientScreen(ClientModel model, ClientController controller) {
		clientScreen = new MainClientScreen(model, controller);
		return clientScreen;
	}
	
	
	
	//==================================================================
	/**
	 * Show TechnicalError and print StackTrace.
	 * 
	 * @param e
	 */
	public void errorOccured(Exception e) {
		LogUtil.LOGGER.error("ERROR", e);
		if(getProfil() == EnumRole.DEV){
			//user is dev, we show stack trace before modal popup
			e.printStackTrace();
		}
		String errorMsg = e.getMessage();
		if("".equals(errorMsg)){
			errorMsg = "Une erreur s'est produite";
		}
		showFailureMessage(errorMsg);
	}
	
    /**
     * Show TechnicalError.
     * 
     * @param message
     */
    public void showFailureMessage(String message) {
        JOptionPane.showMessageDialog(MainScreen.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Show Success Message.
     * 
     * @param message
     */
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(MainScreen.this, message);
    }
    //==================================================================
}
