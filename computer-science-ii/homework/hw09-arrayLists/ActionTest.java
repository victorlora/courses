import static org.junit.Assert.*;

import org.junit.*;

/**
 * ActionTest (JUnit 4)
 * @author  Jason G., James K., Victor L. 
 * @version 2014-10 V1.1.0
 */
public class ActionTest extends ParentTest{
    private static final String DEFAULT_LABEL       = "label";
    private static final String DEFAULT_DESCRIPTION = "description";
    private static final Place  prevPlace = new Place("Prev", "PPPP"),
    nextPlace = new Place("Next", "NNNN");

    private Action  action;

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp()
    {
        this.action = new Action(DEFAULT_LABEL, DEFAULT_DESCRIPTION,
            prevPlace, nextPlace);
        this.parent = this.action;
    }

    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown()
    {
        this.action = null;
    }

    //tests for next place

    @Test public void setNextNull()
    {
        try {
            this.action.setNext(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Next place cannot be null", e.getMessage());
        }
        assertEquals(nextPlace, this.action.getNext());
    }

    @Test public void setNext()
    {
        Place place = new Place("Test", "tttt");
        this.action.setNext(place);
        assertEquals(place, this.action.getNext());
    }

    // tests for prev place

    @Test public void setPrevNull()
    {
        try {
            this.action.setPrev(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Previous place cannot be null", e.getMessage());
        }
        assertEquals(prevPlace, this.action.getPrev());
    }

    @Test public void setPrev()
    {
        Place place = new Place("Test", "tttt");
        this.action.setPrev(place);
        assertEquals(place, this.action.getPrev());
    }

    // test add Required/Forbidden items

    @Test public void addRequiredNull()
    {
        try {
            this.action.addRequired(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Required item cannot be null", e.getMessage());
        }
    }


    @Test public void addForbiddenNull()
    {
        try {
            this.action.addForbidden(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Forbidden item cannot be null", e.getMessage());
        }
    }

    // test required/forbidden item presence

    @Test public void hasRequiredTrue()
    {
        Player player = new Player("player", "test player");
        Item   item   = new Item("item", "test item");
        player.addItem(item);
        action.numRequired = 0;
        this.action.addRequired(item);
        assertTrue(this.action.hasRequired(player, -1));
    }

    @Test public void hasRequiredFalse()
    {
        Player player = new Player("player", "test player");
        Item   item   = new Item("item", "test item");
        this.action.addRequired(item);
        assertFalse(this.action.hasRequired(player, -1));
    }

    @Test public void hasRequiredNull()
    {
        assertFalse(this.action.hasRequired(null, -1));
    }

    @Test public void hasForbiddenTrue()
    {
        Player player = new Player("player", "test player");
        Item   item   = new Item("item", "test item");
        player.addItem(item);
        action.numForbidden = 0;
        this.action.addForbidden(item);
        assertTrue(this.action.hasForbidden(player, -1));
    }

    @Test public void hasForbiddenFalse()
    {
        Player player = new Player("player", "test player");
        Item   item   = new Item("item", "test item");
        this.action.addForbidden(item);
        assertFalse(this.action.hasForbidden(player, -1));
    }

    @Test public void hasForbiddenFalsePlayer()
    {
        Player player = new Player("player", "test player");
        Item   item   = new Item("item", "test item");
        player.addItem(item);
        assertFalse(this.action.hasForbidden(player, -1));
    }

    @Test public void hasForbiddenNull()
    {
        assertFalse(this.action.hasForbidden(null, -1));
    }

    @Test public void allowedForTrue()
    {
        Player player = new Player("player", "test player");
        Item   item   = new Item("item", "test item");
        player.addItem(item);
        action.numRequired = 0;
        this.action.addRequired(item);
        assertTrue(this.action.allowedFor(player, -1));
    }

    @Test public void allowedForFalse()
    {
        Player player = new Player("player", "test player");
        Item   item   = new Item("item", "test item");
        player.addItem(item);
        action.numForbidden = 0;
        this.action.addForbidden(item);
        assertFalse(this.action.allowedFor(player, -1));
    }
} // end class
