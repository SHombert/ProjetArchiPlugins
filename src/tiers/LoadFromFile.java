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

import appli.ILoadRDVs;
import data.Medecin;
import data.Patient;
import data.RDV;

public class LoadFromFile implements ILoadRDVs {

	/*public static void main(String [] args) throws IOException, ParseException {
		List<RDV> listRDV = getRdvList("C:\\Users\\Utilisateur\\Desktop\\MASTER 2\\ARCHI LOG\\ProjetArchiPlugins\\src\\tiers\\rdv_file.txt");

		for (RDV rdv : listRDV) {
		    System.out.println(rdv); 
		}

	}*/

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

				DateFormat format = new SimpleDateFormat("dd-MM-yy");
				Date date = format.parse(dateString);

				String  horaire = line.split(",")[1];
				String  motif = line.split(",")[2];


				String  patientString = line.split(",")[3];
				Patient patient = new Patient();
				patient.setNom(patientString.split(" ")[0]);
				patient.setPrenom(patientString.split(" ")[1]);

				String  medecinString = line.split(",")[4];
				Medecin medecin = new Medecin();
				String [] splittedMedecin = medecinString.split(" ");
				medecin.setNom(splittedMedecin[0]);
				medecin.setPrenom(splittedMedecin[1]);

				RDV rdv = new RDV(date, horaire, motif, patient, medecin);

				rdvList.add(rdv);			  

			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return rdvList;

	}




}
