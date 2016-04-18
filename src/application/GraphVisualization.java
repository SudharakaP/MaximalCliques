package application;

import java.awt.event.MouseAdapter;
import java.util.Set;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

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
public class GraphVisualization implements ViewerListener{
	
	protected boolean loop = true;
	Graph graphDisplay;
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
		graphDisplay = new SingleGraph("Social Network Graph");
		
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
		Viewer viewer = graphDisplay.display();	
		
		// The default action when closing the view is to quit
		// the program.
		viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);

		// We connect back the viewer to the graph,
		// the graph becomes a sink for the viewer.
		// We also install us as a viewer listener to
		// intercept the graphic events.
		ViewerPipe fromViewer = viewer.newViewerPipe();
		fromViewer.addViewerListener(this);
		fromViewer.addSink(graphDisplay);

		// Then we need a loop to do our work and to wait for events.
		// In this loop we will need to call the
		// pump() method before each use of the graph to copy back events
		// that have already occurred in the viewer thread inside
		// our thread.

		while(loop) {
			fromViewer.pump(); // or fromViewer.blockingPump(); in the nightly builds

			// here your simulation code.

			// You do not necessarily need to use a loop, this is only an example.
			// as long as you call pump() before using the graph. pump() is non
			// blocking.  If you only use the loop to look at event, use blockingPump()
			// to avoid 100% CPU usage. The blockingPump() method is only available from
			// the nightly builds.
		}
	}
	
	public void viewClosed(String id) {
		loop = false;
	}

	public void buttonPushed(String id) {
		
		graphDisplay.getNode(id).addAttribute("ui.style", "fill-color: blue;");
	}

	public void buttonReleased(String id) {
		System.out.println("Button released on node "+id);
	}
		
	public static void main(String[] args){
		GraphVisualization graphVisu = new GraphVisualization();
		graphVisu.GraphLoad();
		
	}
}
