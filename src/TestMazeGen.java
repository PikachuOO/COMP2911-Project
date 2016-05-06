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
		MazeArray maze = new MazeArray(10);
		MazeGenerator generator = new MazeGenerator(maze);
		generator.generateMaze(0, 0);
		maze.printMaze();
	}

}
