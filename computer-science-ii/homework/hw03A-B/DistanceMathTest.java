
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DistanceMathTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DistanceMathTest
{
    /**
     * Default constructor for test class DistanceMathTest
     */
    public DistanceMathTest()
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

    /** @Test
    public void nameoffunction(){
    typeofresult result = classname.methodname(inputs for method);
    assertEquals(expected result, result, margin of error);   
    }*/

    @Test
    public void testCmAndCm(){
        double result = DistanceMath.addDistance(1, "cm", 1, "cm");
        assertEquals(2, result, 0.01);
    }

    @Test
    public void testCmAndInch(){
        double result = DistanceMath.addDistance(1, "cm", 1, "in");
        assertEquals(3.54, result, 0.01);
    }

    @Test
    public void testCmAndFt(){
        double result = DistanceMath.addDistance(1, "cm", 1, "ft");
        assertEquals(31.48, result, 0.01);
    }

    @Test
    public void testCmAndM(){
        double result = DistanceMath.addDistance(1, "cm", 1, "m");
        assertEquals(101, result, 0.01);
    }

    @Test
    public void testInAndIn(){
        double result = DistanceMath.addDistance(1, "in", 1, "in");
        assertEquals(2, result, 0.01);
    }

    @Test
    public void testInAndCm(){
        double result = DistanceMath.addDistance(1, "in", 1, "cm");
        assertEquals(1.393701, result, 0.01);
    }

    @Test
    public void testInAndFt(){
        double result = DistanceMath.addDistance(1, "in", 1, "ft");
        assertEquals(13, result, 0.01);
    }

    @Test
    public void testInAndM(){
        double result = DistanceMath.addDistance(1, "in", 1, "m");
        assertEquals(40.3701, result, 0.01);
    }

    @Test
    public void testFtAndFt(){
        double result = DistanceMath.addDistance(1, "ft", 1, "ft");
        assertEquals(2, result, 0.01);
    }

    @Test
    public void testFtAndCm(){
        double result = DistanceMath.addDistance(1, "ft", 1, "cm");
        assertEquals(1.0328084, result, 0.01);
    }

    @Test
    public void testFtAndIn(){
        double result = DistanceMath.addDistance(1, "ft", 1, "in");
        assertEquals(1.083, result, 0.01);
    }

    @Test
    public void testFtAndM(){
        double result = DistanceMath.addDistance(1, "ft", 1, "m");
        assertEquals(4.28084, result, 0.01);
    }

    @Test
    public void testMAndM(){
        double result = DistanceMath.addDistance(1, "m", 1, "m");
        assertEquals(2, result, 0.01);
    }

    @Test
    public void testMAndCm(){
        double result = DistanceMath.addDistance(1, "m", 1, "cm");
        assertEquals(1.01, result, 0.01);
    }

    @Test
    public void testMAndIn(){
        double result = DistanceMath.addDistance(1, "m", 1, "in");
        assertEquals(1.0254, result, 0.01);
    }

    @Test
    public void testMAndFt(){
        double result = DistanceMath.addDistance(1, "m", 1, "ft");
        assertEquals(1.3048, result, 0.01);
    }

    /*** Now test multiplication */

    @Test
    public void testmultCmAndCm(){
        double result = DistanceMath.multDistance(1, "cm", 1, "cm");
        assertEquals(1, result, 0.01);
    }

    @Test
    public void testmultCmAndInch(){
        double result = DistanceMath.multDistance(1, "cm", 1, "in");
        assertEquals(2.54, result, 0.01);
    }

    @Test
    public void testmultCmAndFt(){
        double result = DistanceMath.multDistance(1, "cm", 1, "ft");
        assertEquals(30.48, result, 0.01);
    }

    @Test
    public void testmultCmAndM(){
        double result = DistanceMath.multDistance(1, "cm", 1, "m");
        assertEquals(100, result, 0.01);
    }

    @Test
    public void testmultInAndIn(){
        double result = DistanceMath.multDistance(1, "in", 1, "in");
        assertEquals(1, result, 0.01);
    }

    @Test
    public void testmultInAndCm(){
        double result = DistanceMath.multDistance(1, "in", 1, "cm");
        assertEquals(.393701, result, 0.01);
    }

    @Test
    public void testmultInAndFt(){
        double result = DistanceMath.multDistance(1, "in", 1, "ft");
        assertEquals(12, result, 0.01);
    }

    @Test
    public void testmultInAndM(){
        double result = DistanceMath.multDistance(1, "in", 1, "m");
        assertEquals(39.3701, result, 0.01);
    }

    @Test
    public void testmultFtAndFt(){
        double result = DistanceMath.multDistance(1, "ft", 1, "ft");
        assertEquals(1, result, 0.01);
    }

    @Test
    public void testmultFtAndCm(){
        double result = DistanceMath.multDistance(1, "ft", 1, "cm");
        assertEquals(.0328084, result, 0.01);
    }

    @Test
    public void testmultFtAndIn(){
        double result = DistanceMath.multDistance(1, "ft", 1, "in");
        assertEquals(.083, result, 0.01);
    }

    @Test
    public void testmultFtAndM(){
        double result = DistanceMath.multDistance(1, "ft", 1, "m");
        assertEquals(3.28084, result, 0.01);
    }

    @Test
    public void testmultMAndM(){
        double result = DistanceMath.multDistance(1, "m", 1, "m");
        assertEquals(1, result, 0.01);
    }

    @Test
    public void testmultMAndCm(){
        double result = DistanceMath.multDistance(1, "m", 1, "cm");
        assertEquals(.01, result, 0.01);
    }

    @Test
    public void testmultMAndIn(){
        double result = DistanceMath.multDistance(1, "m", 1, "in");
        assertEquals(.0254, result, 0.01);
    }

    @Test
    public void testmultMAndFt(){
        double result = DistanceMath.multDistance(1, "m", 1, "ft");
        assertEquals(.3048, result, 0.01);
    }

    @Test
    public void testfalse(){
        double result = DistanceMath.multDistance(1, "km", 1, "ft");
        assertEquals(Double.NaN, result, 0);
    }
}
