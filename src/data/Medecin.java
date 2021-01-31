package data;

public class Medecin {
  private String nom;
  private String prenom;
  
    
	public Medecin() {
	  super();
	  // TODO Auto-generated constructor stub
     }
	
	public Medecin(String nom, String prenom) {
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
