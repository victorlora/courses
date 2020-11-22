import java.util.*;

public class TestWtGraph2
{
	public static void main(String[] args)
	{
		String cmdLine = null;
		String cmd = null;
		WtGraph g = new WtGraph();
		
		Scanner keyb = new Scanner( System.in );
		
		do {
			System.out.print("> ");
			
			cmdLine = keyb.nextLine();
			StringTokenizer tok = new StringTokenizer(cmdLine);
			cmd = tok.nextToken();
			
			if (cmd.equals("+")) {
				Vertex v = new Vertex(tok.nextToken());
			    g.insertVertex(v);
			}
			else if (cmd.equals("?")) {
				Vertex v = g.retrieveVertex(tok.nextToken());
				if (v == null)
					System.out.println("That vertex is not in the graph");
				else
					System.out.println("That vertex is in the graph");
			}
			else if (cmd.equals("-")) {
				g.removeVertex(tok.nextToken());
			}
			else if (cmd.equals("!")) {
				g.removeEdge(tok.nextToken(), tok.nextToken());
			}
			else if (cmd.equals("=")) {
			    Vertex v = g.retrieveVertex(tok.nextToken());
			    Vertex w = g.retrieveVertex(tok.nextToken());
			    int wt = Integer.parseInt( tok.nextToken() );
			    g.insertEdge(v.getLabel(), w.getLabel(), wt);
			}
			else if (cmd.equals("#")) {
			    Vertex v = g.retrieveVertex(tok.nextToken());
			    Vertex w = g.retrieveVertex(tok.nextToken());
			    if (v != null && w != null) {
			        int wt = g.edgeWeight(v.getLabel(), w.getLabel());
			        System.out.printf("Edge Weight: %d\n", wt);
			    }
			    if (v == null)
			    	System.out.println("The 1st vertex isn't in the graph");
			    if (w == null)
			    	System.out.println("The 2nd vertex isn't in the graph");
			}
			else if (cmd.equals("e")) {
				if (g.isEmpty())
					System.out.println("The graph is empty");
				else
					System.out.println("The graph is not empty");
			}
			else if (cmd.equals("f")) {
				if (g.isFull())
					System.out.println("The graph is full");
				else
					System.out.println("The graph is not full");
			}
			else if (cmd.equals("c")) {
				System.out.println("Removing all vertices and edges");
				g.clear();
			}
			else if (cmd.equals("s"))
				g.showStructure();
			else if (cmd.equals("d")) {
				if (g.allEven())
					System.out.println("All vertices have even degree");
				else
					System.out.println("Some vertices have odd degree");
			}
		
		} while (!cmd.equals("q"));
			
		
	}
}
