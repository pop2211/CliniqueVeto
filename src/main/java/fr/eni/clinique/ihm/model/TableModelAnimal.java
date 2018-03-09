package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.impl.AnimalManagerImpl;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.common.util.LogUtil;


public class TableModelAnimal extends AbstractTableModel{
	
	private final String[] entetes = { "Numéro", "Nom", "Sex", "Couleur", "Race", "Espèce", "Tatouage" };	
	
	private AnimalManagerImpl animalManagerImpl;

	private List<Animal> animaux;
	private Integer currentClientId;
	
	public TableModelAnimal(){
		this.animalManagerImpl = AnimalManagerImpl.getInstance();
		refresh();
	}
	
	public TableModelAnimal(Integer currentClientId){
		this.animalManagerImpl = AnimalManagerImpl.getInstance();
		setCurrentClientId(currentClientId);
		refresh();
	}
	
	public void setCurrentClientId(Integer currentClientId){
		this.currentClientId = currentClientId;
	}
	
	public void refresh(){
		try {
			this.animaux = new ArrayList<Animal>();
			if(currentClientId != null){
				this.animaux = animalManagerImpl.selectByMaitre(currentClientId);
			}
		} catch (ManagerException e) {
			LogUtil.LOGGER.error("ERROR", e);
		}
		this.fireTableDataChanged();	//UI Refresh
	}
		
	
	public List<Animal> getNotes() {
		return animaux;
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
		return animaux.size();
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		//"Numéro", "Nom", "Sex", "Couleur", "Race", "Espèce", "Tatouage"
		case 0: return animaux.get(rowIndex).getCodeAnimal();
		case 1: return animaux.get(rowIndex).getNomAnimal();
		case 2: return animaux.get(rowIndex).getSexe();
		case 3: return animaux.get(rowIndex).getCouleur();
		case 4: return animaux.get(rowIndex).getRace().getRace();
		case 5: return animaux.get(rowIndex).getRace().getEspece();
		case 6: return animaux.get(rowIndex).getTatouage();

		default:
			throw new IllegalArgumentException();
		}
	}

}
