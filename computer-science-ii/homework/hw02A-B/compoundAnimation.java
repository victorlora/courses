
/**
 * Description:
 * compoundAnimation draws a car, a tree,
 * and a road on the screen and moves the 
 * car and the tree in opposite directions 
 * to simulate motion.
 * 
 * 
 * @author Victor Lora
 * @version 2014 September 10
 * 
 * estimated time: 120 min
 *    actual time: 140 min
 * expected grade: 10/10
 *
 * NOTES:
 * Assignment calls for SimpleGraphics.sleep()
 * to be called 3 times; however, I created a
 * while-loop that calls it multiple times. So 
 * the while-loop is endless.
 * Also, I would like to talk to you about a way
 * to end the while loop when the window is closed.
 *
 */

// import external definitions
import java.awt.Color;
import java.util.Random;
import org.kussmaul.simplegraphics.SimpleGraphics;
import org.kussmaul.simplegraphics.item.SimpleItem;
import org.kussmaul.simplegraphics.item.*;

/*
 * class compoundAnimation draws a tree,
 * a car, and a road on the screen and 
 * calls methods that move 2 out of 3 
 * objects.
 */
public class compoundAnimation
{
    //***********************************************************************
    //***named constants
    /**
     * Size (height & width) of window.
     */
    public static final int WINDOW_SIZE = 400;
    /**
     * Size (height & width) of main car body "rect".
     */    
    public static final int RECT_WIDTH = 100;
    public static final int RECT_HGT   = 40;

    /**
     * Defines car speed
     */
    public static final int CAR_SPEED = 20;

    /**
     * Sets the speed of the tree.
     */
    public static final int TREE_SPEED = -10;
    
    /**
     * Size (height & width) of circle
     */
    public static final int CIRCLE     = 30;

    //***********************************************************************
    //***fields
    
    public static SimpleGraphics    sg;
    
    // road field:
    public static SimpleItem        road;
    
    // car fields:
    public static CompoundItem      car;
    public static SimpleItem        rect1;
    public static SimpleItem        rect2;
    public static SimpleItem        wheel1;
    public static SimpleItem        wheel2;
    public static SimpleItem        line1;
    
    // tree fields:
    public static SimpleItem        tree1;
    public static SimpleItem        tree2;
    public static SimpleItem        tree3;
    public static CompoundItem      tree;
    public static SimpleItem        stump;
    
    public static final Random      random = new Random();

    //***********************************************************************
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
        
        drawRoad();

        // calls makeCar() method, which draws a car        
        makeCar();
        makeTree();
        

        // assigns the truth value to the 
        // variable keepGoing
        boolean keepGoing = true;

        /**
         * creates a while loop that
         * moves the car every 200 msec
         */
        while(keepGoing){
            // calls the following methods
            moveCar();
            moveTree();

            /**
             *resets the car to a pre-determined 
             *position whenever it crosses the screen
             */
            if (car.getX() > WINDOW_SIZE + 100){
                carReset();
            } // end if statement1
            
            /**
             * resets tree to a pre-determined
             * position whenever it exits the
             * screen
             */
            if (tree.getX() < -50){
                treeReset();
            } // end if statement2
            
            SimpleGraphics.sleep(200); // pauses the animation for 200 msec
        } // end while-loop    
    } // end main() method
    
    /**
     * make the road.
     * @param  none
     * @return none
     */
    private static void drawRoad() {
        // sets draw color to GRAY
        sg.setDrawColor(171,171,171);
        
        // creates a rectangular object that represents a road
        road = sg.fillRect (-50,160,WINDOW_SIZE,200);
    } // end drawRoad() method
    
    /**
     *draws a car.
     *@param  none
     *@return none
     */
    private static void makeCar() {
        // set color Red
        sg.setDrawColor(Color.RED);

        // creates Red rectangle, creates main car body
        int rect_dx = random.nextInt(WINDOW_SIZE);
        int rect_dy = WINDOW_SIZE - 100;

        rect1 = sg.fillRect (rect_dx, rect_dy,
            RECT_WIDTH, RECT_HGT);

        // creates top half of car
        rect2 = sg.fillRect(rect1.getX()+15,
            rect1.getY()-20,
            RECT_WIDTH/1.5, 
            RECT_HGT/1.5);

        //sets color to black
        sg.setDrawColor(Color.BLACK);

        // creates Left car wheel
        wheel1 = sg.fillOval(rect1.getX()+10, 
            rect1.getY()+RECT_HGT-10,
            CIRCLE, CIRCLE);

        // creates Right car wheel
        wheel2 = sg.fillOval(rect1.getX()+60, 
            rect1.getY()+RECT_HGT-10,
            CIRCLE, CIRCLE);
        // seperates the top and bottom half of the car                     
        line1  = sg.drawLine(rect1.getX(),rect1.getY(),
            rect1.getX()+RECT_WIDTH,
            rect1.getY());

        // creates compound item
        car   = new CompoundItem(sg, 100, 100);

        // adds the different components of the car to the compound item
        car.add(rect1);
        car.add(rect2);
        car.add(wheel1);
        car.add(wheel2);
        car.add(line1);
    } // end makeCar() method

    /**
     * This class moves the car.
     * @param   none
     * @returns none
     */
    private static void moveCar() {
        car.offset(CAR_SPEED, 0);
    } // end moveCar() method

    /**
     * resets the car
     * @param  none
     * @return none
     */
    private static void carReset() {
        car.setX(-200);
    } // end carReset() method

    /**
     * Draws a tree
     * @param  none
     * @return none
     */
    private static void makeTree(){
        // sets draw color to Green
        sg.setDrawColor(0,153,0);

        //sets random start for tree.
        int treeX = random.nextInt(WINDOW_SIZE);
        int treeY = 100;

        //creates the different sections of the tree
        tree1 = sg.fillTriangle(treeX, treeY,
                                treeX+10, treeY-20,
                                treeX+20, treeY);
        tree2 = sg.fillTriangle(treeX-10, treeY+30,
                                treeX+10, treeY-5,
                                treeX+30, treeY+30);
        tree3 = sg.fillTriangle(treeX-20, treeY+60,
                                treeX+10, treeY+25,
                                treeX+40, treeY+60);
        
        //sets draw color to BROWN
        sg.setDrawColor(179,110,5);
        stump = sg.fillRect(treeX, treeY + 60,
                            20,20);
        
        //creates tree item
        tree   = new CompoundItem(sg, 100, 100);
        
        //adds the different components of the tree to tree item
        tree.add(tree1);
        tree.add(tree2);
        tree.add(tree3);
        tree.add(stump);
    } // end makeTree() method
    
    /**
     * Moves tree
     * @param  none
     * @return none
     */
    private static void moveTree(){
        //changes tree's X position by the value of TREE_SPEED
        tree.offset(TREE_SPEED,0); 
    } // end moveTree() method
    
    /**
     * Resets tree to specified location
     * @param  none
     * @return none
     */
    private static void treeReset(){
        // relocates tree to a position 100 meg. pix past the window.
        tree.setX(WINDOW_SIZE + 100); 
    } // end treeReset() method
} // end compoundAnimation class

