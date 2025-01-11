package perso;

import java.awt.Color;

public class Environment {
	private String environmentName;
	private Color environmentColor;
	private int environmentWeight;
	
	public static Environment beachEnvironment = new Environment("Beach", new Color(255,255,0), 2);
	public static Environment oceanEnvironment = new Environment("Ocean", new Color(0,0,255),5);
	public static Environment plainEnvironment = new Environment("Plain", new Color(0,255,0),1);
	public static Environment forestEnvironment = new Environment("Forest", new Color(0, 87, 33),3);
	public static Environment mountainEnvironment = new Environment("Mountain", new Color(75,75,75),99);
	public static Environment snowEnvironment = new Environment("Snow", new Color(235,235,235),99);
	
	public Environment(String name, Color color, int weight) {
		this.setEnvironmentColor(color);
		this.setEnvironmentName(name);
		this.environmentWeight=weight;
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

	public int getEnvironmentWeight() {
		return environmentWeight;
	}
	
	
}
