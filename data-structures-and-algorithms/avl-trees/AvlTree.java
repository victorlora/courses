/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 03 December 2015
 * @author Victor Lora
 * @description 
 *     An implementation of an AVL Tree
 *   
 */

import java.util.Scanner;

public class AvlTree<T extends Comparable<T>> {
	private Node root = null; // Pointer to the root of the AVL Tree

	// The following variables are used during a
	// restructuring of the tree.

	private Node p1 = null; // Pointer to the pivot node's parent.
	private Node p2 = null; // Pointer to the pivot node.
	private Node p3 = null; // Pointer to pivot's child along the search path.
	private Node p4 = null; // Pointer to pivot's grand-child along the search
							// path.

	// Constants to name the rotations.
	private final static int LL = 0; // Left-Left Rotation.
	private final static int LR = 1; // Left-Right Rotation.
	private final static int RR = 2; // Right-Right Rotation.
	private final static int RL = 3; // Right-Left Rotation.

	public AvlTree() {
		this.root = null; // Start out with an empty tree.
	}

	public void insert(T x) {
		avl_insert(x); // Insert into an AVL tree - restructure as needed.
	}

	private void avl_insert(T x) {
		int rotType; // Type of rotation needed to balance the tree.

		if (search(x)) // Only attach if the new data is not in the tree.
			return;

		attach(x); // Add the new node to the tree.
		p2 = pivot(x); // Find the pivot node;

		if (p2 == null) // If there's no pivot Node
			resetbalance(root, x); // reset bf's from root along the search
									// path.
		else if (addToShort(p2, x)) // elseif (node added to pivot's shorter
									// subtree)
			resetbalance(p2, x); // reset bf's from pivot along the search path.
		else { // Else (node added to pivot's longer subtree)
			resetbalance(p2, x); // restructure the tree.

			setpointers(x); // Set pointers p1, p2, p3 , p4 in preparation
							// to perform a rotation and re-balance the tree.
			rotType = find_rotType(x); // Determine the kind of rotation to
										// perform.

			switch (rotType) {
			case LL:
				LL_rotation();
				break;
			case LR:
				LR_rotation();
				break;
			case RL:
				RL_rotation();
				break;
			case RR:
				RR_rotation();
				break;
			}
		}
	}

	//
	// find_rotType() - This function returns the type of rotation
	// (LL, LR, RR, RL) needed to maintain the balance
	// of a tree when a new node has been attached
	// that unbalances the tree.
	// p2 points to the pivot node upon entry to the function.
	//

	private int find_rotType(T x) {
		
		// determine if added node is to the left or right of p2
		// then determine if added node is to the left or right of 
		// the correspoding child of p2, and return proper rotation type
		if (p2.data.compareTo(x) < 0) {
			if (p2.rchild.data.compareTo(x) < 0) {
				return RR;
			} else {
				return RL;
			}
		} else if (p2.data.compareTo(x) > 0) {
			if (p2.lchild.data.compareTo(x) > 0) {
				return LL;
			} else {
				return LR;
			}
		}
		return -1;
	}

	//
	// resetbalance() - Reset the balance factors when a new node with
	// data value x is attached to the avl tree. Only
	// those nodes along the search path starting from
	// the node addressed by the parameter p need to have
	// their balance factors set.
	//

	private void resetbalance(Node p, T x) {

		// resetbalance for new nodes
		if (p.bf <= 0 && p.data.compareTo(x) > 0) {			// Node added to the left
			p.bf++;											// Increment bf
			resetbalance(p.lchild, x);						// traverse down lchild
		} else if (p.bf >= 0 && p.data.compareTo(x) < 0) {	// Node added to the right
			p.bf--;											// Decrement bf
			resetbalance(p.rchild,x);						// traverse down rchild
			
		// handles resetbalance calls for new nodes and rotations
		} else if (p.bf >= 0 && p.data.compareTo(x) > 0) {	
			resetbalance(p.lchild, x);						
		} else if (p.bf <= 0 && p.data.compareTo(x) < 0) {	
			resetbalance(p.rchild, x);
			
		// handles special RL or LR rotation calls
		} else if (p.lchild != null && p.lchild.bf == 0 && p.rchild.bf == -1) {
			p.bf = -1;
		} else if (p.rchild != null && p.lchild.bf == 1 && p.rchild.bf == 0) {
			p.bf = 1;
		} else {											// if none of the above apply
			p.bf = 0;										// set ptr to zero.
		}
	}


