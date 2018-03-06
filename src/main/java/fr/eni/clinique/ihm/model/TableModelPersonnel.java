package fr.eni.clinique.ihm.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.impl.PersonnelManagerImpl;
import fr.eni.clinique.bo.EnumRole;
import fr.eni.clinique.bo.Personnel;

public class TableModelPersonnel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4414244363450220508L;

	private final String[] entetes = {"CodePerso", "Nom","Role","Mdp"};	
	
	private PersonnelManagerImpl personnelManagerImpl;

	private List<Personnel> personnels;
	
	public TableModelPersonnel() {
		personnelManagerImpl = PersonnelManagerImpl.getInstance();
		try {
			personnels = personnelManagerImpl.selectAll();
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refresh(){
		try {
			this.personnels = personnelManagerImpl.selectAll();
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.fireTableDataChanged();	//UI Refresh
	}
	
	public List<Personnel> getNotes() {
		return personnels;
	}
	

	@Override
	public int getColumnCount() {
		return entetes.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public int getRowCount() {
		return personnels.size();
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		
		case 0:
			return personnels.get(rowIndex).getCodePers();
			
		case 1:

			return personnels.get(rowIndex).getNom();

		case 2:		
			
			return EnumRole.libelleByCode(personnels.get(rowIndex).getRole());

		case 3:

			return personnels.get(rowIndex).getMdp();

		default:
			throw new IllegalArgumentException();
		}
	}

}
