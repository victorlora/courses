
/**
 * Write a description of class PigLatinOpish here.
 * 
 * @author Victor Lora & James Kmetz
 * @version 1 October 2014
 * 
 * estimated time: 180 min
 *    actual time: 240 min
 * expected grade: 10/10
 *
 * NOTES: Created an infinite, but convenient, loop; however, it would be awesome to be
 * able to end the loop with user input (perhaps the word "done"). Feedback on that would
 * be appreciated. Also, empty strings return null, but when running the program through
 * the main fuction inputing (" ") returns (" "ay) or (" "op) because it thinks the quotation
 * marks are part of the input. I tried an if statement saying that if the return is null, then
 * an error should be printed, but this didn't seem to work. Anyway, these were the only two noted
 * issues.
 *
 */
import java.util.Scanner;
public class PigLatinOpish
{
    //*************************************************************************static keyboard field
    public static final Scanner keyboard = new Scanner(System.in);

    //***************************************************************************************methods
    /**
     * Prompts user to input a word and prompts for the language to
     * which the user would like the word translated.
     * 
     * @param  ---
     * 
     * @return ---
     */
    public static void main(String [] args)
    {
        boolean run = true;
        while (run){
            System.out.println("Enter a word to translate");
            String word = keyboard.nextLine();
            word.toLowerCase(); // converts input to lower-case

            System.out.println("Would you like to translate" + 
                "your word to Pig Latin or Opish?");
            String procedure = keyboard.nextLine();
            procedure = procedure.toLowerCase(); // converts input to lower-case

            /**
             * If statement that decides whether to call toPigLatin() or toOpish() 
             * depending on user's input (allows for some spelling variation). 
             * Also passes the word entered to either procedure
             */
            if(procedure.equals("pig latin") || 
            procedure.equals("piglatin")){
                String inPigLatin = toPigLatin(word);

                System.out.println("Your word in pig latin is: " + inPigLatin);
            }
            else if(procedure.equals("opish")){
                String inOpish = toOpish(word);

                System.out.println("Your word in Opish is: " + inOpish);
            }
            System.out.println(""); // prints a blank line
        } // end while loop
    } // end main() method

    /**
     * Converts English words to Pig Latin
     * 
     * @param  word                      user input
     * 
     * @return translatedWord            translated word in Pig Latin
     * 
     */
    public static String toPigLatin(String word){
        final String vowels = "aeiouAEIOU";

        String translatedWord = "";
        String beforeVowel = "";

        int i = 0;

        // returns null for empty strings.
        if (word == ""|| word == " ") {return null;}

        // The following loop checks the char at position 
        // i in word and compares them to the list
        // of vowels. If the char isn't in vowels then it 
        // adds it to the String beforeVowel.
        while (i < word.length() && !vowels.contains("" + word.charAt(i))){
            beforeVowel += word.charAt(i);
            i++;
        } // end while loop
        // pieces together the translated word.
        translatedWord = word.substring(i) + beforeVowel + "ay";

        return translatedWord;
    }// end toPigLatin() method

    /**
     * Converts English words to Pig Latin
     * 
     * @param  word                      user input
     * 
     * @return translatedWord            translated word in Opish
     * 
     */
    public static String toOpish(String word)
    {
        final String vowels = "aeiouAEIOU";

        String word1 = "";
        String translatedWord = "";

        int lastIndex = word.length()-1; // gets the index of the last char in a word
        int i = 0;

        if (word == "" || word == " ") {return null;} 
        while (i < word.length()){
            // Adds the chars at position i to word1 if they are consonants
            if(!vowels.contains("" + word.charAt(i)))
            {
                word1 += word.charAt(i);
            }

            // Adds "op" to word1 when the char at i is a vowel, 
            // the first letter isnt a vowel,
            // and the char before it is a consonant.
            if(vowels.contains("" + word.charAt(i)) && 
               !vowels.contains("" + word.charAt(0)) &&
               !vowels.contains("" + word.charAt(i-1))){
                word1 +="op";
            } 

            // Adds vowels to word1
            if(vowels.contains("" + word.charAt(i))){
                word1 += word.charAt(i);
            } 

            // Adds vowels to the end of word1 only if word ends 
            // with a consonant.
            if(!vowels.contains(""+ word.charAt(lastIndex))){
                translatedWord = word1 + "op";
            }
            else{ translatedWord = word1;
            }

            i++;
        } // end while loop

        return translatedWord;
    } // end toOpish() method
} // end class
