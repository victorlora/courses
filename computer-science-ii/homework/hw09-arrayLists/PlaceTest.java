import static org.junit.Assert.*;

import org.junit.*;

/**
 * PlaceTest (JUnit 4)
 * @author  Jason G., James K., Victor L., Clif Kussmaul 
 * @version 2014-10 V1.1.0
 */
@SuppressWarnings("unused")
public class PlaceTest extends ParentTest{
    private static final String DEFAULT_LABEL       = "label";
    private static final String DEFAULT_DESCRIPTION = "description";
    private Place place;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp() {
        this.place = new Place(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
        this.parent = this.place;
    }

    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown() {
        this.place = null;
    }
} // end class