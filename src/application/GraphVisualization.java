package application;

import java.text.DecimalFormat;
import java.util.Set;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
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
public class GraphVisualization {
	
	private CapGraph graph;
	private Graph graphDisplay;
	private Viewer viewer;
	private DecimalFormat df = new DecimalFormat(".##");
	
	public static void main(String[] args){
		GraphVisualization graphVisu = new GraphVisualization();
		graphVisu.GraphLoad();
	}
	
	/**
	 * Loads the graph (in GraphStream) by adding vertices and edges from CapGraph. 
	 * This method also changes color of the graph and displays the nodes associated with 
	 * the maximum clique in red color.
	 * 
	 * @param args
	 */
	public void GraphLoad(){
			
		// Load LinkedIn graph
		graph = new CapGraph();
		GraphLoader.graphLoader(graph, "data/LinkedInReduced.txt");	
		
		// Create a new Graph object in the GraphStream library
		graphDisplay = new SingleGraph("Social Network Graph");
		
		// Add each vertex to the GraphStream graph along with the vertex number and Closeness Centrality
		for (Vertex v: graph.exportGraph().keySet()){
			Node n = graphDisplay.addNode("" + v.getValue());
			n.addAttribute("ui.label", "" + v.getValue());
			n.addAttribute("centrality", df.format(graph.closeness(new Vertex(v.getValue()))));
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
		graphDisplay.addAttribute("ui.stylesheet", "edge { fill-color: slategray; }"
				+ "graph { fill-color: #9cfccc; }");
		
		// Set maximum clique
		Set<Vertex> maxClique = graph.maximumCliques(1);
		
		// Display vertices corresponding to the maximum clique in red color
		for (Vertex v: maxClique){
			Node node = graphDisplay.getNode("" + v.getValue());
			node.addAttribute("ui.style", "fill-color: red;");
			node.addAttribute("ui.class", "maxClique");
		}
		// Display the graph
		viewer = graphDisplay.display();
		
		EventHandler eventHandle = new EventHandler(viewer, graphDisplay, graph);	
		eventHandle.click();
	}
}
