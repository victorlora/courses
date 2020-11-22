import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HangmanTest.
 *
 * @author  Victor Lora & James Kmetz
 * @version 14 October 2014
 */
public class HangmanTest    {
    private Hangman hman;
    
    @Test public void test1() {
        Character c = new Character('A');
        assertTrue(hman.characterOK(c));}
    @Test public void test2() {
        Character c = new Character('a');
        assertTrue(hman.characterOK(c));}
    @Test public void testSpace() {
        Character c = new Character(' ');           //Should Fail
        assertFalse(hman.characterOK(c));}
    @Test public void testSymbol() {
        Character c = new Character('#');           //Should Fail
        assertFalse(hman.characterOK(c));}
}
