package appli;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import data.Personne;
import data.RDV;
import plateforme.DescripteurPluggin;
import plateforme.Loader;
import tiers.LoadFromFile;

public class Appli implements Runnable{
	IDisplay display;
	HashMap<String, DescripteurPluggin> descriptionPlugginsDispos;
	ILoadRDVs loadRdv;
	ArrayList<RDV> rdvs;
	

	public Appli() {
		// recuperer descripteurs
		descriptionPlugginsDispos = Loader.getDescriptions();
		display = (IDisplay) Loader.loadPlugginsFor(descriptionPlugginsDispos.get("Affichage en liste"), "");// load la liste
		loadRdv =  (ILoadRDVs) Loader.loadPlugginsFor(descriptionPlugginsDispos.get("Chargement par fichiers"), "");
		
		rdvs = (ArrayList<RDV>) loadRdv.getRdvList("src/tiers/rdv_file.txt");
		
		// load des infos à partir du fichier
		//display = Loader.getDisplay(); // pour se débarasser des references statiques à un display
		// FIXME : charger uniquement le descriptif des display potentiels
		// à l'initialisation chargement des pluggins corrects
		
	}
	
	public void run() {
		 System.out.println("Lancement de l'appli");
		 output();
		 while (1==1) {
			 
		 }
	}

	public void output() { // refactor --> extract
		if(display == null) {
			//display = Loader.getDisplayFor(descriptionDisplayDispos.get(0));
		}
		// FIXME : charger display si nécessaire (si on en a pas un on le charge) : par défaut prendre le premier trouvé
		display.displayRDVList(rdvs);
	}
	
	

}
