/*
 * CSI 220: Data Structures & Algorithms.
 * @version 1.0 15 Oct 2015
 * @author Jacob Shelor; Victor Lora
 * @description
 *   class QueueCirc
 *   
 *   A Circular Implementation of the generic QueueInterface.
 */

public class QueueCirc<T> implements QueueInterface<T>
{
	private int front = 0;   // Index of the front item in the queue.
	private int rear = -1;   // Index of the rear item in the queue.
	
	T[] A = (T[]) new Object[20];  // Generic arrays cannot be constructed in Java.
	                               // A solution is to create an array of Object
	                               // and type cast as an array of a generic type.
	                               // Ignore any warnings the compiler generates.
	/*
	 *    The Queue constructor.
	 */
	public QueueCirc()
	{
		return;
	}

	/*
	 *    enqueue() - add an item to the rear of the queue.
	 *                Do nothing if the queue is full.
	 */
	public void enqueue(T item)
	{
		if (!isFull()) {	// if A is not full
			rear = (rear+1) % A.length; // add 1 to rear (making sure to circle around)
			A[rear] = item;	// add item at A[rear]
		}
	}
	
    /*
     *   dequeue() - delete and return the front item
     *               in the queue. Return null if 
     *               the queue is empty.
     */
	public T dequeue()
	{
		T temp;
		if (!isEmpty()) {	// if A is not empty
			temp = A[front];	// store A[front] in temp
			A[front] = null;	// set A[front] to null
			front = (front + 1) % A.length;	// add 1 to front (making sure to circle around)
			return temp;	// return temp
		} else {	// else
			return null;	// return null
		}
	}
	
	/*
	 *    front() - Return (but do not delete) the item
	 *              at the front of the queue.
	 */
    public T front()
    {
    	if (!isEmpty())	// if A is not empty
    		return A[front];	// return A[front]
    	else			// else
    		return null;		// return null
    }
    
    /*
     *    isEmpty() - returns true if the queue is empty 
     *                else returns false.
     */
    public boolean isEmpty()
    {
    	int temp = rear;
    	temp = (temp + 1) % A.length; 
    	return (temp == front);
    }
    
    /*
     *    isFull() - returns true if the queue is empty 
     *               else returns false.
     */
    public boolean isFull()
    {
    	int temp = rear;
    	temp = (temp + 1) % A.length; 
    	temp = (temp + 1) % A.length; 
    	return (temp == front);
    }
    
    /*
     *  toString() - return a printable display of
     *               the items in the queue.
     */
    public String toString()
    {
    	int temp = front;	// store front in temp
    	String str = "";
    	while (temp != rear+1) {	// while not at the end
    		str += A[temp] + "\n";	// add the item to str
    		temp = (temp +1) % A.length; // add 1 to temp (making sure to circle around)
    	}
    	return str;	// return str
    }
}
