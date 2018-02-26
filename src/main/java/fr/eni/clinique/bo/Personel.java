package fr.eni.clinique.bo;

public class Personel {
	private Integer id;
	private String nom; 
	private String mdp; 
	private String role; 
	private byte archive;

    public Personel() {
        
    }

    public Personel(Integer id, String nom, String mdp, String role, Byte archive) {
		this.id = id;
    	this.nom = nom;
		this.mdp = mdp;
		this.role = role;
		this.archive = archive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Byte isArchive() {
		return archive;
	}

	public void setArchive(Byte archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Personel [id=");
		builder.append(id);
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
