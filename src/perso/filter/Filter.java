package perso.filter;

import java.util.Set;

import perso.myCell;

public abstract class Filter {
	
	private myCell currentCell;
	public abstract Set<myCell> getNeighbourSet();
	public abstract void applyColor();
	public abstract void setNeighbours();
	public myCell getCurrentCell() {
		return currentCell;
	}
	public void setCurrentCell(myCell currentCell) {
		this.currentCell = currentCell;
	}
	
	
}
