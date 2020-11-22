import static org.junit.Assert.*;
import org.junit.*;

/**
 * PlaceTest (JUnit 4)
 * @author  Clif Kussmaul
 * @version 2012-10
 */
public class PlaceTest4 {
    private static final String DEFAULT_LABEL       = "Place label";
    private static final String DEFAULT_DESCRIPTION = "Place description";
    private Place place;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp() {
        this.place = new Place(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
    }
    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown() {
        this.place = null;
    }

    // tests for description

    @Test public void setDescriptionNull() {
        assertFalse(this.place.setDescription(null));
        assertEquals(DEFAULT_DESCRIPTION, this.place.getDescription());
    }
    @Test public void setDescriptionEmpty() {
        assertTrue(this.place.setDescription(""));
        assertEquals("", this.place.getDescription());
    }
    @Test public void setDescriptionBlank() {
        assertTrue(this.place.setDescription("  "));
        assertEquals("", this.place.getDescription());
    }
    @Test public void setDescriptionTrim() {
        assertTrue(this.place.setDescription(" " + DEFAULT_DESCRIPTION + " "));
        assertEquals(DEFAULT_DESCRIPTION, this.place.getDescription());
    }

    // tests for label

    @Test public void setLabelNull() {
        assertFalse(this.place.setLabel(null));
        assertEquals(DEFAULT_LABEL, this.place.getLabel());
    }
    @Test public void setLabelEmpty() {
        assertFalse(this.place.setLabel(""));
        assertEquals(DEFAULT_LABEL, this.place.getLabel());
    }
    @Test public void setLabelBlank() {
        assertFalse(this.place.setLabel("  "));
        assertEquals(DEFAULT_LABEL, this.place.getLabel());
    }
    @Test public void setLabelTrim() {
        assertTrue(this.place.setLabel(" " + DEFAULT_LABEL + " "));
        assertEquals(DEFAULT_LABEL, this.place.getLabel());
    }
} // end class