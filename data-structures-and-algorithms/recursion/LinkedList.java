/*
*
* Data Structures & Algorithms. *
* @version 1.0 06 Sept 2015
* @author Casie Ropski; Victor Lora
* @description 
* 	A Linked List Class for implementing
*   some simple recursive functions.
*
*   This program uses a singly linked list without sentinel.
*   Also note that this is a generic class.
*/


public class LinkedList <T>
{
	private Node header = null;  // Pointer to the head of the list.

	public LinkedList()
	{
		header = null;
	}


	public static void main(String[] args)
	{
		// Construct lists of Strings, Integers, and Boolean.

		LinkedList<String> strList = new LinkedList<String>();
		LinkedList<Integer> intList = new LinkedList<Integer>();
		LinkedList<Boolean> boolList = new LinkedList<Boolean>();

		// Now populate the lists.

		strList.addToFront("I'm first");
		strList.addToFront("No you're not!");
		strList.addToFront("Move down buddy");
		strList.addToFront("Hey make room for me");
		strList.addToFront("Move over I'm coming in");
		strList.addToFront("They'll let anyone in this list");

		// Create a list with 68 integers.

		for (int i = 0; i < 68; i++)
			intList.addToFront(i);

		// Display the list contents

		strList.display();
		intList.display();
		boolList.display();

		// Determine the length of each list iteratively.

		System.out.println("\nList lengths computed iteratively\n");
		System.out.println("strList number of nodes: "+ strList.lengthIterative());
		System.out.println("intListnumber of nodes: "+ intList.lengthIterative());
		System.out.println("boolListnumber of nodes: "+ boolList.lengthIterative());

		// Determine the length of each list recursively.

		System.out.println("\nList lengths computed recursively\n");
		System.out.println("strList number of nodes: "+ strList.lengthRecursive(strList.header));
		System.out.println("intList number of nodes: "+ intList.lengthRecursive(intList.header));
		System.out.println("boolList number of nodes: "+ boolList.lengthRecursive(boolList.header)+"\n");
		strList.displayBkwd(strList.header);
	}

	/*
	 *   Enter a data item at the front of the list.
	 */

	public void addToFront(T item)
	{
		Node ptr = new Node();     // Construct a new node - ptr points to it.

		ptr.data = item;           // New node's data is the parameter item.
		ptr.next = header;         // New node points to current front item.

		header = ptr;              // The new node becomes the front node.
	}

	/*
	 *    lengthIterative()
	 *
	 *    Return the length of the list.
	 */

	public int lengthIterative()
	{
		Node ptr = header;     // A pointer to travel the list.
		int count = 0;         // Counts the number of nodes.

		while (ptr != null)    // While not at the end of the list.
		{
			count++;                // Add this node to the count.
			ptr = ptr.next;         // Move to the next node.
		}

		return count;     // Return the number of nodes.
	}


	/*
	 *    lengthRecursive()
	 *
	 *    Return the length of the list that starts
	 *    at the Node pointed to by ptr.
	 */

	public int lengthRecursive(Node ptr) {
		if (ptr == null) 
			return 0;						// BASE STEP

        // INDUCTIVE STEP
		return 1 + lengthRecursive(ptr.next);
	}
	/*
	 *    displayBkwd()
	 *    
	 *    Prints linkList backwards
	 */
	public void displayBkwd(Node ptr) {
		if(ptr.next != null) {			// INDUCTIVE STEP
			displayBkwd(ptr.next);
		} 
		T data = ptr.data;				// BASE STEP
		System.out.println(data);
	}


	/*
	 *   Display the contents of the linked list.
	 */

	public void display()
	{
		Node    ptr = header;  // Pointer to traverse the list.

		while (ptr != null) {    // While not at the end of the list ...
			T data = ptr.data;              // Get data of current node.
			System.out.println(data.toString()); // Print the data.
			ptr = ptr.next;                 // Move to the next node.
		}

		System.out.println("");
	}


	/*
	 *    Inner Class - Node objects for a singly linked list.
	 *
	 */

	private class Node
	{
		public T data;      // Data stored in the Node.
		public Node next;   // Pointer to next Node in the list.
 	}
}
