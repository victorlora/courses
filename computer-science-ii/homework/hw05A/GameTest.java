
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @authors  James Kmetz, Jason Gerstenfeld, Victor Lora
 * @version 2014-10
 */
public class GameTest
{
    private static final String DEFAULT_LABEL       = "Game label";
    private static final String DEFAULT_DESCRIPTION = "Game description";
    private static final String DEFAULT_LABEL2       = "Place label";
    private static final String DEFAULT_DESCRIPTION2 = "Place description";

    private Game game;
    private Place from;
    private Place to;
    

    /**
     * Sets up the test fixture before each test method.
     */
    @Before public void setUp() {
        this.game = new Game(DEFAULT_LABEL, DEFAULT_DESCRIPTION);
        this.from = new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2);
        this.to   = new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2);
    }

    /**
     * Tears down the test fixture after each test method.
     */
    @After public void tearDown() {
        this.game = null;
        this.from = null;
        this.to   = null;
    }

    // tests for description
    @Test public void setDescriptionNull() {
        assertFalse(this.game.setDescription(null));
        assertEquals(DEFAULT_DESCRIPTION, this.game.getDescription());
    }

    @Test public void setDescriptionEmpty() {
        assertTrue(this.game.setDescription(""));
        assertEquals("", this.game.getDescription());
    }

    @Test public void setDescriptionBlank() {
        assertTrue(this.game.setDescription("  "));
        assertEquals("", this.game.getDescription());
    }

    @Test public void setDescriptionTrim() {
        assertTrue(this.game.setDescription(" " + DEFAULT_DESCRIPTION + " "));
        assertEquals(DEFAULT_DESCRIPTION, this.game.getDescription());
    }

    // tests for label
    @Test public void setLabelNull() {
        assertFalse(this.game.setLabel(null));
        assertEquals(DEFAULT_LABEL, this.game.getLabel());
    }

    @Test public void setLabelEmpty() {
        assertFalse(this.game.setLabel(""));
        assertEquals(DEFAULT_LABEL, this.game.getLabel());
    }

    @Test public void setLabelBlank() {
        assertFalse(this.game.setLabel("  "));
        assertEquals(DEFAULT_LABEL, this.game.getLabel());
    }

    @Test public void setLabelTrim() {
        assertTrue(this.game.setLabel(" " + DEFAULT_LABEL + " "));
        assertEquals(DEFAULT_LABEL, this.game.getLabel());
    }
    
    // test addAction
    @Test public void addActionNull() {
        assertFalse(this.game.addAction(null));
    }

    @Test public void addActionPastMax() {
        int MAX = game.ACTION_COUNT_MAX;
        for(int i=0; i<MAX;i++){
            this.game.addAction(new Action("label","desc", this.from, this.to)) ;
        }
        
        assertFalse(this.game.addAction(new Action("label","desc", this.from, this.to))) ;
    }
    
    @Test public void addActionBelowMax() {
        int MAX = game.ACTION_COUNT_MAX;
        for(int i=0; i<MAX-1;i++){
            this.game.addAction(new Action("label","desc", this.from, this.to)) ;
        }
        
        assertTrue(this.game.addAction(new Action("label","desc", this.from, this.to))) ;
    }
    
    // test addPlace
    @Test public void addPlaceNull() {
        assertFalse(this.game.addPlace(null));
    }

    @Test public void addPlacePastMax() {
        int MAX = game.PLACE_COUNT_MAX;
        for(int i=0; i<MAX;i++){
            this.game.addPlace(new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2)) ;
        }
        
        assertFalse(this.game.addPlace(new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2)));
    }
    
    @Test public void addPlaceBelowMax() {
        int MAX = game.PLACE_COUNT_MAX;
        for(int i=0; i<MAX-1;i++){
            this.game.addPlace(new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2)) ;
        }
        
        assertTrue(this.game.addPlace(new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2))) ;
    }  
}
