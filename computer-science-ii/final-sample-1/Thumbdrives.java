
/**
 * Write a description of class thumbdrives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Thumbdrives
{
    // instance variables - replace the example below with your own
    private int price;
    private int memoryAmt;

    /**
     * Constructor for objects of class thumbdrives
     * @param   int     Price  of item
     *          int     Amount of memory
     * @return  ---
     */
    public Thumbdrives(int price, int memoryAmt)
    {
        setPrice(price);
        setMemoryAmt(memoryAmt);
    }

    /**
     * Gets the item price.
     * @params  ---
     * @return  price.
     */
    public  int  getPrice()    { 
        return this.price;
    }

    /**
     * Sets the description.
     * @param       String      newDescription
     * @throws      Exception   if newDescription is null
     */
    public  void setPrice(int newPrice) {
        if (newPrice<0)     {
            throw new IllegalArgumentException("Price cannot be less than zero.");
        }
        this.price = newPrice;
    }

    /**
     * Gets the memory amount for the item.
     * @return  memory amount
     */
    public  int  getMemoryAmt()      { return this.memoryAmt; }

    /**
     * Sets the label.
     * @param       String      newDescription
     * @throws      Exception   if newLabel is null
     *                          if newLabel is empty
     *                          if newLabel is blank
     */
    public  void setMemoryAmt(int newMemoryAmt) {
        if (newMemoryAmt<0)     {
            throw new IllegalArgumentException("Memory amount cannot be less than zero.");
        }
        this.memoryAmt = newMemoryAmt;
    }
}
