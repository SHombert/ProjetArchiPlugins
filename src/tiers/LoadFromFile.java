package tiers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import appliRDV.ILoadRDVs;
import data.Medecin;
import data.Patient;
import data.RDV;

public class LoadFromFile implements ILoadRDVs {

	private List<Medecin> medecins;
	private List<Patient> patients;
	
	
	public LoadFromFile() {
		super();
		this.medecins = new ArrayList<>();
		this.patients = new ArrayList<>();
	}

	public List<RDV> getRdvList(String filename){



		List<RDV> rdvList = new ArrayList();

		File file = new File(filename);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				//construction des objects RDV
				String  dateString = line.split(",")[0];

				//					DateFormat format = new SimpleDateFormat("dd-MM-yy");
				//					String date = format.parse(dateString);

				String  horaire = line.split(",")[1];
				String  motif = line.split(",")[2];


				String  patientString = line.split(",")[3];
				Patient patient = new Patient();
				String [] splittedPatient = patientString.split(" ");
				patient.setNom(splittedPatient[0]);
				patient.setPrenom(splittedPatient[1]);
				patients.add(patient);

				String  medecinString = line.split(",")[4];
				Medecin medecin = new Medecin();
				String [] splittedMedecin = medecinString.split(" ");
				medecin.setNom(splittedMedecin[0]);
				medecin.setPrenom(splittedMedecin[1]);
				medecins.add(medecin);

				RDV rdv = new RDV(dateString, horaire, motif, patient, medecin);

				rdvList.add(rdv);			  

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return rdvList;

	}

	@Override
	public List<Medecin> getMedecins() {
		// TODO Auto-generated method stub
		return medecins;
	}

	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		return patients;
	}




}
