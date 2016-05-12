import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

/**
 * Maze storages, holds all the cells and provides functions for maze generation
 * @author ruby
 *
 */

/**
 * Streamlined the code created by Ruby.
 * Removed unnecessary MazeGenerator class
 * Instead maze generation is done in the constructor of the MazeArray
 * Also MazeArray becomes the concrete class that implements the Maze interface
 * Implemented the A* search for the exit 
 * This can be used to reveal the whole path or a portion of it
 * Also implemented testing if the user is at the exit, assuming top right as exit.
 * @author Elliott
 *
 */
public class MazeArray implements Maze{
	private ArrayList<ArrayList<Cell>> cells;
	private int size;
	private Cell siteA;
	private Cell siteB;
	/**
	 * Generates empty maze grid based on size provided
	 * cells are all disconnected
	 * @param size Width and height of maze
	 */
	public MazeArray(int size){
		this.size = size;
		this.cells = new ArrayList<ArrayList<Cell>>();
		for (int row = 0; row < size; row++){
			ArrayList<Cell> temp = new ArrayList<Cell>();
			for (int col = 0; col < size; col++){
				temp.add(new Cell(row, col));
			}
			this.cells.add(temp);
		}
		this.generateMaze(this.size);
		siteA = getCell(0, size-1);
		siteB = getCell(size-1, 0);
	}
	
	public void setACell(Cell ACell){
		this.siteA = ACell;
	}
	
	public void setBCell(Cell BCell){
		this.siteB = BCell;
	}
	public Cell getSiteA(){
		return this.siteA;
	}
	
	public Cell getSiteB(){
		return this.siteB;
	}
	public void generateMaze(int size){
		Cell startingCell = this.getCell(0,0);
		Stack<Cell> toDo = new Stack<Cell>();
		Random randomizer = new Random();
		toDo.push(startingCell);
	
		//While there are unvisited cells
		while (!toDo.empty()){
			Cell curCell = toDo.peek();
			curCell.setVisited();
			ArrayList<Cell> neighbours = this.getAllUnvisitedNeighbours(curCell);
			 //1. If the current cell has any neighbours which have not been visited
			
			if (!neighbours.isEmpty()){
				//  1. Choose randomly one of the unvisited neighbours
				Cell nextCell = neighbours.get(randomizer.nextInt(neighbours.size()));
				toDo.push(nextCell);
				this.carvePath(curCell, nextCell);
			    //  2. Push the chosen cell to the stack
			    //  3. Remove the wall between the current cell and the chosen cell 
			}
			// otherwise continue, pop off another cell from the stack and on wards
			else{
				toDo.pop();
			}
			
		}
		
	}
	
	/**
	 * Returns cell object at specific location
	 * @param row row number of cell (0 index)
	 * @param col column number of cell (0 index)
	 * @return Cell object at that location, null if invalid
	 */
	public Cell getCell(int row, int col){
		if ((row < 0 || row >= size)||(col < 0 || col >= size)){
			return null;
		}
		return this.cells.get(row).get(col);
	}
	
	/**
	 * Gets all neighbours of cell that has not been visited by maze gen algorithm
	 * @param cell
	 * @return
	 */
	public ArrayList<Cell> getAllUnvisitedNeighbours(Cell cell){
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		int row = cell.getRow();
		int col = cell.getColumn();
		//add north cell
		if (row > 0){
			if (!getCell(row-1, col).hasBeenVisited()){
				neighbours.add(getCell(row-1, col));
			}
		}
		//add south cell
		if (row < this.size-1){
			if (!getCell(row+1, col).hasBeenVisited()){
				neighbours.add(getCell(row+1, col));
			}
		}
		//add west cell
		if (col > 0){
			if (!getCell(row, col-1).hasBeenVisited()){
				neighbours.add(getCell(row, col-1));
			}
		}
		//add east cell
		if (col < this.size-1){
			if (!getCell(row, col+1).hasBeenVisited()){
				neighbours.add(getCell(row, col+1));
			}
		}
		
		return neighbours;
	}
	
	/**
	 * Create path between 2 adjacent cells
	 * @param cell1 Cell object 1
	 * @param cell2 Cell object 2
	 */
	public void carvePath(Cell cell1, Cell cell2){
		// if they are in the same row
		if (cell1.getRow()==cell2.getRow()){
			// if cell 1 is to the right of cell 2
			if (cell1.getColumn()> cell2.getColumn()){
				cell1.setWest(cell2);
				cell2.setEast(cell1);
			}
			// if cell 1 is to the left of cell 2
			else{
				cell1.setEast(cell2);
				cell2.setWest(cell1);
			}
		}
		else {
			// if cell 1 is below cell 2
			if (cell1.getRow() > cell2.getRow()){
				cell1.setNorth(cell2);
				cell2.setSouth(cell1);
			}
			else{
				cell1.setSouth(cell2);
				cell2.setNorth(cell1);
			}
		}
	}
	
