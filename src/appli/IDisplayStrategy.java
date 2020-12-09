package appli;

import data.Personne;

public interface IDisplayStrategy {
	// refactor sur DisplayStrategy --> extract interface
	
	// Permet d'avoir plusieurs implémentations de l'affichage, DisplayStrategy en est une possible

	void output(Personne p);

}