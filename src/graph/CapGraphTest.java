/**
 * 
 */
package graph;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;

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
		
		// setting up graph1
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
		
		// setting up graph2
		GraphLoader.graphLoader(graph2, "data/testadjmatrix.txt");
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
		assertEquals(-1.0, emptyGraph.closeness(new Vertex(1)), 0.001);
		assertEquals(1.75, graph2.closeness(new Vertex(5)), 0.001);
	}

	/**
	 * Test method for {@link graph.CapGraph#shortestPathLength(graph.Vertex, graph.Vertex)}.
	 */
	@Test
	public void testShortestPathLength() {
		assertEquals("Shortest path in empty graph", 0, 
				emptyGraph.shortestPathLength(new Vertex(1), new Vertex(2)));
		
		Vertex v1 = new Vertex(10);
		Vertex v2 = new Vertex(4);
		graph2.addVertex(v1);
		graph2.addEdge(v1, v2);
		assertEquals("Shortest path from vertex 1 to 10", 3, graph2.shortestPathLength(v1, new Vertex(1)));	
	}

	/**
	 * Test method for {@link graph.CapGraph#addVertex(graph.Vertex)}.
	 */
	@Test
	public void testAddVertex() {
		
		// adding new vertex to each empty graph and graph1
		Vertex v1 = new Vertex(23);
		emptyGraph.addVertex(v1);
		graph1.addVertex(v1);
		
		assertEquals("Adding a vertex to empty graph",1, emptyGraph.getNumVertex());
		assertEquals("Adding a vertex to graph1", 6, graph1.getNumVertex());
		
		// adding already inserted vertex to each graph
		emptyGraph.addVertex(new Vertex(23));
		graph1.addVertex(new Vertex(3));
		
		assertEquals("Adding a vertex to empty graph", 1, emptyGraph.getNumVertex());
		assertEquals("Adding a vertex to graph1", 6, graph1.getNumVertex());
	}

	/**
	 * Test method for {@link graph.CapGraph#addEdge(graph.Vertex, graph.Vertex)}.
	 */
	@Test
	public void testAddEdge() {
		
		// adding new edge to each graph		
		emptyGraph.addEdge(new Vertex(1), new Vertex(2));
		graph1.addEdge(new Vertex(1), new Vertex(5));
		graph1.addEdge(new Vertex(5), new Vertex(1));
		
		assertEquals("Adding an edge to empty graph", 0, emptyGraph.getNumEdges());
		assertEquals("Adding an edge to graph1", 7, graph1.getNumEdges());
		
		// adding a self-edge (edge that goes from a vertex to itself)
		graph1.addEdge(new Vertex(1), new Vertex(1));
		graph1.addEdge(new Vertex(1), new Vertex(1));
		
		assertEquals("Adding an edge to empty graph", 0, emptyGraph.getNumEdges());
		assertEquals("Adding an edge to graph1", 8, graph1.getNumEdges());
	}

	/**
	 * Test method for {@link graph.CapGraph#exportGraph()}.
	 */
	@Test
	public void testExportGraph() {
		Map<Vertex, HashSet<Vertex>> map = graph1.exportGraph();
		
		assertTrue("Check for correct element", map.containsKey(new Vertex(1)));
		assertTrue("Check for correct element", map.containsKey(new Vertex(2)));
		assertTrue("Check for correct element", map.containsKey(new Vertex(3)));
		assertTrue("Check for correct element", map.containsKey(new Vertex(4)));
		assertTrue("Check for correct element", map.containsKey(new Vertex(5)));
		assertFalse("Check for correct element", map.containsKey(new Vertex(6)));
		
		assertTrue("Check for correct element", map.get(new Vertex(1)).contains(new Vertex(2)));
		assertTrue("Check for correct element", map.get(new Vertex(1)).contains(new Vertex(4)));
		assertTrue("Check for correct element", map.get(new Vertex(2)).contains(new Vertex(1)));
		assertTrue("Check for correct element", map.get(new Vertex(2)).contains(new Vertex(4)));
		assertTrue("Check for correct element", map.get(new Vertex(2)).contains(new Vertex(3)));
		assertTrue("Check for correct element", map.get(new Vertex(3)).contains(new Vertex(4)));
		assertTrue("Check for correct element", map.get(new Vertex(3)).contains(new Vertex(2)));
		assertTrue("Check for correct element", map.get(new Vertex(4)).contains(new Vertex(1)));
		assertTrue("Check for correct element", map.get(new Vertex(4)).contains(new Vertex(2)));
		assertTrue("Check for correct element", map.get(new Vertex(4)).contains(new Vertex(3)));
		assertTrue("Check for correct element", map.get(new Vertex(4)).contains(new Vertex(5)));		
		assertTrue("Check for correct element", map.get(new Vertex(5)).contains(new Vertex(4)));
		
	}
}
