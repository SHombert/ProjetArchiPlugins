package plateforme;

import java.util.HashMap;

public class DescripteurPluggin {
	String name;
	String className;
	boolean autoLoad;
	boolean autoRun;
	HashMap<String,String> options;
	boolean loaded;

	public DescripteurPluggin(String n, String className) {
		this.name=n;
		this.className=className;
		loaded=false;
	}

	public DescripteurPluggin() {
		// TODO Auto-generated constructor stub
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

	public boolean isAutoLoad() {
		return autoLoad;
	}

	public void setAutoLoad(boolean autoLoad) {
		this.autoLoad = autoLoad;
	}

	public boolean isAutoRun() {
		return autoRun;
	}

	public void setAutoRun(boolean autoRun) {
		this.autoRun = autoRun;
	}

	@Override
	public String toString() {
		return "DescripteurPluggin [name=" + name + ", className=" + className + ", autoLoad=" + autoLoad + ", autoRun="
				+ autoRun + ", loaded=" + loaded + "]";
	}
	
	

}
