
/**
 * Picture draws two images on the screen. One of them is a care
 * package; the other is a platform.
 * 
 * @author Victor Lora
 * @version 2014 September 5
 * 
 * estimated time: 120 min
 *    actual time: XX min
 * expected grade: 10/10
 *
 * NOTES: Design, code, debug, and test a program with a main method 
 * and 2 other methods, each of which draws a simple picture. Use at 
 * least 2 random numbers to vary the picture. The main() * method 
 * should call each picture method several times to demonstrate.
 *
 */

// import external dfinitions
import java.awt.Color;
import java.util.Random;
import org.kussmaul.simplegraphics.SimpleGraphics;
import org.kussmaul.simplegraphics.item.SimpleItem;
/*
 * class Pictures continuously draws
 * a cargo package and a platform on the screen
 */
public class Pictures
{
    //*****************************************
    //***named constants
    /**
     * Size (height & width) of window.
     */
    public static final int WINDOW_SIZE = 400;
    /**
     * Size (height & width) of rect.
     */    
    public static final int RECT_WIDTH = 100;
    public static final int RECT_HGT   = 50;
    
    /**
     * Size (height & width) of plat.
     */
    public static final int PLAT_WIDTH = 150;
    public static final int PLAT_HGT   = 50;

    //*****************************************
    //***fields
    public static SimpleGraphics    sg;
    public static SimpleItem        line1;
    public static SimpleItem        line2;
    public static SimpleItem        triangle;
    public static SimpleItem        platLabel;
    public static SimpleItem        rect;
    public static SimpleItem        plat;
    public static final Random      random = new Random();

    //*****************************************
    //*** methods

    /**
     * Starts the program (ignores command line args).
     */
    public static void main(String [] args) {
        // creates the window
        sg = new SimpleGraphics (WINDOW_SIZE, 
            WINDOW_SIZE, 
            "Picture", 
            true);
        // eliminates delay between drawings
        sg.setDrawDelay(0);

        // assigns the truth value to the 
        // variable keepGoing
        boolean keepGoing = true;
        // starts a while loop that calls
        // the methods that draw the pictures
        while (keepGoing){
            
            /**
             * calls makeCarePackage() method, which draws 
             * the care package
             */ 
            makeCarePackage();
            
            /**
             * calls makePlatform() method, draw platform
             */
            makePlatform();
            
            /**
             * pauses the program for 500 msec
             */
            SimpleGraphics.sleep(500);
            
            /**
             * clears the screen before drawing the new pictures
             */
            sg.clear();
        }

    }

    /**
     *draws care package.
     */
    public static void makeCarePackage() {
        /**
         * assigns random values between 0-255
         * to R,G,B
         */ 
        int red     = random.nextInt(255);
        int green   = random.nextInt(255);
        int blue    = random.nextInt (255);
        sg.setDrawColor(red, green, blue);
        
        // creates rectangle filled in with random color
        rect = sg.fillRect (random.nextInt(WINDOW_SIZE), 
            random.nextInt(250)+100,
            RECT_WIDTH, RECT_HGT);
        
        // creates left parachute string
        line1 = sg.drawLine(rect.getX(),rect.getY(),
            (rect.getX()+50),
            (rect.getY()-100));
            
        // creates right parachute string
        line2 = sg.drawLine(rect.getX()+100,
            rect.getY(),
            (rect.getX()+50),
            (rect.getY()-100));
            
        // creates triangle (parachute) of a different color  
        // using a different method for randomizing colors than
        // the one used above
        sg.setDrawColor(random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255));
        triangle = sg.fillTriangle(rect.getX(),
            rect.getY()-100,
            rect.getX()+50,
            rect.getY()-150,
            rect.getX()+100,
            rect.getY()-100);
    }

    public static void makePlatform(){
        /**
         * assigns random values between 0-255
         * to R,G,B
         */ 
        int red     = random.nextInt(255);
        int green   = random.nextInt(255);
        int blue    = random.nextInt (255);
        sg.setDrawColor(red, green, blue);
        
        // creates a random colored platform at a random location
        // along the x-axis
        plat = sg.fillRect(random.nextInt(WINDOW_SIZE),
            350,
            PLAT_WIDTH, PLAT_HGT);
        
        // creates a label that says "Platform" on the platform
        sg.setDrawColor(Color.BLACK);
        platLabel = sg.drawString(plat.getX()+50,plat.getY()+30,
            "Platform");
    }
}

       
        