/*
 *   MapInterface <K, V>
 *   
 *   A generic interface for the implementation of a map.
 *   K is the type of the Key, V is the Type of the Value.
 */

public interface MapInterface <K, V> {
		
	public int getSize();     // Number of keys currently stored.
    public void makeEmpty();  // Remove all items from the Map.
    
                                         // Add a key and a value;
    public void insert(K key, V Value);  // if key already present, 
                                         // override value.
    
	public void remove(K key);  // Remove key and its value if found,
	                            // otherwise do nothing.
	
	public V getValue(K key);   // Return the value of the item with
	                            // the given key, else return null.
	
	public boolean isEmpty();  // Return true if empty.
	
	public String toString();    // Create a printable display
	                             // of the key, value pairs.
}
