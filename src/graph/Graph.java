package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Graph {
    /* Creates a vertex with the given number. */
    public void addVertex(int num);
    
    /* Creates an edge from the first vertex to the second. */
    public void addEdge(int from, int to);
    
    /* Return the graph's connections in a readable format. 
     * The keys in this HashMap are the vertices in the graph.
     * The values are the nodes that are reachable via a directed
     * edge from the corresponding key. 
	 * The returned representation ignores edge weights and 
	 * multi-edges.  */
    public HashMap<Integer, HashSet<Integer>> exportGraph();
} 
