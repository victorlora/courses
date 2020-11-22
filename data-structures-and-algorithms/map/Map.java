
/*
 *   An implementation of the Map interface.
 *   
 *   A doubly linked list implementation is used.
 *   Front and rear sentinel nodes are used.
 */

public class Map <K, V> implements MapInterface<K, V>
{
	private Node header;   // Pointer to the first item in the Map.
	
	
	// The Constructor sets up the sentinel nodes with
	// header pointing to the front.
	
	public Map()
	{
		Node front = new Node();   // Construct the sentinel
		Node rear = new Node();    // nodes.
		
		front.prev = null;   // Link the sentinels.  All map
		front.next = rear;   // items will be between the sentinels
		rear.prev  = front;  // so each items is assured to have 
		rear.next  = null;   // a node in front and in back.
		
		header = front;  // The header points to the front node.
	}
	
	
	public int getSize()
	{
		Node ptr = header.next;     // A pointer to travel the list.
		int count = 0;         		// Counts the number of nodes.

		while (ptr.next != null)    // While not at the end of the list.
		{
			count++;                // Add this node to the count.
			ptr = ptr.next;         // Move to the next node.
		}

		return count;     // Return the number of nodes.
	}
	
	public void makeEmpty()
	{
		Node ptr = header.next;
		if (ptr.next != null) {  		// If list is not empty
			while (ptr.next != null) 	// and while not the rear sentinel node
				ptr = ptr.next;			// go to the next node
			ptr.prev = header;			// rear sent. points to front sent.
			header.next = ptr;			// front sent. points to rear sent.
		}
		ptr = null;
		return;
	}
	
	
	public void insert(K key, V value)
	{
		Node ptr = header.next;		// Pointer to the first node in list
		boolean exists = false;		// boolean to determine whether key exists
		while (ptr.next != null && !exists) {   // For each item ...
			if (ptr.key.equals(key)) {			// check for the existence of key
				ptr.value = value;				// if found replace the value
				exists = true;
			}
			ptr = ptr.next;						// advance to next item
 		}
		if(!exists) {					// If key is not already present
			Node temp = new Node();   	// Construct the new node.
			temp.key    = key;			// Assign key to the new node
			temp.value  = value;   		// Assign value to new node
			temp.prev   = header;		// Link to the front sentinel
			temp.next    = header.next; // Link to the rear sentinel/next node
			temp.next.prev  = temp;		// Link rear sent./next node to the new node
			temp.prev.next  = temp;   	// Link front sentinel to the new node
		}

	}
	
	
	public void remove(K key)
	{
		Node ptr = header.next;			// Pointer to the first node in the list
		
		while (ptr.next != null) {   		// For each item ...
			if (ptr.key.equals(key)) {		// If the key is present
				ptr.next.prev = ptr.prev;	// Remove that node
				ptr.prev.next = ptr.next;	// Link prev./next nodes to each-other
			} 
			ptr = ptr.next;
 		}
	}
	
	
	public V getValue(K key)
	{
		Node ptr = header.next;
		
		while (ptr.next != null) {   	// For each item ...
			if (ptr.key.equals(key)) {	// If key is present in that node
				return ptr.value;		// return the value for that node
			} 
			ptr = ptr.next;
 		}
		
		return null;	// Otherwise return null
	}
	
	public boolean isEmpty()
	{
		Node ptr = header.next;	// Pointer to the first node or rear sentinel
		if(ptr.next != null) {  // if the first item in the list is not
			return false;		// the rear sentinel node, then the list is not
		}						// empty; return false
		
		return true;			// otherwise return true
	}
	
	/*
	 *   toString() - return a String representation
	 *                of the map.
	 */
	
	public String toString()
	{
		String str = "The Map\n--------------\n";
		
		Node ptr = header.next;
		
		while (ptr.next != null) {   // For each item ...
			str = str + "key: ";              // use the toString()
		    str = str + ptr.key.toString();   // method for each key
		    str = str + "   ";                // and value to append
			str = str + "value: ";            // its String  
			str = str + ptr.value.toString(); // representation to
			str = str + "\n";                 // the Map's String
			ptr = ptr.next;                   // representation.
		}
		
		str = str + "--------------\n";
		
		return str;
	}
	
	/*
	 *    Inner Class - Node objects for Map items
	 *                  in a doubly linked list.
	 *    
	 */

	private class Node
	{
		public K key;       // The item's key.
		public V value;     // The item's value.
		public Node prev;   // Pointer to the previous Node.
		public Node next;   // Pointer to the next node.
	}

}
