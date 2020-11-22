

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
        assertFalse(this.testPlayer.addItem(null));
    }
    
    @Test
    public void addItemTrue()
    {
        assertTrue(this.testPlayer.addItem(testItem));
    }
    
    @Test
    public void addItemPastMax()
    {
        int MAX = testPlayer.ITEM_COUNT_MAX;
        for(int i=0; i<MAX; i++){
            this.testPlayer.addItem(new Item(DEFAULT_LABEL, DEFAULT_DESCRIPTION)) ;
        }
        assertFalse(this.testPlayer.addItem(new Item(DEFAULT_LABEL, DEFAULT_DESCRIPTION))) ;
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
        assertFalse(this.testPlayer.removeItem("not label"));
    }
    
    @Test
    public void removeItem1Item()
    {
        this.testPlayer.addItem(testItem);
        assertTrue(this.testPlayer.removeItem("label"));
    }
    
    @Test
    public void removeItem2Items()
    {
        this.testPlayer.addItem(new Item("item1", "description"));
        this.testPlayer.addItem(new Item("item2", "description"));
        assertTrue(this.testPlayer.removeItem("item1"));
    }
    
    @Test
    public void removeItem3Items()
    {
        this.testPlayer.addItem(new Item("item1", "description"));
        this.testPlayer.addItem(new Item("item2", "description"));
        this.testPlayer.addItem(new Item("item3", "description"));
        assertTrue(this.testPlayer.removeItem("item2"));
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
