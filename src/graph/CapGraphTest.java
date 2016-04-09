/**
 * 
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.GraphLoader;

/**
 * @author Sudharaka Palamakumbura
 *
 */
public class CapGraphTest {
	
	CapGraph emptyGraph = new CapGraph();
	
	CapGraph graph1 = new CapGraph();
	
	CapGraph graph2 = new CapGraph();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		
		graph1.addVertex(v1);
		graph1.addVertex(v2);
		graph1.addVertex(v3);
		graph1.addVertex(v4);
		graph1.addVertex(v5);
		
		graph1.addEdge(v1, v2);
		graph1.addEdge(v3, v2);
		graph1.addEdge(v4, v3);
		graph1.addEdge(v5, v4);
		graph1.addEdge(v4, v2);
		graph1.addEdge(v1, v4);
		
		//System.out.println(graph.exportGraph());
		//System.out.println(graph.getNumEdges());
		//System.out.println(graph.getNumVertex());*/
			
		GraphLoader.graphLoader(graph2, "data/testadjmatrix.txt");
		
		//System.out.println(graph2.exportGraph());
		//System.out.println(graph2.getNumVertex());
		//System.out.println(graph2.getNumEdges());
		//System.out.println(graph2.closeness(new Vertex(5)));
	}

	/**
	 * Test method for {@link graph.CapGraph#getNumVertex()}.
	 */
	@Test
	public void testGetNumVertex() {
		assertEquals("Test for empty graph", 0, emptyGraph.getNumVertex());
		assertEquals("Test correct number of vertices NOT using GraphLoader", 5, graph1.getNumVertex());
		assertEquals("Test correct number of vertices using GraphLoader", 5, graph2.getNumVertex());
	}

	/**
	 * Test method for {@link graph.CapGraph#getNumEdges()}.
	 */
	@Test
	public void testGetNumEdges() {
		assertEquals("Test for empty graph", 0, emptyGraph.getNumVertex());
		assertEquals("Test correct number of edges NOT using GraphLoader", 6, graph1.getNumEdges());
		assertEquals("Test correct number of edges using GraphLoader", 6, graph2.getNumEdges());
	}

	/**
	 * Test method for {@link graph.CapGraph#closeness(graph.Vertex)}.
	 */
	@Test
	public void testCloseness() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link graph.CapGraph#shortestPathLength(graph.Vertex, graph.Vertex)}.
	 */
	@Test
	public void testShortestPathLength() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link graph.CapGraph#addVertex(graph.Vertex)}.
	 */
	@Test
	public void testAddVertex() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link graph.CapGraph#addEdge(graph.Vertex, graph.Vertex)}.
	 */
	@Test
	public void testAddEdge() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link graph.CapGraph#exportGraph()}.
	 */
	@Test
	public void testExportGraph() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link graph.CapGraph#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		fail("Not yet implemented"); // TODO
	}

}
