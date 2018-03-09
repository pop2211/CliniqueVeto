package fr.eni.clinique.bll.manager;

import java.util.List;

import fr.eni.clinique.bll.exception.ManagerException;
import fr.eni.clinique.dal.exception.DaoException;

public interface GenericManager<T, ID> {
	    
	    /**
	     * Insert an Element.
	     * 
	     * @param element The Element.
	     * 
	     * @throws DaoException
	     */
		T insert(T element) throws ManagerException;
		
		/**
		 * Update An Element.
		 * 
		 * @param element The Element.
		 * 
		 * @throws DaoException
		 */
		void update(T element) throws ManagerException;	
		
		/**
		 * Delete an Element.
		 * 
		 * @param id The ID of the Element.
		 * 
		 * @throws DaoException
		 */
		void delete(ID id) throws ManagerException;
		
		/**
		 * Get Element by its ID.
		 * 
		 * @param id The id of the Element.
		 * 
		 * @return The Selected Element.
		 * 
		 * @throws DaoException
		 */
		T selectById(ID id) throws ManagerException;
		
		/**
		 * Get all elements.
		 * 
		 * @return The Elements found.
		 * 
		 * @throws DaoException
		 */
		List<T> selectAll() throws ManagerException;
		

	}
