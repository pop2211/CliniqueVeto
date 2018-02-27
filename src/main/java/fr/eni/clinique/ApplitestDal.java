package fr.eni.clinique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.impl.AnimalJDBCDAOImpl;
import fr.eni.clinique.dal.exception.DaoException;

public class ApplitestDal {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AppliTestBO.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
            //TEST serialisation
            AnimalDAO animalDAO = new AnimalJDBCDAOImpl();
            
            Race race = new Race("pasderace", "bambie");
            Animal animal = new Animal("MamandeBambi", "h", "bleu", "symbole", "alcoolique", false , race , 5);
            
            LOGGER.info("Check inserting Eleve : {}", animalDAO.insert(animal));
            
            LOGGER.info("Check reading Eleve 001: {}", animalDAO.selectById(animal.getCodeAnimal()));
            
            LOGGER.info("Check reading all Eleve : {}", animalDAO.selectAll());
            
          
          
            // Test SELECT ALL
            //List<Eleve> eleves = eleveDAO.selectAll();
            
            //LOGGER.info("Eleves before Inserting: {}", eleves);
            
            // Test insert Eleve
            //Eleve newEleve = eleveDAO.insert(new Eleve("Montiel", "Bernard", "Rue de l'ENI", "10/10/1990"));
            
            //LOGGER.info("New Eleve: {}", newEleve);
            
            // Test update
            /*Eleve eleve = eleves.get(0);
            eleve.setPrenom("Jacques");
            
            eleveDAO.update(eleve);*/
            
            // Test Delete
            /*Eleve eleve = eleves.get(0);
            eleveDAO.delete(eleve.getId());*/
            
            // Test Select by Id.
            //LOGGER.info("Eleve with id 2: {}", eleveDAO.selectById(2));
            
            // Test Search by name and firstname.
            //LOGGER.info("Sarch Eleves : {}", eleveDAO.search("Montiel", "Bernard"));
          
            
            
            
            //ArticleDAO articleDAO = new ArticleDAOJDPCImpl();
            
            // Test SELECT ALL
            /*List<Article> articles = articleDAO.selectAll();
            
            LOGGER.info("articles before Inserting: {}", articles);*/
            
            // Test insert Eleve
           /* Article newArticle = articleDAO.insert(new Stylo("REF", "MARQUE", "DESIGNATION", 4, 5, "BLEU"));
            Article newArticle2 = articleDAO.insert(new Ramette("REF2", "MARQUE3", "DESIGNATION2", 5, 6, 12));
            LOGGER.info("New Stylo: {}", newArticle);
            LOGGER.info("New Ramette: {}", newArticle2);*/
            
            //Test update
            /*Article article = articles.get(0);
            article.setMarque("marqueUpdate");
            
            articleDAO.update(article);*/
            
            // Test Delete
            /*Article article = articles.get(0);
            articleDAO.delete(article.getIdArticle());*/
            
            // Test Select by Id.
            //LOGGER.info("Article with id 5: {}", articleDAO.selectById(20));
            
            // Test Search by name and firstname.
            //LOGGER.info("Sarch Eleves : {}", eleveDAO.search("Montiel", "Bernard"));
            
          
            //test rollback
             // articleDAO.insertBis();
            
        } catch (DaoException e) {
            LOGGER.error("ERROR", e);
            e.printStackTrace();
        }
		
	}

}
