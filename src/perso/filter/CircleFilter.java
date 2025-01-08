package perso.filter;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import perso.Coordinate;
import perso.Environment;
import perso.myCell;
import perso.myJFrame;

public class CircleFilter extends Filter{
	
	
	private myCell northNeighbour;
	private myCell northEastNeighbour;
	private myCell northWestNeighbour;
	private myCell southNeighbour;
	private myCell southEastNeighbour;
	private myCell southWestNeighbour;
	private myCell eastNeighbour;
	private myCell westNeighbour;
	
	private Set<myCell> neighbourSet;
	
	public CircleFilter() {
		neighbourSet = new HashSet<>();
	}
	
	public CircleFilter(myCell currentCell) {
		super.setCurrentCell(currentCell);
		neighbourSet = new HashSet<>();
	}
	
	public myCell getNorthNeighbour() {
		if(northNeighbour == null)
			return myJFrame.baseCell;
		return northNeighbour;
	}

	public void setNorthNeighbour(myCell northNeighbour) {
		this.northNeighbour = northNeighbour;
		neighbourSet.add(northNeighbour);
	}

	public myCell getNorthEastNeighbour() {
		if(northEastNeighbour == null)
			return myJFrame.baseCell;
		return northEastNeighbour;
	}

	public void setNorthEastNeighbour(myCell northEastNeighbour) {
		this.northEastNeighbour = northEastNeighbour;
		neighbourSet.add(northEastNeighbour);
	}

	public myCell getNorthWestNeighbour() {
		if(northWestNeighbour == null)
			return myJFrame.baseCell;
		return northWestNeighbour;
	}

	public void setNorthWestNeighbour(myCell northWestNeighbour) {
		this.northWestNeighbour = northWestNeighbour;
		neighbourSet.add(northWestNeighbour);
	}

	public myCell getSouthNeighbour() {
		if(southNeighbour == null)
			return myJFrame.baseCell;
		return southNeighbour;
	}

	public void setSouthNeighbour(myCell southNeighbour) {
		this.southNeighbour = southNeighbour;
		neighbourSet.add(southNeighbour);
	}

	public myCell getSouthEastNeighbour() {
		if(southEastNeighbour == null)
			return myJFrame.baseCell;
		return southEastNeighbour;
	}

	public void setSouthEastNeighbour(myCell southEastNeighbour) {
		this.southEastNeighbour = southEastNeighbour;
		neighbourSet.add(southEastNeighbour);
	}

	public myCell getSouthWestNeighbour() {
		if(southWestNeighbour == null)
			return myJFrame.baseCell;
		return southWestNeighbour;
	}

	public void setSouthWestNeighbour(myCell southWestNeighbour) {
		this.southWestNeighbour = southWestNeighbour;
		neighbourSet.add(southWestNeighbour);
	}

	
	public myCell getEastNeighbour() {
		if(eastNeighbour == null)
			return myJFrame.baseCell;
		return eastNeighbour;
	}

	public void setEastNeighbour(myCell eastNeighbour) {
		this.eastNeighbour = eastNeighbour;
		neighbourSet.add(eastNeighbour);
	}

	public myCell getWestNeighbour() {
		if(westNeighbour == null)
			return myJFrame.baseCell;
		return westNeighbour;
	}

	public void setWestNeighbour(myCell westNeighbour) {
		this.westNeighbour = westNeighbour;
		neighbourSet.add(westNeighbour);
	}
	
	@Override
	public Set<myCell> getNeighbourSet() {
		return neighbourSet;
	}

	@Override
	public void applyColor() {
		Map<String, Integer> neighbourCellsCount = countNeighbours();
		String currentCellEnvironmentName = getCurrentCell().getEnvironment().getEnvironmentName();
		
		
		if(currentCellEnvironmentName.equals("Sand") && neighbourCellsCount.get("Beach") >= 4)
			getCurrentCell().setEnvironment(Environment.oceanEnvironment);
		
		else if(currentCellEnvironmentName.equals("Sand") && neighbourCellsCount.get("Ocean") >= 4)
			getCurrentCell().setEnvironment(Environment.oceanEnvironment);
		
		/*else if(currentCellEnvironmentName.equals("Ocean") && neighbourCellsCount.get("Plain") > 0)
			getCurrentCell().setEnvironment(Environment.beachEnvironment);
		else if(currentCellEnvironmentName.equals("Plain") && neighbourCellsCount.get("Ocean") > 0)
			getCurrentCell().setEnvironment(Environment.beachEnvironment);*/
		
		/*if(neighbourCellsCount.get("Beach") == 8)
			getCurrentCell().setEnvironment(Environment.beachEnvironment);
		if(neighbourCellsCount.get("Ocean") == 8)
			getCurrentCell().setEnvironment(Environment.oceanEnvironment);
		if(neighbourCellsCount.get("Plain") == 8)
			getCurrentCell().setEnvironment(Environment.plainEnvironment);*/
	
		getCurrentCell().colorCell();
		
	}

