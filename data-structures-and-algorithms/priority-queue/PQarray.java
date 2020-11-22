/*
 *    PQarray - this class implements a generic priority queue
 *              using an array.
 *
 *              In this implementation, the front item in the
 *              queue is anchored in the array element with index 0.
 *              Thus, the first element in the array is always the
 *              item at the front of the queue.
 *
 *              Note: The dequeue operation is O(n) since all items
 *                    must shift to compensate for the front item
 *                    leaving the array.  A circular implementation
 *                    would result in a O(1) dequeue.
 */

public class PQarray <T extends Comparable<T>>
                        implements PQInterface<T>
{
	@SuppressWarnings("unchecked")
    private T[] A = (T[]) new Comparable[100];
    private int rear = -1;                  // Index of the rear of the queue.

    public PQarray()
    {
    	return;  // Don't really need a constructor.
    }

    /* enqueue() - add an item to the rear of the queue, advancing ahead
     *             of items with strictly lower priority.
     */

    public void enqueue(T t)
    {
    	int i = 0;  // Search index into the array A - used to find the
    	            // insertion point of the new item.

    	// We will actually start at the front of the queue and search
    	// down until we find an item of lower priority than our new item.
    	// This will be the insertion point for the new item.

    	while ( i <= rear  &&           // while (still in the queue and
    		    t.compareTo(A[i]) <= 0) //        new item has <= priority
    		i = i + 1;                  //        to the current item)
    	                                //      move down queue to find
    	                                //      insertion point.

    	// Having found the insertion point - shift the queue entries with
    	// lower priority to the right to make room for the new item.

    	for (int j = rear; j >= i; j--)
    		A[j+1] = A[j];

    	A[i] = t;   // Enter the new item into the queue.

    	rear = rear + 1;  // Update the location of the rear queue item.
    }

    /*
     *   isEmpty - return whether or not there are any items
     *             in the queue.
     */

    public boolean isEmpty()
    {
    	return rear == -1;
    }

    /*
     *   isFull - return whether or not there is
     *            room in the queue for new items.
     */

    public boolean isFull()
    {
    	return rear == A.length -1;
    }

    /*
     *  front() - return the front item in the queue, do
     *            not remove it from the queue.
     *
     *            If the queue is empty, return null.
     */

    public T front()
    {
    	if (rear == -1)
    		return null;

    	return A[0];  // The front item is always in A[0]
    }


    /*
     *  dequeue() - return the front item in the queue,
     *              and remove it from the queue.
     *
     *            If the queue is empty, return null.
     */

    public T dequeue()
    {
    	T item = A[0];  // Collect the item at the front of the queue.

    	if (rear == -1)    // Check for an empty queue.
    		return null;

    	// Shift all the remaining queue items to the left to
    	// maintain the implementation requirement that the front
    	// item always be in A[0];

    	for (int i = 1; i <= rear; i++)
    		A[i-1] = A[i];

    	A[rear] = null;   // Get rid of the double reference to the rear item.
    	rear = rear - 1;  // Update the index of the rear item.

    	return item;  // Return the dequeued front item.
    }

    public String toString()
    {
    	String str = "PQarray -front-------\n";

    	// Traverse the array - append the queue items to
    	//                      the string to be returned.

    	for (int i = 0; i <= rear; i++)
    		str = str + A[i].toString() + "\n";

    	str = str + "---------rear------\n";
    	return str;
    }

}