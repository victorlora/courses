
/**
 * Keeps track of items belonging to different objects such
 * as players or places
 * 
 * @author  Jason G., James K., Victor L.
 * @version 2014-10
 */
public abstract class ItemList extends Parent
{
    //************************************************************
    //*** instance fields

    public static final int ITEM_COUNT_MAX     = 60;

    private Item [] itemsList = new Item[ITEM_COUNT_MAX];
    private int numItems = 0;

    //************************************************************
    // constructors

    public ItemList (String label, String description)
    {
        super(label, description);
    }

    //************************************************************
    //*** accessor methods

    /**
     * Checks to see if the object contains a specific
     * item in their inventory
     * @param   Item        specific item
     * @return  boolean     true  - if item is in inventory
     *                      false - otherwise
     */
    public boolean hasItem(Item item)
    {
        for (int i = 0; i< numItems; i++){
            if(itemsList[i].getLabel().equals(item.getLabel())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a newItem to the objects inventory
     * @param   Item        newItem
     * @return  boolean     True  - if item is added succesfully
     * @throws  Exception   if item is null or arrayList is full
     */
    public boolean addItem(Item newItem)
    {
        if (null == newItem) { 
            throw new IllegalArgumentException("The item cannot be null");
        }
        // FUTURE: auto-expand placeList when full; prevent duplicate
        if (this.itemsList.length == numItems) { 
            throw new IllegalArgumentException("The array is full");
        }
        this.itemsList[numItems++] = newItem;
        return true;
    }

    /**
     * Removes specific items from the inventory
     * @param   Item        item
     * @return  booelan     true  - if successfully removed from inventory
     *                      false - otherwise
     */
    public boolean removeItem(Item oldItem)
    {
        for(int i=0; i< numItems; i++) {
            if(itemsList[i].equals(oldItem)) {
                itemsList[i] = itemsList[numItems-1];
                itemsList[numItems-1] = null;
                numItems--;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a string with an object item's name
     * @param   ---
     * @return  String      item lable/name
     */
    public String getItemNames()
    {
        String itemName = "";
        for (int i=0; i<numItems; i++) {
            itemName = itemName + " " + itemsList[i].getLabel();
        }
        if (numItems == 0) {
            return "empty";
        }else{
            return itemName;
        }
    }

    /**
     * Gets Item with given label.
     * @param   label         must not be null or blank
     * @return  Item          requested Item, null on error
     */
    public Item getItem(String label) {
        if (null == label) { return null; }
        label = label.trim();
        for (int i=0; i<numItems; i++) {
            Item item = this.itemsList[i];
            if (label.equals(item.getLabel())) {
                return item;
            }
        }
        return null;
    }
}
