package perso.algorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import perso.Coordinate;
import perso.myCell;

public class Dijkstra implements SearchAlgorithm{

	private Map<myCell,myCell> foundPath;
	
	List<myCell> closedList;
	List<myCell> openList;
	
	Map<String, myCell> cellMap;
	Coordinate startingPoint;
	Coordinate endingPoint;
	
	public Dijkstra(Map<String, myCell> cellMap, Coordinate startingPoint, Coordinate endingPoint) {
		this.foundPath = new HashMap<>();
		
		this.cellMap = cellMap;
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
	}
	
	
	@Override
	public void colorPath() {
		myCell previousCell = cellMap.get(endingPoint.toString());
		while(previousCell != null) {
			previousCell.setBackground(new Color(0,0,0));
			previousCell = foundPath.get(previousCell);
			
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void start() {
		Coordinate actualPoint= startingPoint;
		myCell actualCell = cellMap.get(actualPoint.toString());
		
		List<myCell> openList = new ArrayList<>();
		openList.add(actualCell);
		
		List<myCell> closedList = new ArrayList<>();
		
		foundPath.put(actualCell, null);
		actualCell.setCellPathValue(0);
		
		while(!openList.isEmpty()) {
			actualCell = openList.get(0);
			
			if(closedList.contains(actualCell)) {
				int t=0;
			}
			
			closedList.add(actualCell);
			openList.remove(actualCell);
			
			
			
			System.out.println(actualCell.toString());
			
			//actualCell.activateLineBorder();
			
			for(myCell neighbour : actualCell.getNeighbourSet()) {
				
				if(closedList.contains(neighbour)) {
					int t=0;
				}
				
				if(!closedList.contains(neighbour) && !openList.contains(neighbour)) {
					openList.add(neighbour);
					neighbour.setCellPathValue(Double.MAX_VALUE);;
				}
				
				
				double pathValue = actualCell.getCellPathValue() + neighbour.getEnvironment().getEnvironmentWeight();
				if(neighbour.getCellPathValue() > pathValue) {
					neighbour.setCellPathValue(pathValue);
					foundPath.remove(neighbour);
					foundPath.put(neighbour, actualCell);
				}
				
				
			}
		}
	}

	public Map<myCell, myCell> getFoundPath() {
		return foundPath;
	}
}
