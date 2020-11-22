
/**
 * Write a description of class Triple here.
 * 
 * @author  Victor A. Lora 
 * @version November 2014
 */

// this class represents a set of 3 integers
public class Triple
{
    // instance field
    private int [] values;

    // creates a new Triple object using 3 integers
    // throws IllegalArgumentException if any value is non-positive
    public Triple (int a, int b, int c) {
        if (a<0 || b<0 || c<0) {
            throw new IllegalArgumentException("the number cannot be negative");
        }
        this.values = new int [] {a, b, c};
    }

    // creates a new Triple object using an array of 3 integers
    // thorws IllegalArgumentException
    // if the array is the wrong size or if any value is non-positive
    public Triple (int[] newValues)  {
        if (newValues.length > 3) {
            throw new IllegalArgumentException("Array cannot be longer than three integers");
        }
        for (int i=0; i < newValues.length; i++) {
            if (newValues[i]<0) {
                throw new IllegalArgumentException("No number in the array can be negative");
            }
        }
        this.values = newValues;
    }

    // returns true if this and that Triples contain the same values
    public boolean equals (Triple that) {
        if (this.values.equals(that.values)) {return true;
        }else{
            return false;
        }
    }

    // returns true if this Triple is a valid triangle
    // e.g. {3,3,3} is a valid triangle, {2,7,2} is not
    public boolean isTriangle() {
        if (this.values[0]+this.values[1] > this.values[2] && 
        this.values[1]+this.values[2] > this.values[0] &&
        this.values[0]+this.values[2] > this.values[1]) {
            return true;
        }
        return false;
    }

    // returns true if this Triple is a valid right triangle
    // (sum of squares of two size == square of third side)
    // e.g. {3,5,4} is a valid right triangle, {3,3,3} is not
    public boolean isRightTriangle() {
        if (this.values[0]*this.values[0]+this.values[1]*this.values[1] == this.values[2]*this.values[2] || 
        this.values[1]*this.values[1]+this.values[2]*this.values[2] == this.values[0]*this.values[0] ||
        this.values[0]*this.values[0]+this.values[2]*this.values[2] == this.values[1]*this.values[1]) {
            return true;
        }
        return false;
    }
}
