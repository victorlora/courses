
/**
 * Write a description of class Laptops here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laptops extends Monitors
{
    // instance variables - replace the example below with your own
    private int diskSize;

    /**
     * Constructor for objects of class Laptops
     * @params  int     price
     *          int     amount of memory
     *          int     screen size
     *          int     weight
     *          int     disk size
     */
    public Laptops(int price, int memoryAmt, int screenSize, int weight, int diskSize)
    {
        super(price, memoryAmt, screenSize, weight);
        setDiskSize(diskSize);
    }

    /**
     * Returns the disk size.
     * @return  int     disk size
     */
    public int getDiskSize()
    {
        return this.diskSize;
    }
    
    /**
     * Sets the disk size.
     * @param  int     disk size
     */
    public void setDiskSize(int newDiskSize)
    {
        if (diskSize < 0) {
            throw new IllegalArgumentException ("Size of Disk cannot be negative");
        }
        this.diskSize = newDiskSize;
    }
}
