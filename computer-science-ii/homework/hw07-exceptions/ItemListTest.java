
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemListTest.
 *
 * @author  Jason G., James K., Victor L. 
 * @version 2014-10 V1.1.0
 */
public class ItemListTest extends ParentTest
{
    private static final String DEFAULT_LABEL       = "label";
    private static final String DEFAULT_DESCRIPTION = "description";

    private Item          testItem, testItem2;
    private Player        testPlayer;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        this.testItem   = new Item(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
        this.testItem2  = new Item(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
        this.testPlayer = new Player(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
        this.parent     = testPlayer;
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        this.testItem   = null;
        this.testItem2  = null;
        this.testPlayer = null;
    }

    @Test
    public void addItemNull()
    {
        try {
            this.testPlayer.addItem(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("The item cannot be null", e.getMessage());
        }
    }

    @Test
    public void addItemTrue()
    {
        assertTrue(this.testPlayer.addItem(testItem));
    }

    @Test
    public void addItemPastMax()
    {
        try {
            @SuppressWarnings("static-access")
						int MAX = testPlayer.ITEM_COUNT_MAX;
            for(int i=0; i<MAX; i++){
                this.testPlayer.addItem(new Item(DEFAULT_LABEL, DEFAULT_DESCRIPTION)) ;
            }
        }
        catch (IllegalArgumentException e) {
            assertEquals("The array list is full", e.getMessage());
        }
    }

    @Test
    public void hasItemTrue()
    {
        testPlayer.addItem(testItem);
        assertTrue(this.testPlayer.hasItem(testItem));
    }

    @Test
    public void hasItemFalse()
    {
        assertFalse(this.testPlayer.hasItem(testItem2));
    }

    // Test remove item 1, 2, and 3
    @Test
    public void removeItemFalse()
    {
        this.testPlayer.addItem(testItem);
        assertFalse(this.testPlayer.removeItem(testItem2));
    }

    @Test
    public void removeItem1Item()
    {
        this.testPlayer.addItem(testItem);
        assertTrue(this.testPlayer.removeItem(testItem));
    }

    @Test
    public void removeItem2Items()
    {
        Item item1 = new Item("item1", "description");
        Item item2 = new Item("item2", "description");
        this.testPlayer.addItem(item1);
        this.testPlayer.addItem(item2);
        assertTrue(this.testPlayer.removeItem(item1));
    }

    @Test
    public void removeItem3Items()
    {
        Item item1 = new Item("item1", "description");
        Item item2 = new Item("item2", "description");
        Item item3 = new Item("item3", "description");
        this.testPlayer.addItem(item1);
        this.testPlayer.addItem(item2);
        this.testPlayer.addItem(item3);
        assertTrue(this.testPlayer.removeItem(item2));
    }

    // getItemName() Test
    @Test
    public void getItemNameEmpty()
    {
        assertEquals("empty", this.testPlayer.getItemNames());
    }

    @Test
    public void getItemNameSingle()
    {
        this.testPlayer.addItem(testItem);
        assertEquals(" label", this.testPlayer.getItemNames());
    }

    @Test
    public void getItemNameMultiple()
    {
        this.testPlayer.addItem(testItem);
        this.testPlayer.addItem(testItem2);
        assertEquals(" label label", this.testPlayer.getItemNames());
    }

    @Test
    public void getItemNull()
    {
        this.testPlayer.addItem(testItem);
        assertEquals(null, this.testPlayer.getItem(null));
    }

    @Test
    public void getItemNotInList()
    {
        this.testPlayer.addItem(testItem);
        assertEquals(null, this.testPlayer.getItem("not label"));
    }

    @Test
    public void getItemInList()
    {
        this.testPlayer.addItem(testItem);
        assertEquals(testItem, this.testPlayer.getItem("label"));
    }
}
