package fr.eni.clinique.ihm.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.manager.impl.ClientManagerImpl;
import fr.eni.clinique.bll.manager.impl.RdvManagerImpl;
import fr.eni.clinique.bo.Rdv;

public class TableModelAgenda extends AbstractTableModel{

	private static final long serialVersionUID = 7961502842732162947L;

	private final String[] entetes = {"Heure", "Nom du client","Animal","Race"};	
	
	private ClientManagerImpl clientManagerImpl;
	private RdvManagerImpl rdvManagerImpl;
	private Integer currentVetoId;
	private String date;

	private List<Rdv> rdvs;
	
	public TableModelAgenda(Integer currentVetoId, String date){
		rdvManagerImpl = RdvManagerImpl.getInstance();
		clientManagerImpl = new ClientManagerImpl();
		this.currentVetoId = currentVetoId;
		this.date = date;
		refresh(currentVetoId, date);
	}
	
	public void refresh(Integer currentVetoId, String date){
		try {
			this.rdvs = rdvManagerImpl.selectByVetAndDate(currentVetoId, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.fireTableDataChanged();	//UI Refresh
	}
	
	public List<Rdv> getRdvs() {
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
		String txt = "";
		
		switch (columnIndex) {
		
		case 0:
			return rdvs.get(rowIndex).getTime();
			
		case 1:
			try {
				txt = clientManagerImpl.selectById(rdvs.get(rowIndex).getAnimal().getClient().getCodeClient()).getFullname();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return txt;

		case 2:		
			try {
				txt = rdvs.get(rowIndex).getAnimal().getNomAnimal();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return txt;

		case 3:
			try {
				txt = rdvs.get(rowIndex).getAnimal().getRace().getRace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return txt;

		default:
			throw new IllegalArgumentException();
		}
	}
}
