/*
 *    PQDemo - a program to test an implementation
 *             of a Priority Queue.
 */


public class PQDemo {

    public static void main(String[] args) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        PriorityQueue<String>  strPQ = new PriorityQueue<String>();
        PriorityQueue<IntegerNode> intPQ = new PriorityQueue<IntegerNode>();
        PriorityQueue<Student> sPQ = new PriorityQueue<Student>();
        
        pq.enqueue(6);     // enqueue some Integers into the queue pq.
        pq.enqueue(8);     //
        pq.enqueue(3);     // 10 should end up at the front of the queue
        pq.enqueue(7);     // (it is the highest value) and 1 should
        pq.enqueue(2);     // end up at the rear of the queue.
        pq.enqueue(10);
        pq.enqueue(1);
        pq.enqueue(5);
        
        System.out.println( pq.toString() ); // Display the queue to see if
                                             // Integers are in the correct
                                             // priority order.
                                             
        Integer i = pq.dequeue();    // Test removing an integer from the queue.
                                     // and display the resulting queue.
                                     
        System.out.println( pq.toString() );
        
        strPQ.enqueue("Washington");   // enqueue some strings - words will
        strPQ.enqueue("Adams");        //         be prioritized by
        strPQ.enqueue("Jefferson");    //         alphabetical order.
        strPQ.enqueue("Madison");      // i.e. lower in the aplhabet means
        strPQ.enqueue("Monroe");       //      lower in priority.
        
        System.out.println( strPQ.toString() );  // Washington should be at
                                                 // the front.
        
        intPQ.enqueue(new IntegerNode(6));   // enqueue some IntegerNodes.
        intPQ.enqueue(new IntegerNode(8));   //
        intPQ.enqueue(new IntegerNode(3));   // For IntegerNodes, smaller
        intPQ.enqueue(new IntegerNode(7));   // integers values have higher
        intPQ.enqueue(new IntegerNode(2));   // priority - so 1 should be at
        intPQ.enqueue(new IntegerNode(10));  // the front of the queue and 10
        intPQ.enqueue(new IntegerNode(1));   // at the rear of the queue.
        intPQ.enqueue(new IntegerNode(5));
        
        System.out.println( intPQ.toString() );  // Display the IntegerNode
        										// queue.

        sPQ.enqueue(new Student(2015, "Smith", "Joe")); // enqueue some Students
        sPQ.enqueue(new Student(2014, "Smith", "Joe"));	// sorted by lowest
        sPQ.enqueue(new Student(2015, "Smith", "Adam"));// class year, then by
        sPQ.enqueue(new Student(2015, "Smith", "Mike"));// last name alpha...
        sPQ.enqueue(new Student(2013, "Jackson", "Amy"));
        sPQ.enqueue(new Student(2018, "Matten", "Pamela"));
        sPQ.enqueue(new Student(2012, "Johnson", "Joe"));
        
        System.out.println(sPQ.toString());	// print queue
        
        sPQ.dequeue();	// test dequeueing a student
        
        System.out.println(sPQ.toString());
        
    }
    
}
