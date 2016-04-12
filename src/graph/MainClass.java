package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import algorithms.BronKerbosch;
import util.GraphLoader;

/**
 * A class that contains the main method to find Closeness Centrality of the LinkedIn Graph.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	
	public static void main(String[] args){
		
		// Array for containing closeness centrality of each node
		List<Double> centralities = new ArrayList<Double>();
		
		// Loading the LinkedIn Graph
		CapGraph g = new CapGraph();
		GraphLoader.graphLoader(g, "data/testadjmatrix2.txt");
		/*Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		Vertex v6 = new Vertex(6);
		Vertex v7 = new Vertex(7);
		
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);
		g.addVertex(v5);
		g.addVertex(v6);
		g.addVertex(v7);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v5);
		g.addEdge(v2, v3);
		g.addEdge(v2, v5);
		g.addEdge(v5, v4);
		g.addEdge(v3, v4);
		g.addEdge(v4, v6);
		g.addEdge(v2, v4);
		g.addEdge(v3, v5);*/
		
		// Print the number of vertices and edges
		System.out.println("Number of Nodes: " + g.getNumVertex());
		System.out.println("Number of Edges: " + g.getNumEdges());
		
		// Add centrality of each node to list
		//for (Vertex v: g.exportGraph().keySet())
			//centralities.add(g.closeness(v));
		
		//System.out.println("Closeness Centrality: "+ centralities);
		for (Set<Vertex> set: BronKerbosch.maximalCliques(g))
			if (set.size() > 0)
				System.out.println(set);
		
		//System.out.println(g.maximalCliques());
		System.out.println(g.exportGraph());
		
	}
}
