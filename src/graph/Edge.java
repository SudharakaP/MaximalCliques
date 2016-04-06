package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sudharaka Palamakumbura
 * 
 * Represents an Edge in the graph.
 *
 */
public class Edge {
	
	// define fields for adjacent vertices and edges
	private List<Vertex> vertices = new ArrayList<Vertex>();
	private List<Edge> adjEdges = new ArrayList<Edge>();
	
	/**
	 * @return the adjEdges
	 */
	public List<Edge> getAdjEdges() {
		return adjEdges;
	}

	/**
	 * @param adjEdges the adjEdges to set
	 */
	public void setAdjEdges(Edge edge) {
		this.adjEdges.add(edge);
	}

	/**
	 * Constructor for creating an edge object.
	 * 
	 * @param graph
	 * @param v1
	 * @param v2
	 */
	public Edge(Vertex v1, Vertex v2){
		vertices.add(v1);
		vertices.add(v2);
	}

	/**
	 * @return the vertices
	 */
	public List<Vertex> getVertices() {
		return vertices;
	} 
}