	//
	// setpointers() - Upon entry, p2 points to the pivot node associated
	// with the attaching of a new node with data value x.
	// Instance variable p1, p2, p4 are set as follows:
	//
	// p1 = parent of the pivot, else null.
	// p3 = child of pivot along search path.
	// p4 = grand-child of the pivot along search path.
	//

	private void setpointers(T x) {
		if (p2 != null) {	// if p2 is not null/if more than one
			Node ptr = this.root;
			boolean here = false;
			
			// traverse down until child of current pointer equals p2
			// make current point p1
			while (ptr != null && !here) {
				if (ptr.data.compareTo(x) == 0)
					here = true;
				else if (ptr.data.compareTo(x) < 0) {
					if (ptr.rchild == p2) {
						p1 = ptr;
					}
					ptr = ptr.rchild;
				} else {
					if (ptr.lchild == p2) {
						p1 = ptr;
					}
					ptr = ptr.lchild;
				}
			}

			// handle when p1 == p2 
			if (p1 == p2) {
				p1 = null;
			}

			// set pointers for p2-p4
			if (p2.data.compareTo(x) < 0)
				p3 = p2.rchild;
			else
				p3 = p2.lchild;

			if (p3.data.compareTo(x) < 0)
				p4 = p3.rchild;
			else
				p4 = p3.lchild;
			
			
			// DEBUG PRINTS:
			if (p1 != null) {
				System.out.println("p1: " + p1.data);
			} else {
				System.out.println("p1: null");
			}
			System.out.println("p2: " + p2.data);
			System.out.println("p3: " + p3.data);
			System.out.println("p4: " + p4.data);
		}

	}

	//
	// addToShort() - Determines, by looking at balance factors, if
	// the new node with data value x was added to
	// the subtree of the pivot node with the smaller
	// height. If so, the function returns true,
	// otherwise it returns false.
	//

	private boolean addToShort(Node p2, T x) {
		if (p2.data.compareTo(x) < 0 && p2.bf == 1) // New node added on right
			return true; // tree but left is longer.
		else if (p2.data.compareTo(x) > 0 && p2.bf == -1) // New node added on
															// left
			return true; // tree but right is longer.
		else // ... New node must have been added to
			return false; // the longer subtree of the pivot.
	}

	//
	// pivot() - Return a pointer to the pivot node associated with the newly
	// inserted
	// node with data value x. The pivot is the node on the search path,
	// with nonzero balance factor, closest to the new node.
	//

	private Node pivot(T x) {
		Node p; // A pointer used to traverse down the tree.

		this.p2 = null; // Return null if there is no pivot node.

		if (root != null) { // Start the search from the top of the tree.
			p = root;
			while (p.data.compareTo(x) != 0) { // Not at the leaf yet.
				if (p.bf != 0) // Found a potential pivot further down
					p2 = p; // the tree, update p2.

				if (p.data.compareTo(x) < 0) // ... p follows the search path
					p = p.rchild; // down to the newly inserted
				else // node.
					p = p.lchild;
			}
		}

		return p2;
	}

	//
	// LL_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.
	//

	private void LL_rotation() {
		System.out.println("Doing LL rotation");

		if (p1 == null) {
			root = p3;
		} else {
			if (p1.lchild == p2)
				p1.lchild = p3;
			else
				p1.rchild = p3;
		}
		p2.lchild = p3.rchild;
		p3.rchild = p2;

		// reset balance factors from p3 to p2
		resetbalance(p3, p2.data);
	}

	//
	// LR_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.
	//

	private void LR_rotation() {
		System.out.println("Doing LR rotation");

		if (p1 == null) {
			root = p4;
		} else {
			if (p1.rchild == p2)
				p1.rchild = p4;
			else
				p1.lchild = p4;
		}

		p3.rchild = p4.lchild;
		p2.lchild = p4.rchild;
		p4.lchild = p3;
		p4.rchild = p2;

		// reset balance factors for root, p4, and paths to p2 and p3
		resetbalance(p4, p4.data);
		resetbalance(p4, p2.data);
		resetbalance(p4, p3.data);
		resetbalance(p2, p2.data);

	}

