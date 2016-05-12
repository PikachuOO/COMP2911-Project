import java.util.ArrayList;

public class Cell {
	private Cell north;
	private Cell south;
	private Cell east;
	private Cell west;
	private User occupiedBy;
	private int row;
	private int column;
	private boolean visited;
	private boolean onSolutionPath;
	private boolean hasCoin;
	private boolean explored;
	
	public Cell(int row, int column){
		this.row = row;
		this.column = column;
		this.north = null;
		this.south = null;
		this.east = null;
		this.west = null;
		this.occupiedBy = null;
		this.visited = false;
		this.onSolutionPath = false;
		this.explored = false;
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
	
	public boolean hasBeenExplored(){
		return this.explored;
	}
	
	public void setExplored(){
		this.explored = true;
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
	
	public void setOccupyingUser(User user){
		this.occupiedBy = user;
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
	
	public void setOnSolutionPath(){
		this.onSolutionPath = true;
	}
	
	public boolean isOnSolutionPath(){
		return this.onSolutionPath;
	}
	
	public ArrayList<Cell> getNeighbours(){
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		if (this.north!=null){
			neighbours.add(this.north);
		}
		if (this.south!=null){
			neighbours.add(this.south);
		}
		if (this.east!=null){
			neighbours.add(this.east);
		}
		if(this.west!=null){
			neighbours.add(this.west);
		}
		return neighbours;
	}
	
	public String toString(){
		String s = "Cell ("+this.row+","+this.column+")";
		return s;
	}
	
	/**
	 * Sets the current cell to have a coin, even if it already has coin
	 */
	public void addCoin(){
		this.hasCoin = true;
	}
	
	/**
	 * Attempts to remove coin from the current cell
	 * @return True if there was a coin there and its successfully removed, false if there was no coin to start with
	 */
	public boolean removeCoin(){
		if (this.hasCoin){
			this.hasCoin = false;
			return true;
		}
		return false;
	}
	
	public boolean hasCoin(){
		return this.hasCoin;
	}

}
