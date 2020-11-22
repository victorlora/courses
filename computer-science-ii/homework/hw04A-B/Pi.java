
/**
 * Estimates Pi using different terms.
 * 
 * @author Victor Lora & James Kmetz
 * @version 14 October 2014
 * 
 * estimated time: 60 min
 *    actual time: 40 min
 * expected grade: 10/10
 *
 * NOTES: N/a
 *
 */

import java.text.DecimalFormat;
public class Pi
{
    //**************************************************************instance fields
    private double Pi;
    private int    n;

    //**********************************************************************methods

    /**
     * Approximates Pi to n terms.
     * 
     * @param   n                           number of terms
     * 
     * @return  ---
     */
    public void setPi(int n)
    {
        this.n = n; // assigns user's input to the instance n

        boolean add = true;
        double  sum  = 0;

        for (int k = 1; k <= 2*n-1; k+=2){
            // if statement that alternates between +/-(1/k) depending on 
            //the truth value of add
            if(add){
                sum += (1.0/k);
            }else{
                sum -= (1.0/k);
            }
            add = !add; // changes add to true or false after every loop
        }

        this.Pi = 4.0*sum; // assigns the approximation to the instance Pi
    } // end Pi constructor

    /**
     * Returns the value of Pi without parameters
     */
    public double getPi()
    {
        return this.Pi;
    }

    /**
     * Constructs an object that approximates Pi to n terms depending on user
     * input.
     * 
     * @param   n                           number of terms
     * 
     * @return  ---
     */
    //   public Pi(int terms)
    //   { setPi(terms); }

    /**
     * Returns the approximation of Pi as a string.
     * 
     * @param   ---                         
     * 
     * @return  result                      approximation of Pi in a sentence
     */
    public String toString()
    {
        DecimalFormat formattingObject = new DecimalFormat("##.###");
        String pi = formattingObject.format(Pi);
        String result = "pi approximated with " + n +" term(s) is " + pi + ".";
        return result; 
    } // end toString() method
} // end class
