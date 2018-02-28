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

import fr.eni.clinique.ihm.listener.PersonnelActionListener;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.login.InternalFrameLogin;


public class ConnexionScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private JMenu menuAgenda;
	private InternalFrameLogin frameLogin;
	
	private PersonnelActionListener actionListener;


	public ConnexionScreen(String title, PersonnelModel mode) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		// initialiser l'ecran MDI
		desktopPane = new JDesktopPane();

		// Associer le JDesktopPane à la JFrame
		setContentPane(desktopPane);

		// Barre de menus
		setJMenuBar(getMenuBarre());
		
		//Frame interne login
		desktopPane.add(getFrameLogin());
		getFrameLogin().setVisible(true);

	}

	public void createMenuBar() {

		// Menu Fichier
		JMenu menu = new JMenu("Fichier");
		menuBarre.add(menu);

		// Sous menu Déconnexion
		JMenuItem menuItem = new JMenuItem("Déconnexion");
		menuItem.setActionCommand("deconnexion");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Sous menu fermer
		menuItem = new JMenuItem("Fermer");
		menuItem.setActionCommand("fermer");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Menu Agenda
		menuItem = new JMenuItem("Ecran");
		menuBarre.add(menuItem);		
		menuItem.setActionCommand("ecran");
		menuItem.addActionListener(this);

	}

	// Réagir aux clicks sur les menus
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "deconnexion":
			System.out.println("Deconnexion");
			break;
		case "fermer":
			System.exit(0);
			break;

		case "ecran":
			System.out.println("coucou");
			getFrameLogin().setVisible(true);
			break;

		default:
			System.out.println("Probleme e=" + e);
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

	public InternalFrameLogin getFrameLogin() {
		if(frameLogin== null){
			frameLogin = new InternalFrameLogin();
		}
		return frameLogin;
	}
	
	 public void setActionListener(PersonnelActionListener actionListener) {
	        
	        if(actionListener != null) {
	            
	            this.actionListener = actionListener;
	            
	            /*try {

	            } catch (Exception e) {
	                showFailureMessage(e.getMessage());
	            }*/
	        }
	    }

}
