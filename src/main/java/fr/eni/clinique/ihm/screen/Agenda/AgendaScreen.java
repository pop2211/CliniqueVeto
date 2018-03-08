package fr.eni.clinique.ihm.screen.Agenda;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import fr.eni.clinique.ihm.screen.common.GenericScreen;

public class AgendaScreen extends GenericScreen {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6638231117920115532L;

	/**
	 * Create the frame.
	 */
	public AgendaScreen() {
		
		super("Gestion des Agendas", true, true, true, true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		
		
		
		this.pack();
	}

	@Override
	public void processEvent(String eventName, Object eventParam) {
		// TODO Auto-generated method stub
		
	}

}
