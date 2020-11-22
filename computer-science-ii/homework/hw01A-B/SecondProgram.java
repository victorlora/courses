
/**
 * Write a description of class SecondProgram here.
 * 
 * @author Victor Lora) 
 * @version (date submitted)
 * 
 * estimated time: 10 min
 *    actual time: 5 min
 * expected grade: 10 /10
 *
 * NOTES: Modify the first program so that it adds three numbers instead of two.
 *
 */
//import Scanner class so it can be used below
import java.util.Scanner;
public class SecondProgram{
    /**
     * Prints some text and adds some numbers.
     */
    public void printAndAdd() {
        System.out.println("Hello out there.");
        System.out.println("I will add three numbers for you.");
        System.out.println("Enter three whole numbers on a line:");
        
        int n1, n2, n3; //assigns interger values to n1, n2, and n3
       
        //Scanner detects inputs from users
        Scanner keyboard = new Scanner(System.in);
        //nextInt() requires the inputs entered to be specifically whole numbers
        n1 = keyboard.nextInt();
        n2 = keyboard.nextInt();
        n3 = keyboard.nextInt();
        
        /**
        * Prints some text and displays the sum of the three numbers.
        */
        System.out.println("The sum of the three numbers is");
        System.out.println(n1 + n2 + n3);
    } // end main () method
    
} // end class