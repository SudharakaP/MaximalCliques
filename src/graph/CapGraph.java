package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import algorithms.BreadthFirstSearch;
import algorithms.BronKerbosch;

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
	private Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
	private Set<Edge> edges = new HashSet<Edge>();

	/**
	 * @return the edges
	 */
	public Set<Edge> getEdges() {
		return edges;
	}

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
		for (Vertex v: vertices.values()){
			if (!v.equals(vertex)){
				double shortest = BreadthFirstSearch.shortestLength(this, vertex, v);
				
				// if path exist add its length to centrality measurement
				if (shortest != 0)
					centrality += 1/Math.pow(2, shortest);	
			}
		}
		return centrality;
	}
	
	/**
	 * This method returns a list of all maximal cliques of the graph. The size
	 * parameter defines the size of the maximal cliques. For example size = 2 outputs
	 * all maximal cliques whose size is greater than 2. size = 0 outputs all the maximal
	 * cliques of the graph.
	 * 
	 * @param size
	 * @return List of all maximal cliques whose size is greater than the input size
	 */
	public List<Set<Vertex>> maximalCliques(int size){
		List<Set<Vertex>> maximalCliques = new ArrayList<Set<Vertex>>();
		
		for (Set<Vertex> set: BronKerbosch.maximalCliques(this))
			if (set.size() > size)
				maximalCliques.add(set);
		
		Collections.sort(maximalCliques, new Comparator<Set<Vertex>>() {
	        @Override
	        public int compare(final Set<Vertex> s1, final Set<Vertex> s2) {
	            return Integer.valueOf(s1.size()).compareTo(Integer.valueOf(s2.size()));
	        }
	    });
		
		return maximalCliques;
	}
	
	/**
	 * This method returns the n-th largest clique of this graph
	 * 
	 * @param n 
	 * @return the n-th largest clique of the graph
	 */
	public Set<Vertex> maximumCliques(int number){	
		
		List<Set<Vertex>> set = maximalCliques(0);
		
		return set.get(set.size() - number);
	}
	
	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(Vertex vertex) {
		if (!adjList.containsKey(vertex)){
			vertices.put(vertex.getValue(), vertex);
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
			vertices.get(v1.getValue()).setAdjVertices(v2);
			vertices.get(v2.getValue()).setAdjVertices(v1);
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
