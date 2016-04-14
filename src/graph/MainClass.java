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
		GraphLoader.graphLoader(g, "data/testadjmatrix.txt");
		
		// Print the number of vertices and edges
		System.out.println("Number of Nodes: " + g.getNumVertex());
		System.out.println("Number of Edges: " + g.getNumEdges());
		
		// Add centrality of each node to list
		for (Vertex v: g.exportGraph().keySet())
			centralities.add(g.closeness(v));
		
		List<Set<Vertex>> maximalCliques = new ArrayList<Set<Vertex>>();
		for (Set<Vertex> set: BronKerbosch.maximalCliques(g))
			if (set.size() > 0)
				maximalCliques.add(set);
		
		System.out.println("Closeness Centrality: "+ centralities);	
		
		System.out.println(maximalCliques);
		
		CapGraph g1 = new CapGraph();
		System.out.println(g1.maximalCliques(0));
	}
}
