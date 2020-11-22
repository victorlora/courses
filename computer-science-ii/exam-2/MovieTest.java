
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MovieTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MovieTest
{
    private static final String DEFAULT_NAME     = "name";
    private static final String DEFAULT_LANGUAGE = "language";
    private static final String DEFAULT_RATING   = "rating";
    private static final int    DEFAULT_YEAR     = 2014;

    public Movie movie;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        this.movie = new Movie(DEFAULT_NAME, DEFAULT_LANGUAGE, DEFAULT_YEAR, DEFAULT_RATING);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        this.movie = null;
    }

    @Test
    public void getName()
    {
        assertEquals(DEFAULT_NAME, this.movie.getName());
    }

    @Test
    public void setName()
    {
        this.movie.setName("newName");
        assertEquals("newName", this.movie.getName());
    }

    @Test
    public void setNameNull()
    {
        try{
            this.movie.setName(null);
        }
        catch(IllegalArgumentException e) {
            assertEquals("Name cannot be null", e.getMessage());
        }
        assertEquals(DEFAULT_NAME, this.movie.getName());
    }

    @Test
    public void getLanguage()
    {
        assertEquals(DEFAULT_LANGUAGE, this.movie.getLanguage());
    }

    @Test
    public void setLanguage()
    {
        this.movie.setLanguage("newLanguage");
        assertEquals("newLanguage", this.movie.getLanguage());
    }

    @Test
    public void setLanguageNull()
    {
        try{
            this.movie.setLanguage(null);
        }
        catch(IllegalArgumentException e) {
            assertEquals("Language cannot be null", e.getMessage());
        }
        assertEquals(DEFAULT_LANGUAGE, this.movie.getLanguage());
    }

     @Test
    public void getRating()
    {
        assertEquals(DEFAULT_RATING, this.movie.getRating());
    }

    @Test
    public void setRating()
    {
        this.movie.setRating("newRating");
        assertEquals("newRating", this.movie.getRating());
    }
    
    @Test
    public void setRatingNull()
    {
        try{
            this.movie.setRating(null);
        }
        catch(IllegalArgumentException e) {
            assertEquals("Rating cannot be null", e.getMessage());
        }
        assertEquals(DEFAULT_RATING, this.movie.getRating());
    }

     @Test
    public void getYear()
    {
        assertEquals(DEFAULT_YEAR, this.movie.getYear());
    }

    @Test
    public void setYear()
    {
        this.movie.setYear(2015);
        assertEquals(2015, this.movie.getYear());
    }
    
    @Test
    public void setYearNeg()
    {
        try{
            this.movie.setYear(-2014);
        }
        catch(IllegalArgumentException e) {
            assertEquals("Year cannot be negative", e.getMessage());
        }
        assertEquals(DEFAULT_YEAR, this.movie.getYear());
    }
}
