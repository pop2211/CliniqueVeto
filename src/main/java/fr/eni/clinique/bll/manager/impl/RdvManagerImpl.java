package fr.eni.clinique.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bll.manager.RdvManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.DaoFactory;

public class RdvManagerImpl implements RdvManager{
	private RdvDAO rdvDAO = DaoFactory.RdvDAO();
    
	private static RdvManagerImpl instance;
	 
	public static RdvManagerImpl getInstance() {
		if(instance == null) {
			instance = new RdvManagerImpl();
		}
		return instance;
	}

	@Override
	public Rdv insert(Rdv rdv) throws ManagerException {
        try {
        	validerRdv(rdv);
            
        	rdv = rdvDAO.insert(rdv);
            
        } catch (DaoException e) {
            throw new ManagerException("Echec addRdv", e);
        }
        return rdv;
	}

	@Override
	public void update(Rdv element) throws ManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws ManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rdv selectById(Integer id) throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rdv> selectAll() throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validerRdv(Rdv rdv) throws ManagerException {

        try {
            ObjectUtil.checkNotNullWithMessage(rdv, "Une erreur technique est survenue");
            ObjectUtil.checkNotNullWithMessage(rdv.getAnimal(), "L'animal est obligatoire");
            ObjectUtil.checkNotNullWithMessage(rdv.getVeto(), "Le veterinnaire est obligatoire");
            ObjectUtil.checkNotNullWithMessage(rdv.getDateRdv(), "La date est obligatoire");
            ObjectUtil.checkTimestamp(rdv.getDateRdv(), "Date au format invalide");
        } catch (IllegalArgumentException e) {
            throw new ManagerException(String.format("Erreur de validation : %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

	@Override
	public List<Rdv> selectByVetAndDate(Integer codePersonne, String date) throws ManagerException {
        List<Rdv> listeRendezVous = new ArrayList<Rdv>();
		
		try {
			listeRendezVous = rdvDAO.selectByVetAndDate(codePersonne, date);
            
        } catch (DaoException e) {
            throw new ManagerException("Erreur récupération Liste RDV", e);
        }
		return listeRendezVous;
	}

}
