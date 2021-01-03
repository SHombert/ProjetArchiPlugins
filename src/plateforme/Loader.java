package plateforme;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import appli.IDisplayStrategy;



public class Loader {
	
	static final String CONFIG ="config.json";

	private ArrayList<DescripteurPluggin> descriptionsPluggins;
 
	// Externalisation des instanciations en dur (factory ou autre)

	public ArrayList<DescripteurPluggin> getDescriptions() {// améliorer -> ne charger que les pluggins logiques/contextuels
		
		// parametre avec classe correspondant à l'interface que les pluggins vont satisfaire
		// un avec classe (interface) ou cdc
		//
		descriptionsPluggins = new ArrayList();
	    JSONParser parser = new JSONParser();
	    try {
	    	Reader reader = Files.newBufferedReader(Paths.get(CONFIG));
	    	JSONArray pluggins = (JSONArray) parser.parse(reader);
		    for (Object o : pluggins) {
		    	JSONObject plugginObj = (JSONObject) o;
		    	DescripteurPluggin pluggin = new DescripteurPluggin();
			    pluggin.setName((String) plugginObj.get("name"));
			    pluggin.setClassName((String) plugginObj.get("className"));
			    pluggin.setAutoLoad(Boolean.parseBoolean((String) plugginObj.get("autoload")));
			    pluggin.setAutoRun(Boolean.parseBoolean((String) plugginObj.get("autorun")));
			    descriptionsPluggins.add(pluggin);
			}
		    return descriptionsPluggins;
		    
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*génériser cette fonction car IDisplay stategy dépendance qu'on ne doit pas avoir et getDisplay -> GetPluggin*/
	public static Object loadPlugginsFor(DescripteurPluggin descripteurPluggin, String interfaceName) {
		return null;
		
		//ajouter classname dans config
		
	}
	
	public void loadAuto() {
		
	}
	
	// Parcourt les descripteurs de pluggins et lance la méthode run sur ceux qui sont taggés "autorun"
	public void autoRun() {
		for(DescripteurPluggin d : descriptionsPluggins) {
			if(d.isAutoRun()) {
				//threads ? 
				try {
					Class c = Class.forName(d.getClassName());
					Object app = c.newInstance();
					d.setLoaded(true);
					Thread t = new Thread ((Runnable) app);
					t.start();
					//Method m = c.getMethod("run", null);
					//m.invoke(app, null);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Loader loader = new Loader();
		loader.descriptionsPluggins = loader.getDescriptions();
		System.out.println(loader.descriptionsPluggins.toString());
		//AutoLoad
		//AutoRun
		loader.autoRun();
		
	}

	
	// methode qui charge
	
}
