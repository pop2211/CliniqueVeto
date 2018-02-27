package fr.eni.clinique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bll.exception.ManagerException;

public class AppliTestBLL {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestBLL.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			
			
		} catch (ManagerException e) {
            LOGGER.error("ERROR", e);
            e.printStackTrace();
        }
		}
}
