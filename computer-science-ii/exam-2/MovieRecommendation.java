import java.util.*;
/**
 * Write a description of class MovieRecommendation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRecommendation
{
    //************************************************************
    //***Constant scanner
    public static Scanner scanner = new Scanner(System.in);

    //************************************************************
    //*** instance fields
    private ArrayList <Movie> movieList = new ArrayList <Movie> ();

    //************************************************************
    //*** constructor
    public  MovieRecommendation() {
    }
    //************************************************************
    //*** methods
    public void movieRecommender()   {
        boolean done = false;
        while (!done) {
            System.out.println("What language do you prefer?");
            String langPref = scanner.nextLine();
            if (langPref.equalsIgnoreCase("quit")) {System.exit(0);}
            System.out.println("What rating do you prefer?");
            String ratingPref = scanner.nextLine();
            if (ratingPref.equalsIgnoreCase("quit")) {System.exit(0);}
            System.out.println("I recommend that you watch: \n" + getMovie(langPref, ratingPref));
        }
    }

    public void movieAdd() {    
        movieList.add(new Movie("Monsters Inc",  "English", 2001, "G"   ));
        movieList.add(new Movie("Toy Story"   ,  "English", 2000, "G"   ));
        movieList.add(new Movie("I Am Legend" ,  "English", 2006, "PG13"));
        movieList.add(new Movie("Fury"        ,  "English", 2014, "R"   ));
        movieRecommender();
    }

    public String getMovie(String lang, String rating) {
        String recommendedMovies = "";
        if (null == lang || null == rating) { return null; }
        lang   = lang.trim();
        rating = rating.trim();
        for (int i=0; i<movieList.size(); i++) {
            if (lang.equalsIgnoreCase(this.movieList.get(i).getLanguage()) &&
            rating.equalsIgnoreCase(this.movieList.get(i).getRating())) {
                recommendedMovies = recommendedMovies + this.movieList.get(i).getName() + "\n";
            }
        }
        return recommendedMovies;
    }

    public static void main(String[] args) {
        MovieRecommendation movie = new MovieRecommendation();
        movie.movieAdd();
    }
}
