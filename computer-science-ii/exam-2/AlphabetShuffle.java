import java.util.*;
/**
 * Write a description of class AlphabetShuffle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlphabetShuffle
{
    //************************************************************
    //***Constant scanner
    public static          Scanner scanner = new Scanner(System.in);
    public static final    Random  random  = new Random();

    //************************************************************
    //*** instance fields
    public static char [] alphabet = {'A','B','C','D','E','F','G','H','I','J','K',
            'L','M','N','O','P','Q','R','S','T','U','V',
            'W','X','Y','Z'};

    //************************************************************
    //*** methods

    public static void game()
    {
        String  randomLettersPrint = "";
        String  randomLetters      = "";
        String  userWord           = "";
        int     count              = 0;
        boolean done               = false;

        for(int i=0; i<10; i++) {
            int n = random.nextInt(alphabet.length);
            randomLettersPrint = randomLettersPrint + alphabet[n] + " ";
            randomLetters = randomLetters + alphabet[n];
        }
        System.out.println("Welcome to Shuffled Letters. Your letters are: " + randomLettersPrint);
        while (!done) {
            System.out.print("Enter a valid word: ");
            userWord = scanner.nextLine();
            userWord = userWord.toUpperCase();
            for(int j=0; j<userWord.length(); j++) {
                if (!randomLetters.contains("" + userWord.charAt(j))){
                    done = true;
                }
            }
            count++;
        }
        System.out.println("Error: " + userWord + " is not a valid word.");
        System.out.println("You entered " + count + " word(s)");
    }
}
