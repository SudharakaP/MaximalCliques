package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import graph.CapGraph;
import graph.Vertex;

/**
 * Handles mouse and keystroke events.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class EventHandler implements ViewerListener, KeyListener {
	
	protected boolean loop = true;
	
	private Graph graphDisplay;
	private Viewer viewer;
	private CapGraph graph;
	
	private DecimalFormat df = new DecimalFormat(".##");
	
	public EventHandler(Viewer v, Graph gs, CapGraph g){
		// Assign variables
		graphDisplay = gs;
		viewer = v;
		graph = g;
		
		// Add key listener.
		viewer.getDefaultView().addKeyListener(this);
	}
	/**
	 * Listens to clicks on nodes of the graph (burrowed from the GraphStream tutorial)
	 * http://graphstream-project.org/doc/Tutorials/Graph-Visualisation/
	 */
	public void click(){
		
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
	
	@Override
	/* (non-Javadoc)
	 * @see org.graphstream.ui.view.ViewerListener#viewClosed(java.lang.String)
	 */
	public void viewClosed(String id) {
		loop = false;
		System.exit(0);
	}

	@Override
	/* (non-Javadoc)
	 * @see org.graphstream.ui.view.ViewerListener#buttonReleased(java.lang.String)
	 */
	public void buttonReleased(String id) {
		
		Node node = graphDisplay.getNode(id);
		
		// If node color is black or node color is red, change the color to purple and display 
		// Closeness Centrality.
		if (!node.hasAttribute("ui.style") || node.getAttribute("ui.style")
				.toString().equals("fill-color: black;") || node.getAttribute("ui.style")
				.toString().equals("fill-color: red;")) {
			
			// Round the Closeness Centrality to two decimals.
			String closeness = df.format(graph.closeness(new Vertex(Integer.parseInt(id))));
			
			node.addAttribute("ui.style", "fill-color: purple;");
			node.addAttribute("ui.label", closeness);
		
		// else if the node color is purple (already clicked node) and its original color is
		// black and change its color to black.
		}else if (node.getAttribute("ui.style").toString().equals("fill-color: purple;") 
				&& !node.hasAttribute("ui.class")){
			node.changeAttribute("ui.style", "fill-color: black;");
			node.changeAttribute("ui.label", id);
			
		// else (if the node is purple and its original color is red) then change node
		// color to red and label it with its ID.
		}else{
			node.changeAttribute("ui.style", "fill-color: red;");
			node.changeAttribute("ui.label", id);
		}
	}
	
	@Override
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent key) {
		if (key.getKeyChar() == 'c'){
			for (Node n: graphDisplay.getNodeSet())
				n.changeAttribute("ui.label", n.getAttribute("centrality").toString());
		}else if (key.getKeyChar() == 'i'){
			for (Node n: graphDisplay.getNodeSet()){
				n.changeAttribute("ui.label", "" + n.getId());
		
				if (!n.hasAttribute("ui.class")){
					n.changeAttribute("ui.style", "fill-color: black;");
				}else{
					n.changeAttribute("ui.style", "fill-color: red;");
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void buttonPushed(String arg0) {}
}
