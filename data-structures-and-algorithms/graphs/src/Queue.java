

public class Queue<T> implements QueueInterface<T> 
{

	Node front = null;
	Node rear = null;
	int count = 0;
	
	public Queue() {
		count = 0;
	}
	
	public boolean isEmpty()
	{
		return front == null;
	}
	
	public boolean isFull()
	{
		return false;
	}
	
	public void enqueue(T val)
	{
		Node n = new Node();
		n.data = val;
		n.next = null;
		
		if (front == null)
			front = n;
		else
			rear.next = n;
		
		rear = n;
		count++;	
	}
	
	public T dequeue()
	{
		if (count == 0)
			return null;
		
		count--;
		T val = front.data;
		front = front.next;
		return val;
	}
	
	public T front()
	{
		if (count == 0)
			return null;
		
		return front.data;
	}
	
	public String toString()
	{
		Node ptr = front;
		String str = "The Queue\n---------\n";
		
		while (ptr != null) {
			str = str + ptr.data.toString() + "\n";
			ptr = ptr.next;
		}
		
		str = str + "---------\n";
		return str;
	}
		
	
	class Node
	{
		T data;
		Node next;
	}
	
	
}
