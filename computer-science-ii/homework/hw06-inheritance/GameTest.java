
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
public class GameTest extends ParentTest 
{
    private static final String DEFAULT_LABEL       = "label";
    private static final String DEFAULT_DESCRIPTION = "description";
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
        this.parent = this.game;
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
    
    //test addPlayer
    @Test public void addPlayerNull()
    {
        assertEquals(false, this.game.addPlayer(null));
    }
}
