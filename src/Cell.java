
public class Cell {
	private Cell north;
	private Cell south;
	private Cell east;
	private Cell west;
	private User occupiedBy;
	private int row;
	private int column;
	private boolean visited;
	
	public Cell(int row, int column){
		this.row = row;
		this.column = column;
		this.north = null;
		this.south = null;
		this.east = null;
		this.west = null;
		this.occupiedBy = null;
		this.visited = false;
	}
	
	public Cell getNorth(){
		return this.north;
	}
	
	public Cell getSouth(){
		return this.south;
	}
	
	public Cell getEast(){
		return this.east;
	}
	
	public Cell getWest(){
		return this.west;
	}
	
	public void setNorth(Cell n){
		this.north = n;
	}
	
	public void setSouth(Cell s){
		this.south = s;
	}
	
	public void setEast(Cell e){
		this.east = e;
	}
	
	public void setWest(Cell w){
		this.west = w;
	}
	
	public User getOccupyingUser(){
		return this.occupiedBy;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getColumn(){
		return this.column;
	}
	
	public boolean hasBeenVisited(){
		return this.visited;
	}
	
	public void setVisited(){
		this.visited = true;
	}

}
