package fr.eni.clinique.ihm.screen.personnel;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class PersonnelScreen extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonnelScreen frame = new PersonnelScreen();
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
	public PersonnelScreen() {
		setBounds(100, 100, 450, 300);

	}

}
