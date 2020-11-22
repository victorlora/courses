

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PiTest.
 *
 * @author  Victor Lora & James Kmetz
 * @version 14 October 2014
 */
public class PiTest
{
    private Pi  pi;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void before()
    {
        this.pi = new Pi();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void after()
    {
        this.pi = null;
    }
    
    /**
     * Does the testing.
     */
    @Test public void test1()  {
        this.pi.setPi(1);
        assertEquals(4, this.pi.getPi(), .1);
    }
        
    @Test public void testPi()  {
        this.pi.setPi(500);
        assertEquals(3.14, this.pi.getPi(), .1);
    }
        
    @Test public void testZero()  {
        this.pi.setPi(0);
        assertEquals(0, this.pi.getPi(), .1);
    }
    //Since setPi cannot be given a null type or be left empty,
    //there aren't any test classes to make for those scenarios.
}
