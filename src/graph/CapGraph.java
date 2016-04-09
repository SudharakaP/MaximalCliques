package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.GraphLoader;

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
			System.err.print("Given vertex not contained in graph.");
			return -1;
		}
		
		// if the vertex has no adjacent vertices (not connected) return 0
		if (adjList.get(vertex).size() == 0)
			return 0;
		
		double centrality = 0;
		for (Vertex v: vertices){
			if (!v.equals(vertex)){
				double shortest = shortestPathLength(vertex, v);
				centrality += 1/Math.pow(2, shortest);
			}
		}
		return centrality;
	}
	
	public int shortestPathLength(Vertex v1, Vertex v2){
		
		// Initialize data structures
		List<Vertex> queue = new LinkedList<Vertex>();
		Set<Vertex> visited = new HashSet<Vertex>();
		Map<Vertex, Vertex> parentMap = new HashMap<Vertex, Vertex>();
		
		// Implementation of the bfs algorithm
		queue.add(v1);
		visited.add(v1);
		
		while(!queue.isEmpty()){
			Vertex curr = queue.remove(0);
			
			if(curr.equals(v2)){
				List<Vertex> shortestPath = new ArrayList<Vertex>();
				shortestPath.add(curr);
				
				Vertex child = parentMap.get(curr);
				
				while(!child.equals(v1)){
					shortestPath.add(child);
					child = parentMap.get(child);
				}
				shortestPath.add(v1);
				return shortestPath.size() - 1;
			}
			
			for (Vertex n : adjList.get(curr)){
				if (!visited.contains(n)){
					visited.add(n);
					parentMap.put(n, curr);
					queue.add(n);
				}
			}
		}
		// The path from start to goal does not exist
		return 0;
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
		if (adjList.containsKey(v1) && adjList.containsKey(v2)){
			edges.add(new Edge(v1, v2));
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
	
	public static void main(String[] args){
		/*
		CapGraph graph = new CapGraph();
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		
		graph.addEdge(v1, v2);
		graph.addEdge(v3, v2);
		graph.addEdge(v4, v3);
		graph.addEdge(v5, v4);
		graph.addEdge(v4, v2);
		graph.addEdge(v1, v4);
		
		//System.out.println(graph.exportGraph());
		//System.out.println(graph.getNumEdges());
		//System.out.println(graph.getNumVertex());*/
		
		CapGraph g2 = new CapGraph();
		
		GraphLoader.graphLoader(g2, "data/testadjmatrix.txt");
		
		System.out.println(g2.exportGraph());
		System.out.println(g2.vertices);
		System.out.println(g2.getNumVertex());
		System.out.println(g2.getNumEdges());
		System.out.println(g2.closeness(new Vertex(4)));
		
	}
}
