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
		//create a new square maze of size 10x10
		MazeArray maze = new MazeArray(10);
		//display it in ASCII
		maze.printMaze();
	}

}
