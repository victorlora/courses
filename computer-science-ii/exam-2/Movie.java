
/**
 * Write a description of class Movie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movie
{
    //*** instance fields
    private String name;
    private String language;
    private String rating;
    private int    year;

    //*** Constructors
    public Movie (String name, String language, int year)  {
        setName     (name);
        setLanguage (language);
        setYear     (year);
    }

    public Movie (String name, String language, int year, String rating)  {
        setName     (name);
        setLanguage (language);
        setYear     (year);
        setRating   (rating);
    }

    //***get & set methods
    public String getName() {return this.name;  }

    public void   setName(String newName)  {
        // TODO: error handling
        this.name = newName;
    }

    public String getLanguage() {return this.language;  }

    public void   setLanguage (String newLanguage)  {
        // TODO: error handling
        this.language = newLanguage;
    }

    public String getRating() {return this.rating;  }

    public void   setRating (String newRating)  {
        // TODO: error handling
        this.rating = newRating;
    }


    public int    getYear() {return this.year;  }

    public void   setYear (int newYear)  {
        // TODO: error handling
        this.year = newYear;
    }
}// end class Movie
