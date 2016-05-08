/**
 * State class used for A* search
 * Stores the current cell, previous state, total cost to get to this state g(n) 
 * and the heuristic used
 * Two states are compared by their function value
 * f(n) = g(n) + h(n) 
 * @author Elliott
 *
 */
public class State implements Comparable<State>{
	private Cell currentCell;
	private State previousState;
	private int totalCost;
	private Heuristic heuristic;
	
	public State(Cell currCell, State prevState, int totalCost, Heuristic h){
		this.currentCell = currCell;
		this.previousState = prevState;
		this.totalCost = totalCost;
		this.heuristic = h;
	}
	
	public Cell getCurrentCell(){
		return this.currentCell;
	}
	
	public State getPreviousState(){
		return this.previousState;
	}
	
	public int getTotalCost(){
		return this.totalCost;
	}
	
	public int compareTo(State other){
		int thisStateFunctionValue = this.totalCost+this.heuristic.getHeuristicValue(this);
		int otherStateFunctionValue = other.getTotalCost()+this.heuristic.getHeuristicValue(other);
		if (thisStateFunctionValue==otherStateFunctionValue){
			return 0;
		} else if (thisStateFunctionValue<otherStateFunctionValue){
			return -1;
		}
		return 1;
		
	}

}
