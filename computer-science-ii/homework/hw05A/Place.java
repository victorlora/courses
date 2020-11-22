/**
 * Place in the text game
 * 
 * @author  Clif Kussmaul
 * @version 2012-10
 */
public class Place {

    //************************************************************
    // instance fields
    private String  label, description;

    //************************************************************
    // constructors
    
    /**
     * Creates new Place with given label & description
     * @param label
     * @param description
     */
    public Place(String label, String description) {
        setLabel(label);
        setDescription(description);
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
    
} // end class
