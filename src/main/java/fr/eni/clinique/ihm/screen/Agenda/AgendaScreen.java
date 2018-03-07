package fr.eni.clinique.ihm.screen.Agenda;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class AgendaScreen extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaScreen frame = new AgendaScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgendaScreen() {
		setBounds(100, 100, 450, 300);

	}

}
