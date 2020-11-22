
/**
 * Write a description of class FirstProgram here.
 * 
 * @author Victor Lora) 
 * @version (date submitted)
 * 
 * estimated time: 10 min
 *    actual time: 5 min
 * expected grade: 10 /10
 *
 * NOTES: Copy the first sample java program provided on a handout, 
 *          and make sure it compiles and runs correctly.
 *
 */

//import Scanner class so it can be used below
import java.util.Scanner;
public class FirstProgram{
    /**
     * Prints some text and adds some numbers.
     */
    public void printAndAdd() {
        System.out.println("Hello out there.");
        System.out.println("I will add two numbers for you.");
        System.out.println("Enter two whole numbers on a line:");
        
        int n1, n2;
        //Scanner detects inputs from users
        Scanner keyboard = new Scanner(System.in);
        //nextInt() requires the inputs entered to be specifically whole numbers
        n1 = keyboard.nextInt();
        n2 = keyboard.nextInt();
        
        /**
        * Prints some text and displays the sum of two numbers.
        */
        System.out.println("The sum of the two numbers is");
        System.out.println(n1 + n2);
    } // end main () method
    
} // end class