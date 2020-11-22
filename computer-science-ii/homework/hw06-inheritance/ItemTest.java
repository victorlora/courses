
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
public class ItemTest
{
    private static final String DEFAULT_LABEL       = "Item label";
    private static final String DEFAULT_DESCRIPTION = "Item description";
    public Item item;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp() {
        this.item = new Item(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
    }

    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown() {
        this.item = null;
    }

    // tests for description

    @Test public void setDescriptionNull() {
        assertFalse(this.item.setDescription(null));
        assertEquals(DEFAULT_DESCRIPTION, this.item.getDescription());
    }

    @Test public void setDescriptionEmpty() {
        assertTrue(this.item.setDescription(""));
        assertEquals("", this.item.getDescription());
    }

    @Test public void setDescriptionBlank() {
        assertTrue(this.item.setDescription("  "));
        assertEquals("", this.item.getDescription());
    }

    @Test public void setDescriptionTrim() {
        assertTrue(this.item.setDescription(" " + DEFAULT_DESCRIPTION + " "));
        assertEquals(DEFAULT_DESCRIPTION, this.item.getDescription());
    }

    // tests for label

    @Test public void setLabelNull() {
        assertFalse(this.item.setLabel(null));
        assertEquals(DEFAULT_LABEL, this.item.getLabel());
    }

    @Test public void setLabelEmpty() {
        assertFalse(this.item.setLabel(""));
        assertEquals(DEFAULT_LABEL, this.item.getLabel());
    }

    @Test public void setLabelBlank() {
        assertFalse(this.item.setLabel("  "));
        assertEquals(DEFAULT_LABEL, this.item.getLabel());
    }

    @Test public void setLabelTrim() {
        assertTrue(this.item.setLabel(" " + DEFAULT_LABEL + " "));
        assertEquals(DEFAULT_LABEL, this.item.getLabel());
    }
}
