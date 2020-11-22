/*
 *    IntegerNode is a class that 
 *    gives smaller integers higher priority.
 *    
 *    It does this by implementing the Comparable interface
 *    to treat lower numbers as higher in priority value.
 */


public class IntegerNode implements Comparable<IntegerNode>
{
    
    Integer i;  // The integer stored in the node.
    
    public IntegerNode(Integer x)      // The constructor initializes
    {                                  // the instance variable i.
        this.i = x;
    }

    /*
     * compareTo() compares this objects integer to
     *             another (rhs - right hand side) 
     *             objects integer value.
     *
     *             If this node has a smaller integer value
     *             it has higher priority and in this sense
     *             is "bigger" than the rhs object and compareTo()
     *             returns +1.
     */         

    public int compareTo(IntegerNode rhs)
    {
        if (this.i > rhs.i)        // This object has lower priority
            return -1;             // ... so return -1.
        else if (this.i < rhs.i)   // This object has higher priority
            return 1;              // ... so return +1.
        else                       // The objects have equal priority
            return 0;              // ... so return 0.
    }

    
    public String toString()
    {                          // The object's string representation
        return i.toString();   // is just that of its integer.
    }
}
