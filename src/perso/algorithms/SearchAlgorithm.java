package perso.algorithms;

import java.util.Map;

import perso.myCell;

public interface SearchAlgorithm {
	public void colorPath();
	public void start();
	public Map<myCell, myCell> getFoundPath();
}
