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

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.ihm.listener.PersonnelActionListener;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.login.InternalFrameLogin;

public class ConnexionScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private InternalFrameLogin frameLogin;
	private JButton frameLoginBtn;

	private PersonnelActionListener actionListener;

	public ConnexionScreen(String title, PersonnelModel model) {

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
		desktopPane.add(getFrameLogin());
		getFrameLogin().setVisible(true);

		frameLoginBtn = getFrameLogin().getValidateButton();
		System.out.println("FAIL COMMING");
		frameLoginBtn.addActionListener(this);
		System.out.println("AFTER FAIL ?");
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
			getFrameLogin().setVisible(true);
			break;
		case "connexion":
			System.out.println("CLIC CONNEXION");
			connectPersonnel();
			//si connexion OK:
			getFrameLogin().setVisible(false);
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
		if (frameLogin == null) {
			frameLogin = new InternalFrameLogin();
		}
		return frameLogin;
	}

	public void setActionListener(PersonnelActionListener aL) {

		System.out.println("MAINSCREEN EXEC setActionListener (1)");

		if (aL != null) {

			System.out.println("MAINSCREEN EXEC setActionListener (2)");

			this.actionListener = aL;

			/*
			 * try {
			 * 
			 * } catch (Exception e) { showFailureMessage(e.getMessage()); }
			 */
		}
	}
	
	
    /**
     * Connect Personnel.
     */
    private boolean connectPersonnel() {

        try {
        	System.out.println("TRY");
        	Personnel identifiants = getFrameLogin().readPersonnelLoginPassword();
            //actionListener.connectPersonnel(new PersonnelActionEvent(identifiants));
        	
            Boolean testLoginPass = false;
            if(testLoginPass){
            	showSuccessMessage("Connexion réussie !");
            	return true;
            }
            
            
        } catch (Exception e) {
            showFailureMessage(e.getMessage());
        }
        return false;
    }
    
    
    
    /**
     * Show TechnicalError.
     * 
     * @param message
     */
    private void showFailureMessage(String message) {
        JOptionPane.showMessageDialog(ConnexionScreen.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    
    /**
     * Show Success Message.
     * 
     * @param message
     */
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(ConnexionScreen.this, message);
    }

    

}
