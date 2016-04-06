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
	 * Constructor for creating an edge object.
	 * 
	 * @param graph
	 * @param v1
	 * @param v2
	 */
	public Edge(CapGraph graph, Vertex v1, Vertex v2){
		if(graph.getAdjList().containsKey(v1) && graph.getAdjList().containsKey(v2)){
			vertices.add(v1);
			vertices.add(v2);
		}
	}

	/**
	 * @return the vertices
	 */
	public ArrayList<Vertex> getVertices() {
		return vertices;
	} 
}
