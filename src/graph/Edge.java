package graph;

import java.util.ArrayList;

/**
 * @author Sudharaka Palamakumbura
 * 
 * Represents an Edge in the graph.
 *
 */
public class Edge {
	
	private ArrayList<Vertex> vertices;

	/**
	 * @return the vertices
	 */
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(Vertex v1, Vertex v2) {
		vertices.add(v1);
		vertices.add(v2);
	} 
}
