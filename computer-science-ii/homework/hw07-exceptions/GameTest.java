
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
        try {
            this.game.addAction(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("New action cannot be null", e.getMessage());
        }
    }

    @Test public void addActionPastMax() {
        @SuppressWarnings("static-access")
				int MAX = game.ACTION_COUNT_MAX;
        for(int i=0; i<MAX;i++){
            this.game.addAction(new Action("label","desc", this.from, this.to));
        }

        try {
            this.game.addAction(new Action("label","desc", this.from, this.to));
        }
        catch (IllegalArgumentException e) {
            assertEquals("Action array list is full", e.getMessage());
        }
    }

    // test addPlace

    @Test public void addPlaceNull() {
        try {
            this.game.addPlace(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("New place cannot be null", e.getMessage());
        }
    }

    @Test public void addPlacePastMax() {
        @SuppressWarnings("static-access")
				int MAX = game.PLACE_COUNT_MAX;
        for(int i=0; i<MAX;i++){
            this.game.addPlace(new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2)) ;
        }
        try {
            this.game.addPlace(new Place(DEFAULT_LABEL2, DEFAULT_DESCRIPTION2));
        }
        catch (IllegalArgumentException e) {
            assertEquals("Place array list is full", e.getMessage());
        }
    }

    //test addPlayer
    @Test public void addPlayerNull()
    {
        try {
            this.game.addPlayer(null);
        }
        catch (IllegalArgumentException e) {
            assertEquals("New player cannot be null", e.getMessage());
        }
    }
}
