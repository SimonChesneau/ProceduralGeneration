package perso;

public class Coordinate {
	private int row=0;
	private int col=0;
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	

	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public int getCol() {
		return col;
	}



	public void setCol(int col) {
		this.col = col;
	}



	@Override
	public boolean equals(Object obj) {
		Coordinate target = (Coordinate) obj;
		
		if(this.row == target.getRow() && this.col == target.getCol())
			return true;
		return false;
	}



	@Override
	public String toString() {
		return row + "," + col;
	}
	
	
	
	
}
