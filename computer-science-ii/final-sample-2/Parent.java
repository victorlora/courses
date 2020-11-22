
/**
 * Abstract class Parent - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Parent
{
    //**************************************************************
    //***Instance fields
    private String name;
    private int    phoneNumber;
    private long    studentID;

    //**************************************************************
    //***Constructor
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the sum of x and y 
     */
    public Parent(String name, int phoneNumber, long studentID)
    {
        setName(name);
        setPhoneNumber(phoneNumber);
        setID(studentID);
    }// end Parent constructor

    //**************************************************************
    //***get/set methods

    /**
     * Returns the persons name
     * @returns String      name
     */
    public String getName()
    {
        return this.name;
    }// end getName() method

    /**
     * Sets the name
     * @param   String      name
     */
    public void setName(String newName)
    {
        if (null==newName) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        else if (newName.length() == 0) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        else if (newName.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        else if (newName.equals(" ")) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = newName;
    }// end setName() method

    /**
     * Returns the phone number
     * @returns int      phone number
     */
    public int getPhoneNumber()
    {
        return this.phoneNumber;
    }// end getPhoneNubmer() method

    /**
     * Sets the name
     * @param   int      phone number
     */
    public void setPhoneNumber(int newNumber)
    {
        int length = String.valueOf(newNumber).length();
        if (newNumber < 0) {
            throw new IllegalArgumentException("Number cannot be less" +
                "than zero");
        }
        if (length < 10)    {
            throw new IllegalArgumentException("Phone numbers require 9 digits");
        }
        this.phoneNumber = newNumber;
    }// end setPhoneNumber() method

    /**
     * Returns the ID
     * @returns int      phone number
     */
    public long getID()
    {
        return this.studentID;
    }// end getID() method

    /**
     * Sets the ID
     * @param   int      student ID starting with the number after the -
     */
    public void setID(long newID)
    {
        int length = String.valueOf(newID).length();
        if (newID < 0) {
            throw new IllegalArgumentException("ID cannot be less" +
                "than zero");
        }
        if (length < 7)    {
            throw new IllegalArgumentException("ID numbers require 7 digits");
        }
        this.studentID = newID;
    }// end setID() method
}// end Parent class
