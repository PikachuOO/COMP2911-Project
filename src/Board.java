import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel implements ActionListener {

	private MazeArray maze;
	private int size, 
				cellSize;
	
	
	private Image verticalWall,
				  horizontalWall, 
				  playerOneIcon,
				  playerTwoIcon,
				  finishImage;
	
	private ArrayList<User> players;
	
	public Board(int size, int cellSize){
		this.size = size;
		this.cellSize = cellSize;
		maze = new MazeArray(size);
		
		//maze.printMaze();
		
		ImageIcon img = new ImageIcon("vertical_wall.png");
		verticalWall = img.getImage();
		img = new ImageIcon("horizontal_wall.png");
		horizontalWall = img.getImage();
		img = new ImageIcon("finish.png");
		finishImage = img.getImage();
		
		//Listen for key presses
		addKeyListener(new ActionListener());
		//adds the KeyListener to the frame
		setFocusable(true);
		
		this.players = new ArrayList<User>();
		
		img = new ImageIcon("playerOne.png");
		playerOneIcon = img.getImage();
		User playerOne = new User(1, playerOneIcon,0,0);
		players.add(playerOne);
		maze.getCell(playerOne.getPosY(), playerOne.getPosX()).setOccupyingUser(playerOne);
		
		img = new ImageIcon("playerTwo.png");
		playerTwoIcon = img.getImage();
		User playerTwo = new User(2, playerTwoIcon,size-1,size-1);
		players.add(playerTwo);
		maze.getCell(playerTwo.getPosY(), playerTwo.getPosX()).setOccupyingUser(playerTwo);
		
		
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
					if(cell.getOccupyingUser().equals(players.get(0))) {
						g.drawImage(playerOneIcon, x*cellSize+5, y*cellSize+5, null);
					}
					if(cell.getOccupyingUser().equals(players.get(1))) {
						g.drawImage(playerTwoIcon, x*cellSize+5, y*cellSize+5, null);
					}
				}
			}
			
		}
		
		cell = maze.getExitCell();
		//System.out.println(cell.getRow() + ", " + cell.getColumn());
		g.drawImage(finishImage, cell.getColumn()*cellSize+5, cell.getRow()*cellSize+5, null);
		
	}

	public class ActionListener extends KeyAdapter {
		
		public void keyPressed (KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			User firstPlayer = players.get(0);
			Cell firstPlayerCell = maze.getCell(firstPlayer.getPosY(), firstPlayer.getPosX());
			
			User secondPlayer = players.get(1);
			Cell secondPlayerCell = maze.getCell(secondPlayer.getPosY(), secondPlayer.getPosX());
			
			//Player One Keys
			if(keyCode == KeyEvent.VK_UP) {
				if (firstPlayerCell.getNorth() != null){
					firstPlayerCell.getNorth().setOccupyingUser(firstPlayer);
					firstPlayerCell.setOccupyingUser(null);
					firstPlayer.setPosY(firstPlayer.getPosY()-1);
					firstPlayer.numMovesMade++;
				}
			}
			if(keyCode == KeyEvent.VK_DOWN) {
				if (firstPlayerCell.getSouth() != null){
					firstPlayerCell.getSouth().setOccupyingUser(firstPlayer);
					firstPlayerCell.setOccupyingUser(null);
					firstPlayer.setPosY(firstPlayer.getPosY()+1);
					firstPlayer.numMovesMade++;
				}
			}
			if(keyCode == KeyEvent.VK_RIGHT) {
				if (firstPlayerCell.getEast() != null){
					firstPlayerCell.getEast().setOccupyingUser(firstPlayer);
					firstPlayerCell.setOccupyingUser(null);
					firstPlayer.setPosX(firstPlayer.getPosX()+1);
					firstPlayer.numMovesMade++;
				}
			}
			if(keyCode == KeyEvent.VK_LEFT) {
				if (firstPlayerCell.getWest() != null){
					firstPlayerCell.getWest().setOccupyingUser(firstPlayer);
					firstPlayerCell.setOccupyingUser(null);
					firstPlayer.setPosX(firstPlayer.getPosX()-1);
					firstPlayer.numMovesMade++;
				}
			}
			
			//Player Two Keys
			if(keyCode == KeyEvent.VK_W) {
				if (secondPlayerCell.getNorth() != null){
					secondPlayerCell.getNorth().setOccupyingUser(secondPlayer);
					secondPlayerCell.setOccupyingUser(null);
					secondPlayer.setPosY(secondPlayer.getPosY()-1);
					secondPlayer.numMovesMade++;
				}
			}
			if(keyCode == KeyEvent.VK_S) {
				if (secondPlayerCell.getSouth() != null){
					secondPlayerCell.getSouth().setOccupyingUser(secondPlayer);
					secondPlayerCell.setOccupyingUser(null);
					secondPlayer.setPosY(secondPlayer.getPosY()+1);
					secondPlayer.numMovesMade++;
				}
			}
			if(keyCode == KeyEvent.VK_D) {
				if (secondPlayerCell.getEast() != null){
					secondPlayerCell.getEast().setOccupyingUser(secondPlayer);
					secondPlayerCell.setOccupyingUser(null);
					secondPlayer.setPosX(secondPlayer.getPosX()+1);
					secondPlayer.numMovesMade++;
				}
			}
			if(keyCode == KeyEvent.VK_A) {
				if (secondPlayerCell.getWest() != null){
					secondPlayerCell.getWest().setOccupyingUser(secondPlayer);
					secondPlayerCell.setOccupyingUser(null);
					secondPlayer.setPosX(secondPlayer.getPosX()-1);
					secondPlayer.numMovesMade++;
				}
			}
		
			repaint();
			
		}
		
	}
}
