package application;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
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
	
	Viewer graphView;
	
	/**
	 * Loads the graph.
	 * 
	 * @param args
	 */
	public void GraphLoad(){
		
		CapGraph graph = new CapGraph();
		GraphLoader.graphLoader(graph, "data/LinkedInReduced.txt");
		
		Graph graphDisplay = new SingleGraph("Social Network Graph");
		
		for (Vertex v: graph.exportGraph().keySet())
			graphDisplay.addNode("" + v.getValue());
			
		for (graph.Edge e: graph.getEdges()){
			int node1 = e.getVertices().get(0).getValue();
			int node2 = e.getVertices().get(1).getValue();
			try{
				graphDisplay.addEdge(node1 + "" + node2, "" + node1, "" + node2);
			}catch (EdgeRejectedException | IdAlreadyInUseException exception){}
		}
		
		

		graphView = graphDisplay.display();	
		graphDisplay.addAttribute("ui.stylesheet", "edge { fill-color: blue; }");
	}
	
	public static void main(String[] args){
		GraphVisualization graphVisu = new GraphVisualization();
		graphVisu.GraphLoad();
		
		
	}
}
