
/**
 * Abstract class Parent - write a description of the class here
 * 
 * @author  Jason G., James K., Victor L. 
 * @version 2014-10 V1.1.0
 */
public abstract class Parent
{
    //************************************************************
    //*** instance fields
    
    private String  label, description;

    //************************************************************
    // constructors
    
    public Parent(String label, String description)
    {
        setLabel(label);
        setDescription(description);
    }
    
    //************************************************************
    // accessor methods
    
    /**
     * Gets the description.
     * @return description.
     */
    public  String  getDescription()    { return this.description; }
    
    /**
     * Sets the description.
     * @return true if description is set, false is description is null.
     */
    public  boolean setDescription(String newDescription) {
        if (null == newDescription)     { return false; }
        newDescription      = newDescription.trim();
        this.description    = newDescription;
        return true;
    }
    
    /**
     * Sets the description.
     * @return true if description is set, false is description is null.
     */
    public  String  getLabel()      { return this.label; }
    
    /**
     * Sets the label.
     * @return true if label is set, false is label is null or contains nothing.
     */
    public  boolean setLabel(String newLabel) {
        if (null == newLabel)       { return false; }
        newLabel    = newLabel.trim();
        if (0 == newLabel.length()) { return false; }
        this.label  = newLabel;
        return true;
    }
}
