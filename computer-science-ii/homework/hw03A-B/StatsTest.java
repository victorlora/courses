
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StatsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StatsTest
{
    private Stats stats;
    private int [] data3;
    /**
     * Default constructor for test class StatsTest
     */
    public StatsTest()
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
        this.stats = new Stats(data3);
        this.data3 = new int[3];
        for (int i=0; i<data3.length; i++) 
        {
            data3[i] = i;
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        this.stats = null;
        this.data3 = null;
    }

    @Test 
    public void setValuesData3()
    {
        this.stats.setValues ( data3 );
        assertEquals( data3,      this.stats.getValues()  );
        assertEquals( ("0,1,2,"), this.stats.toString()   );
        assertEquals( 3,          this.stats.getSize()    );
        assertEquals( 3,          this.stats.getTotal()   );
        assertEquals( 1,          this.stats.getAverage() );
        assertEquals( 1,          this.stats.replace(1,2) );
        assertEquals( 2,          this.stats.getMax()     );
        assertEquals( 0,          this.stats.getMin()     );
    }

    @Test
    public void setValuesNull()
    {
        this.stats.setValues (null);
        assertEquals(null, this.stats.getValues() );
    }
}
