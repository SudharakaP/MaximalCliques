package graph;

/**
 * @author Sudharaka Palamakumbura
 * 
 * Represents a Vertex/Node in the Graph.
 *
 */
public class Vertex {
	
	private final int value;
	
	/**
	 * Constructor for creating a vertex.
	 * 
	 * @param a
	 */
	public Vertex(int a){
		this.value = a;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}	
}
