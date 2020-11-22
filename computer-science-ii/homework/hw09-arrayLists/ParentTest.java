
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class parentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ParentTest
{

    private static final String DEFAULT_LABEL       = "label";
    private static final String DEFAULT_DESCRIPTION = "description";
    public Parent parent;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp() {
        this.parent = new Game(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
    }

    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown() {
        this.parent = null;
    }

    // tests for description

    @Test public void setDescriptionNull() {
        try {
            this.parent.setDescription(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Description cannot be null", e.getMessage());
        }
        assertEquals(DEFAULT_DESCRIPTION, this.parent.getDescription());
    }

    @Test public void setDescriptionEmpty() {
        this.parent.setDescription("");
        assertEquals("", this.parent.getDescription());
    }

    @Test public void setDescriptionBlank() {
        this.parent.setDescription(" ");
        assertEquals("", this.parent.getDescription());
    }

    @Test public void setDescriptionTrim() {
        this.parent.setDescription(" " + DEFAULT_DESCRIPTION + " ");
        assertEquals(DEFAULT_DESCRIPTION, this.parent.getDescription());
    }

    // tests for label

    @Test public void setLabelNull() {
        try {
            this.parent.setLabel(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Label cannot be null", e.getMessage());
        }
        assertEquals(DEFAULT_LABEL, this.parent.getLabel());
    }

    @Test public void setLabelEmpty() {
        try {
            this.parent.setLabel("");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Label cannot be empty", e.getMessage());
        }
        assertEquals(DEFAULT_LABEL, this.parent.getLabel());
    }

    @Test public void setLabelBlank() {
        try {
            this.parent.setLabel(" ");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Label cannot be blank", e.getMessage());
        }
        assertEquals(DEFAULT_LABEL, this.parent.getLabel());
    }

    @Test public void setLabelTrim() {
        assertTrue(this.parent.setLabel(" " + DEFAULT_LABEL + " "));
        assertEquals(DEFAULT_LABEL, this.parent.getLabel());
    }

}
