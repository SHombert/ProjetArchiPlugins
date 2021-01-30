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



public class Loader implements Subject{
	
	static final String CONFIG ="config.json";
	List<Observer> suscribers;
 
	private HashMap<String,DescripteurPlugin> descriptionsPlugins;
	
	private static Loader instance = new Loader();
	
	public Loader() {
		suscribers = new ArrayList<Observer>();
	}
	
	public static Loader getInstance() {
		return instance;
	}
 
	public void setDescriptionsPlugins(HashMap<String, DescripteurPlugin> descriptionsPluggins) {
		this.descriptionsPlugins = descriptionsPluggins;
	}

	public HashMap<String, DescripteurPlugin> getDescriptionsPlugins() {
		return descriptionsPlugins;
	}
	
	/**
	 * Methode privée utilisée au lancement de la plateforme pour charger les plugins du fichier de config
	 */
	private void getDescriptions() {
		descriptionsPlugins = new HashMap<String,DescripteurPlugin>();
	    JSONParser parser = new JSONParser();
	    try {
	    	Reader reader = Files.newBufferedReader(Paths.get(CONFIG));
	    	JSONArray plugins = (JSONArray) parser.parse(reader);
		    for (Object o : plugins) {
		    	JSONObject pluginObj = (JSONObject) o;
		    	DescripteurPlugin plugin = new DescripteurPlugin();
			    plugin.setName((String) pluginObj.get("name"));
			    plugin.setClassName((String) pluginObj.get("className"));
			    plugin.setAutoRun(Boolean.parseBoolean((String) pluginObj.get("autorun")));
			    // Gestion plugins requis
			    List<String> reqs = (List<String>) pluginObj.get("requirements");
			    if(reqs==null || !reqs.isEmpty()) {
			    	plugin.setRequirements(reqs);
			    }
			    // Gestion arguments constructeur par défaut
			    List<String> args = (List<String>) pluginObj.get("params");
			    if(args==null || !args.isEmpty()) { 	
			   		plugin.addArgs(args);
			    }
			    // Gestion dependecy : pluginParent
			    if(pluginObj.get("dependency")!=null || pluginObj.get("dependency")!="") {
			    	plugin.setDependency((String) pluginObj.get("dependency"));
			    }
			    descriptionsPlugins.put(plugin.getName(), plugin);
			}
		    
		    
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode qui renvoit les plugins dépendants du plugin dont le nom est passé en paramètre (dependency)
	 * Utilisée pour ne renvoyer aux plugins qui les demandent uniquement les plugins qui les concernent
	 * @return descripteurs : map de key,value, la clé est le nom du plugin et la valeur de descripteur de plugin correspondant
	 */
	public static HashMap<String, DescripteurPlugin> getDescripteurs(String dependency) {
		HashMap<String, DescripteurPlugin> descripteurs = new HashMap<String, DescripteurPlugin>();
		for(DescripteurPlugin d : Loader.getInstance().getDescriptionsPlugins().values()) {
			if(d.getDependency()!=null && d.getDependency().equals(dependency)) {
				descripteurs.put(d.getName(), d);
			}
		}
		return descripteurs;
		
	}

	/**
	 * Methode qui instancie un plugin a partir du descripeur passé en parametre, utilise le constructeur par défaut avec ou sans arguments 
	 * Les arguments du constructeurs doivent être précisés dans le descripteur de plugin
	 * @param descripteurPlugin : descripteur correspondant au plugin que l'on veut charger
	 * @return une instance du plugin demandé
	 */
	public static Object loadPluginsFor(DescripteurPlugin descripteurPlugin ) {
		Loader.getInstance().notifySubscribers(descripteurPlugin.getName(),Status.ASKED.value());
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
			Loader.getInstance().notifySubscribers(descripteurPlugin.getName(),Status.LOADED.value());

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			Loader.getInstance().notifySubscribers(descripteurPlugin.getName(),Status.FAILURE.value());
		}		
		return pluggin;
	}
	
	
	/**
	 * Parcourt les descripteurs de pluggins et lance la méthode run sur ceux qui sont taggés "autorun"
	 */
	
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
		loader.autoRun();
		loader.notifyInit();
		
	}

	/**
	 * Notifie les plugins disponibles dans la config non chargés à l'initialisation de la plateforme (les chargés ont déjà été notifiés dans autorun)
	 */
	private void notifyInit() {
		for (DescripteurPlugin d : descriptionsPlugins.values()){
			if(!d.isLoaded())
				notifySubscribers(d.getName(),Status.AVAILABLE.value());
		}
		
	}

	@Override
	public void addSubscriber(Observer observer) {
		if(this.suscribers==null) {
			this.suscribers = new ArrayList<Observer>();
		}
		this.suscribers.add(observer);
	}

	@Override
	public void removeSubscriber(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifySubscribers(String name, String status) {
		for(Observer suscriber : this.suscribers) {
           suscriber.update(name,status);
        }
	}


	
}
