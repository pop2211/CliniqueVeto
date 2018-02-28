package fr.eni.clinique.ihm.screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.clinique.common.AppConstants;

public class InternalFrameLogin extends JInternalFrame {
	
	private static final long serialVersionUID = -4557163862895833172L;
	
	private JPanel mainPanel;
    private JTextField loginInput;
    private JTextField passwordInput;
    private JButton validateButton;
	
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
        
        
        //validateButton = new JButton();
        //validateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Save24.gif")));
        /*
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionListener != null) {
                    saveArticle();
                }
            }
        });
        */
        
        
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
     * @param actionListener the actionListener to set
     */
    /*
    public void setActionListener(CatalogActionListener actionListener) {
        
        if(actionListener != null) {
            
            this.actionListener = actionListener;
            
            try {

                // Fire Initialisation Event.
                this.actionListener.init();
                
                // Get First Article
                Article article = model.firstArticle();

                if (article == null) {
                    actionListener.newArticle(); // Not Article to show => show a new Article.

                    article = model.currentArticle();
                }
                showArticle(article); // First Article

            } catch (Exception e) {
                showFailureMessage(e.getMessage());
            }
        }
    }
    
    public void removeActionListener() {
        this.actionListener = null;
    }
    */

}
