/*
 * CSI 220: Data Structures & Algorithms.
 * @version 1.0 15 Oct 2015
 * @author Jacob Shelor; Victor Lora
 * @description
 *   class QueueS
 *   
 *   An implementation of the QueueInterface using stacks.
 */

public class QueueS<T> implements QueueInterface<T>
{
	
	Stack<T> stk1 = new Stack<T>(); 
	Stack<T> stk2 = new Stack<T>();
	
	/*
	 *    The Queue constructor.
	 */
	public QueueS()
	{
		return;
	}

	/*
	 *    enqueue() - add an item to the rear of the queue.
	 *                Do nothing if the queue is full.
	 */
	public void enqueue(T item)
	{
		if (stk1.isEmpty()) // if stk1 is empty
			stk1.push(item);		// push the first item onto stk1
		else {				// else
			while(!stk1.isEmpty()) 	// while stk1 is not empty
				stk2.push(stk1.pop());	// move all items to stk2
			stk1.push(item);		// push the new item onto stk1
			while(!stk2.isEmpty())	// move all items back from stk2 to stk1
				stk1.push(stk2.pop());	// to preserve the correct stack order
		}
	}
	
    /*
     *   dequeue() - delete and return the front item
     *               in the queue. Return null if 
     *               the queue is empty.
     */
	public T dequeue()
	{
		if (!stk1.isEmpty()) 
			return stk1.pop();
		else
			return null;
	}
	
	/*
	 *    front() - Return (but do not delete) the item
	 *              at the front of the queue.
	 */
    public T front()
    {
    	return stk1.top();
    }
    
    /*
     *    isEmpty() - returns true if the queue is empty 
     *                else returns false.
     */
    public boolean isEmpty()
    {
    	if (stk1.isEmpty())
    		return true;
    	else
    		return false;
    }
    
    /*
     *    isFull() - returns true if the queue is empty 
     *               else returns false.
     */
    public boolean isFull()
    {
    	return false;
    }
    
    /*
     *  toString() - return a printable display of
     *               the items in the queue.
     */
    public String toString()
    {
    	return stk1.toString();
    }
}
