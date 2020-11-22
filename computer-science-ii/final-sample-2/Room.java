import java.util.*;
/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room
{
    //**************************************************************************************************
    //***Instance Field
    private int roomNumber;
    private ArrayList <Resident> roomResidents = new ArrayList <Resident> ();

    //**************************************************************************************************
    //***Constructors
    /**
     * Constructor for a room with 1 resident
     */
    public Room(int roomNumber, Resident resident)
    {
        setRoomNumber(roomNumber);
        setSingleRoom(resident);
    }

    /**
     * Constructor for a room with 2 resident
     */
    public Room(int roomNumber, Resident resident1, Resident resident2)
    {
        setRoomNumber(roomNumber);
        setDoubleRoom(resident1, resident2);
    }

    /**
     * Constructor for a room with 4 resident
     */
    public Room(int roomNumber, Resident resident1, Resident resident2, 
    Resident resident3, Resident resident4)
    {
        setRoomNumber(roomNumber);
        setQuadRoom(resident1, resident2, resident3, resident4);
    }

    //**************************************************************************************************
    //***Get/Set Methods

    public int getRoomNumber()
    {
        return this.roomNumber;
    }

    public void setRoomNumber(int newRoomNumber)
    {
        if (newRoomNumber < 0)  {
            throw new IllegalArgumentException("Room numbers cannot be negative");
        }
        this.roomNumber = newRoomNumber;
    }

    public String getResidents()
    {
        String roomResidents = "";
        for(int i=0; i<this.roomResidents.size(); i++) {
            roomResidents = roomResidents + this.roomResidents.get(i).getName() + " ";
        }
        return roomResidents;
    }

    public void setSingleRoom(Resident newResident)
    {
        roomResidents.add(newResident);
    }
    
    public void setDoubleRoom(Resident newResident1, Resident newResident2)
    {
        roomResidents.add(newResident1);
        roomResidents.add(newResident2);
    }
    
    public void setQuadRoom(Resident newResident1, Resident newResident2,
                            Resident newResident3, Resident newResident4)
    {
        roomResidents.add(newResident1);
        roomResidents.add(newResident2);
        roomResidents.add(newResident3);
        roomResidents.add(newResident4);
    }
}
