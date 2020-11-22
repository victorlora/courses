/*
 * CSI 220: Data Structures & Algorithms.
 * @version 1.0 15 Oct 2015
 * @author Jacob Shelor; Victor Lora
 * @description
 *   class Stack Q
 *   
 *   An implementation of the StackInterface using Queues.
 */
public class StackQ <T> implements StackInterface<T> {
	QueueCirc<T> q1 = new QueueCirc<T>();
	QueueCirc<T> q2 = new QueueCirc<T>();

	
	/*
	 *   push(item) - add item to the stack.  It is at
	 *                the front of the list.
	 */
	public void push(T item)
	{
		if (q1.isEmpty())	// if q1 is empty
			q1.enqueue(item);	// push the first item onto the queue
		else {				// else
			while (!q1.isEmpty() && !q1.isFull()) // while q1 is not empty
				q2.enqueue(q1.dequeue());	// move all items to q2
			q1.enqueue(item);				// push new item to q1
			while (!q2.isEmpty() && !q2.isFull()) 	// move items back from q2 to q1 to
				q1.enqueue(q2.dequeue());			// preserve the correct stack order
		}
		
	}                                     
	
	
	/*
	 *   pop() - remove the top item from the stack and
	 *           return it as the function value.
	 */
	public T pop()
	{
		return q1.dequeue();
	}
	
	/*
	 *   top() - return the top stack item.
	 */
	public T top()
	{
		return q1.front();
	}
	
	/*
	 *    isEmpty() - return True if the stack is empty
	 *                otherwise return False.
	 */
	public boolean isEmpty()
	{
		if (q1.isEmpty())
			return true;
		else
			return false;
	}
	
	
	/*
	 *    isFull() - return True if the stack is full
	 *                otherwise return False.
	 */
	public boolean isFull()
	{
		if (q1.isFull())
			return true;
		else
			return false;
	}
	
	/*
	 *    toString() - returns Stack as a string.
	 *                
	 */
	public String toString()
	{
		String str = "The Stack\n---------\n";
		
		while (!q1.isEmpty()) {		// while q1 is not empty
			T temp = q1.dequeue();	// dequeue every item in q1
			str = str + temp + "\n";	// append it to str
			q2.enqueue(temp);		// enque it in q2
		}
		
		str = str + "---------\n";
		
		while (!q2.isEmpty()) {		// while q2 is not empty
			q1.enqueue(q2.dequeue()); // reverse the above
		}							
		// it may be better to simply switch the two stacks 
		//for instance: temp = stk1, stk1 = stk2, stk2 = temp
		
		return str;
	}
		
	
}
