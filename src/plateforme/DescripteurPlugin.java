package plateforme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DescripteurPlugin {
	protected String name;
	protected String className;
	protected boolean autoRun;
	protected HashMap<String,String> options;
	protected Class[] args; 
	protected List<String> dependencies;
	protected boolean loaded;

	public DescripteurPlugin(String n, String className) {
		this.name=n;
		this.className=className;
		loaded=false;
	}

	public DescripteurPlugin() {
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

	public HashMap<String, String> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, String> options) {
		this.options = options;
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
				+ args + ", dependencies=" + dependencies + "]";
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}
	
	public void addArgs(List<String> argClasses) {
		args=new Class[argClasses.size()];
		System.out.println(argClasses);
		for (int i=0; i<argClasses.size(); i++) {
			try {
				System.out.println(i);
				System.out.println(argClasses.get(i));
				args[i]=Class.forName(argClasses.get(i));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
