
/**
 * Write a description of class RA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RA extends Parent
{
    //******************************************************************************
    //***Instance Field
    private String title;
    private int    budget;

    //******************************************************************************
    //***Constructor
    /**
     * Constructor for objects of class RA
     */
    public RA(String name, int phoneNumber, long studentID, String title, int budget)
    {
        super(name, phoneNumber, studentID);
        setTitle(title);
        setBudget(budget);
    }
    
    //******************************************************************************
    //***Get/Set Methods
    
    /**
     * Gets the RA title
     * @returns String      title
     */
    public String getTitle()
    {
        return this.title;
    }
    
    /**
     * Sets the RA title
     * @param   String      title
     */
    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }
    
    /**
     * Gets budget
     * @return  int     budget
     */
    public int getBudget()
    {
        return this.budget;
    }
    
    /**
     * Sets budget
     * @params  int     budget
     */
    public void setBudget(int newBudget)
    {
        if (newBudget < 0)  {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.budget = newBudget;
    }
}
