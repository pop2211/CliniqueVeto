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

import fr.eni.clinique.ihm.controller.AnimalController;
import fr.eni.clinique.ihm.controller.ClientController;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.AnimalModel;
import fr.eni.clinique.ihm.model.ClientModel;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.Rdv.RdvScreen;
import fr.eni.clinique.ihm.screen.client.ClientScreen;
import fr.eni.clinique.ihm.screen.login.InternalFrameLogin;

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
	private JMenu menuGestionDuPersonnel;
	private JMenuItem menuGestionDesAnimaux;
	
	private InternalFrameLogin frameLogin;
	private ClientScreen clientScreen;
	private AnimalScreen animalScreen;
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
		menuBarre.add(menu);
			
			// Sous menu Déconnexion
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
		menuAgenda.setActionCommand("gestionAgenda");
		menuAgenda.addActionListener(this);
		menuBarre.add(menuAgenda);
		
		//Menu GestionDuPersonnel
		menuGestionDuPersonnel = new JMenu("Gestion du Personnel");
		menuGestionDuPersonnel.setActionCommand("gestionPersonnel");
		menuGestionDuPersonnel.addActionListener(this);
		menuBarre.add(menuGestionDuPersonnel);
		
		//Menu Gestion des animaux
		menuGestionDesAnimaux = new JMenuItem("Gestion des Animaux");
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
			if(profil == "")
			{
				frameLogin.setVisible(true);
			}
			else {
				setProfil("");
			}
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

            AnimalModel animalModel = new AnimalModel();
            AnimalController animalController = new AnimalController(animalModel);
			animalScreen = getAnimalScreen(animalModel, animalController);
			desktopPane.add(animalScreen);
			animalScreen.setVisible(true);
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
	
	public RdvScreen getRdvScreen(PersonnelModel model, PersonnelController controller) {
		/*if (frameLogin == null) {
			frameLogin = new InternalFrameLogin(model, controller);
		}*/
		RdvScreen rdvScreen = new RdvScreen();
		return rdvScreen;
	}

	public ClientScreen getClientScreen(ClientModel model, ClientController controller) {
		/*if(clientScreen == null) {
			clientScreen = new ClientScreen(model, controller);
		}*/
		clientScreen = new ClientScreen(model, controller);
		return clientScreen;
	}
	
	public AnimalScreen getAnimalScreen(AnimalModel model, AnimalController controller) {
		/*if(animalScreen == null) {
			animalScreen = new AnimalScreen(model, controller);
		}*/
		animalScreen = new AnimalScreen(model, controller);
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
