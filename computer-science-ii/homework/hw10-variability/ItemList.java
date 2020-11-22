import java.util.*;
/**
 * Keeps track of items belonging to different objects such
 * as players or places
 * 
 *@authors  James Kmetz, Jason Gerstenfeld, Victor Lora
 *           Variability: Victor and Adrian
 * @version 2014-10
 */
public abstract class ItemList extends Parent
{
    //************************************************************
    //*** instance fields
    
    private ArrayList <Item> itemsList = new ArrayList <Item>();
    private int numItems = 0;

    //************************************************************
    // constructors

    public ItemList (String label, String description)
    {
        super(label, description);
    }
    
     public ItemList (String label, ArrayList <String> description)
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
        return this.itemsList.contains(item);
    }

    /**
     * Adds a newItem to the objects inventory
     * @param   Item        newItem
     * @return  boolean     True  - if item is added succesfully
     * @throws  Exception   if item is null
     */
    public boolean addItem(Item newItem)
    {
        if (null == newItem) { 
            throw new IllegalArgumentException("The item cannot be null");
        }
        // FUTURE: auto-expand placeList when full; prevent duplicate
        this.itemsList.add(newItem);
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
        if (null == oldItem) {return false;}
        this.itemsList.remove(oldItem);
        return true;
    }

    /**
     * Returns a string with an object item's name
     * @param   ---
     * @return  String      item lable/name
     */
    public String getItemNames()
    {
        String itemName = "";
        for (int i=0; i<this.itemsList.size(); i++) {
            itemName = itemName + " " + itemsList.get(i).getLabel();
        }
        if (this.itemsList.size() == 0) {
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
        for (int i=0; i<this.itemsList.size(); i++) {
            Item item = this.itemsList.get(i);
            if (label.equals(item.getLabel())) {
                return item;
            }
        }
        return null;
    }
}
