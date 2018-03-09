package fr.eni.clinique.ihm.screen.personnel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.ihm.controller.PersonnelController;
import fr.eni.clinique.ihm.model.PersonnelModel;
import fr.eni.clinique.ihm.screen.common.GenericScreen;
import fr.eni.clinique.ihm.screen.common.JTextFieldLimit;

public class RebootPasswordPersonnelScreen extends GenericScreen {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField mdpTbx;
	private Integer currentCodePersonnel;
	private PersonnelController personnelController;
	private PersonnelModel personnelModel;


	public RebootPasswordPersonnelScreen(PersonnelScreen personnelScreen, PersonnelModel model, PersonnelController controller, Integer CodePerso) {
		super("Modification Mot de passe personnel", true, true, true,true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		currentCodePersonnel = CodePerso;
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 184, 139, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblChangementDuMot = new JLabel("Changement du mot de passe");
		lblChangementDuMot.setForeground(new Color(255, 0, 0));
		lblChangementDuMot.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblChangementDuMot = new GridBagConstraints();
		gbc_lblChangementDuMot.insets = new Insets(0, 0, 5, 5);
		gbc_lblChangementDuMot.gridx = 4;
		gbc_lblChangementDuMot.gridy = 1;
		getContentPane().add(lblChangementDuMot, gbc_lblChangementDuMot);
		
		JLabel lblNouveauMdp = new JLabel("Nouveau MDP :");
		lblNouveauMdp.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNouveauMdp = new GridBagConstraints();
		gbc_lblNouveauMdp.insets = new Insets(0, 0, 5, 5);
		gbc_lblNouveauMdp.anchor = GridBagConstraints.EAST;
		gbc_lblNouveauMdp.gridx = 3;
		gbc_lblNouveauMdp.gridy = 4;
		getContentPane().add(lblNouveauMdp, gbc_lblNouveauMdp);
		
		mdpTbx = new JTextField();
		mdpTbx.setDocument(new JTextFieldLimit(10));
		GridBagConstraints gbc_mdpTbx = new GridBagConstraints();
		gbc_mdpTbx.insets = new Insets(0, 0, 5, 5);
		gbc_mdpTbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_mdpTbx.gridx = 4;
		gbc_mdpTbx.gridy = 4;
		getContentPane().add(mdpTbx, gbc_mdpTbx);
		mdpTbx.setColumns(10);
		
		JButton validerBtn = new JButton("Valider");
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.changePswPersonnel(currentCodePersonnel, mdpTbx.getText());
					setVisible(false);
					personnelScreen.getTableModel().refresh();
					showSuccessMessage("Mot de Passe modifié !");
				} catch (ManagerException e1) {
					errorOccured(e1);
				}
				
			}
		});
		validerBtn.setIcon(new ImageIcon(RebootPasswordPersonnelScreen.class.getResource("/images/ico/done_32p.png")));
		GridBagConstraints gbc_validerBtn = new GridBagConstraints();
		gbc_validerBtn.insets = new Insets(0, 0, 0, 5);
		gbc_validerBtn.gridx = 4;
		gbc_validerBtn.gridy = 7;
		getContentPane().add(validerBtn, gbc_validerBtn);

		pack();
	}
	
	public void showPersonnel(Personnel personnnel) {
		mdpTbx.setText(ObjectUtil.nullToBlank(personnnel.getMdp()).trim());		
	}
	

	@Override
	public void processEvent(String eventName, Object eventParam) {
		// TODO Auto-generated method stub
		
	}
	

}
