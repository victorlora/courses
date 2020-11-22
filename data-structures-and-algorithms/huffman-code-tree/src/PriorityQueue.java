/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 26 October 2015
 * @author Victor Lora
 * @description 
 *   Priority queue that uses doubly linked list
 *   
 */
public class PriorityQueue<T extends Comparable<T>> implements PQInterface<T> {
	
	private Node fPtr;	// Pointer to the front sentinel in the PQ.
	private Node rPtr;	// Pointer to the rear sentinel in the PQ.

	/*
	 * Constructor for PriorityQueue
	 * 
	 * Sets up the sentinel nodes with fPtr pointing to 
	 * the front and rPtr pointing to the rear.
	 */
	public PriorityQueue() {
		Node front = new Node();	// Construct the sentinel
		Node rear = new Node();		// nodes.

		front.prev = null;	// Link the sentinels. All queue
		front.next = rear;	// items will be between the sentinels
		rear.prev = front;	// so each items is assured to have
		rear.next = null;	// a node in front and in back.

		fPtr = front; 		// The fPtr points to the front node.
		rPtr = rear;		// The rPtr points to the rear node.
	}

	

	/*
	 * makeEmpty()
	 * 
	 * Removes all items from the priority queue.
	 * (Not needed for this assignment)
	 */
	public void makeEmpty() {
		Node ptr = fPtr.next;
		if (ptr.next != null) { 	// If list is not empty
			while (ptr.next != null)	// and while not the rear sentinel
				ptr = ptr.next; 			// go to the next node
			ptr.prev = fPtr; 			// rear sent. points to front sent.
			fPtr.next = ptr; 			// front sent. points to rear sent.
		}
		ptr = null;					// Set ptr to null for trash collection.
		return;
	}

	/*
	 * enqueue()
	 * 
	 * Inserts new node before a node of lower priority, 
	 * If the node is of equal priority it will insert itself after 
	 * the last node of equal priority.
	 */
	public void enqueue(T data) {
		Node ptr = fPtr.next;
		
		Node temp = new Node(); // Construct the new node.
		temp.data = data; 		// Assign value to the new node
		
		if (isEmpty()) {	// If the queue is empty
			temp.prev = fPtr; 		// Link to front sentinel
			temp.next = fPtr.next;	// Link to rear sentinel
			temp.next.prev = temp;	// Link rear sentinel to new node
			temp.prev.next = temp;	// Link front sentinel to new node
			return;
		} else {
			while (ptr.next != null) {	// while not at the rear sent.
				// if data priority coming in is greater than queue data 
				if (data.compareTo(ptr.data) > 0) { 
					// ...insert before queue data
					temp.prev = ptr.prev;	// Link to previous node
					temp.next = ptr;		// Link to current node
					temp.next.prev = temp;	// Link current to new node
					temp.prev.next = temp;	// Link previous to new node
					return;
				}
				ptr = ptr.next;	// otherwise, keep traveling down the queue.
			}
			// If the end of list is reached, attach new node before rear sent.
			temp.prev = rPtr.prev;	// Link to previous node
			temp.next = rPtr;		// Link to rear sentinel
			temp.next.prev = temp;	// Link sentinel to new node.
			temp.prev.next = temp;	// Link previous to new node.
		}

	}

//	public void enqueue(T data) {
//		if(!isFull()) {
//			Node ptr = fPtr.next;
//			Node empty = new Node();
//			empty.data = data;
//			
//			if(!isEmpty()) 
//			{
//				while (empty.data.compareTo(ptr.data) <= 0 && ptr.next != rPtr) 
//				{
//					ptr = ptr.next;
//					
//					if(empty.data.compareTo(ptr.data) <= 0)
//						ptr = ptr.next;
//				}
//			}
//			empty.next = ptr;
//			empty.prev = ptr.prev;
//			ptr.prev.next = empty;
//			ptr.prev =empty;
//		}
//	}
	/*
	 * dequeue()
	 * 
	 * Remove front node and return its data
	 */
	public T dequeue() {
		T data = fPtr.next.data;	// store data contained in first node
		
		Node ptr = fPtr.next;		// Pointer to the first node in queue
		ptr.next.prev = ptr.prev;	// Remove that node 
		ptr.prev.next = ptr.next;	// Link prev./next nodes to each-other
		
		ptr = null;					// Set ptr for trash collection

		return data;				// return data contained in first node
	}

	/*
	 * front()
	 * 
	 * returns the data contained in the first node in queue.
	 */
	public T front() {
		return fPtr.next.data;
	}

	/*
	 * isEmpty()
	 * 
	 * returns true when front and rear sentinel nodes are pointing to
	 * each other.
	 */
	public boolean isEmpty() {
		Node ptr = fPtr.next; 	// Pointer to first node or rear sentinel
		if (ptr.next != null) { // if the first item in queue is not
			return false; 		// rear sentinel node, then queue is not
		} 						// empty; return false

		return true; // otherwise return true
	}

	/*
	 * isFull()
	 * 
	 * Always returns false, because this implementation uses
	 * a doubly linked list.
	 */
	public boolean isFull() {
		return false;
	}

	/*
	 * toString() - return a String representation of the PQ.
	 */
	public String toString() {
		String str = "PQ----front-------\n";

		Node ptr = fPtr.next;

		while (ptr.next != null) { // For each item ...
			str = str + ptr.data.toString(); 	// method for each data
			str = str + "\n"; 					// the PQ's String
			ptr = ptr.next; 					// representation.
		}

		str = str + "-------rear-------\n";

		return str;
	}
	
	/*
	 * getSize()
	 * 
	 * returns the size of the priority queue, minus the two
	 * sentinel nodes.
	 * (Not needed for this assignment)
	 */
	public int getSize() {
		Node ptr = fPtr.next; 	// A pointer to travel the list.
		int count = 0; 			// Counts the number of nodes.

		while (ptr.next != null) // While not at the end of the list.
		{
			count++; 				// Add this node to the count.
			ptr = ptr.next; 		// Move to the next node.
		}

		return count; 			// Return the number of nodes.
	}

	/*
	 * Inner Class - Node objects for PriorityQueue items in doubly 
	 * linked list.
	 */
	private class Node {
		public T data;		// The item's data.
		public Node prev;	// Pointer to the previous Node.
		public Node next;	// Pointer to the next node.
	}
}
