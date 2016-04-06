package graph;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sudharaka Palamakumbura
 * 
 * Represents a Vertex/Node in the Graph.
 *
 */
public class Vertex {
	
	private final int value;
	private Set<Vertex> adjVertices = new HashSet<Vertex>();
	
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

	/**
	 * @return the adjVertices
	 */
	public Set<Vertex> getAdjVertices() {
		return adjVertices;
	}

	/**
	 * @param adjVertices the adjVertices to set
	 */
	public void setAdjVertices(HashSet<Vertex> adjVertices) {
		this.adjVertices = adjVertices;
	}
	
	@Override
	public String toString(){
		return Integer.toString(value);
	}
}
