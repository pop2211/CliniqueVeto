package fr.eni.clinique.bo;

public enum EnumRole {
	
    ADMIN("ADMIN", "Administrateur", "gestion administrative de la clinique (financière et ressources humaines)"), 
    USER("USER", "Utilisateur", "aider les vétérinaires lors des consultations à la clinique en exécutant les actes de base. Souvent des stagiaires provenant des écoles vétérinaires."),
    SECRETAIRE("SECRETAIRE", "Secrétaire", "lien entre la clinique et le client. Prise de rendez-vous téléphonique, préparation des tournées, facturation."), 
    VETERINAIRE("VETERINAIRE", "Vétérinaire", "effectuer des consultations à la clinique sur rendez-vous et effectuer des consultations à domicile (tournée planifiée)"),
    SUPPORT_TECH("SUPPORT_TECH", "Support technique", "gestion du parc matériel de la clinique (matériel professionnel, bureautique et informatique)"),
    DEVELOPPEUR("DEVELOPPEUR", "Développeur", "");
    
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
	
}
