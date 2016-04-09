package graph;

import java.util.HashMap;
import java.util.HashSet;
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
		
		GraphLoader.graphLoader(g2, "data/LinkedInData.txt");
		
		System.out.println(g2.exportGraph());
		System.out.println(g2.vertices);
		System.out.println(g2.getNumVertex());
		System.out.println(g2.getNumEdges());
		
		for (HashSet<Vertex> value: g2.adjList.values()){
			if (value.size() == 1)
				System.out.println(value);
		}	
	}
}
