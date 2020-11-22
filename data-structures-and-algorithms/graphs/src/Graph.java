

public interface Graph
{
	public void insertVertex(Vertex newVertex);           // Insert new Vertex in the graph
	public void insertEdge(String v1, String v2, int wt); // Insert a new edge with weight
	                                                      //   wt between Vertices with labels
	                                                      //   v1 and v2
	public Vertex retrieveVertex(String v);          // Return pointer to Vertex with label v
	public int edgeWeight(String v1, String v2);     // Return edge weight of the edge
	                                                 //   between Vertices with labels v1, v2
	public void removeVertex(String v);              // Remove vertex with label v
	public void removeEdge(String v1, String v2);    // Remove edge between vertices w/ labels v1, v2.
	
	public void clear();            // Remove all vertices and edges from the graph.
	public boolean isEmpty();       // Return whether the graph is empty.
	public boolean isFull();        // Return whether the graph is full.
	public void showStructure();    // Display the structure of the graph.
	
	public void reset();   // Set all Vertices to be unvisited.
}
	
	
	
	