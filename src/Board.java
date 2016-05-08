import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements ActionListener {

	private MazeArray maze;
	private int size, 
				cellSize;
	
	
	private Image verticalWall,
				  horizontalWall, 
				  userImage;
	
	public Board(int size, int cellSize){
		this.size = size;
		this.cellSize = cellSize;
		maze = new MazeArray(size);
		
		maze.printMaze();
		
		ImageIcon img = new ImageIcon("vertical_wall.png");
		verticalWall = img.getImage();
		img = new ImageIcon("horizontal_wall.png");
		horizontalWall = img.getImage();
		img = new ImageIcon("user.png");
		userImage = img.getImage();
		
		//just for testing's sake
		maze.getCell(10, 10).setOccupyingUser(new User());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();

	}
	
	public void paint(Graphics g){
		super.paint(g);

		
		
		//paint in top wall
		for (int i = 0; i < size; i++){
			//g.fillRect(i*cellSize, 0, 35, 5); //horizontal
			g.drawImage(horizontalWall, i*cellSize, 0, null);
		}
		
		Cell cell;
		for (int y = 0; y < size; y++){
			//paint the left size wall
			//g.fillRect(0, y*cellSize, 5, 35); //vertical
			g.drawImage(verticalWall, 0, y*cellSize, null);
			
			for (int x = 0; x < size; x++){
				cell = maze.getCell(y, x);
				
				//g.setColor(Color.BLACK);
				//if there is a wall to the right. paint it in
				if (cell.getEast() == null){
					//g.fillRect((x+1)*cellSize, y*cellSize, 5, 35); //vertical
					g.drawImage(verticalWall, (x+1)*cellSize, y*cellSize, null);
				}
				if (cell.getSouth() == null){
					//g.fillRect(x*cellSize, (y+1)*cellSize, 35, 5); //horizontal
					g.drawImage(horizontalWall, x*cellSize, (y+1)*cellSize, null);
				}
				if (cell.getOccupyingUser() != null){
					//g.setColor(Color.CYAN);
					//g.drawOval(x*cellSize, y*cellSize, cellSize, cellSize);
					g.drawImage(userImage, x*cellSize+5, y*cellSize+5, null);
				}
					
				
				
			}
			
		}
		
		
	}

}
