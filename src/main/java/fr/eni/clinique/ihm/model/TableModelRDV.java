package fr.eni.clinique.ihm.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.impl.AnimalManagerImpl;
import fr.eni.clinique.bll.manager.impl.RdvManagerImpl;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.ihm.controller.ClientController;

public class TableModelRDV extends AbstractTableModel{

	
	private static final long serialVersionUID = 4379302346906726025L;

	private final String[] entetes = {"Heure", "Nom du client","Animal","Race"};	
	
	private ClientController controller;
	private RdvManagerImpl rdvManagerImpl;
	private Integer currentVetoId;

	private List<Rdv> rdvs;
	
	public TableModelRDV() {
		rdvManagerImpl = RdvManagerImpl.getInstance();
		try {
			rdvs = rdvManagerImpl.selectAll();
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TableModelRDV(Integer currentVetoId){
		this.rdvManagerImpl = RdvManagerImpl.getInstance();
		setCurrentVetoId(currentVetoId);
		refresh();
	}
	
	public void setCurrentVetoId(Integer currentVetoId){
		this.currentVetoId = currentVetoId;
	}
	
	public void refresh(){
		try {
			this.rdvs = rdvManagerImpl.selectAll();
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.fireTableDataChanged();	//UI Refresh
	}
	
	public List<Rdv> getNotes() {
		return rdvs;
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
		return rdvs.size();
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		
		case 0:
			
			return rdvs.get(rowIndex).getDateRdv();
			
		case 1:

			try {
				return controller.loadClient(rdvs.get(rowIndex).getAnimal().getCodeClient()).getFullname();
			} catch (ManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		case 2:		
			
			return rdvs.get(rowIndex).getAnimal().getNomAnimal();

		case 3:

			return rdvs.get(rowIndex).getAnimal().getRace().getRace();

		default:
			throw new IllegalArgumentException();
		}
	}
	
}
