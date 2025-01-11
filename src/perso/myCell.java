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
	private double theoricalDistanceTillFinishingPoint;
	private double cellPathValue;
	
	public myCell(int cellRowNumber, int cellColNumber, Filter filter) {
		super.setSize(super.getWidth(), super.getWidth());
		super.repaint();
		
		cellPathValue=0;
		
		
		this.cellCoordinates = new Coordinate(cellRowNumber, cellColNumber);
		
		this.setBackground(baseColor);
		
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
			return new Environment("default", baseColor,10000);
		return environment;		
	}

	public void setTheoricalDistanceTillFinishingPoint(double theoricalDistanceTillFinishingPoint) {
		this.theoricalDistanceTillFinishingPoint = theoricalDistanceTillFinishingPoint;
	}

	public double getTheoricalDistanceTillFinishingPoint() {
		return theoricalDistanceTillFinishingPoint;
	}
	
	public void activateLineBorder() {
		this.setBorder(cellBorder);
		this.repaint();
	}

	
	@Override
	public String toString() {
		return "myCell [cellCoordinates=" + cellCoordinates.toString() + "]";
	}

	public double getCellPathValue() {
		return cellPathValue;
	}

	public void setCellPathValue(double cellPathValue) {
		this.cellPathValue = cellPathValue;
	}

	
}
