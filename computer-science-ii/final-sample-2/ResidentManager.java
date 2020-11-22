import java.util.*;
/**
 * Write a description of class ResidentManagers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResidentManager extends Parent
{
    //**************************************************************************************************
    //***Instance Field
    private ArrayList <RA> RAList    = new ArrayList <RA> ();

    //**************************************************************************************************
    //***Constructor
    /**
     * Constructor for objects of class ResidentManagers
     */
    public ResidentManager(String name, int phoneNumber, long studentID, ArrayList <RA> RAList)
    {
       super(name, phoneNumber, studentID);
       setRAList(RAList);
    }

    //**************************************************************************************************
    //***Get/Set Methods
    
    public String getRAList()
    {
        String RAList = "";
        for(int i=0; i<this.RAList.size(); i++) {
            RAList = RAList + this.RAList.get(i).getName();
        }
        return RAList;
    }
    
    public void setRAList(ArrayList <RA> newRAList)
    {
        if (newRAList.size() == 0) {
            throw new IllegalArgumentException ("RA List cannot be empty");
        }
        this.RAList = newRAList;
    }
}
