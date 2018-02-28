package fr.eni.clinique.ihm.screen.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.ihm.listener.PersonnelActionListener;

public class InternalFrameLogin extends JInternalFrame implements ActionListener {
	
	private static final long serialVersionUID = -4557163862895833172L;
	
	private JPanel mainPanel;
    private JTextField loginInput;
    private JTextField passwordInput;
    private JButton validateButton;
    //private JPanel buttonBar;
    
    private PersonnelActionListener actionListener;
    //private PersonnelModel model;
    
    public JButton getValidateButton(){
    	return validateButton;
    }
	
	public InternalFrameLogin() {
		//Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
		super("Connexion", true, true, true,true);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100,400, 200);
		
		loginInput = createTextField(AppConstants.EMPTY, "");
		passwordInput = createTextField(AppConstants.EMPTY, "");
		
		setContentPane(createMainPanel());
		
        addFormRow("Nom", loginInput, 1);
        addFormRow("Mot de passe", passwordInput, 2);
        
       
		//buttonBar = createButtonBar();
        //mainPanel.add(buttonBar, createGridBagConstraints(1, 1, 10));
        
        
        validateButton = new JButton("Valider");
        validateButton.setActionCommand("connexion");
        validateButton.addActionListener(this);
//        validateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(actionListener != null) {
//                    connectPersonnel();
//                }
//            }
//        });
        
        
        mainPanel.add(validateButton, createGridBagConstraints(0.7, 1, 3));
        
        
	}
	
    /**
     * Connect Personnel.
     */
	/*
    private void connectPersonnel() {

        try {
        	System.out.println("TRY");
            actionListener.connectPersonnel(new PersonnelActionEvent(readPersonnelLoginPassword()));

            showSuccessMessage("Connexion réussie !");

        } catch (Exception e) {
            showFailureMessage(e.getMessage());
        }
    }
    */
    
    
    /**
     * Read Personnel login and password from the UI.
     * 
     * @return
     */
    private Personnel readPersonnelLoginPassword() {
        Personnel personnel = new Personnel();
        personnel.setNom(loginInput.getText().trim());
        personnel.setMdp(passwordInput.getText().trim());
        
        return personnel;
    }
	
	
    private JTextField createTextField(String defaultValue, String tooltip) {
        
        JTextField textField = new JTextField(defaultValue);
        textField.setToolTipText(tooltip);
        return textField;
    }
    
    private void addFormRow(String label, JComponent component, int lineNumber) {
        mainPanel.add(createLabel(label.concat(":")), createGridBagConstraints(0.3, 0, lineNumber));
        mainPanel.add(component, createGridBagConstraints(0.7, 1, lineNumber));
    }
    
    private JPanel createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setLayout(new GridBagLayout());
        return mainPanel;
    }
    
    private JLabel createLabel(String text) {
        return new JLabel(text);
    }
    
    private GridBagConstraints createGridBagConstraints(double weight, int cellX, int cellY) {
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = cellX;
        gridBagConstraints.gridy = cellY;
        gridBagConstraints.weightx = weight;
        
        return gridBagConstraints;
    }


    
    
    /**
     * Show TechnicalError.
     * 
     * @param message
     */
    private void showFailureMessage(String message) {
        JOptionPane.showMessageDialog(InternalFrameLogin.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    
    /**
     * Show Success Message.
     * 
     * @param message
     */
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(InternalFrameLogin.this, message);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
    /*
    private JPanel createButtonBar() {

        validateButton = new JButton("Valider");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    connectPersonnel();
                }
            }
        });
        
        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(true);
        panelButtons.setLayout(new GridBagLayout());
        
        panelButtons.add(validateButton, createGridBagConstraints(0.2, 3, 1));
        
        return panelButtons;
    }
    */

}