	private Map<String, Integer> countNeighbours() {
		Map<String, Integer> neighbourCellsCount = new HashMap<>();
		for(Environment env : myJFrame.environmentColorList)
			neighbourCellsCount.put(env.getEnvironmentName(), 0);
		
		neighbourCellsCount.put("default", 0);
		
		for(myCell neighbourCell : neighbourSet) {
			String environmentName = neighbourCell.getEnvironment().getEnvironmentName();
			
			int count = neighbourCellsCount.get(environmentName);
			count++;
			neighbourCellsCount.remove(environmentName);
			neighbourCellsCount.put(environmentName, count);
		
		}
		return neighbourCellsCount;
	}

	@Override
	public void setNeighbours() {
		Coordinate cellCoordinates = getCurrentCell().getCellCoordinates();
		
		int northRow = cellCoordinates.getRow() - 1;
		int southRow = cellCoordinates.getRow() + 1;
		int eastCol  = cellCoordinates.getCol() + 1;
		int westCol  = cellCoordinates.getCol() - 1;
		
		Coordinate northCellCoordinates = new Coordinate(northRow, cellCoordinates.getCol());
		Coordinate northEastCellCoordinates = new Coordinate(northRow, eastCol);
		Coordinate northWestCellCoordinates = new Coordinate(northRow, westCol);
		Coordinate southCellCoordinates = new Coordinate(southRow, cellCoordinates.getCol());
		Coordinate southEastCellCoordinates = new Coordinate(southRow, eastCol);
		Coordinate southWestCellCoordinates = new Coordinate(southRow, westCol);
		Coordinate eastCellCoordinates  = new Coordinate(cellCoordinates.getRow(), eastCol);
		Coordinate westCellCoordinates  = new Coordinate(cellCoordinates.getRow(), westCol);
		
		
		myCell northCell=myJFrame.baseCell;
		myCell northEastCell=myJFrame.baseCell;
		myCell northWestCell=myJFrame.baseCell;
		myCell southCell=myJFrame.baseCell;
		myCell southEastCell=myJFrame.baseCell;
		myCell southWestCell=myJFrame.baseCell;
		myCell eastCell=myJFrame.baseCell;
		myCell westCell=myJFrame.baseCell;
		
		if(northCellCoordinates.getRow() > 0 && northNeighbour == null)
			northCell = myJFrame.cellMap.get(northCellCoordinates.toString());

		if(northEastCellCoordinates.getRow() > 0 && northEastCellCoordinates.getCol() < myJFrame.rowNumber+1 && northEastNeighbour == null)
			northEastCell = myJFrame.cellMap.get(northEastCellCoordinates.toString());
		
		if(northWestCellCoordinates.getRow() > 0 && northWestCellCoordinates.getCol() > 0 && northWestNeighbour == null)
			northWestCell = myJFrame.cellMap.get(northWestCellCoordinates.toString());
		
		if(southCellCoordinates.getRow() < myJFrame.rowNumber+1 && southNeighbour == null)
			southCell = myJFrame.cellMap.get(southCellCoordinates.toString());
		
		if(southEastCellCoordinates.getRow() < myJFrame.rowNumber+1 && southEastCellCoordinates.getCol() < myJFrame.rowNumber+1 && southEastNeighbour == null)
			southEastCell = myJFrame.cellMap.get(southEastCellCoordinates.toString());
		
		if(southWestCellCoordinates.getRow() < myJFrame.rowNumber+1 && southWestCellCoordinates.getCol() > 0 && southWestNeighbour == null)
			southWestCell = myJFrame.cellMap.get(southWestCellCoordinates.toString());
		
		if(eastCellCoordinates.getCol() < myJFrame.rowNumber+1 && eastNeighbour == null)
			eastCell  = myJFrame.cellMap.get(eastCellCoordinates.toString());
		
		if(westCellCoordinates.getCol() > 0 && westNeighbour == null)
			westCell  = myJFrame.cellMap.get(westCellCoordinates.toString());
		
		setNorthNeighbour(northCell);
		setNorthEastNeighbour(northEastCell);
		setNorthWestNeighbour(northWestCell);
		setSouthEastNeighbour(southEastCell);
		setSouthWestNeighbour(southWestCell);
		setSouthNeighbour(southCell);
		setEastNeighbour(eastCell);
		setWestNeighbour(westCell);
		
	}
}
