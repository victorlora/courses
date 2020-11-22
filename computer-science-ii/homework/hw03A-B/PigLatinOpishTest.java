

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PigLatinOpishTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PigLatinOpishTest
{
    /**
     * Default constructor for test class PigLatinOpishTest
     */
    public PigLatinOpishTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Tests for English to PigLatin/Opish.
     * 
     * public void nameoffunction()
     * {
     * typeofresult result = classname.methodname(inputs for method);
     * assertEquals(expected result, result);  
     * }
     * 
     */
    //toPigLatin tests***************************************************
    @Test
    public void testPLAdd(){
        String result = PigLatinOpish.toPigLatin("Add");
        result = result.toLowerCase();
        assertEquals("adday", result);
    }
    
    @Test
    public void testPLB(){
        String result = PigLatinOpish.toPigLatin("b");
        result = result.toLowerCase();
        assertEquals("bay", result);
    }
    
    @Test
    public void testPLBat(){
        String result = PigLatinOpish.toPigLatin("Bat");
        result = result.toLowerCase();
        assertEquals("atbay", result);
    }
    
    @Test
    public void testPLChat(){
        String result = PigLatinOpish.toPigLatin("Chat");
        result = result.toLowerCase();
        assertEquals("atchay", result);
    }
    
    @Test
    public void testPLE(){
        String result = PigLatinOpish.toPigLatin("e");
        result = result.toLowerCase();
        assertEquals("eay", result);
    }
    
    @Test
    public void testPLEach(){
        String result = PigLatinOpish.toPigLatin("each");
        result = result.toLowerCase();
        assertEquals("eachay", result);
    }
    
    
    @Test
    public void testPLEmpty(){
        String result = PigLatinOpish.toPigLatin(" ");
        assertEquals(null, result);
    }
    
    @Test
    public void testPLStraight(){
        String result = PigLatinOpish.toPigLatin("straight");
        assertEquals("aightstray", result);
    }
    
    //toOpish() tests****************************************************
    @Test
    public void testOpishAdd(){
        String result = PigLatinOpish.toOpish("Add");
        result = result.toLowerCase();
        assertEquals("addop", result);
    }
    
    @Test
    public void testOpishB(){
        String result = PigLatinOpish.toOpish("b");
        result = result.toLowerCase();
        assertEquals("bop", result);
    }
    
    @Test
    public void testOpishBat(){
        String result = PigLatinOpish.toOpish("Bat");
        result = result.toLowerCase();
        assertEquals("bopatop", result);
    }
    
    @Test
    public void testOpishChat(){
        String result = PigLatinOpish.toOpish("Chat");
        result = result.toLowerCase();
        assertEquals("chopatop", result);
    }
    
    @Test
    public void testOpishE(){
        String result = PigLatinOpish.toOpish("e");
        result = result.toLowerCase();
        assertEquals("e", result);
    }
    
    @Test
    public void testOpishEach(){
        String result = PigLatinOpish.toOpish("each");
        result = result.toLowerCase();
        assertEquals("eachop", result);
    }
    
    @Test
    public void testOpishEmpty(){
        String result = PigLatinOpish.toOpish("");
        assertEquals(null, result);
    }
    
    @Test
    public void testOpishEndWithVowel(){
        String result = PigLatinOpish.toOpish("bite");
        result = result.toLowerCase();
        assertEquals("bopitope", result);
    }
    
    @Test
    public void testOpishStraight(){
        String result = PigLatinOpish.toOpish("straight");
        assertEquals("stropaightop", result);
    }
    
    @Test
    public void testOpishTear(){
        String result = PigLatinOpish.toOpish("tear");
        result = result.toLowerCase();
        assertEquals("topearop", result);
    }
    
}
