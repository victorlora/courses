
/**
 * Write a description of class Laptops here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cameras extends Monitors
{
    // instance variables - replace the example below with your own
    private int pixelSize;

    /**
     * Constructor for objects of class Laptops
     * @params  int     price
     *          int     amount of memory
     *          int     screen size
     *          int     weight
     *          int     pixel size
     */
    public Cameras(int price, int memoryAmt, int weight, int pixelSize)
    {
        super(price, memoryAmt, 0, weight);
        setpixelSize(pixelSize);
    }

    /**
     * Returns the pixel size.
     * @return  int     pixel size
     */
    public int getpixelSize()
    {
        return this.pixelSize;
    }
    
    /**
     * Sets the pixel size.
     * @param  int     pixel size
     */
    public void setpixelSize(int newpixelSize)
    {
        if (pixelSize < 0) {
            throw new IllegalArgumentException ("Size of Disk cannot be negative");
        }
        this.pixelSize = newpixelSize;
    }
}
