package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumRole {
	
    ADMIN("ADM", "Administrateur", "gestion administrative de la clinique (financière et ressources humaines)"), 
    USER("USR", "Utilisateur", "aider les vétérinaires lors des consultations à la clinique en exécutant les actes de base. Souvent des stagiaires provenant des écoles vétérinaires."),
    SECRETAIRE("SEC", "Secrétaire", "lien entre la clinique et le client. Prise de rendez-vous téléphonique, préparation des tournées, facturation."), 
    VETERINAIRE("VET", "Vétérinaire", "effectuer des consultations à la clinique sur rendez-vous et effectuer des consultations à domicile (tournée planifiée)"),
    SUPPORT_TECH("SUP", "Support technique", "gestion du parc matériel de la clinique (matériel professionnel, bureautique et informatique)"),
    DEVELOPPEUR("DEV", "Développeur", "");
    
	private String code; 
    private String libelle;
    private String description;
     
    private EnumRole(String code, String libelle, String description) {
    	this.code = code;
        this.libelle = libelle;
        this.description = description ;  
    }
     
    public String getCode() {
        return  this.code;
    }
    
    public String getLibelle() {
        return  this.libelle;  
    }
    
    public String getDescription() {
        return  this.description;  
    }
    
    public static List<EnumRole> getList(){
    	List<EnumRole> myList = new ArrayList<EnumRole>();
    	myList.addAll(Arrays.asList(ADMIN, USER,SECRETAIRE,VETERINAIRE, SUPPORT_TECH, DEVELOPPEUR));
		return myList;
    	
    }
 
    public static String libelleByCode(String code){
    	List<EnumRole> myList = getList();
    	for(EnumRole role : myList){
    		if(role.getCode().equals(code)){
    			return role.getLibelle();
    		}
    	}
    	return null;
    }
	
}
