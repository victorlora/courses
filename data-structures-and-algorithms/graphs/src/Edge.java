/*
 *    An Edge class for representing edges in a weighted graph.
 */


public class Edge implements Comparable<Edge>
{
	private Vertex vertexA = null;   // One vertex in the edge.
	private Vertex vertexB = null;   // The other vertex.
	private int weight;              // The weight (cost) of the edge.
	private boolean visited = false; // Signals the edge was visited.
	
	
	/*
	 *   The Edge constructor - it take two vertices and a weight.
	 */
	
	public Edge(Vertex vertexA, Vertex vertexB, int weight)
	{
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.weight = weight;
	}
	
	/*
	 *    getVertexA()
	 *    
	 *    Accessor for the first vertex of an edge.
	 */
	
	public Vertex getVertexA()
	{
		return vertexA;
	}

	/*
	 *    getVertexB()
	 *    
	 *    Accessor for the second vertex of an edge.
	 */

	public Vertex getVertexB()
	{
		return vertexB;
	}
	
	/*
	 *    getWeight()
	 *    
	 *    Accessor for the edge weight.
	 */
	
	public int getWeight()
	{
		return weight;
	}
	
	/*
	 *    visited()
	 *    
	 *    Returns the value of the edge's visited flag,
	 *    while also setting it to true. This is known
	 *    in the trade as a Test-and-Set function.
	 */
	
	public boolean visited()
	{
		boolean v = visited;
		visited = true;
		return visited;
	}
	
	/*
	 *    is Visited()
	 *    
	 *    Returns if the edge is visited.
	 */
	
	public boolean isVisited()
	{
		return visited;
	}
	
	/*
	 *   reset()
	 *   
	 *   resets the visited flag to false.
	 */
	public void reset()
	{
		visited = false;
	}
	
	/*
	 *   compareTo() - used to prioritize edges.  Edges with a lower weight
	 *                 will be given a higher priority.
	 */
	
	public int compareTo(Edge rhs)
	{
		if (this.weight < rhs.weight)
			return 1;
		else if (this.weight > rhs.weight)
			return -1;
		else
			return 0;
	}
	
	/*
	 *   toString() - return a string representation
	 *                of the edge - the labels on its
	 *                vertices and its weight.
	 */
	
	public String toString()
	{
		String s = vertexA.getLabel() + "  " +
	               vertexB.getLabel() + "  " +
				   weight;
		
		return s;
	}
	
	/*
	 *    equals()
	 *    
	 *    Returns whether or not the two edge vertices are the same vertex.
	 *    
	 */
	
	public boolean equals()
	{
		return (vertexA == vertexB);
	}
}
