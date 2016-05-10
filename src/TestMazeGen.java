import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Used to test maze generation by generating maze of size 10, then printing it
 * @author ruby
 *
 */
public class TestMazeGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create a new square maze of size 20x20
		int size = 20;
		MazeArray maze = new MazeArray(size);
		//maze.getCell(5, 5).setOccupyingUser(new User());
		//set the exit cell of the maze to be top right
		maze.setExitCell(maze.getCell(0, size-1));
		maze.addCoins(5);
		ImageIcon img = new ImageIcon("playerOne.png");
		Image playerOneIcon = img.getImage();
		User playerOne = new User(1, playerOneIcon,0,0, maze.getCell(0,0));
		maze.getCell(playerOne.getPosY(), playerOne.getPosX()).setOccupyingUser(playerOne);
		maze.printMaze();
		//use a Manhattan distance heuristic
		Heuristic h = new ManhattanDistanceHeuristic(maze.getExitCell());
		//test getting a hint
		maze.getExitPathHint(h, 5);
		maze.printMaze();
		//test revealing the solution
		maze.solveMaze(h);
		maze.printMaze();
	}

}
