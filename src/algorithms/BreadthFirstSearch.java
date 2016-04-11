package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Graph;
import graph.Vertex;

/**
 * Implements the Breadth First Search algorithm and returns the shortest
 * path length between two vertices/nodes of the graph.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class BreadthFirstSearch {
	
	public static int shortestLength(Graph graph, Vertex v1, Vertex v2){
		
		Map<Vertex, HashSet<Vertex>> adjList = graph.exportGraph();
		
		if (!adjList.containsKey(v1) || !adjList.containsKey(v2))
			return 0;
		
		// Initialize data structures
		List<Vertex> queue = new LinkedList<Vertex>();
		Set<Vertex> visited = new HashSet<Vertex>();
		Map<Vertex, Vertex> parentMap = new HashMap<Vertex, Vertex>();
		
		// Implementation of the bfs algorithm
		queue.add(v1);
		visited.add(v1);
		
		while(!queue.isEmpty()){
			Vertex curr = queue.remove(0);
			
			if(curr.equals(v2)){
				List<Vertex> shortestPath = new ArrayList<Vertex>();
				shortestPath.add(curr);
				
				Vertex child = parentMap.get(curr);
				
				while(!child.equals(v1)){
					shortestPath.add(child);
					child = parentMap.get(child);
				}
				shortestPath.add(v1);
				return shortestPath.size() - 1;
			}
			
			for (Vertex n : adjList.get(curr)){
				if (!visited.contains(n)){
					visited.add(n);
					parentMap.put(n, curr);
					queue.add(n);
				}
			}
		}
		// The path from start to goal does not exist
		return 0;
	}
}
