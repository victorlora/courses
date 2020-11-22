import static org.junit.Assert.*;
import org.junit.*;

/**
 * ActionTest (JUnit 4)
 * @author  Clif Kussmaul
 * @version 2012-10
 */
public class ActionTest4 {
    private static final String DEFAULT_LABEL       = "Action label";
    private static final String DEFAULT_DESCRIPTION = "Action description";
    private static final Place  prevPlace = new Place("Prev", "PPPP"),
    nextPlace = new Place("Next", "NNNN");

    private Action  action;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp() {
        this.action = new Action(DEFAULT_LABEL, DEFAULT_DESCRIPTION,
            prevPlace, nextPlace);
    }

    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown() {
        this.action = null;
    }

    // tests for description

    @Test public void setDescriptionNull() {
        assertFalse(this.action.setDescription(null));
        assertEquals(DEFAULT_DESCRIPTION, this.action.getDescription());
    }

    @Test public void setDescriptionEmpty() {
        assertTrue(this.action.setDescription(""));
        assertEquals("", this.action.getDescription());
    }

    @Test public void setDescriptionBlank() {
        assertTrue(this.action.setDescription("  "));
        assertEquals("", this.action.getDescription());
    }

    @Test public void setDescriptionTrim() {
        assertTrue(this.action.setDescription(" " + DEFAULT_DESCRIPTION + " "));
        assertEquals(DEFAULT_DESCRIPTION, this.action.getDescription());
    }

        // tests for label

    @Test public void setLabelNull() {
        assertFalse(this.action.setLabel(null));
        assertEquals(DEFAULT_LABEL, this.action.getLabel());
    }

    @Test public void setLabelEmpty() {
        assertFalse(this.action.setLabel(""));
        assertEquals(DEFAULT_LABEL, this.action.getLabel());
    }

    @Test public void setLabelBlank() {
        assertFalse(this.action.setLabel("  "));
        assertEquals(DEFAULT_LABEL, this.action.getLabel());
    }

    @Test public void setLabelTrim() {
        assertTrue(this.action.setLabel(" " + DEFAULT_LABEL + " "));
        assertEquals(DEFAULT_LABEL, this.action.getLabel());
    }

    // tests for next place

    @Test public void setNextNull() {
        assertFalse(this.action.setNext(null));
        assertEquals(nextPlace, this.action.getNext());
    }

    @Test public void setNext() {
        Place place = new Place("Test", "tttt");
        assertTrue(this.action.setNext(place));
        assertEquals(place, this.action.getNext());
    }

    // tests for prev place

    @Test public void setPrevNull() {
        assertFalse(this.action.setPrev(null));
        assertEquals(prevPlace, this.action.getPrev());
    }

    @Test public void setPrev() {
        Place place = new Place("Test", "tttt");
        assertTrue(this.action.setPrev(place));
        assertEquals(place, this.action.getPrev());
    }
} // end class
