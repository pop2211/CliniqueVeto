package fr.eni.clinique.ihm.screen.login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.MainScreen;
import fr.eni.clinique.ihm.screen.common.GenericScreen;

public class LoginScreen extends GenericScreen{
	
	
	private static final long serialVersionUID = -6157689225828979560L;
	
	private PersonnelModel model;
	private PersonnelController controller;
	
	private JPanel mainPanel;
    private JTextField loginInput;
    private JTextField passwordInput;
    private JButton validateButton;
    private JMenu menu;
	
	public LoginScreen(MainScreen mainScreen, PersonnelModel model, PersonnelController controller) {
		//Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
		super("Connexion", true, false, true, false);
		this.mainScreen = mainScreen;
		this.controller = controller;
		this.model = model;
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		//setBounds(100, 100,350, 170);
		
		String defaultLogin = "u";
		String defaultPassword = "u";
		
        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
		setContentPane(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{20, 68, 0, 80, 80, 20, 0};
		gbl_mainPanel.rowHeights = new int[]{20, 0, 0, 35, 20, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
        
        JLabel lblNom = new JLabel("Nom:");
        GridBagConstraints gbc_lblNom = new GridBagConstraints();
        gbc_lblNom.insets = new Insets(0, 0, 5, 5);
        gbc_lblNom.gridx = 1;
        gbc_lblNom.gridy = 1;
        mainPanel.add(lblNom, gbc_lblNom);
        
        loginInput = new JTextField(defaultLogin);
        loginInput.setToolTipText("Votre Nom");
        GridBagConstraints gbc_loginInput = new GridBagConstraints();
        gbc_loginInput.fill = GridBagConstraints.HORIZONTAL;
        gbc_loginInput.gridwidth = 2;
        gbc_loginInput.insets = new Insets(0, 0, 5, 5);
        gbc_loginInput.gridx = 3;
        gbc_loginInput.gridy = 1;
        mainPanel.add(loginInput, gbc_loginInput);
        
        JLabel lblPrenom = new JLabel("Mot de passe:");
        GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
        gbc_lblPrenom.insets = new Insets(0, 0, 5, 5);
        gbc_lblPrenom.gridx = 1;
        gbc_lblPrenom.gridy = 2;
        mainPanel.add(lblPrenom, gbc_lblPrenom);
        
        passwordInput = new JPasswordField(defaultPassword);
        passwordInput.setToolTipText("Votre MDP");
        GridBagConstraints gbc_passwordInput = new GridBagConstraints();
        gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordInput.gridwidth = 2;
        gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
        gbc_passwordInput.gridx = 3;
        gbc_passwordInput.gridy = 2;
        mainPanel.add(passwordInput, gbc_passwordInput); 
        
		Image img = null;
		try {
			img = ImageIO.read(new File("src//main//resources//images//ico//done_16p.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
        validateButton = new JButton("Valider");
        validateButton.setIcon(new ImageIcon(img));
	    validateButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	connectPersonnel();
	        }
	    });
	    
        GridBagConstraints gbc_validateButton = new GridBagConstraints();
        gbc_validateButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_validateButton.insets = new Insets(0, 0, 5, 5);
        gbc_validateButton.gridx = 4;
        gbc_validateButton.gridy = 3;
        mainPanel.add(validateButton, gbc_validateButton);

		
		this.pack();
		//this.setLocationRelativeTo(null);
		
		Dimension dim = this.mainScreen.getSize();
		Integer x = (dim.width-this.getSize().width)/2;
		Integer y = ((dim.height-this.getSize().height)/2)-150;
		this.setLocation(x, y);
     
	}
	

    /**
     * Connect Personnel.
     */
    private Boolean connectPersonnel() {
    	Boolean testLoginPass = false;
    	try {
        	Personnel identifiants = readPersonnelLoginPassword();

        	testLoginPass = controller.connectPersonnel(identifiants);
        	
            if(testLoginPass){
            	
            	this.setVisible(false);
            	//acces au MainScreen
            	MainScreen parent = (MainScreen) this.getTopLevelAncestor();
            	parent.setProfil("test");
            	
            	//TODO :
            	getMainScreen().setProfil("test2");
            }
            
        } catch (Exception e) {
            errorOccured(e);
        }
        return testLoginPass;
    }

	/**
     * Read Personnel login and password from the UI.
     * 
     * @return
     */
    public Personnel readPersonnelLoginPassword() {
        Personnel personnel = new Personnel();
        personnel.setNom(loginInput.getText().trim());
        personnel.setMdp(passwordInput.getText().trim());
        
        return personnel;
    }


	@Override
	public void processEvent(String eventName, Object eventParam) {
		// TODO Auto-generated method stub
		
	}
	
}
