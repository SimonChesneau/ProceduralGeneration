package perso;

import java.awt.Color;

public class Environment {
	private String environmentName;
	private Color environmentColor;
	
	public static Environment beachEnvironment = new Environment("Beach", new Color(255,255,0));
	public static Environment oceanEnvironment = new Environment("Ocean", new Color(0,0,255));
	public static Environment plainEnvironment = new Environment("Plain", new Color(0,255,0));
	
	public Environment(String name, Color color) {
		this.setEnvironmentColor(color);
		this.setEnvironmentName(name);
	}
	
 	public String getEnvironmentName() {
		return environmentName;
	}
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	public Color getEnvironmentColor() {
		return environmentColor;
	}
	public void setEnvironmentColor(Color environmentColor) {
		this.environmentColor = environmentColor;
	}
	
	
}
