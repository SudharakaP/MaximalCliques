package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Sudharaka Palamakumbura
 * 
 * Represents an Edge in the graph.
 *
 */
public class Edge {
	
	// define fields for adjacent vertices and edges
	private Set<Vertex> vertices = new HashSet<Vertex>();

	/**
	 * Constructor for creating an edge object.
	 * 
	 * @param graph
	 * @param v1
	 * @param v2
	 */
	public Edge(Vertex v1, Vertex v2){
		vertices.add(v1);
		vertices.add(v2);
	}

	/**
	 * @return the vertices
	 */
	public Set<Vertex> getVertices() {
		return vertices;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!vertices.equals(other.vertices))
			return false;
		return true;
	} 
}
