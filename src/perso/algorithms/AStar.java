package perso.algorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import perso.Coordinate;
import perso.myCell;

public class AStar implements SearchAlgorithm{
	
	private Map<myCell,myCell> foundPath;

	List<myCell> closedList;
	List<myCell> openList;
	
	Map<String, myCell> cellMap;
	Coordinate startingPoint;
	Coordinate endingPoint;
	
	public AStar(Map<String, myCell> cellMap, Coordinate startingPoint, Coordinate endingPoint) {
		this.foundPath = new HashMap<>();
		
		this.cellMap = cellMap;
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
	}

	public void start(){
		
		Coordinate actualPoint= startingPoint;
		myCell actualCell = cellMap.get(actualPoint.toString());
		
		foundPath.put(actualCell, null); //the null object is to stop the search when we color the path
		
		openList = new ArrayList<>();
		closedList = new ArrayList<>();
		closedList.add(cellMap.get("1,1"));
		openList.remove(cellMap.get("1,1"));
		
		
		while(actualCell.getCellCoordinates().getRow() != endingPoint.getRow() || actualCell.getCellCoordinates().getCol() != endingPoint.getCol()) {
			
			actualPoint = actualCell.getCellCoordinates();
			double minimumFoundTheoricalDistance = Double.MAX_VALUE;
			
			
			if(actualCell.getTheoricalDistanceTillFinishingPoint() == 0D) {
				double theoricalDistanceTillFinishingPoint = Math.sqrt((Math.pow((actualPoint.getRow()-endingPoint.getRow()),2) + Math.pow((actualPoint.getCol()-endingPoint.getCol()),2)));
				actualCell.setTheoricalDistanceTillFinishingPoint(theoricalDistanceTillFinishingPoint);
			}
			
			
			myCell nextCell = actualCell;
			
			for(myCell neighbour : actualCell.getNeighbourSet()) {
				if(!openList.contains(neighbour) && !closedList.contains(neighbour))
					openList.add(neighbour);
				
				double pathValue = neighbour.getTheoricalDistanceTillFinishingPoint() + actualCell.getCellPathValue();
				if(pathValue <= neighbour.getCellPathValue() || !closedList.contains(neighbour)) {
					neighbour.setCellPathValue(pathValue);
					if(foundPath.containsValue(neighbour))
						foundPath.remove(neighbour);
					

					foundPath.put(neighbour, actualCell);
				}
			}
			
			for(myCell openCell : openList) {
				if(openCell.getTheoricalDistanceTillFinishingPoint() == 0D) {
					double theoricalDistanceTillFinishingPoint = Math.sqrt((Math.pow((actualPoint.getRow()-endingPoint.getRow()),2) + Math.pow((actualPoint.getCol()-endingPoint.getCol()),2)));
					openCell.setTheoricalDistanceTillFinishingPoint(theoricalDistanceTillFinishingPoint);
				}
				
				double environmentWeight = openCell.getEnvironment().getEnvironmentWeight();
				double calculatedDistance = openCell.getTheoricalDistanceTillFinishingPoint() + environmentWeight ;
				if(calculatedDistance < minimumFoundTheoricalDistance) {
					minimumFoundTheoricalDistance = calculatedDistance;
					nextCell = openCell;
				}
			}
			
			//actualCell.activateLineBorder();
			System.out.println(nextCell.getCellCoordinates().toString());
			openList.remove(nextCell);
			closedList.add(nextCell);
			
			//actualCell.activateLineBorder();
			actualCell = nextCell;
			
		}
		
	}
	
	public void colorPath() {
		myCell previousCell = cellMap.get(endingPoint.toString());
		while(previousCell != null) {
			previousCell.setBackground(new Color(255,0,0));
			previousCell = foundPath.get(previousCell);
			
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public Map<myCell, myCell> getFoundPath() {
		return foundPath;
	}
}
