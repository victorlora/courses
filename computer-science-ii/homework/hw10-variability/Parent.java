import java.util.*;
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
    private ArrayList <String>  descriptionList = new ArrayList <String>  ();

    public static final Random      random = new Random();

    //************************************************************
    // constructors

    public Parent(String label, String description)
    {
        setLabel(label);
        setDescription(description);
    }

    public Parent(String label, ArrayList <String> description)
    {
        setLabel(label);
        setArrayDescription(description);
    }
    //************************************************************
    // accessor methods

    /**
     * Gets the description.
     * @return description.
     */
    public  String  getDescription()    { 
        if (descriptionList.size() != 0) {
            int i = random.nextInt(descriptionList.size());
            return descriptionList.get(i);
        }else {
            return this.description; 
        }
    }

    /**
     * Sets the description.
     * @param       String      newDescription
     * @throws      Exception   if newDescription is null
     */
    public  void setDescription(String newDescription) {
        if (null == newDescription)     {
            throw new IllegalArgumentException("Description cannot be null");
        }

        newDescription      = newDescription.trim();
        this.description    = newDescription;
    }

    /**
     * Sets the description.
     * @param       ArrayList   newDescription
     * @throws      Exception   if newDescription is null
     */
    public  void setArrayDescription(ArrayList <String> newDescription) {
        if (newDescription.contains(null))     {
            throw new IllegalArgumentException("Descriptions cannot be null");
        }

        this.descriptionList = newDescription;
    }

    /**
     * Sets the description.
     * @return true if description is set, false is description is null.
     */
    public  String  getLabel()      { return this.label; }

    /**
     * Sets the label.
     * @param       String      newDescription
     * @throws      Exception   if newLabel is null
     *                          if newLabel is empty
     *                          if newLabel is blank
     */
    public  boolean setLabel(String newLabel) {
        if (null == newLabel)     {
            throw new IllegalArgumentException("Label cannot be null");
        }
        if (newLabel.equals("")) {
            throw new IllegalArgumentException("Label cannot be empty");
        }
        if (newLabel.equals(" ")) {
            throw new IllegalArgumentException("Label cannot be blank");
        }
        newLabel    = newLabel.trim();
        this.label  = newLabel;
        return true;
    }
}
