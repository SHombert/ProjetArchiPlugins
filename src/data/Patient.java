package data;

public class Patient {
	private String nom;
	private String prenom;
	 
    public Patient() {
	  super();
	  // TODO Auto-generated constructor stub
     }
	
	public Patient(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
    }
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return nom.toUpperCase() + " " + prenom;
	}
	
	
}
