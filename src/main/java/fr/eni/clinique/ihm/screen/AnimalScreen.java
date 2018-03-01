package fr.eni.clinique.ihm.screen;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class AnimalScreen extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalScreen frame = new AnimalScreen();
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
	public AnimalScreen() {
		setBounds(100, 100, 450, 300);

	}

}
