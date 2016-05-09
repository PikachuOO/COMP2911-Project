import java.awt.Image;

public class User {

	int ID;
	Image icon;
	int numMovesMade;
	int currentScore;
	int posX;
	int posY;
	
	public User(int ID, Image icon, int posX, int posY){
		this.ID = ID;
		this.icon = icon;
		numMovesMade = 0;
		currentScore = 0;
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getNumMovesMade() {
		return numMovesMade;
	}
	
	public void setNumMovesMade(int numMovesMade) {
		this.numMovesMade = numMovesMade;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
