
/**
 * FileStub basically builds the typical code structure that is used
 * in this class. It requests certain inputs from the user in order to create an 
 * accurate structure.
 * 
 * @author Victor Lora 
 * @version 2014 September 3
 * 
 * estimated time: 45 min
 *    actual time: 90 min
 * expected grade: 10 /10
 *
 * NOTES: Design, code, debug, and test a program that asks the user a series of questions,
 *        and then displays the following items on the screen, correctly formatted:
 *              -initial comment with file name, author name,
 *               estimated & actual time, and notes
 *              -class declaration, including closing comment
 *              -main method declaration, including initial comment
 *               and closing comment
 *
 */

// import Scanner class so it can be used below
import java.util.Scanner;

/**
 * FileStub class requests user to input some data and outputs the basic
 * structure of a code.
 */
public class FileStub
{
    // Scanner detects inputs from users
    public static final Scanner keyboard = new Scanner(System.in);
    /**
     * gets class name from user & returns it to the
     * calling method
     * @param   none
     * @return  class name
     */
    public static String getClassName() {
        // prints some text on screen
        System.out.println("Enter the class name: ");
         
        // nextLine() detects the next line typed by the user
        // and assigns it to className
        String className = keyboard.nextLine();
        return className; // returns className    
    } // end getClassName()
    
    /**
     * gets & returns the user's name
     * @param   none
     * @return  userName
     */
    public static String getUserName(){
        // prints some text on screen
        System.out.println("Enter user's name: ");
        
        // nextLine() detects the next line typed by the user
        // and assigns it to userName
        String userName = keyboard.nextLine();
        return userName; //returns userName     
    } // end getUserName() method
    
    /**
     * gets & returns the estimated time
     * @param   none
     * @return  approxTime time in minutes
     */
    public static int getEstimatedTime(){
        // prints some text on screen
        System.out.println("Enter estimated time (in minutes)");
        
        // nextInt() assigns the next interger input to approxTime
        int approxTime = keyboard.nextInt();
        
        return approxTime; // returns approxTime
    } // end getEstimatedTime() method
    
    /**
     * prints the class comments
     * @param   String className
     * @return  none
     */
    public static void printClassComment(String className) {
        // prints some text and assigns user's input to className
        // with user's inputted class name
        System.out.println("/**");
        System.out.println("*Write a description of " + className + " here");
        System.out.println("*");
        System.out.println("*/");
    } // end printClassComment() method
    
    /**
     * prints the class declaration, and then calls
     * printMainMethodComment() and printMainMethod()
     * @param   String className
     * @return  none
     */
    public static void printClass(String className) {
        // prints the basic structure of class declaration and
        // replaces className with user's inputted class name
        System.out.println("public void " + className + " {");
        System.out.println(""); // prints blank lines for clearer structure
        
        // calls printMainMethodComment() method
        printMainMethodComment();
        // calls printMainMethod() method
        printMainMethod();
        
        // prints closing comments
        System.out.println("} // end class");
        System.out.println("");
    } // end printClass() method
    
    public static void printMainMethodComment() {
        // prints standard Main method comments
        System.out.println("    /**");
        System.out.println("    *Performs main action for this class.");
        System.out.println("    *(Called automatically by Java.)");
        System.out.println("    *");
        System.out.println("    */");
    } // end printMainMethodComment()
    
    public static void printMainMethod(){
        // prints standard Main method structure
        System.out.println("    public static void main(String [] args) {");
        System.out.println("");
        System.out.println("        // put your code here!!!");
        System.out.println("");
        System.out.println("    } // end main() method"); // end main() method comments
        System.out.println("");
    }// end printMainMethodComment()
    
    /**
     * Performs main action for this class.
     * (Called automatically by Java.)
     */
    public static void main(String[] args){
        // assigns user's inputs to the variable
        // by calling the different methods that request user's 
        // input
        String className = getClassName();
        String userName = getUserName();
        int approxTime = getEstimatedTime();
        
        // prints starting comment structure found at the beginning
        // of this program, replacing certain variables with user's
        // inputs
        System.out.println("");
        System.out.println("/**");
        System.out.println("*");
        System.out.print("*Write a description of class " + className);
        System.out.println(" here");
        System.out.println("*");
        System.out.println("*@author " + userName);
        System.out.println("*");
        System.out.println("*@version (date submitted) ");
        System.out.println("*");
        System.out.println("*estimated time: " + approxTime + " minute(s)");
        System.out.println("*");
        System.out.println("*NOTES: ");
        System.out.println("*");
        System.out.println("*/");
        System.out.println("");
        
        // calls printClassComment() method
        // which prints initial class comments
        printClassComment(className);
        printClass(className); // calls printClass() method, which calls
                               // two other methods
    } // end main() method
  
} // end class
