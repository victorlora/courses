
/**
 * Action in text game.
 * 
 * @authors  James Kmetz, Jason Gerstenfeld, Victor Lora
 * @version 2014-10
 */
public class Action extends Parent
{
    //************************************************************
    //***constant field

    public static final int ITEM_COUNT_MAX = 60;

    // ***********************************************************
    //***instance fields

    private Place    next;
    private Place    prev;

    //private ItemList required    = new Item[ITEM_COUNT_MAX];
    //private ItemList forbidden   = new Item[ITEM_COUNT_MAX];
    // use ItemList methods instead of those below.
    private Item [] required    = new Item[ITEM_COUNT_MAX];
    private Item [] forbidden   = new Item[ITEM_COUNT_MAX];

    // ***********************************************************
    //***class fields

    int numRequired    = 0;
    int numForbidden   = 0;

    //************************************************************
    //***constructors

    /**
     * Creates new Action with given label & description,
     * previous and next place
     * @param String        label
     *                      description
     *        Place         previous place
     *                      next place
     */
    public Action (String label, String description,
    Place prev, Place next) 
    {
        super(label, description);
        setPrev(prev);
        setNext(next);
    }

    /**
     * Creates new Action with given label & description
     * @param   String      label
     *          String      description
     * @return  ---
     */
    public Action (String label, String description)
    {
        super(label, description);
    } // end Action constructor

    //************************************************************
    // accessor/mutator methods

    /**
     * Gets the previous place.
     * @return previous place
     */
    public Place getPrev()       {return this.prev;}

    /**
     * Sets the previous place
     * @param       Place       previous place
     * @throws      Exception   if Previous place is null
     */
    public void setPrev(Place newPrev)
    {
        if (null == newPrev) {
            throw new IllegalArgumentException("Previous place cannot be null");
        }
        this.prev = newPrev;
    } // end setPrev() method

    /**
     * Gets the next place.
     * @return next place
     */
    public Place getNext()       {return this.next;}

    /**
     * Sets the next place
     * @param       Place       next/new place
     * @throws      Exception   if Next place is null
     */
    public void setNext(Place newNext)
    {
        if (null == newNext) {
            throw new IllegalArgumentException("Next place cannot be null");
        }
        this.next = newNext;
    } // end setNext() method

    /**
     * Adds an item to the list of required items.
     * @param       Item        newItem
     * @throws      Exception   if newItem is null 
     *                          if required array is full
     */
    public void addRequired(Item newItem)
    {
        if (null == newItem) { 
            throw new IllegalArgumentException("Required item cannot be null");
        }
        // FUTURE: auto-expand placeList when full; prevent duplicate
        if (this.required.length == numRequired) {
            throw new IllegalArgumentException("Required array list is full");
        }
        this.required[numRequired++] = newItem;
    } // end addRequired() method

    /**
     * Add an item to the list of forbidden items.
     * @param       Item        newItem
     * @throws      Exception   if newItem is null 
     *                          if forbidden array is full
     */
    public void addForbidden(Item newItem)
    {
        if (null == newItem) { 
            throw new IllegalArgumentException("Forbidden item cannot be null");
        }
        // FUTURE: auto-expand placeList when full; prevent duplicate
        if (this.required.length == numForbidden) {
            throw new IllegalArgumentException("Forbidden array list is full");
        }
        this.forbidden[numForbidden++] = newItem;
    } // end addForbidden() method

    /**
     * Returns a string with the Required item name
     * @param   ---
     * @return  String      item lable/name
     */
    public String getRequiredItemNames(Player player)
    {
        String itemName = "";
        for (int i=0; i<numRequired; i++) {
            if (!player.hasItem(required[i])) {
                itemName = itemName + " " + required[i].getLabel();
            }
        }
        if (numRequired == 0) {
            return "empty";
        }else{
            return itemName;
        }
    }

    /**
     * Returns a string with the Required item name
     * @param   ---
     * @return  String      item lable/name
     */
    public String getForbiddenItemNames(Player player)
    {
        String itemName = "";
        for (int i=0; i<numForbidden; i++) {
            if (!player.hasItem(forbidden[i])) {
                itemName = itemName + " " + forbidden[i].getLabel();
            }
        }
        if (numForbidden == 0) {
            return "empty";
        }else{
            return itemName;
        }
    }

    /**
     * Check if the player has a required item
     * @return true if player has the item, false if null or player does not have required item
     */
    public boolean hasRequired(Player player, int print)
    {
        if (null == player)                  { return false; }
        if (numRequired == 0)                { return true; }
        for (int i=0; i<numRequired; i++) {
            if (!player.hasItem(required[i])) {
                if (print == 1) {
                    System.out.println("You lack: " + getRequiredItemNames(player));
                }
                return false;
            }
        }
        return true;
    } // end hasRequired() method

    /**
     * Check if the player has a forbidden item
     * @return true if player has the item, false if null or player does not have the item
     */
    public boolean hasForbidden(Player player, int print)
    {
        if (null == player)                { return false; }
        if (numForbidden == 0)                { return false; }
        for (int i=0; i<numForbidden; i++){
            if (player.hasItem(forbidden[i])) {
                if (print == 1) {
                    System.out.println("You possess " + getForbiddenItemNames(player) + 
                        " , which are forbidden. Remove them from your inventory to continue");
                }
                return true;
            }
        }
        return false;
    } // end hasForbidden() method

    /**
     * Makes sure the player both has the required items and doesn't have the forbidden items
     * @return true if player passes both and false if player does not.
     */
    public boolean allowedFor(Player player, int print)
    {
        if (null == player)                { return false; }
        if (hasRequired(player, print) && !hasForbidden(player, print)) {
            return true;
        }else{
            return false;
        }
    } // end allowedFor() method
} // end class
