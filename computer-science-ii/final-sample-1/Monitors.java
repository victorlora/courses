
/**
 * Write a description of class Monitors here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Monitors extends Thumbdrives
{
    // instance variables
    private int screenSize;
    private int weight;

    /**
     * Constructor for objects of class Monitors
     * @param   int     Price
     *          int     Memory Amount
     *          int     Screen Size
     *          int     Weight        
     */
    public Monitors(int price, int memoryAmt, int screenSize, int weight)
    {
        super(price, memoryAmt);
        setScreenSize(screenSize);
        setWeight(weight);
    }

    /**
     * Returns the size of the item's screen
     * @return  int     Screen Size
     */
    public int getScreenSize()
    {
        return this.screenSize;
    }
    
    /**
     * Sets the screen size.
     * @param   int     item's Screen Size
     */
    public void setScreenSize(int newScreenSize)
    {
        if (newScreenSize < 0) {
            throw new IllegalArgumentException("Screen size cannot be less than zero.");
        }
        this.screenSize = newScreenSize;
    }
    
    /**
     * Returns the items weight
     * @return  int     Weight
     */
    public int getWeight()
    {
        return this.weight;
    }
    
    /**
     * Sets the item weight
     * @params  int     item weight
     */
    public void setWeight(int newWeight)
    {
        if (newWeight<0) {
            throw new IllegalArgumentException("Weight cannot be less than zero.");
        }
        this.weight = newWeight;
    }
}
