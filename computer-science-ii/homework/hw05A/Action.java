
/**
 * Action in text game.
 * 
 * @authors  James Kmetz, Jason Gerstenfeld, Victor Lora
 * @version 2014-10
 */
public class Action
{
    //************************************************************
    // instance fields
    private String  label, description;
    private Place next;
    private Place prev;

    //************************************************************
    // constructors

    /**
     * Creates new Place with given label & description
     * @param label
     * @param description
     */
    public Action (String label, String description, Place prev, Place next) {
        setLabel(label);
        setDescription(description);
        setPrev(prev);
        setNext(next);
    }

    //************************************************************
    // accessor methods

    public  String  getDescription()    { return this.description; }

    public  boolean setDescription(String newDescription) {
        if (null == newDescription)     { return false; }
        newDescription      = newDescription.trim();
        this.description    = newDescription;
        return true;
    }

    public  String  getLabel()      { return this.label; }

    public  boolean setLabel(String newLabel) {
        if (null == newLabel)       { return false; }
        newLabel    = newLabel.trim();
        if (0 == newLabel.length()) { return false; }
        this.label  = newLabel;
        return true;
    }

    public Place getPrev()  {return this.prev;}
    public boolean setPrev(Place newPrev) {
        if (null == newPrev)    {return false;}
        this.prev = newPrev;
        return true;
    }

    public Place getNext() {return this.next;}

    public boolean setNext(Place newNext) {
        if (null == newNext)    {return false;}
        this.next = newNext;
        return true;
    }

}

