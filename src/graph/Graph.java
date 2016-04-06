package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Interface for defining a Graph Object
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public interface Graph {
	
    /* Adds the given vertex to the graph. */
    public void addVertex(Vertex vertex);
    
    /* Add the given edge between two vertices. */
    public void addEdge(Vertex v1, Vertex v2);
    
    /* Return the graph's connections in a readable format. 
     * The keys in this HashMap are the vertices in the graph.
     * The values are the nodes that are reachable via a directed
     * edge from the corresponding key. 
	 * The returned representation ignores edge weights and 
	 * multi-edges.  */
    public HashMap<Integer, HashSet<Integer>> exportGraph();
} 
