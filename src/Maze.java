
public interface Maze {
	/**
	 * Given a row and column number, get the cell object in the position in the maze.
	 * As such UI people should be able to iterate over the number of rows and columns in the maze,
	 * use this method to get the corresponding cells, and extract details from the cells in order to create the graphical representation of the maze.
	 * @param row The row number (0 to numRows-1)
	 * @param column The column number (0 to numCols-1)
	 * @return A Cell object, the cell in this position in the maze
	 */
	public Cell getCell(int row, int column);
	

	
	/**
	 * Returns the cell where the user currently is.
	 * @return The cell where the user currently is.
	 */
	public Cell getUserCellLocation();
	
	
	/**
	 * Use an A* algorithm to solve the maze completely
	 */
	public void solveMaze(Heuristic h);
	
	/**
	 * Use the same A* algorithm i.e. private method to solve the maze, but this time only reveal the first few steps (according to the level of difficulty)
	 * @param numStepsToReveal The number of cells to reveal that are part of the path out of the maze.
	 */
	public void getExitPathHint(Heuristic h, int numStepsToReveal);

	
	/**
	 * Creates the Maze (all mazes are square at the moment)
	 * @param size The number of rows and columns of the Maze to be created
	 */
	public void generateMaze(int size);
	
	

}
