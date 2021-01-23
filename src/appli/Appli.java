package appli;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import data.Personne;
import data.RDV;
import plateforme.DescripteurPlugin;
import plateforme.Loader;
import tiers.LoadFromFile;

public class Appli implements Runnable {
	private static final String APPNAME = "Application RDV médicaux";
	IDisplay display;
	HashMap<String, DescripteurPlugin> descriptionPluginsDispos;
	ILoadRDVs loadRdv;
	ArrayList<RDV> rdvs;
	// ICreateRDV createRDV;

	public Appli() {
		// recuperer descripteurs
		System.out.println("Lancement de l'appli : constructeur");
		descriptionPluginsDispos = Loader.getDescripteurs(APPNAME);
				display = (IDisplay) Loader.loadPluginsFor(descriptionPluginsDispos.get("Affichage en liste"));// load la liste
		loadRdv =  (ILoadRDVs) Loader.loadPluginsFor(descriptionPluginsDispos.get("Chargement par fichiers"));
		
		rdvs = (ArrayList<RDV>) loadRdv.getRdvList("src/tiers/rdv_file.txt");
		
		// load des infos à partir du fichier
		
	}
	
	public void run() {
		 System.out.println("Lancement de l'appli");
		 output();
		 while (1==1) {
			 // clique sur new --> lance createNewRDV()
		 }
	}

	public void output() { // refactor --> extract
		if(display == null) {
			//display = Loader.getDisplayFor(descriptionDisplayDispos.get(0));
		}
		// FIXME : charger display si nécessaire (si on en a pas un on le charge) : par défaut prendre le premier trouvé
		display.displayRDVList(rdvs);
	}
	
	/*
	 * 
	 * createRDV() {
	 *  	si pas null;  createRDV = Loader.loadPluginFor(descripteur, [])  --> Lance la fenetre
	 *  	createRDV.getRDV();
	 *  	
	 * 
	 */
	

}
