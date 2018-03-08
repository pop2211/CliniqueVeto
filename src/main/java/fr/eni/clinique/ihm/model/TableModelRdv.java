package fr.eni.clinique.ihm.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.impl.AnimalManagerImpl;
import fr.eni.clinique.bll.manager.impl.ClientManagerImpl;
import fr.eni.clinique.bll.manager.impl.RdvManagerImpl;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.ihm.controller.ClientController;

public class TableModelRdv extends AbstractTableModel{

	
	private static final long serialVersionUID = 4379302346906726025L;

	private final String[] entetes = {"Heure", "Nom du client","Animal","Race"};	
	
	private ClientManagerImpl clientManagerImpl;
	private RdvManagerImpl rdvManagerImpl;
	private Integer currentVetoId;
	private String date;

	private List<Rdv> rdvs;
	
	public TableModelRdv(Integer currentVetoId, String date){
		rdvManagerImpl = RdvManagerImpl.getInstance();
		clientManagerImpl = new ClientManagerImpl();
		this.currentVetoId = currentVetoId;
		this.date = date;
		refresh(currentVetoId, date);
	}
	
	public void refresh(Integer currentVetoId, String date){
		try {
			this.rdvs = rdvManagerImpl.selectByVetAndDate(currentVetoId, date);
			System.out.println(rdvs);
		} catch (ManagerException e) {
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
			
			return rdvs.get(rowIndex).getTime();
			
		case 1:
			try {
				return (clientManagerImpl.selectById(rdvs.get(rowIndex).getAnimal().getCodeClient())).getFullname();
			} catch (ManagerException e) {
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
