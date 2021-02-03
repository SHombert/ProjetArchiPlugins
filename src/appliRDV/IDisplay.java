package appliRDV;

import java.util.List;

import javax.swing.JComponent;

import data.Personne;
import data.RDV;

public interface IDisplay {
	// refactor sur DisplayStrategy --> extract interface
	
	// Permet d'avoir plusieurs impl�mentations de l'affichage, DisplayStrategy en est une possible

	 public JComponent displayRDVList(List<RDV> rdvList); // changer en displayRDV 


}