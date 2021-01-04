package appli;

import java.util.ArrayList;

import data.Personne;
import data.RDV;
import plateforme.DescripteurPluggin;
import plateforme.Loader;

public class Appli implements Runnable{
	IDisplay display;
	ArrayList<DescripteurPluggin> descriptionDisplayDispos;
	ArrayList<RDV> rdvs;
	
	
	

	public Appli() {
		// recuperer descripteurs
		// load la liste
		// load des infos à partir du fichier
		//display = Loader.getDisplay(); // pour se débarasser des references statiques à un display
		// FIXME : charger uniquement le descriptif des display potentiels
		// à l'initialisation chargement des pluggins corrects
		
			
	}
	

	/**public void setDisplay(IDisplayStrategy display) { on peut supprimer car mtnt on charge via loader
		this.display = display;
	}*/


	public void run() {
		 System.out.println("Lancement de l'appli");
		 while (1==1) {
			 
		 }
	}

	public void output(Personne p) { // refactor --> extract
		if(display == null) {
			//display = Loader.getDisplayFor(descriptionDisplayDispos.get(0));
		}
		// FIXME : charger display si nécessaire (si on en a pas un on le charge) : par défaut prendre le premier trouvé
		display.output(p);		
	}
	
	

}
