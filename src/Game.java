import javax.swing.*;

public class Game {

	public Game(){
		JFrame f = new JFrame();
		f.setTitle("Maze Game");
		int size = 20;
		int cellSize = 30;
		f.add(new Board(size, cellSize));
		f.setSize((size)*cellSize + 5, (size+1)*cellSize);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		new Game();
	}
	
}
