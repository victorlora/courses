
/**
 * Creates the classic game hangman.
 * 
 * @author  Victor Lora & James Kmetz
 * @version 14 October 2014
 * 
 * estimated time: 240 min
 *    actual time: 300 min
 * expected grade: 10/10
 *
 * NOTES: N/a
 *
 */

import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import org.kussmaul.simplegraphics.SimpleGraphics;
import org.kussmaul.simplegraphics.item.SimpleItem;
public class Hangman
{
    //**************************************************************************************** field
    public static SimpleGraphics            sg;
    public static SimpleItem        inputChars;
    public static SimpleItem              head;
    public static SimpleItem              body;
    public static SimpleItem              armL;
    public static SimpleItem              armR;
    public static SimpleItem              legL;
    public static SimpleItem              legR;
    public static SimpleItem       gallowLine1;
    public static SimpleItem       gallowLine2;
    public static SimpleItem       gallowLine3;
    public static SimpleItem       gallowLine4;

    ////********************************************************************** static keyboard field
    public static final Scanner keyboard = new Scanner(System.in);

    //************************************************************************************** methods
    /**
     * Selects a random word from the array of Strings
     * 
     * params   ---
     * 
     * returns  hangManWord
     * 
     */
    private static String selectWord()
    {
        String [] words = {"Elephant","Dog","Airplane","Computer", "Backpack", "Trumbower", "Carpet",
                "Cappuccino", "Electricity", "Festival", "Floodlight", "Gemstone", "Library", 
                "Microscope", "Parachute"}; // array of words

        Random rand = new Random();
        int i       = rand.nextInt(words.length);

        String hangManWord = words[i];                      // selects a random word from the array
        hangManWord        = hangManWord.toLowerCase();     // converts it to lowercase

        return hangManWord;                 
    } // end selectWord() method

    /**
     * Starts the hangman game.
     * 
     * params   ---
     * 
     * returns  ---
     */
    public static void Game()
    {
        sg = new SimpleGraphics (400, 400, "Hangman", true);    // draws window
        gallowLine1 = sg.drawLine(125, 100, 125, 50);
        gallowLine2 = sg.drawLine(125,  50, 300, 50);
        gallowLine3 = sg.drawLine(300,  50, 300, 300);
        gallowLine4 = sg.drawLine(400, 300, 200, 300);
        
        String gameWord    = selectWord();      // selects a random word.
        int gameWordLength = gameWord.length(); // gets length of the selected word

        String guessWord      = "";
        String alreadyGuessed = "";
        String wrongGuess     = "";

        for (int i=0; i<gameWordLength; i++){
            guessWord += "_"; // Creates a string of empty lines
        }
        char[] guessWordChars = guessWord.toCharArray(); // converts the guessWord to an array

        inputChars = sg.drawString("Arial",Font.PLAIN, 20 ,100, 30, guessWord);    
        // draws empty line on the window

        System.out.println("");
        System.out.println("Welcome to hangman, your word is "+ gameWordLength + " characters long");

        char guessedCharacter = requestInput(); // requests user input
        guessedCharacter = Character.toLowerCase(guessedCharacter); // converts user input to LC

        boolean complete  = false;  
        int wrongAttempt  =     0;

        // checks to see if user input is correct, then continues to request further letter
        // inputs until the word is complete.
        while (!complete){
            boolean correctGuess = true;

            // checks to see if the character entered is in the word and replaces blank lines with
            // the character entered at the appropriate location
            for (int j=0;j<gameWordLength;j++){
                if (guessedCharacter==(gameWord.charAt(j))){
                    guessWordChars[j]  = guessedCharacter;
                    alreadyGuessed    += guessedCharacter;
                    correctGuess       = true;
                }
                guessWord = String.valueOf(guessWordChars); // converts array back String
            }

            if (gameWord.contains(Character.toString(guessedCharacter))){
                correctGuess = true;
            }
            else if (!wrongGuess.contains(Character.toString(guessedCharacter))
                  && !alreadyGuessed.contains(Character.toString(guessedCharacter))){
                correctGuess  = false;

                wrongAttempt +=     1;
                wrongGuess = wrongGuess + guessedCharacter + " ";

                System.out.println("");
                System.out.println("Wrong attempt please try again.");
                System.out.println("So far you've guessed the following "+
                    "wrong characters: " + wrongGuess);
            }

            // calls draw() if the guess is incorrect
            if (!correctGuess){
                draw(wrongAttempt);
            }

            if (wrongAttempt >= 6){
                System.out.println("");
                System.out.println("You have run out of attempts");
                System.out.println("The correct word was: " + gameWord);
                break;
            }

            inputChars.remove();
            inputChars = sg.drawString("Arial",Font.PLAIN, 20 ,100, 30, guessWord);

            if (guessWord.equals(gameWord)){complete=true; break;}

            System.out.println("");
            guessedCharacter = requestInput();
            guessedCharacter = Character.toLowerCase(guessedCharacter);
            guessWordChars   = guessWord.toCharArray();

            if (alreadyGuessed.contains(Character.toString(guessedCharacter))){
                System.out.println("You've already guessed that letter. Try another");
            }
            else if(wrongGuess.contains(Character.toString(guessedCharacter))){
                System.out.println("You've already guessed that letter. Try another");
            }

        }
        
        if (complete){
            System.out.println("You win!");
        }
        System.out.println("");
        System.out.println("Game Over!");
    }// end Game() method

    /**
     * Prints statement requesting user input and then
     * returns their input
     * 
     * params   ---
     * 
     * returns  guessedCharacter            valid user input
     * 
     */
    private static char requestInput()
    {
        System.out.println("Enter a letter:");              // request user input

        char guessedCharacter = keyboard.next().charAt(0);
        // character input from user

        while (!characterOK(guessedCharacter)){
            System.out.println("");
            System.out.println("That is an invalid entry.");
            System.out.println("Enter a valid letter: ");

            guessedCharacter = keyboard.next().charAt(0);
        }

        return guessedCharacter;
    } // requestInput() method

    /**
     * Checks to make sure that the users input is valid
     * 
     * params   guessedCharacter            user input
     * 
     * returns  boolean                     true  - if input is valid
     *                                      false - if input is invalid
     * 
     */
    public static boolean characterOK(char guessedCharacter)
    {
        String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

        if (alphabet.contains(Character.toString(guessedCharacter))){
            return true;
        }else{
            return false;
        }
    } // characterOK() method

    /**
     * Draws the hangman body parts everytime the user guesses
     * incorrectly.
     * 
     * params   attempts            number of wrong attempts by user
     * 
     * returns  SimpleItem          draw commands for the different body parts
     */
    private static SimpleItem draw(int attempts)
    {  
        switch (attempts)
        {
            case 1: // draws head            
            return head = sg.drawOval(100, 100, 50, 50);

            case 2: // draws body
            return body = sg.drawLine(125, 150, 125, 200);

            case 3: // draws left arm
            return armL = sg.drawLine(125, 165, 95, 185);

            case 4: // draws right arm
            return armR = sg.drawLine(125, 165, 155, 185);

            case 5: // draws left leg
            return legL = sg.drawLine(125, 200, 95, 250);

            case 6: // draws right leg
            return legR = sg.drawLine(125, 200, 155, 250);

            default:
            System.out.println("Fatal Error"); System.exit(0);
            return null;
        } // switch()
    } // end draw() method
} // end class
