package plateforme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Classe représentant un plugin disponible dans la configuration de la plateforme
 *
 */
public class DescripteurPlugin {
	protected String name;
	protected String className;
	protected boolean autoRun;
	// paramètre du constructeur par défaut de la classe principale du plugin
	protected Class[] args; 
	// plugins nécessaires au lancement du plugin
	protected List<String> requirements;
	// plugin parent (si existant)
	protected String dependency;
	protected boolean loaded;

	public DescripteurPlugin(String n, String className) {
		this.name=n;
		this.className=className;
		loaded=false;
	}
	
	public DescripteurPlugin() {
	}
	
	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	public Class[] getArgs() {
		return args;
	}

	public void setArgs(Class[] args) {
		this.args = args;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public boolean isAutoRun() {
		return autoRun;
	}

	public void setAutoRun(boolean autoRun) {
		this.autoRun = autoRun;
	}

	@Override
	public String toString() {
		return "DescripteurPlugin [name=" + name + ", className=" + className + ", autoRun=" + autoRun + ", args="
				+ Arrays.toString(args) + ", requirements=" + requirements + ", dependency=" + dependency + "]";
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}
	
	public void addArgs(List<String> argClasses) {
		args=new Class[argClasses.size()];
		for (int i=0; i<argClasses.size(); i++) {
			try {
				args[i]=Class.forName(argClasses.get(i));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
