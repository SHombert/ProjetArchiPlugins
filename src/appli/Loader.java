package appli;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class Loader {

	// Externalisation des instanciations en dur (factory ou autre)
	public static IDisplayStrategy getDisplay() {
		
		
		return null;
	}

	public static ArrayList<DescripteurPluggin> getDescriptions() {
		ArrayList<DescripteurPluggin> descriptions = new ArrayList();
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("config.txt")); // chargement systématique => pas optimal
			HashMap<String, String> props = (HashMap<String, String>) prop.stringPropertyNames();

			for(String key : props.keySet()) {
				DescripteurPluggin d = new DescripteurPluggin(props.get(key));
				descriptions.add(d);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static IDisplayStrategy getDisplayFor(DescripteurPluggin descripteurPluggin) {
		return null;
		
		//ajouter classname dans config
		
	}
	
	
	// methode qui renvoie liste de descripteurs 

	
}
