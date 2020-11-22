
/**
 * MadLib creates a Mad Lib based on the different
 * user inputs.
 * 
 * @author Victor Lora 
 * @version 2014 September 3
 * 
 * estimated time: 45 min
 *    actual time: 30 min
 * expected grade: 10 /10
 *
 * NOTES: Design, code, debug, and test a program that asks the user to enter 
 * at least 5 words, and prints a madlib using those words. For each part of speech 
 * you need (at least 3), write a method that prompts the user, reads a String from 
 * the keyboard, and returns that String. As a result, your main method will contain code 
 * like this:
 *  -String noun1 = getNoun(); 
 *  -String noun2 = getNoun(); 
 *  -String verb1 = getVerb();
 */

// import Scanner class so it can be used below
import java.util.Scanner;

public class MadLib
{
    // Scanner detects inputs from users
    public static final Scanner keyboard = new Scanner(System.in);
    /**
     * requires user to input a noun and 
     * returns it to calling method
     * @param   none
     * @return  noun
     */
    public static String getNoun(){
        System.out.println("Enter a NOUN: ");   // prints user input request
        String noun = keyboard.nextLine();  // assigns next line entered to noun
        return noun;    // returns noun
    } // end getNoun() method
    
    /**
     * requires user to input a musical instrument and 
     * returns it to calling method
     * @param   none
     * @return  instrument
     */
    public static String getInstrument(){
        System.out.println("Enter a MUSICAL INSTRUMENT: "); // prints user input request
        String instrument = keyboard.nextLine();    // assigns next line entered to instrument
        return instrument;  // returns instrument
    } // end getInstrument() method
    
    /**
     * requires user to input an animal and 
     * returns it to calling method
     * @param   none
     * @return  animal
     */
    public static String getAnimal(){
        System.out.println("Enter an ANIMAL: ");    // prints user input request
        String animal = keyboard.nextLine();    // assigns next line entered to animal
        return animal;  // returns animal
    } // end getAnimal() method
    
    /**
     * requires user to input an adjective and 
     * returns it to calling method
     * @param   none
     * @return  adjective
     */
    public static String getAdjective(){
        System.out.println("Enter an ADJECTIVE: "); // prints user input request
        String adjective = keyboard.nextLine(); // assigns next line entered to adjective
        return adjective;   // returns adjective
    } // end getAdjective() method
    
    /**
     * requires user to input a silly word and 
     * returns it to calling method
     * @param   none
     * @return  sillyWord
     */
    public static String getSillyWord(){
        System.out.println("Enter a SILLY WORD: "); // prints user input request
        String sillyWord = keyboard.nextLine(); // assigns next line entered to sillyWord
        return sillyWord;   // return sillyWord
    } // end getSillyWord() method
    
    /**
     * Performs main action for this class.
     * (Called automatically by Java.)
     */
    public static void main(String [] args)
    {
      
      // calls the different methods and assigns the user's inputs to each
      // of the different variables
      String sillyWord1 = getSillyWord();
      System.out.println("");
      String Animal = getAnimal();
      System.out.println("");
      String Instrument = getInstrument();
      System.out.println("");
      String noun1 = getNoun();
      System.out.println("");
      String adjective = getAdjective();
      System.out.println("");
      String noun2 = getNoun();
      
      // prints out the MadLib using the different variables
      // entered by the user
      System.out.println("");
      System.out.println("**************************************");
      System.out.println("Hey, "+sillyWord1+", "+sillyWord1+"!");
      System.out.print("The "+Animal+" and ");
      System.out.println("the "+Instrument+",");
      System.out.println("The cow jumped over the "+noun1); 
      System.out.println("The "+adjective+" dog laughed");
      System.out.println("To see such sport,");
      System.out.println("And the "+noun2+" ran away with the spoon.");
      System.out.println("**************************************");

    } // end main() method
  
} // end class
