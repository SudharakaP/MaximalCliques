package util;

import java.io.InputStream;
import java.util.Scanner;
import graph.Vertex;

/**
 * @author Sudharaka Palamakumbura
 *
 */
public class GraphLoader {

	/**
	 * Loads data from a file to graph. The file should contain data as an adjacency matrix
	 * with each element separated by ,(commas) and each row separated by square brackets.
	 * eg: [1,2,3],[5,7,6],[9,4,2] represents an adjacency matrix with three rows and columns. 
	 * 
	 * @param g
	 * @param filename
	 */
	public static void graphLoader(graph.Graph g, InputStream inputStream){
		
		Scanner sc;
		try{
			sc = new Scanner(inputStream);
		}catch (Exception e){
			e.printStackTrace();
			return;
		}
		
		int row_index = 0;
		
		// set delimiter so that the calling next() on the scanner separates the matrix to
		// strings that contain the elements of the rows of the matrix.
		sc.useDelimiter("\\[|\\],\\[|\\]");
				
		// iterates over each row of the adjacency matrix
		while (sc.hasNext()){
			row_index ++;
			String row = sc.next();
			
			int column_index = 0;
			Vertex v_row = new Vertex(row_index);
			g.addVertex(v_row);
			
			// iterates over each element of the row
			for (String e: row.split(",")){
				column_index ++;				
			
				// construct the adjacency list of this graph
				if (Integer.parseInt(e) == 1){
					Vertex v_col = new Vertex(column_index);
					g.addVertex(v_col);
					g.addEdge(v_row, v_col);
				}
			}			
		}
		sc.close();
	}
}
