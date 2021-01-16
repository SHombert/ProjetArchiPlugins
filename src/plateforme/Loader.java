package plateforme;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import appli.IDisplay;



public class Loader {
	
	static final String CONFIG ="config.json";
 
	private HashMap<String,DescripteurPlugin> descriptionsPlugins;
	
	private static Loader instance = new Loader();
	
	public Loader() {
		
	}
	
	public static Loader getInstance() {
		return instance;
	}
 
	/**
	 * Methode privée utilisée au lancement de la plateforme pour charger les plugins du fichier de config
	 */
	private void getDescriptions() {
		HashMap<String,DescripteurPlugin> descriptionsPlugins = new HashMap<String,DescripteurPlugin>();
	    JSONParser parser = new JSONParser();
	    try {
	    	Reader reader = Files.newBufferedReader(Paths.get(CONFIG));
	    	JSONArray pluggins = (JSONArray) parser.parse(reader);
		    for (Object o : pluggins) {
		    	JSONObject plugginObj = (JSONObject) o;
		    	DescripteurPlugin pluggin = new DescripteurPlugin();
			    pluggin.setName((String) plugginObj.get("name"));
			    pluggin.setClassName((String) plugginObj.get("className"));
			    pluggin.setAutoRun(Boolean.parseBoolean((String) plugginObj.get("autorun")));
			    List<String> deps = (List<String>) plugginObj.get("requirements");
			    if(deps==null || !deps.isEmpty()) {
			    	pluggin.setRequirements(deps);
			    }
			    List<String> args = (List<String>) plugginObj.get("params");
			    if(args==null || !args.isEmpty()) { 	
			   		pluggin.addArgs(args);
			    }
			    //TODO : gestion dependecy : pluginParent
			    descriptionsPlugins.put(pluggin.getName(), pluggin);
			}
		    Loader.getInstance().setDescriptionsPlugins(descriptionsPlugins);
		    
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode utilisée par les pluggins pour récupérer les plugins disponibles
	 * @return
	 */
	
	
	// TODO 
	public static HashMap<String, DescripteurPlugin> getDescripteurs(String dependency) {
		return null;
		
	}
	

	public void setDescriptionsPlugins(HashMap<String, DescripteurPlugin> descriptionsPluggins) {
		this.descriptionsPlugins = descriptionsPluggins;
	}

	public HashMap<String, DescripteurPlugin> getDescriptionsPlugins() {
		return descriptionsPlugins;
	}

	public static Object loadPluginsFor(DescripteurPlugin descripteurPlugin ) {
		Class c;
		Constructor constructor;
		Object pluggin= null;
		try {
			c = Class.forName(descripteurPlugin.getClassName());
			if(descripteurPlugin.getArgs()!=null) {
				constructor = c.getConstructor(descripteurPlugin.getArgs());
			}else {
				constructor = c.getConstructor(null);
			}
			pluggin = constructor.newInstance();
			Loader.getInstance().getDescriptionsPlugins().get(descripteurPlugin.getName()).setLoaded(true);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Monitor.addPluggin()
		
		return pluggin;
	}
	
	
	// Parcourt les descripteurs de pluggins et lance la méthode run sur ceux qui sont taggés "autorun"
	
	private void autoRun() {
		for(DescripteurPlugin d : descriptionsPlugins.values()) {
			if(d.isAutoRun()) {
				try {
					Thread t = new Thread ((Runnable) Loader.loadPluginsFor(d));
					t.start();
				} catch ( SecurityException | IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Loader loader = Loader.getInstance();
		loader.getDescriptions();
		System.out.println(loader.descriptionsPlugins.toString());
		
		loader.autoRun();
		// maj moniteur
		
	}


	
}
