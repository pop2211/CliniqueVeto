package fr.eni.clinique.ihm.screen;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.common.util.StringUtil;
import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.AnimalModel;
import fr.eni.clinique.ihm.model.ClientModel;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.Rdv.RdvScreen;
import fr.eni.clinique.ihm.screen.animal.AnimalScreen;
import fr.eni.clinique.ihm.screen.client.MainClientScreen;
import fr.eni.clinique.ihm.screen.common.GenericScreen;
import fr.eni.clinique.ihm.screen.login.InternalFrameLogin;
import fr.eni.clinique.ihm.screen.personnel.PersonnelScreen;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

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
	private JMenu menuAgenda;
	private JMenuItem menuGestionDuPersonnel;
	private JMenuItem menuGestionDesAnimaux;
	
	private InternalFrameLogin frameLogin;
	private MainClientScreen clientScreen;
	private AnimalScreen animalScreen;
	private PersonnelScreen personnelScreen;
	private RdvScreen rdvScreen;
	

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
		menuAgenda = new JMenu("Agenda");
		menuAgenda.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
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
		
		//Menu Gestion des animaux
		menuGestionDesAnimaux = new JMenuItem("Gestion des Animaux");
		menuGestionDesAnimaux.setMinimumSize(new Dimension(140, 22));
		menuGestionDesAnimaux.setPreferredSize(new Dimension(140, 22));
		menuGestionDesAnimaux.setMaximumSize(new Dimension(140, 22));
		menuGestionDesAnimaux.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		menuGestionDesAnimaux.setActionCommand("gestionAnimaux");
		menuGestionDesAnimaux.addActionListener(this);
		menuBarre.add(menuGestionDesAnimaux);

		MenuProfil(getProfil());
	}
	
	public void MenuProfil(String profil) {
		if(profil != "") {
			menuGestionDesRendezvous.setVisible(true);
			menuAgenda.setVisible(true);
			menuGestionDuPersonnel.setVisible(true);
			menuGestionDesAnimaux.setVisible(true);
		}
		else {
			menuGestionDesRendezvous.setVisible(false);
			menuAgenda.setVisible(false);
			menuGestionDuPersonnel.setVisible(false);
			menuGestionDesAnimaux.setVisible(false);
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
		case "gestionClients":
			//frame GestionCllient
            // Create client Model & Controller
            ClientModel clientModel = new ClientModel();
            ClientController clientController = new ClientController(clientModel);
			clientScreen = getClientScreen(clientModel, clientController);
			desktopPane.add(clientScreen);
			clientScreen.setVisible(true);
			break;
		case "gestionAnimaux":
			//frame GestionAnimaux
			animalScreen = getAnimalScreen();
			desktopPane.add(animalScreen);
			animalScreen.setVisible(true);
			break;
		case "gestionPersonnel":
			//frame GestionAnimaux

            PersonnelModel personnelModel = new PersonnelModel();
            PersonnelController personnelController = new PersonnelController(personnelModel);
			personnelScreen = getPersonnelScreen(personnelModel, personnelController);
			desktopPane.add(personnelScreen);
			personnelScreen.setVisible(true);
			break;
		default:
			System.out.println("Probleme Event = " + e);
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

	public InternalFrameLogin getFrameLogin(PersonnelModel model, PersonnelController controller) {
		if (frameLogin == null) {
			frameLogin = new InternalFrameLogin(model, controller);
		}
		return frameLogin;
	}
	
	private PersonnelScreen getPersonnelScreen(PersonnelModel personnelModel, PersonnelController personnelController) {
		personnelScreen = new PersonnelScreen(personnelController, personnelModel);
		return personnelScreen;
	}
	
	public RdvScreen getRdvScreen(PersonnelModel model, PersonnelController controller) {
		/*if (frameLogin == null) {
			frameLogin = new InternalFrameLogin(model, controller);
		}*/
		RdvScreen rdvScreen = new RdvScreen();
		return rdvScreen;
	}

	public MainClientScreen getClientScreen(ClientModel model, ClientController controller) {
		/*if(clientScreen == null) {
			clientScreen = new ClientScreen(model, controller);
		}*/
		clientScreen = new MainClientScreen(model, controller);
		return clientScreen;
	}
	
	public AnimalScreen getAnimalScreen() {
		/*if(animalScreen == null) {
			animalScreen = new AnimalScreen(model, controller);
		}*/
		animalScreen = new AnimalScreen();
		return animalScreen;
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
}
