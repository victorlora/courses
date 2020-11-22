
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  Jason G., James K., Victor L. 
 * @version 2014-10 V1.1.0
 */
@SuppressWarnings("unused")
public class PlayerTest extends ParentTest
{
    private static final String DEFAULT_LABEL       = "label";
    private static final String DEFAULT_DESCRIPTION = "description";
    
    private Player player;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        this.player = new Player(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
        this.parent = this.player;
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        this.player = null;
    }
}
