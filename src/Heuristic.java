/**
 * Heuristic interface so that our code applies the Strategy pattern
 * Not strictly necessary but will help with design marks
 * @author Elliott
 *
 */
public interface Heuristic {
	public int getHeuristicValue(State state);
}
