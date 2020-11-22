
/**
 * Picture draws two images on the screen. One of them is a care
 * package; the other is a platform.
 * 
 * @author Victor Lora
 * @version 2014 September 5
 * 
 * estimated time: 120 min
 *    actual time: 400 min
 * expected grade: 10/10
 *
 * NOTES: I had trouble getting the three objects to move at different
 * speeds dispite attempting to use different SimpleGraphics.sleep()
 * assignments to each one.
 *
 */

// import external definitions
import java.awt.Color;
import java.util.Random;
import org.kussmaul.simplegraphics.SimpleGraphics;
import org.kussmaul.simplegraphics.item.SimpleItem;
/*
 * class Animation continuously draws
 * a cargo package and a platform on the screen
 */
public class Animation
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
    public static final int PACKAGE_SPEED = 10;

    /**
     * Size (height, width, & speed) of plat.
     */
    public static final int PLAT_WIDTH = 150;
    public static final int PLAT_HGT   = 50;
    public static final int PLAT_SPEED = 25;

    /**
     * Size (height & width) of circle
     */
    public static final int CIRCLE     = 50;

    //*****************************************
    //***fields
    public static SimpleGraphics    sg;
    public static SimpleItem        line1;
    public static SimpleItem        line2;
    public static SimpleItem        triangle;
    public static SimpleItem        platLabel;
    public static SimpleItem        rect;
    public static SimpleItem        plat;
    public static SimpleItem        circle;
    public static final Random      random = new Random();

    //*****************************************
    //*** methods

    /**
     * Starts the program (ignores command line args).
     * @parameter none
     * @returns none
     */
    public static void main(String [] args) {
        // creates the window
        sg = new SimpleGraphics (WINDOW_SIZE, 
                                 WINDOW_SIZE, 
                                 "Picture", 
                                 true);
        // eliminates delay between drawings
        sg.setDrawDelay(0);
        /**
         * calls makeCarePackage() method, which draws 
         * the care package
         */         
        makeCarePackage();

        /**
         * calls makePlatform() method, draw platform
         */
        makePlatform();

        // assigns the truth value to the 
        // variable keepGoing
        boolean keepGoing = true;
        
        // creates a while loop
        while(keepGoing){
            // calls the following methods
            movePackage();
            movePlatform();
            makeCircle(100);
            makeCircle(300);
            makeCircle(400);

            // resets the care package to a pre-determined position
            // whenever it crosses outside of the screen
            if (rect.getY() > WINDOW_SIZE + 100){
                packageReset();
            }
            
            // resets the platform to a pre-determined position
            // whenever it crosses outside of the screen
            if (plat.getX() > WINDOW_SIZE + 100){
                platformReset();
            }         
            
            SimpleGraphics.sleep(20);
        }
    }

    /**
     *draws care package.
     *@parameters none
     *@return none
     */
    private static void makeCarePackage() {
        /**
         * assigns random values between 0-255
         * to R,G,B
         */ 
        int red     = random.nextInt(255);
        int green   = random.nextInt(255);
        int blue    = random.nextInt (255);
        sg.setDrawColor(red, green, blue);

        // creates rectangle filled in with random color
        int rect_dx = random.nextInt(WINDOW_SIZE);
        int rect_dy = 50;
        rect = sg.fillRect (rect_dx, rect_dy,
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

        // starts a while loop that calls
        // the methods that draw the pictures

    }

    /**
     * draws the platform.
     * @parameters none
     * @return none
     */
    private static void makePlatform(){
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

    /**
     * This class moves the care package
     * @parameters none
     * @returns none
     */
    private static void movePackage() {
        // Assigns the current Y positions of the different
        // parts of the care package to the following variables
        // then adds the constant speed to each so that they move
        // together.
        double rect_Y   = rect.getY() + PACKAGE_SPEED;
        double line1_Y  = line1.getY() + PACKAGE_SPEED;
        double line2_Y  = line2.getY() + PACKAGE_SPEED;
        double triangle_Y = triangle.getY() + PACKAGE_SPEED;
        
        // Sets the new Y-position for each of the care package
        // elements
        rect.setY(rect_Y);
        line1.setY(line1_Y);
        line2.setY(line2_Y);
        triangle.setY(triangle_Y);
    }
    
    /**
     * moves the platform
     * @parameter none
     * @return none
     */
    private static void movePlatform(){
        // gets & assigns the current platform position
        // to the variable plat_X and platLabel_X and 
        // adds some speed.
        double plat_X = plat.getX() + PLAT_SPEED;
        double platLabel_X = platLabel.getX() + PLAT_SPEED;
        
        // assigns the new X-location to platform and label
        plat.setX(plat_X);
        platLabel.setX(platLabel_X);
    }
    /**
     * resets platform once it crosses the window
     * @parameter none
     * @return none
     */
    private static void platformReset(){
        plat.setX(-100);
        platLabel.setX(-50);
    }

    /**
     * resets care package once it crosses the window
     * @parameter none
     * @return none
     */
    private static void packageReset(){
        rect.setY(-200);
        line1.setY(-200);
        line2.setY(-200);
        triangle.setY(-300);
    }
    
    /**
     * Draws a circle
     * @parameter none
     * @return
     */
    private static void makeCircle(int circleY){
        sg.setDrawColor(random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255));
        circle = sg.fillOval(random.nextInt(WINDOW_SIZE),
                             circleY,
                             CIRCLE, CIRCLE);
        SimpleGraphics.sleep(100);
        sg.remove(circle);
    }
}
