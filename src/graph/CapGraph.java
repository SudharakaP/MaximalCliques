package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * Implements the Graph interface. This is implemented with an 
 * undirected graph in mind since in this project we are dealing with
 * a particular LinkedIn graph which is undirected. 
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class CapGraph implements Graph {
	
	private int numVertex;
	private int numEdges;
	private HashMap<Vertex, ArrayList<Vertex>> adjList = new HashMap<Vertex, ArrayList<Vertex>>();

	/**
	 * @return the adjList
	 */
	public HashMap<Vertex, ArrayList<Vertex>> getAdjList() {
		return adjList;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(Vertex vertex) {
		adjList.put(vertex, new ArrayList<Vertex>());
		numVertex++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		if (adjList.containsKey(v1) && adjList.containsKey(v2)){
			adjList.get(v1).add(v2);
			adjList.get(v2).add(v1);
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