	//
	// RR_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.
	//

	private void RR_rotation() {
		System.out.println("Doing RR rotation");

		if (p1 == null) {
			root = p3;
		} else {
			if (p1.rchild == p2)
				p1.rchild = p3;
			else
				p1.lchild = p3;
		}
		p2.rchild = p3.lchild;
		p3.lchild = p2;

		// reset balance factors from p3 to p2
		resetbalance(p3, p2.data);
	}

	//
	// RL_rotation() - rebalance the tree by performing
	// an LL rotation of the nodes.

	private void RL_rotation() {
		System.out.println("Doing RL rotation");

		if (p1 == null) {
			root = p4;
		} else {
			if (p1.rchild == p2)
				p1.rchild = p4;
			else
				p1.lchild = p4;
		}

		p3.lchild = p4.rchild;
		p2.rchild = p4.lchild;
		p4.rchild = p3;
		p4.lchild = p2;
		
		// reset balance factors for root, p4, and paths to p2 and p3
		resetbalance(p4, p2.data);
		resetbalance(p4, p3.data);
		resetbalance(p4, p4.data);
		resetbalance(p2, p2.data);
	}

	/*
	 * attach() - attach a node with data value x in the Binary Search Tree.
	 */

	public void attach(T x) {
		this.root = rattach(this.root, x);
	}

	/*
	 * rattach() - return a pointer to the root of a BST with data item x
	 * inserted. Do not insert duplicate data items.
	 */

	public Node rattach(Node root, T x) {
		if (root == null) // Base Step - Empty tree
			root = new Node(x, null, null, 0);
		else if (x.compareTo(root.data) < 0) // Smaller values go in
			root.lchild = rattach(root.lchild, x); // the left subtree,
		else if (x.compareTo(root.data) > 0) // larger values go in
			root.rchild = rattach(root.rchild, x); // the right subtree.

		return root;
	}

	public void printTree() {
		rPrintTree(root, 0);
	}

	/*
	 * rPrintTree() - the usual quick recursive method to print a tree.
	 */

	public void rPrintTree(Node r, int level) {

		if (r == null) // Empty tree, nothing to print.
			return;

		rPrintTree(r.rchild, level + 1); // Print the right subtree.

		for (int i = 0; i < level; i++)
			System.out.print("     ");

		System.out.println(r.data.toString() + "(" + r.bf + ")"); // Print the
																	// root.

		rPrintTree(r.lchild, level + 1); // Print the left subtree.
	}

	public boolean empty() {
		return this.root == null;
	}

	public boolean search(T x) {
		Node ptr = this.root;

		while (ptr != null)
			if (ptr.data.compareTo(x) == 0)
				return true;
			else if (ptr.data.compareTo(x) < 0)
				ptr = ptr.rchild;
			else
				ptr = ptr.lchild;

		return false;
	}

	public static void main(String[] args) {
		AvlTree<Integer> t1 = new AvlTree<Integer>();

		Scanner keyb = new Scanner(System.in); // Scanner to read from keyboard.
		int value = 0; // Value to insert into tree.

		do {
			// Read a value to insert into the tree.

			System.out.print("Insert what integer (-1 to quit)? ");
			value = keyb.nextInt();

			if (value != -1) { // a value of -1 terminates the program.
				t1.insert(value); // Insert the value into the AVL tree.
				t1.printTree(); // Check if value was correctly inserted.
			}
		} while (value != -1);

		System.out.println("\nProgram terminating\n");
	}

	// inner class - a Node in the AVL tree.

	public class Node {
		T data; // The data item stored in the node.
		Node lchild; // Pointer to left child (left subtree).
		Node rchild; // Pointer to right child (right subtree).
		int bf; // The Balance Factor of the node.

		public Node() {
			this.data = null;
			this.lchild = null;
			this.rchild = null;
			this.bf = 0;
		}

		public Node(T data, Node lchild, Node rchild, int bf) {
			this.data = data;
			this.lchild = lchild;
			this.rchild = rchild;
			this.bf = bf;
		}

	}
}