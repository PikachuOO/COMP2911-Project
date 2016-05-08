/**
 * A concrete heuristic that uses a Manhattan distance to calculate the 
 * cost from the current cell to the exit cell (which is stored by the class)
 * Manhattan distance = |row1 - row2| + |col1 - col2|
 * @author Elliott
 *
 */
public class ManhattanDistanceHeuristic implements Heuristic {
	private Cell exitCell;
	
	public ManhattanDistanceHeuristic(Cell exitCell){
		this.exitCell = exitCell;
	}
	
	public int getHeuristicValue(State state){
		Cell currentCell = state.getCurrentCell();
		int distance = Math.abs(currentCell.getColumn()-exitCell.getColumn()) + Math.abs(currentCell.getRow()-exitCell.getRow());
		return distance;
	}
}
