/*
 *    A Vertex class used in a weighted graph implementation.
 */


public class Vertex {
	
	private String label;      // A label for the vertex.
	private boolean visited;   // Flags if the vertex is visited.  This
	                           //   flag is used in several common graph algorithms
	                           //   such as Breadth and Depteh first search as 
	                           //   well as Dijkstra's Shortest Path Algorithm.
	
	/*
	 *    Vertex() constructor - takes a label as an argument.
	 */
	
	public Vertex(String name)
	{
		label = name;
		visited = false;
	}
	
	/*
	 *    getLabel() - returns the vertex's label.
	 */
	
	public String getLabel()
	{
		return label;
	}
	
	/*
	 *   visit() - returns if the vertex was visited while
	 *             also setting the visited flag to true.
	 */
	
	public boolean visit()
	{
		boolean was = visited;
		visited = true;
		return was;
	}
	
	/*
	 *   isVisted()
	 *   
	 *   returns if the vertex was visited - neither
	 *           sets nor resets the visited flag.
	 */
	
	public boolean isVisited()
	{
		return visited;
	}
	
	/*
	 *    reset() - set the visited flag to false.
	 */
	
	public void reset()
	{
		visited = false;
	}
	
}
