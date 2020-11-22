/*
 *     An interface for a basic Binary Search Tree
 */

public interface BSTInterface <T extends Comparable<T>>
{
	public void insert(T x);  // Enter data x in the tree.
	public void remove(T x);  // Remove x from the tree if present.
	public void removeMin();  // Remove smallest tree item if present.
	public void removeMax();  // Remove largest tree item if present.
	public T find(T x);       // Return x if in tree, else null.
	public T findMax();       // Return largest item in the tree.
	public T findMin();       // Return smallest item in the tree.
	public void printTree();  // Display the tree and stdout.
    public boolean isEmpty(); // Determine if the tree is empty.
 	public void makeEmpty();  // Remove all items from the tree.
	public void inorder();    // Display an inorder traversal of the tree.
	public void preorder();   // Display a preorder traversal of the tree
	public void postorder();  // Display a postorder traversal of the tree.
}
