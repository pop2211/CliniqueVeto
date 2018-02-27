package fr.eni.clinique.bo;

public enum EnumRole {
	
    ADMIN("ADMIN", "Administrateur"), 
    USER("USER", "Utilisateur"),  
    SECRETAIRE("SECRETAIRE", "Secrétaire"), 
    VETERINAIRE("VETERINAIRE", "Vétérinaire"),
    DEVELOPPEUR("DEVELOPPEUR", "Développeur"),
    SUPPORT_TECH("SUPPORT_TECH", "Support technique");
    
	private String code; 
    private String libelle;
     
    private EnumRole(String code, String libelle) {
    	this.code = code;
        this.libelle = libelle ;  
    }
     
    public String getCode() {
        return  this.code ;
    }
    public String getLibelle() {
        return  this.libelle ;  
    }
	
}
