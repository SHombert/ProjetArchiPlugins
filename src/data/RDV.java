package data;

import java.util.Date;

public class RDV {

	
	private Date date;
	private String horaire;
	private String motif;
	private Patient patient;
	private Medecin medecin;
	
	public RDV(Date date, String horaire, String motif, Patient patient, Medecin medecin) {
		this.date = date;
		this.horaire = horaire;
		this.motif = motif;
		this.patient = patient;
		this.medecin = medecin;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getHoraire() {
		return horaire;
	}
	
	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}
	
	public String getMotif() {
		return motif;
	}
	
	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Medecin getMedecin() {
		return medecin;
	}
	
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public String toString() {
		return "RDV [date=" + date + ", horaire=" + horaire + ", motif=" + motif + ", patient=" + patient + ", medecin="
				+ medecin + "]";
	}
	
	
}
