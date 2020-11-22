/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 07 December 2015
 * @author Victor Lora & Michael Lynn
 * @description 
 *     WtGraph - implements the Graph interface
 *               for a weighted graph using a vertex list
 *               and an adjacency matrix.
 */

public class WtGraph implements Graph {
	public static final int DEF_MAX_GRAPH_SIZE = 100;
	public static final int INFINITE_EDGE_WT = Integer.MAX_VALUE;

	private int size; // The number of vertices in the graph.
	private Vertex[] vertexList; // An array containing the grahs vertices.
	private int[][] adjMatrix; // The adjacency matrix storing the edge weights
								// between the vertices.

	/*
	 * default WtGraph() constructor. - uses the default maximum graph size.
	 */

	public WtGraph() {
		setup(DEF_MAX_GRAPH_SIZE); // Set up vertex list and adjacency matrix.
	}

	/*
	 * WtGraph() constructor. - accepts an argument to specify the maximum graph
	 * size.
	 */

	public WtGraph(int maxNumber) {
		setup(maxNumber); // Set up the vertex list and adjacency matrix.
	}

	/*
	 * setup() - constructs the initial vertex list and adjacency matrix.
	 */

	private void setup(int maxNumber) {
		vertexList = new Vertex[maxNumber];
		adjMatrix = new int[maxNumber][maxNumber];
		size = 0;

		for (int i = 0; i < maxNumber; i++) // Set all the matrix entries to
			for (int j = 0; j < maxNumber; j++) // INFINITE_EDGE_WT to indicate
				adjMatrix[i][j] = INFINITE_EDGE_WT; // the absence of edges.
	}

	/*
	 * insertVertex() - add a new vertex to the graph.
	 */

	public void insertVertex(Vertex newVertex) {
		vertexList[size] = newVertex;
		size++;
	}

	/*
	 * allEven() - determines if all the vertices are of even degree.
	 */

	public boolean allEven() {
		for (int i = 0; i < size; i++) {
			if (neighbors(vertexList[i]).length % 2 != 0 
					|| neighbors(vertexList[i]).length < 2)
				return false;
		}

		return true;
	}

	/*
	 * insertEdges() - insert a new edge with weight wt between vertices with
	 * labels v1 and v2.
	 */

	public void insertEdge(String v1, String v2, int wt) {
		setEdge(index(v1), index(v2), wt);		// sets adjMatrix[v1][v2]
		setEdge(index(v2), index(v1), wt);		// sets adjMatrix[v2][v1]
	}

	/*
	 * retrieveVertex() - return a pointer to the Vertex with the given label v.
	 * If no such vertex exists, return null.
	 */
	public Vertex retrieveVertex(String v) {
		for (int i = 0; i < size; i++) {
			if (vertexList[i] != null && vertexList[i].getLabel().equals(v))
				return vertexList[i];
		}

		return null;
	}

	/*
	 * edgeWeight() - return the weight of the edge between the vertices with
	 * labels v1 and v2.
	 */

	public int edgeWeight(String v1, String v2) {
		return getEdge(index(v1), index(v2));
	}

	/*
	 * removeVertex() - remove from the graph the vertex with label v. This
	 * involves removing the label from the vertex list as well as removing the
	 * edges the vertex was part of.
	 */
	public void removeVertex(String v) {
		int ind = index(v); // Get the vertex's array index.

		if (ind == -1) // Vertex not in graph.
			return;

		// Move vertices over to fill the gap in the vertex list.
		for (int i = ind + 1; i < size; i++)
			vertexList[i - 1] = vertexList[i];

		// Move entries in the adjacency matrix to fill the gap.
		
		// Move Quadrant I Over
		for (int i = ind + 1; i < size; i++) {
			for (int j = 0; j < ind; j++) {
				adjMatrix[i - 1][j] = adjMatrix[i][j];
			}
		}
		// Move Quadrant III Up
		for (int i = 0; i < ind; i++) {
			for (int j = ind + 1; j < size; j++) {
				adjMatrix[i][j - 1] = adjMatrix[i][j];
			}
		}
		// Move Quadrant IV Up and Over
		for (int i = ind + 1; i < size; i++) {
			for (int j = ind + 1; j < size; j++) {
				adjMatrix[i - 1][j - 1] = adjMatrix[i][j];
			}
		}

		vertexList[size - 1] = null;

		size = size - 1; // We have one fewer vertices.
	}

