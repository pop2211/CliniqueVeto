package fr.eni.clinique.bo;

public class Personnel {
	
	private Integer codePers;
	private String nom; 
	private String mdp; 
	private String role; //EnumRole
	private Boolean archive;
	
	
    public Personnel() {
        
    }
    
    public Personnel(String nom, String mdp, String role, Boolean archive) {
    	this.nom = nom;
		this.mdp = mdp;
		this.role = role;
		this.archive = archive;
	}
    
    public Personnel(Integer codePers, String nom, String mdp, String role, Boolean archive) {
    	this(nom, mdp, role, archive);
    	this.codePers = codePers;
	}

	public Personnel(String nom, String mdp) {
		super();
		this.nom = nom;
		this.mdp = mdp;
	}

	public Integer getCodePers() {
		return codePers;
	}

	public void setCodePers(Integer codePers) {
		this.codePers = codePers;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean isArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Personel [codePers=");
		builder.append(codePers);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", mdp=");
		builder.append(mdp);
		builder.append(", role=");
		builder.append(role);
		builder.append(", archive=");
		builder.append(archive);
		builder.append("]");
		return builder.toString();
	}
	
}
