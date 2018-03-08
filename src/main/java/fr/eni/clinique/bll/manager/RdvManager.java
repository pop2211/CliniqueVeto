package fr.eni.clinique.bll.manager;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.bo.Rdv;

public interface RdvManager extends GenericManager<Rdv, Integer>{

	List<Rdv> selectByVetAndDate(Integer codePersonne, String date) throws ManagerException;
}
