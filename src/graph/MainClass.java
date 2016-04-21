package graph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("LinkedInReduced.txt");
		GraphLoader.graphLoader(g, inputStream);	
		
		// Print the number of vertices and edges
		System.out.println("Number of Nodes: " + g.getNumVertex());
		System.out.println("Number of Edges: " + g.getNumEdges());
		
		// Add centrality of each node to list
		for (Vertex v: g.exportGraph().keySet())
			centralities.add(g.closeness(v));
		
		System.out.println("Closeness Centralities: " + centralities);
				
		// print all maximal cliques in the graph
		List<Set<Vertex>> maximalCliques = g.maximalCliques(0);
		
		//System.out.println("Number of Maximal Cliques: " + maximalCliques.size());
		
		System.out.println(maximalCliques);	
	}
}
