
/**
 * This class, CircumferenceProgram, takes the users radius input and outputs the circles
 * circumeference.
 * 
 * @author Victor Lora) 
 * @version (date submitted)
 * 
 * estimated time: 25 min
 *    actual time: 15 min
 * expected grade: 10 /10
 *
 * NOTES: Write a complete Java program that will ask the user for the radius of a circle, 
 * and print the circumference (equal to 2 * radius * pi). 
 * You can use the Java constant Math.PI or you can use an approximation, such as 3.14159.
 *
 */

//import Scanner class so it can be used below
import java.util.Scanner;
public class CircumferenceProgram{
    /**
     * Prints some text and request a radius input.
     */
    public void printAndAdd() {
        System.out.println("Hello out there.");
        System.out.println("I will calculate the circumference of a circle for you.");
        System.out.println("Enter the radius of your circle on a line:");
        
        //assigns a floating value to the variable r1
        float r1;
        
        //Scanner detects inputs from users
        Scanner keyboard = new Scanner(System.in);
        
        /**nextFloat() requires the inputs entered to be specifically a number
        however the number entered doesn't have to be a whole number.
        If the input is not a number, the program will return an error.
        */
        r1 = keyboard.nextFloat();
                
        /**
        * Prints some text and outputs the circumfrence of your circle.
        */
        System.out.println("The circumference of your circle is");
        
        //Prints the results only to the second decimal place
        System.out.printf("%.2f", r1 * 2 * Math.PI);
    } // end main () method
    
} // end class