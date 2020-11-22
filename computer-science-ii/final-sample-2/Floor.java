import java.util.*;
/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor
{
    //**************************************************************************************************
    //***Instance Field
    private int floorNumber;
    private RA  residentAdvisor;
    private ArrayList <Room> roomList    = new ArrayList <Room> ();

    //**************************************************************************************************
    //***Constructors
    /**
     * Constructor for a room with 1 resident
     */
    public Floor(int floorNumber, RA residentAdvisor, ArrayList <Room> roomList)
    {
        setFloorNumber(floorNumber);
        setRA(residentAdvisor);
        setRoomList(roomList);
    }

    //**************************************************************************************************
    //***Get/Set Methods

    public int getFloorNumber()
    {
        return this.floorNumber;
    }

    public void setFloorNumber(int newFloorNumber)
    {
        if (newFloorNumber < 0)  {
            throw new IllegalArgumentException("Floor numbers cannot be negative");
        }
        this.floorNumber = newFloorNumber;
    }

    public String getRA()
    {
        return this.residentAdvisor.getName();
    }

    public void setRA(RA newResidentAdvisor)
    {
        this.residentAdvisor = newResidentAdvisor;
    }
    
    public String getRoomList() 
    {
        String roomList = "";
        for(int i=0; i<this.roomList.size(); i++) {
            roomList = roomList + this.roomList.get(i).getRoomNumber() + "\n";
        }
        return roomList;
    }
        
    public void setRoomList(ArrayList <Room> newRoomList)
    {
        this.roomList = newRoomList;
    }
}
