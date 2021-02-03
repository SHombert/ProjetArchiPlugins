package plateforme;


import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Loader implements Subject {
	
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
	 * Methode priv�e utilis�e au lancement de la plateforme pour charger les plugins du fichier de config
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
	 * M�thode qui renvoit les plugins d�pendants du plugin dont le nom est pass� en param�tre (dependency)
	 * Utilis�e pour ne renvoyer aux plugins qui les demandent uniquement les plugins qui les concernent
	 * @return descripteurs : map de key,value, la cl� est le nom du plugin et la valeur de descripteur de plugin correspondant
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
	 * Methode qui instancie un plugin a partir du descripeur pass� en parametre, utilise le constructeur par d�faut avec ou sans arguments 
	 * Les arguments du constructeurs doivent �tre pr�cis�s dans le descripteur de plugin
	 * @param descripteurPlugin : descripteur correspondant au plugin que l'on veut charger
	 * @return une instance du plugin demand�
	 */
	
		
	public static Object loadPluginsFor(DescripteurPlugin descripteurPlugin , Object [] args) {
	    Loader.getInstance().notifySubscribers(descripteurPlugin.getName(),Status.ASKED.value());
		Class c;
		Constructor constructor;
		Object plugin= null;
		try {
			c = Class.forName(descripteurPlugin.getClassName());
			if(descripteurPlugin.getArgs()!=null) {
				constructor = c.getConstructor(descripteurPlugin.getArgs());
			}else {
				constructor = c.getConstructor(null);
			}
			plugin = constructor.newInstance(args);
			
			Loader.getInstance().getDescriptionsPlugins().get(descripteurPlugin.getName()).setLoaded(true);
			Loader.getInstance().notifySubscribers(descripteurPlugin.getName(),Status.LOADED.value());

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			Loader.getInstance().notifySubscribers(descripteurPlugin.getName(),Status.FAILURE.value());
		}		
		return plugin;
	}
	
	
	/**
	 * Parcourt les descripteurs de pluggins et lance la m�thode run sur ceux qui sont tagg�s "autorun"
	 */
	private void autoRun() {
		for(DescripteurPlugin d : descriptionsPlugins.values()) {
			if(d.isAutoRun()) {
				try {
					Thread t = new Thread ((Runnable) Loader.loadPluginsFor(d, null));
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
			if(!d.isLoaded()) {
				notifySubscribers(d.getName(),Status.AVAILABLE.value());
			}
			else {
				if(!d.getName().equals("Moniteur de plugins"))
					notifySubscribers(d.getName(),Status.LOADED.value());

			}
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
		if (this.suscribers.contains(observer)) {
			this.suscribers.remove(observer);
		}
	}

	@Override
	public void notifySubscribers(String name, String status) {
		for(Observer suscriber : this.suscribers) {
           suscriber.update(name,status);
        }
	}


	
}
