package appli;

import java.util.List;

import data.Personne;
import data.RDV;

public interface IDisplay {
	// refactor sur DisplayStrategy --> extract interface
	
	// Permet d'avoir plusieurs implémentations de l'affichage, DisplayStrategy en est une possible

	 public void displayRDVList(List<RDV> rdvList);

}