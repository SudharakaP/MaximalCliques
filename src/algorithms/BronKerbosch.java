package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.Vertex;

/**
 * This class implements the Bron-Kerbosh algorithm for finding all Maximal Cliques
 * in a graph. (https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm)
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class BronKerbosch {
	
	private static List<Set<Vertex>> cliques = new ArrayList<Set<Vertex>>();
	
	public static List<Set<Vertex>> maximalCliques(graph.Graph graph){
		
		Set<Vertex> R = new HashSet<Vertex>();
		Set<Vertex> X = new HashSet<Vertex>();
		
		Set<Vertex> P = new HashSet<Vertex>(graph.exportGraph().keySet());
		
		bronKerbosch(R, P, X);
		
		return cliques;
	}
	
	private static void bronKerbosch(Set<Vertex> R, Set<Vertex> P, Set<Vertex> X){
		
		//System.out.println(R);
		//System.out.println(X);
		//System.out.println(P);
				
		if (P.isEmpty() && X.isEmpty())
			cliques.add(R);
		
		Set<Vertex> PCopy = new HashSet<Vertex>(P);

		for (Vertex v: PCopy){
			Set<Vertex> N = v.getAdjVertices();
			
			Set<Vertex> RModified = new HashSet<Vertex>(R);
			Set<Vertex> PModified = new HashSet<Vertex>(P);
			Set<Vertex> XModified = new HashSet<Vertex>(X);
	
			RModified.add(v);
			PModified.retainAll(N);
			XModified.retainAll(N);
			
			bronKerbosch(RModified, PModified, XModified);
			P.remove(v);
			X.add(v);
		}
	}
}