	/*
	 * getSize() - return the number of vertices in the graph.
	 */

	public int getSize() {
		return size;
	}

	/*
	 * removeEdge() - remove the edge between the vertices with labels v1 and
	 * v2.
	 */

	public void removeEdge(String v1, String v2) {
		setEdge(index(v1), index(v2), INFINITE_EDGE_WT);	// sets adjMatrix[v1][v2] = -1
		setEdge(index(v2), index(v1), INFINITE_EDGE_WT);	// sets adjMatrix[v1][v2] = -1
	}

	/*
	 * getVertextList() - return a reference to the array of vertices.
	 */

	public Vertex[] getVertexList() {
		return vertexList;
	}

	/*
	 * clear() - remove all vertices and edges from the graph.
	 */
	public void clear() {
		for (int i = 0; i < vertexList.length; i++) {
			vertexList[i] = null;	// sets all indexes to null
			for (int j = 0; j < vertexList.length; j++)
				adjMatrix[i][j] = INFINITE_EDGE_WT;	// sets all entries to '-'
		}
	}

	/*
	 * reset() - set all vertices in the graph to be not visited.
	 */

	public void reset() {
		for (int i = 0; i < size; i++)
			vertexList[i].reset();
	}

	/*
	 * Check for empty or full graphs.
	 */

	public boolean isEmpty() {
		return (size == 0);
	}

	public boolean isFull() {
		return (size == vertexList.length);
	}

	/*
	 * showStructure() - display the data structures that implement the graph -
	 * this includes the vertexList and the adjacency matrix.
	 */

	public void showStructure() {
		System.out.println("vertexList");
		System.out.println("----------");
		for (int i = 0; i < vertexList.length; i++)
			if (vertexList[i] != null)
				System.out.printf("%3d  %s\n", i, vertexList[i].getLabel());

		System.out.printf("\nAdjacency Matrix\n----------------\n");
		System.out.printf("   |");
		for (int i = 0; i < size; i++)
			System.out.printf("%3d |", i);
		System.out.println();

		System.out.printf("---+");
		for (int i = 0; i < size; i++)
			System.out.printf("----+", i);
		System.out.println();

		for (int i = 0; i < size; i++) {
			System.out.printf("%3d|", i);
			for (int j = 0; j < size; j++)
				if (adjMatrix[i][j] == INFINITE_EDGE_WT)
					System.out.printf("  - |");
				else
					System.out.printf("%3d |", adjMatrix[i][j]);
			System.out.println();
		}
	}

	/*
	 * neighbors() - return an array of vertices that are neighbors of vertex V.
	 */

	public Vertex[] neighbors(Vertex v) {
		// First count how many neighbors Vertex v has
		// The construct an array of Vertices
		//
		// Vertex [] neighbor = new Vertex[count];
		//
		int vertex = index(v.getLabel());
		int count = 0;
		for (int i = 0; i < vertexList.length; i++) {
			if (adjMatrix[vertex][i] != INFINITE_EDGE_WT)
				count++;
		}

		Vertex[] neighbor = new Vertex[count];
		count = 0;

		for (int j = 0; j < vertexList.length; j++) {
			if (adjMatrix[vertex][j] != INFINITE_EDGE_WT) {
				neighbor[count] = vertexList[j];
				count++;
			}
		}

		return neighbor;
	}

	/*
	 * index() - return the vertexList index of the vertex with label v. If the
	 * vertex doesn't exist return -1.
	 */

	private int index(String v) {
		for (int i = 0; i < vertexList.length; i++) {
			if (vertexList[i].getLabel().equals(v))
				return i;
		}

		return -1;
	}

	/*
	 * getEdge() - get the edge weight of the edge between the vertices with
	 * array indices row and col.
	 */

	private int getEdge(int row, int col) {
		return adjMatrix[row][col];
	}

	/*
	 * setEdge() - set the weight of the edge between vertices with array
	 * indices row and col to wt.
	 */

	private void setEdge(int row, int col, int wt) {
		adjMatrix[row][col] = wt;
	}

}
