package appli;

import java.util.ArrayList;

import data.Personne;
import plateforme.DescripteurPluggin;
import plateforme.Loader;

public class Appli {
	IDisplayStrategy display;
	ArrayList<DescripteurPluggin> descriptionDisplayDispos;
	
	
	

	public Appli() {
		//display = Loader.getDisplay(); // pour se débarasser des references statiques à un display
		// FIXME : charger uniquement le descriptif des display potentiels
	
	}
	

	/**public void setDisplay(IDisplayStrategy display) { on peut supprimer car mtnt on charge via loader
		this.display = display;
	}*/


	private void run() {
		Personne p = new Personne();
		p.setAge(p.getAge()+1);
		output(p); 
	}

	public void output(Personne p) { // refactor --> extract
		if(display == null) {
			//display = Loader.getDisplayFor(descriptionDisplayDispos.get(0));
		}
		// FIXME : charger display si nécessaire (si on en a pas un on le charge) : par défaut prendre le premier trouvé
		display.output(p);		
	}
	
	

}
