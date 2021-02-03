package appliRDV;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import data.Medecin;
import data.Patient;
import data.RDV;

public interface ILoadRDVs {
	
	public List<RDV> getRdvList(String filename); 
	public List<Medecin> getMedecins(); 
	public List<Patient> getPatients(); 

}