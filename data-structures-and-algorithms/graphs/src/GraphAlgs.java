/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 07 December 2015
 * @author Victor Lora & Michael Lynn
 * @description 
 * 		GraphAlgs-
 * 			Contains an implementation of breadth first and
 * 			depth first.
 */

import java.util.*;
import java.io.*;

public class GraphAlgs {
	
	
	/*
	 *   main() - A main routine that:
	 *            1) Reads a file containing vertices and edges - supplied as a command line parameter.
	 *            2) Forms the WtGraph
	 *            3) Repeatedly prompts for an algorithm to run.
	 *            
	 *            4) The data file is a sequence of lines with the format.
	 *                  vertex                        - define a vertex
	 *                  source  destination  weight   - define edges.
	 */
	
	public static void main(String[] args)
	{
		WtGraph g = new WtGraph(3000);

        boolean LabPart2 = true;
		
		try
		{
			FileReader fin = new FileReader( args[0] );
			Scanner graphFile = new Scanner( fin );
			
			// Read vertices and edges and insert.
			
			String line;
			
			while (graphFile.hasNextLine()) {
				line = graphFile.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				
				try {
					if (st.countTokens() == 1) {     // Inserting a new vertex if 
						String v = st.nextToken();   // not already in the graph.
						if (g.retrieveVertex(v) == null)
							g.insertVertex( new Vertex(v));
					}
					else if (st.countTokens() == 3) {  // Inserting a new edge
						String src = st.nextToken();
						String dest = st.nextToken();
						int weight = Integer.parseInt( st.nextToken());
						
						if (g.retrieveVertex(src) == null)
							g.insertVertex( new Vertex( src ));
						if (g.retrieveVertex(dest) == null)
							g.insertVertex( new Vertex(dest));
						
						g.insertEdge(src, dest, weight);
					}
					else {
						System.out.println("Skipping ill-formatted line: " + line);
					}
				}
				catch( NumberFormatException e) {
					System.out.println("Skipping ill-formatted line: " + line);
				}
			}
		}
		catch( IOException e) {
			System.err.println(e);
		}
		
		System.out.println("File read\n");
	    g.showStructure();	

	    
	    Scanner keyb = new Scanner(System.in);
	    processRequests(keyb, g);
	    
	    System.out.println("\nProgram terminated");
	}
	
	
	public static void processRequests(Scanner keyb, WtGraph g)
	{
		boolean repeat = true;
		
		while (repeat)
			try
			{
				System.out.print("Enter algorithm  d)epth, b)readth, dij)kstra e)ven q)uit: ");
				String alg = keyb.nextLine();
				
				if (alg.equals("d")) {
					System.out.println("... Depth-First Search");
					System.out.print("Enter starting vertex: ");
					String startVertex = keyb.nextLine();
					depthFirst(startVertex, g);
				}
				else if (alg.equals("b")) {
					System.out.println("... Breadth-First Search");
					System.out.print("Enter starting vertex: ");
					String startVertex = keyb.nextLine();
					breadthFirst(startVertex, g);
				}
				else if (alg.equals("dij")) {
					System.out.println("... Dijkstra's Algorithm");
					System.out.print("Enter starting vertex: ");
					String startVertex = keyb.nextLine();
					dijkstra(startVertex, g);
				}
				else if (alg.equals("e")) {
					boolean allEven = g.allEven();
					if (allEven)
						System.out.println("All vertices have even degree.");
					else
						System.out.println("Some vertices have odd degree or zero degrees.");
				}
				else if (alg.equals("q"))
					repeat = false;
			}
			catch ( NoSuchElementException e)
			{
				repeat = false;
			}
	}
	
	
	/*
	 *   depthFirst - print out the order in which the vertices
	 *                  of a graph will be traversed in a depthFirst
	 *                  traversal from a given starting node.
	 */
	
	public static void depthFirst(String startV, WtGraph g)
	{
        Stack <Vertex> visited = new Stack <Vertex>();
        Vertex[] neighbors;
        g.reset();	// sets all visited to false
        
        visited.push(g.retrieveVertex(startV));
        while (!visited.isEmpty()) {
        	Vertex u = visited.pop();
        	if (!u.isVisited()) {
        		u.visit();
        		System.out.println(u.getLabel());
        		neighbors = g.neighbors(u);
        		for (Vertex each:neighbors) {
        			visited.push(each);
        		}
        	}
        }
        
	}
	
	
	/*
	 *   breadthFirst - print out the order in which the vertices
	 *                  of a graph will be traversed in a breadthFirst
	 *                  traversal from a given starting node.
	 */
	
	public static void breadthFirst(String startV, WtGraph g)
	{
		Queue <Vertex> visited = new Queue <Vertex>();
        Vertex[] neighbors;
        g.reset();	// sets all visited to false
        
        visited.enqueue(g.retrieveVertex(startV));
        while (!visited.isEmpty()) {
        	Vertex u = visited.dequeue();
        	if (!u.isVisited()) {
        		u.visit();
        		System.out.println(u.getLabel());
        		neighbors = g.neighbors(u);
        		for (Vertex each:neighbors) {
        			visited.enqueue(each);
        		}
        	}
        }
	}
	
	public static void dijkstra(String startV, WtGraph g)
	{

	}
	
}
