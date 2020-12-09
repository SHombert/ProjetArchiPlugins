package appli;

import java.util.ArrayList;

import data.Personne;

public class Appli {
	IDisplayStrategy display;
	ArrayList<DescripteurPluggin> descriptionDisplayDispos;
	
	
	public static void main(String[] args) {
		Appli appli = new Appli();
		appli.run();
		//appli.setDisplay(new DisplayStrategyPlus());
	}

	public Appli() {
		//display = Loader.getDisplay(); // pour se débarasser des references statiques à un display
		// FIXME : charger uniquement le descriptif des display potentiels
	
		descriptionDisplayDispos = Loader.getDescriptions();
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
			display = Loader.getDisplayFor(descriptionDisplayDispos.get(0));
		}
		// FIXME : charger display si nécessaire (si on en a pas un on le charge) : par défaut prendre le premier trouvé
		display.output(p);		
	}
	
	

}
