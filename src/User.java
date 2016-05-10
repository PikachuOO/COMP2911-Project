import java.awt.Image;

public class User {

	private int ID;
	Image icon;
	private int numMovesMade;
	private int currentScore;
	private int posX;
	private int posY;
	private Cell curCell;
	
	public User(int ID, Image icon, int posX, int posY, Cell startingCell){
		this.ID = ID;
		this.icon = icon;
		this.numMovesMade = 0;
		this.currentScore = 0;
		this.posX = posX;
		this.posY = posY;
		this.curCell = startingCell;
	}
	
	public Cell getCell(){
		return this.curCell;
	}
	
	//called every time user cell moves to new position
	public void setCell(Cell cell){
		this.curCell = cell;
		this.posX = cell.getColumn();
		this.posY = cell.getRow();
		this.numMovesMade++;
		
		if (this.curCell.hasCoin()){
			this.curCell.removeCoin();
			this.addToCurrentScore(10); //magic number, fix later
		}
	}
	
	public int getID() {
		return this.ID;
	}
	
	public int getNumMovesMade() {
		return this.numMovesMade;
	}
	
	
	public int getCurrentScore() {
		return this.currentScore;
	}

	
	public void addToCurrentScore(int score){
		this.currentScore += score;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	

	
}
