import java.util.*;
/**
 * Place in the text game
 * 
 * @author  Jason G., James K., Victor L.
 *          Variability: Victor and Adrian
 * @version 2012-10
 */
public class Place extends ItemList{

    //************************************************************
    // constructors

    /**
     * Creates new Place with given label & description
     * @param   String      label
     *          String      description
     * @return  ---
     */
    public Place(String label, String description) 
    {
        super(label, description);
    } // ends Place constructor

    /**
     * Creates new Place with given label & description
     * @param   String      label
     *          String      description
     * @return  ---
     */
    public Place(String label, ArrayList <String> description) 
    {
        super(label, description);
    } // ends Place constructor
} // end class
