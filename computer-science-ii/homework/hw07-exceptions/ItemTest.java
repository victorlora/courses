
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemTest.
 *
 * @author  Jason G., James K., Victor L. 
 * @version 2014-10 V1.1.0
 */
@SuppressWarnings("unused")
public class ItemTest extends ParentTest
{
    private static final String DEFAULT_LABEL       = "label";
    private static final String DEFAULT_DESCRIPTION = "description";
    public Item item;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp() {
        this.item = new Item(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
        this.parent = this.item;
    }

    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown() {
        this.item = null;
        this.parent = null;
    }
}
