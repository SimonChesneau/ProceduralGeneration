package perso.filter;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
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
	
	private int islandNumberFactor = 8;
	private int islandSizeFactor = 10;
	private int mountainPresenceFactor = 15;
	private int mountainSizeFactor= 10;
	private int snowPresenceFactor = 30;
	private int forestPresenceFactor=5;
	
	private Set<myCell> neighbourSet;
	
	private Map<String, Integer> neighbourCellsCount;
	
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
		countNeighbours();
		String currentCellEnvironmentName = getCurrentCell().getEnvironment().getEnvironmentName();
		Random rand = myJFrame.rand;		
		
		if(currentCellEnvironmentName.equals("Sand") && neighbourCellsCount.get("Ocean") >= 3)
			getCurrentCell().setEnvironment(Environment.oceanEnvironment);
		
		if(currentCellEnvironmentName.equals("Plain") && neighbourCellsCount.get("Ocean") >= 3)
			getCurrentCell().setEnvironment(Environment.oceanEnvironment);
		
		if(currentCellEnvironmentName.equals("Sand") && neighbourCellsCount.get("Beach") >= 4)
			getCurrentCell().setEnvironment(Environment.oceanEnvironment);
		
		if(currentCellEnvironmentName.equals("Sand") && neighbourCellsCount.get("Ocean") >= 4)
			getCurrentCell().setEnvironment(Environment.oceanEnvironment);
		
		
		if(currentCellEnvironmentName.equals("Plain") && neighbourCellsCount.get("Plain") <= 2)
			getCurrentCell().setEnvironment(Environment.plainEnvironment);
		
		if(neighbourCellsCount.get("Ocean") >= 5) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > islandNumberFactor)
				getCurrentCell().setEnvironment(Environment.oceanEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.beachEnvironment);
		}
		
		if(currentCellEnvironmentName.equals("Ocean") && neighbourCellsCount.get("Ocean") > 0 && neighbourCellsCount.get("Beach") > 0 ) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > islandSizeFactor)
				getCurrentCell().setEnvironment(Environment.oceanEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.beachEnvironment);
		}
		
		if(currentCellEnvironmentName.equals("Beach") && neighbourCellsCount.get("Ocean") == 0 && neighbourCellsCount.get("Beach") > 0 ) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > 15)
				getCurrentCell().setEnvironment(Environment.plainEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.beachEnvironment);
		}
		
		
		if(currentCellEnvironmentName.equals("Ocean") && (neighbourCellsCount.get("Beach") == 1 || neighbourCellsCount.get("Beach") == 2)) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > islandSizeFactor)
				getCurrentCell().setEnvironment(Environment.oceanEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.beachEnvironment);
		}
		
		if(currentCellEnvironmentName.equals("Plain") && neighbourCellsCount.get("Plain") == 8) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > mountainPresenceFactor)
				getCurrentCell().setEnvironment(Environment.plainEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.mountainEnvironment);
		}else if(currentCellEnvironmentName.equals("Plain") && neighbourCellsCount.get("Plain") >= 7) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > forestPresenceFactor)
				getCurrentCell().setEnvironment(Environment.plainEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.forestEnvironment);
		}
		
		if(currentCellEnvironmentName.equals("Plain") && neighbourCellsCount.get("Mountain") >= 1) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > mountainSizeFactor)
				getCurrentCell().setEnvironment(Environment.plainEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.mountainEnvironment);
		} 
		
		if(currentCellEnvironmentName.equals("Mountain") && neighbourCellsCount.get("Mountain") == 8) {
			int randomFactor = rand.nextInt(0, 101);
			if(randomFactor > snowPresenceFactor)
				getCurrentCell().setEnvironment(Environment.mountainEnvironment);
			else
				getCurrentCell().setEnvironment(Environment.snowEnvironment);
		}
		
		fermeture(false);
		
	}

	public void fermeture(boolean isFinal) {
		String currentCellEnvironmentName = getCurrentCell().getEnvironment().getEnvironmentName();
		
		if(!isFinal) {
			
			if(neighbourCellsCount != null) {
				
				if(neighbourCellsCount.get("Beach") >= 7  && !currentCellEnvironmentName.equals("Mountain"))
					getCurrentCell().setEnvironment(Environment.beachEnvironment);
				if(neighbourCellsCount.get("Ocean") >= 7  && !currentCellEnvironmentName.equals("Mountain"))
					getCurrentCell().setEnvironment(Environment.oceanEnvironment);
				if(neighbourCellsCount.get("Plain") >= 7  && !currentCellEnvironmentName.equals("Mountain") && !currentCellEnvironmentName.equals("Forest"))
					getCurrentCell().setEnvironment(Environment.plainEnvironment);
				if(neighbourCellsCount.get("Mountain") >= 5 && !currentCellEnvironmentName.equals("Snow"))
					getCurrentCell().setEnvironment(Environment.mountainEnvironment);
				if(neighbourCellsCount.get("Snow") >= 2 && neighbourCellsCount.get("Plain") == 0 && neighbourCellsCount.get("Beach") == 0 && currentCellEnvironmentName.equals("Mountain"))
					getCurrentCell().setEnvironment(Environment.snowEnvironment);
				if(neighbourCellsCount.get("Forest") >= 2 && neighbourCellsCount.get("Beach") == 0 && neighbourCellsCount.get("Snow") == 0 && currentCellEnvironmentName.equals("Plain"))
					getCurrentCell().setEnvironment(Environment.forestEnvironment);
			
				
				if(currentCellEnvironmentName.equals("Ocean") && neighbourCellsCount.get("Ocean") <= 1) {
					if(neighbourCellsCount.get("Beach") >= neighbourCellsCount.get("Plain"))
						getCurrentCell().setEnvironment(Environment.beachEnvironment);
					else
						getCurrentCell().setEnvironment(Environment.plainEnvironment);
				}
			}
		}else {
			if(neighbourCellsCount.get("Beach") >= 7 && !currentCellEnvironmentName.equals("Beach"))
				getCurrentCell().setEnvironment(Environment.beachEnvironment);
			if(neighbourCellsCount.get("Ocean") >= 7 && !currentCellEnvironmentName.equals("Ocean"))
				getCurrentCell().setEnvironment(Environment.oceanEnvironment);
			if(neighbourCellsCount.get("Plain") >= 7 && !currentCellEnvironmentName.equals("Plain"))
				getCurrentCell().setEnvironment(Environment.plainEnvironment);
			if(neighbourCellsCount.get("Mountain") >= 7 )
				getCurrentCell().setEnvironment(Environment.mountainEnvironment);
			if(neighbourCellsCount.get("Snow") >= 7 )
				getCurrentCell().setEnvironment(Environment.snowEnvironment);
			if(neighbourCellsCount.get("Forest") >= 7 )
				getCurrentCell().setEnvironment(Environment.forestEnvironment);
		}
		countNeighbours();
		getCurrentCell().colorCell();
	}

	private void countNeighbours() {
		Map<String, Integer> neighbourCellsCount = new HashMap<>();
		for(Environment env : myJFrame.allEnvironmentColorList)
			neighbourCellsCount.put(env.getEnvironmentName(), 0);
		
		neighbourCellsCount.put("default", 0);
		
		for(myCell neighbourCell : neighbourSet) {
			String environmentName = neighbourCell.getEnvironment().getEnvironmentName();
			
			int count = neighbourCellsCount.get(environmentName);
			count++;
			neighbourCellsCount.remove(environmentName);
			neighbourCellsCount.put(environmentName, count);
		
		}
		
		this.neighbourCellsCount= neighbourCellsCount; 
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
