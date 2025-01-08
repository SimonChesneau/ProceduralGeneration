package perso;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import perso.filter.Filter;

public class myCell extends JPanel{
	public static Color baseColor = new Color(255, 255, 255); 
	public static Color borderColor = new Color(0,0,0);
	public static LineBorder cellBorder = new LineBorder(borderColor);
	
	private Filter filter;
	
	private Coordinate cellCoordinates;
	private Environment environment;
	
	public myCell(int cellRowNumber, int cellColNumber, Filter filter) {
		this.cellCoordinates = new Coordinate(cellRowNumber, cellColNumber);
		
		this.setBackground(baseColor);
		this.setBorder(cellBorder);
		
		this.filter = filter;
		this.filter.setCurrentCell(this);
	}
	
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	public Filter getFilter() {
		return filter;
	}
	
	public Coordinate getCellCoordinates() {
		return cellCoordinates;
	}


	public void setCellCoordinates(Coordinate cellCoordinates) {
		this.cellCoordinates = cellCoordinates;
	}

	public void colorCell() {
		if(environment == null)
			this.setBackground(new Color(0,0,0));
		else
			this.setBackground(environment.getEnvironmentColor());
		this.repaint();
	}

	
	public Set<myCell> getNeighbourSet() {
		return filter.getNeighbourSet();
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
		
	}

	public Environment getEnvironment() {
		if(environment == null)
			return new Environment("default", baseColor);
		return environment;		
	}
	
	
}
