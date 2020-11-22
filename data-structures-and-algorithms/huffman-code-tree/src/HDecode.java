/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 12 November 2015
 * @author Victor Lora & Jalal Khan
 * @description 
 *   A program that translates Infix to Postfix.
 *   
 *
 *    A program to decode a file using Huffman Code Algorithm.
 */

import java.io.*;


public class HDecode {

	private Node root = null; // Root of the Huffman Code Tree.

	private String inputFilename;
	private String outputFilename;
	private int fileSize; // The number of bytes
							// in the original file.

	private BitReader bitr;
	private FileOutputStream outF = null;

	public static void main(String[] args) throws FileNotFoundException, IOException {

		HDecode decoder = new HDecode(args[0]); // Construct a Huffman Decoder
		decoder.decode();
	}

	public HDecode(String inputFilename) throws FileNotFoundException {
		this.inputFilename = inputFilename;
		
		//create an output file name with the same name as the input and .orig filetype
		this.outputFilename = inputFilename.substring(0, inputFilename.indexOf(".huf"));
		
		outF = new FileOutputStream(this.outputFilename + ".orig");
	}

	/*
	 * Read the filesize then reconstruct the huffman code tree. 
	 * Use the tree to decode the rest of the file. 
	 * Output a file with a .orig file type that should exactly match
	 * the original text file
	 */
	public void decode() throws IOException {
		
		bitr = new BitReader(inputFilename); //create a new bit reader
		
		fileSize = bitr.readInt(); //the first int is the file size

		root = readTree(); //the huffman code tree is after the file size
		
		int bit = 0; //create an int to read each bit
		
		for (int i = 0; i < fileSize; i++) { //loop for each bit in the file
			
			//traverse the tree from the root until the right node is found
			Node temp = root;
			while (temp.lchild != null || temp.rchild != null) { 
				bit = bitr.readBit();
				if (bit == 1) //if 1, we should go right
					temp = temp.rchild;
				else //else, we should go left
					temp = temp.lchild;
			} 
			
			outF.write(temp.data); //write the correct node's data
			temp = root; //reset temp to the top of the tree
		}
	}

	/*
	 * Reads the tree in the huffman encoded file. each leaf node is preceded by a 0
	 * followed by the data. An intermediary node is preceded by a 1. Use this pattern
	 * to reconstruct the huffman tree
	 */
	public Node readTree() {
		int bit = bitr.readBit(); //create an int variable to read each bit

		if (bit == 0) { //if 0, we are at a leaf node, read the data
			byte data = bitr.readByte();
			Node temp = new Node();
			temp.data = data;
			return temp;
		} else { //otherwise, we are at an intermediary node, keep reading
			Node lchild = readTree();
			Node rchild = readTree();
			Node temp = new Node();
			temp.lchild = lchild;
			temp.rchild = rchild;
			lchild.parent = temp;
			rchild.parent = temp;
			return temp;
		}
	}
	
	public class Node implements Comparable<Node> {
		byte data; // A byte of data - stored in an Integer.
		Node lchild; // Left child pointer.
		Node rchild; // Right child pointer.
		Node parent; // Pointer to parent node.
		Integer frequency; // Frequency the data within
							// a file being encoded.
		/*
		 * Basic node constructor.
		 */

		public Node() {
			data = 0; // Each Huffman Code Tree node
			lchild = null; // contains data, pointers to
			rchild = null; // children and parent nodes
			parent = null; // plus a frequency count
			frequency = 0; // associated with the data.
		}

		/*
		 * Constructor specifying all values of the node instance variables.
		 */

		public Node(byte data, Node lchild, Node rchild, Node parent, int frequency) {
			this.data = data;
			this.lchild = lchild;
			this.rchild = rchild;
			this.parent = parent;
			this.frequency = frequency;
		}

		/*
		 * compareTo() - Compare two frequency values. We want Nodes with lower
		 * frequencies to have higher priority in the priority queue.
		 * 
		 */

		public int compareTo(Node other) {
			if (this.frequency.compareTo(other.frequency) < 0)
				return 1;
			else if (this.frequency.compareTo(other.frequency) > 0)
				return -1;
			else
				return 0;
		}

		public String toString() {
			char ch = (char) this.data;

			String str = "byte: " + data + "  char: ";

			if (data > (byte) 31)
				str = str + (char) data + "  freq: " + frequency;
			else
				str = str + " " + "  freq: " + frequency;

			return str;
		}

	}
}
