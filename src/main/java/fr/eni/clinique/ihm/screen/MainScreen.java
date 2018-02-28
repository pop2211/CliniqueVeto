package fr.eni.clinique.ihm.screen;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.listener.PersonnelActionListener;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.login.InternalFrameLogin;

public class MainScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private PersonnelModel model;
	private PersonnelController controller;

	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private InternalFrameLogin frameLogin;

	public MainScreen(String title, PersonnelModel model, PersonnelController controller) {
		this.controller = controller;
		this.model = model;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int largeurFen = (int) Math.round(screenSize.width * 0.8);
		int hauteurFen = (int) Math.round(screenSize.height * 0.8);
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
			frameLogin.setVisible(true);
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

	public InternalFrameLogin getFrameLogin(PersonnelModel model, PersonnelController controller) {
		if (frameLogin == null) {
			frameLogin = new InternalFrameLogin(model, controller);
		}
		return frameLogin;
	}
    
    /**
     * Show TechnicalError.
     * 
     * @param message
     */
    private void showFailureMessage(String message) {
        JOptionPane.showMessageDialog(MainScreen.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Show Success Message.
     * 
     * @param message
     */
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(MainScreen.this, message);
    }
    
}
