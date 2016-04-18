package application;

import java.awt.event.MouseAdapter;
import java.util.Set;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import graph.CapGraph;
import graph.Vertex;
import util.GraphLoader;

/**
 * Visualization of the graph using the GraphStream library.
 * (http://graphstream-project.org/)
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class GraphVisualization extends MouseAdapter{
	
	/**
	 * Loads the graph.
	 * 
	 * @param args
	 */
	public void GraphLoad(){
		
		// Load LinkedIn graph
		CapGraph graph = new CapGraph();
		GraphLoader.graphLoader(graph, "data/LinkedInReduced.txt");
		
		// Create a new Graph object in the GraphStream library
		Graph graphDisplay = new SingleGraph("Social Network Graph");
		
		// Add each vertex to the GraphStream graph along with the vertex number
		for (Vertex v: graph.exportGraph().keySet()){
			Node n = graphDisplay.addNode("" + v.getValue());
			n.addAttribute("ui.label", "" + v.getValue());
			
		} 
		
		// Add all edges to the GraphStream graph
		for (graph.Edge e: graph.getEdges()){
			int node1 = e.getVertices().get(0).getValue();
			int node2 = e.getVertices().get(1).getValue();
			try{
				graphDisplay.addEdge(node1 + "" + node2, "" + node1, "" + node2);
			}catch (EdgeRejectedException | IdAlreadyInUseException exception){}
		}
		
		// Change edge color of graph to "darkslategray"
		graphDisplay.addAttribute("ui.stylesheet", "edge { fill-color: darkslategray; }");
		
		// Set maximum clique
		Set<Vertex> maxClique = graph.maximumCliques(1);
		
		// Display vertices corresponding to the maximum clique in red color
		for (Vertex v: maxClique)
			graphDisplay.getNode("" + v.getValue()).addAttribute("ui.style", "fill-color: red;");
		
		// Display the graph
		graphDisplay.display();	
	}
		
	public static void main(String[] args){
		GraphVisualization graphVisu = new GraphVisualization();
		graphVisu.GraphLoad();
		
	}
}
