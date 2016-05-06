import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Class used to generate maze, takes in mazeArray object and changes relation between cells
 * @author ruby
 *
 */
public class MazeGenerator {
	private MazeArray maze;
	
	public MazeGenerator(MazeArray maze){
		this.maze = maze;

	}
	
	public void generateMaze(int row, int col){
		Cell startingCell = this.maze.getCell(row,col);
		Stack<Cell> toDo = new Stack<Cell>();
		Random randomizer = new Random();
		toDo.push(startingCell);
	
		//While there are unvisited cells
		while (!toDo.empty()){
			Cell curCell = toDo.peek();
			curCell.setVisited();
			ArrayList<Cell> neighbours = this.maze.getAllUnvisitedNeighbours(curCell);
			 //1. If the current cell has any neighbours which have not been visited
			
			if (!neighbours.isEmpty()){
				//  1. Choose randomly one of the unvisited neighbourse
				Cell nextCell = neighbours.get(randomizer.nextInt(neighbours.size()));
				toDo.push(nextCell);
				this.maze.carvePath(curCell, nextCell);
			    //  2. Push the chosen cell to the stack
			    //  3. Remove the wall between the current cell and the chosen cell 
			}
			// otherwise continue, pop off another cell from the stack and on wards
			else{
				toDo.pop();
			}
			
		}
		
	}
	

}