	public void printMaze(){
		//print the top wall
		Cell curCell;
		for (int i = 0; i < this.size; i++){
			System.out.print("**");
		}
		System.out.println("*");
		//for every line
		for (int row = 0; row < this.size; row++){
			//print the first wall
			String curLine = "*";
			String botLine = "*";
			//print each thingy
			for (int col = 0; col < this.size; col++){
				//print an x for the users location
				curCell = getCell(row,col);
				if (curCell.getOccupyingUser()!=null){
					curLine += "x";
				//print an o if on the revealed solution path
				} 
				else if (curCell.hasCoin()){
					curLine += "@";
				}
				/*
				else if (curCell.equals(maze.getSiteA())){
					curLine += "A";
				}
				else if (curCell.equals(maze.getSiteB())){
					curLine += "B";
				}
				*/
				else if (curCell.isOnSolutionPath()){
					curLine += "O";
				} else {
				//otherwise an empty cell
					curLine += " ";
				}
				if (getCell(row, col).getEast() == null){
					curLine += "*";
				}
				else {
					curLine += " ";
				}
				if (getCell(row, col).getSouth() == null){
					botLine += '*';
				}
				else {
					botLine += " ";
				}
				botLine += "*";
			}
			System.out.println(curLine);
			System.out.println(botLine);
		}
	}



	/**
	 * Returns the first cell it finds which contains a user.
	 * Thus assumes a single player game
	 */
	public Cell getUserCellLocation() {
		for (int row=0; row<size; row++){
			ArrayList<Cell> thisRow = this.cells.get(row);
			for (int col=0; col<size; col++){
				Cell thisCell = thisRow.get(col);
				if (thisCell.getOccupyingUser()!=null){
					return thisCell;
				}
			}
		}
		return null;
	}
	
	/**
	 * Private helper method
	 * Does all the work of finding the path from the users location to the exit
	 * Using an A* algorithm and the supplied heuristic (currently a Manhattan distance heuristic)
	 * Returns a stack of the cells that make up the solution path
	 * A stack is used so that when cells are popped off the path goes in the correct direction
	 * i.e. From the user -> exit, as opposed to exit->user which is what would normally be produced by A*
	 * @param h
	 * @return
	 */
	private Stack<Cell> astar(Heuristic h){
		Cell currentCell = this.getUserCellLocation();
		State startingState = new State(currentCell,null,0,h);
		PriorityQueue<State> statesToExpand = new PriorityQueue<State>();
		Hashtable<Integer,Cell> visited = new Hashtable<Integer,Cell>();
		statesToExpand.add(startingState);
		while (true){
			State currentState = statesToExpand.poll();
			currentCell = currentState.getCurrentCell();
			visited.put(currentCell.hashCode(),currentCell);
			if (currentCell==this.siteA){
				Stack<Cell> pathToExit = new Stack<Cell>();
				while (currentState!=null){
					currentCell = currentState.getCurrentCell();
					pathToExit.add(currentCell);
					currentState = currentState.getPreviousState();
				}
				return pathToExit;
			}
			ArrayList<Cell> neighbours = currentCell.getNeighbours();
			ListIterator<Cell> listIterator = neighbours.listIterator();
			while (listIterator.hasNext()){
				Cell thisCell = listIterator.next();
				if (visited.get(thisCell.hashCode())==null){
					State newState = new State(thisCell,currentState,currentState.getTotalCost()+1,h);
					statesToExpand.add(newState);
				}
			}
			
		}
	}
	
	/**
	 * Reveals all the steps on the solution path from the users current location
	 * To the exit of the maze
	 */
	public void solveMaze(Heuristic h) {
		Stack<Cell> pathToExit = this.astar(h);
		while (!pathToExit.empty()){
			Cell thisCell = pathToExit.pop();
			thisCell.setOnSolutionPath();
		}
		
		
	}

	/**
	 * Sets the first numStepsToRevealCells of the solution path (not including the cell the user is in)
	 * As part of the solution path so that they can be revealed by the printMaze method
	 * Or alternatively this information retrieved by the front end and displayed
	 */
	public void getExitPathHint(Heuristic h, int numStepsToReveal) {
		Stack<Cell> pathToExit = this.astar(h);
		int i = 0;
		while (i<numStepsToReveal+1 && !pathToExit.empty()){
			Cell thisCell = pathToExit.pop();
			thisCell.setOnSolutionPath();
			i+=1;
		}
		
	}

	/**
	 * Returns true if the user is in siteA
	 * False otherwise
	 */
	public boolean userAtA() {
		if (this.getUserCellLocation()==this.siteA){
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a number of coins randomly around the maze
	 * @param coinNum number of coins to be added
	 */
	public void addCoins(int coinNum){
		Random rng = new Random();
		for (int i = 0; i < coinNum; i++){
			int row = rng.nextInt(size-1);
			int col = rng.nextInt(size-1);
			while (this.getCell(row,col).hasCoin()){
				row = rng.nextInt(size-1);
				col = rng.nextInt(size-1);
			}
			this.getCell(row,col).addCoin();
		}
		
	}
	
	public void exploreNearbyCells(Cell cell){
		int rowNum = cell.getRow();
		int colNum = cell.getColumn();
		for (int row = rowNum-2; row <= rowNum+2; row++){
			for (int col = colNum-2; col <= colNum+2; col++){
				if (getCell(row, col)!=null && getCell(row, col).hasBeenExplored()==false){
					getCell(row,col).setExplored();
				}
			}
		}
	}


}
