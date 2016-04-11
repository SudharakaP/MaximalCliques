package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import algorithms.BreadthFirstSearch;

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
	
	// the number of edges and vertices
	private int numVertex;
	private int numEdges;
	
	// lists of vertices and edges
	private Set<Vertex> vertices = new HashSet<Vertex>();
	private Set<Edge> edges = new HashSet<Edge>();

	// hashmap that stores the graph structure
	private Map<Vertex, HashSet<Vertex>> adjList = new HashMap<Vertex, HashSet<Vertex>>();

	/**
	 * @return the numVertex
	 */
	public int getNumVertex() {
		return numVertex;
	}
	
	/**
	 * @return the numEdges
	 */
	public int getNumEdges() {
		return numEdges;
	}

	/**
	 * Calculate the closeness centrality of the vertex according to the definition of Dangalchev 
	 * (http://www.sciencedirect.com/science/article/pii/S0378437105012768).
	 * 
	 * @param vertex
	 * @return closeness centrality of the vertex
	 */
	public double closeness(Vertex vertex){
		 
		// if the vertex is not contained in graph return -1 
		if (!adjList.containsKey(vertex)){
			return -1;
		}
		
		// if the vertex has no adjacent vertices (not connected) return 0
		if (adjList.get(vertex).size() == 0)
			return 0;
		
		double centrality = 0;
		for (Vertex v: vertices){
			if (!v.equals(vertex)){
				double shortest = shortestPathLength(vertex, v);
				
				// if path exist add its length to centrality measurement
				if (shortest != 0)
					centrality += 1/Math.pow(2, shortest);	
			}
		}
		return centrality;
	}
	
	/**
	 * Implements the Breadth First Search algorithm using the 
	 * BreadthFirstSearch class and acts as a helper method for
	 * closeness(Vertex vertex) method.
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int shortestPathLength(Vertex v1, Vertex v2){		
		return BreadthFirstSearch.shortestLength(this, v1, v2);		
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(Vertex vertex) {
		if (!adjList.containsKey(vertex)){
			vertices.add(vertex);
			adjList.put(vertex, new HashSet<Vertex>());
			numVertex++;
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		if (adjList.containsKey(v1) && adjList.containsKey(v2) && !edges.contains(edge)){
			edges.add(edge);
			adjList.get(v1).add(v2);
			adjList.get(v2).add(v1);
			numEdges++;
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public Map <Vertex, HashSet<Vertex>> exportGraph() {
		return adjList;		
	}
}
