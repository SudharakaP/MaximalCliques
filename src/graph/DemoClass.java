package graph;

import java.util.ArrayList;
import java.util.List;

import util.GraphLoader;

/**
 * Demo class to find Closeness Centrality of the LinkedIn Graph.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class DemoClass {

	/**
	 * @param args
	 */
	
	public static void main(String[] args){
		
		// Array for containing closeness centrality of each node
		List<Double> centralities = new ArrayList<Double>();
		
		// Loading the LinkedIn Graph
		CapGraph g = new CapGraph();
		GraphLoader.graphLoader(g, "data/LinkedInData.txt");
		
		// Print the number of vertices and edges
		System.out.println("Number of Nodes: " + g.getNumVertex());
		System.out.println("Number of Edges: " + g.getNumEdges());
		
		// Add centrality of each node to list
		for (Vertex v: g.exportGraph().keySet())
			centralities.add(g.closeness(v));
		
		System.out.println("Closeness Centrality: "+ centralities);
	}

}
