package fr.eni.clinique.ihm;

import javax.swing.JInternalFrame;

public class InternalFrameLogin extends JInternalFrame {
	
	public InternalFrameLogin() {
		//Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
		super("Connexion", true, true, true,true);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100,400, 200);

	}

}
