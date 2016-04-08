package util;

import java.io.File;
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
	public static void graphLoader(graph.Graph g, String filename){
		
		Scanner sc;
		try{
			sc = new Scanner(new File(filename));
		}catch (Exception e){
			e.printStackTrace();
			return;
		}
		
		int row_index = 0;
		sc.useDelimiter("\\[|\\],\\[|\\]");
		while (sc.hasNext()){
			row_index ++;
			String row = sc.next();
			
			int column_index = 0;
			Vertex v_row = new Vertex(row_index);
			g.addVertex(v_row);
			
			for (String e: row.split(",")){
				column_index ++;				
				
				if (Integer.parseInt(e) == 1){
					Vertex v_col = new Vertex(column_index);
					g.exportGraph().get(v_row).add(v_col);
					g.addEdge(v_row, v_col);
				}
			}			
		}
		sc.close();
	}
}
