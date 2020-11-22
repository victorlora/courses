
/**
 * Stats takes an array and performs certain actions to it
 * such as get the avarage, the min/max, and the total.
 * 
 * @author Victor Lora
 * @version 09/17/14
 * 
 * estimated time: 120 min
 *    actual time: 180 min
 * expected grade: 10/10 
 *
 */


//import Scanner class so it can be used below
import java.util.Scanner;
public class Stats
{
    // creates a static variable for keyboard to be used below
    public static final Scanner keyboard = new Scanner(System.in);      

    //***INSTANCE FIELD
    /**
     * declares variable 'values' as an array with integers
     */
    private int [] array;

    //**************************************************************************************** methods

    /**
     * 
     * a constructor that takes no parameters, and calls setValues().
     * 
     */
    public Stats()
    {
        setValues();
    } // end Stats() constructor method

    /**
     * 
     * a constructor that takes an integer parameter, and calls setValues().
     * 
     * @params   int arraySize
     * 
     * @return  none
     */
    public Stats(int arraySize){
        setValues(arraySize);
    }// end Stats(int arraySize) constructor method

    /**
     * 
     * Constructor takes an array of integers as a parameter
     * 
     * @params   int [] array        An array called "array"
     * 
     * @return  none 
     * 
     */
    public Stats(int[] array)
    {
        setValues(array);
    }// end Stats(int[] array) constructor method

    /**
     * setValues() method with an array parameter that checks for errors and sets the field
     * 
     * @params   int [] array    array parameter
     * 
     * @return  none
     * 
     */
    public void setValues(int[] array)
    {
        if (array != null) {
            this.array = array;
        }
    } // end setValues() method

    /**
     * 
     * setValues() method with an integer parameter that creates an array of that size, 
     * and prompts the user to enter that number of values.
     * 
     * @params   int size       entered by the user
     * 
     * @return  none
     * 
     */
    public void setValues(int size)
    {
        int [] array = new int[size];
        for (int x = 1; x <= size; x++){
            System.out.println("Enter the "+ x + " number:");
            int assignment = keyboard.nextInt();
            array[x-1] = assignment;
        }
        setValues(array);
    }// end setValues() method

    /**
     * 
     * setValues() method with no parameters that prompts the user for the number of 
     * values, and then prompts the user to enter that number of values.
     * 
     * @params  none
     * 
     * @return  arraySize
     */
    public int setValues() 
    {
        System.out.println("Enter array size: ");
        int arraySize = keyboard.nextInt();
        if (arraySize > 0) {
            setValues(arraySize);
        }
        return arraySize;
    }// end setValues() method

    /**
     * getSize() method that returns the number of values in the array field.
     * 
     * @params   none
     * 
     * @return  this.values.length      returns the length of the array 
     *          -1                      returns -1 as an error
     *          
     */
    public int getSize()
    {
        if (this.array == null){
            return -1;
        }
        else{
            return this.array.length;
        }
    }// end getSize() method

    /**
     * toString() method uses a loop to return a String containing all of the integers
     * 
     * @params   none
     * 
     * @return  numbers   returns the integers of the array in a string numbers    
     * 
     */
    public String toString()
    {   
        String numbers = "";
        for (int i = 0; i < this.array.length; i++){
            numbers += this.array[i] + ",";
        }
        return numbers;
    }// end toString() method

    /**
     * 
     * getTotal() method uses a loop to return the sum of all of the values
     * 
     * @params  none
     * 
     * @return  sum     sum is the value of all the integers within the array
     *                  added together
     * 
     */
    public int getTotal()
    {
        int sum = 0;
        for (int i = 0; i < this.array.length; i++){
            sum = sum + this.array[i];
        }
        System.out.print(sum);
        return sum;
    }// end getTotal() method

    /**
     * 
     * getAverage() method that returns the average value 
     * (the sum of all of the values divided by the number of values)
     * 
     * @params   none
     * 
     * @return  average       average is the value of all the average of the array
     * 
     */
    public int getAverage()
    {
        int total   = getTotal();
        int average = total/this.array.length;
        return average;
    }// end getAverage() method

    /**
     * 
     * getMin() method uses a loop to return the smallest value in the array.
     * 
     * @params   none
     * 
     * @return   smallest       smallest is the value of the smallest number of the array
     * 
     */
    public int getMin()
    {
        int smallest = this.array[0];
        for(int i=1; i< this.array.length; i++)
        {
            if(this.array[i] < smallest)
            {
                smallest = this.array[i];   
            }
        }
        System.out.println("Smallest Number is : " + smallest);

        return smallest;
    }// end getMin() method

    /**
     * 
     * getMax() method uses a loop to return the largest value in the array.
     * 
     * @params   none
     * 
     * @return   biggest       biggest is the value of the smallest number of the array
     * 
     */
    public int getMax()
    {
        int biggest = this.array[0];
        for(int i=1; i< this.array.length; i++)
        {
            if(this.array[i] > biggest){
                biggest = this.array[i];
            }
        }
        System.out.println("Largest Number is : " + biggest);
        return biggest;
    }// end getMax() method

    /**
     * 
     * replace() method with two integer parameters that finds each occurrence of the first integer in the array, 
     * replaces it with the second integer, and returns the number of such replacements that occurred.
     * 
     * @param   int num 1       the number the method will search for in the array
     *          int num 2       the number the method will replace num1 with
     *          
     * @return  count           the amount of times the method replaced num1 with num2
     * 
     */
    public int replace(int old1, int new1)
    {
        int count = 0;
        for (int i=0; i<this.array.length; i++){
            if(this.array[i] == old1){
                this.array[i] = new1;
                count++;
            }
        }
        return count;
    } // end replace() method

    /**
     * gets the values for this.array for testing purposes
     */
    public int [] getValues()
    {
        return this.array;
    }// end getValues() method
} // end class
