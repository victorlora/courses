import java.util.*;
/**
 * Write a description of class Building here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Building
{
    // instance variables - replace the example below with your own
    private String buildingName;
    private ResidentManager HR;
    private ArrayList <Floor> floorList = new ArrayList <Floor> ();
    

    /**
     * Constructor for objects of class Building
     */
    public Building(String buildingName, ResidentManager HR, ArrayList <Floor> floors)
    {
        setBuildingName(buildingName);
        setHR(HR);
        setFloorList(floors);
    }

    public String getName()
    {
        return this.buildingName;
    }
    
    public void setBuildingName(String newBuildingName)
    {
        this.buildingName = newBuildingName;
    }
    
    public String getHR()
    {
        return this.HR.getName();
    }
    
    public void setHR(ResidentManager newHR)
    {
       this.HR = newHR; 
    }
    
    public String getFloorList()
    {
        String floorList = "";
        for(int i=0; i<this.floorList.size(); i++)   {
            floorList = floorList + this.floorList.get(i).getFloorNumber() + " ";
        }
        return floorList;
    }
    
    public void setFloorList(ArrayList <Floor> newFloors)
    {
        this.floorList = newFloors;
    }
}
