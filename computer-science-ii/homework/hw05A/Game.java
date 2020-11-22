import java.util.Scanner;

/**
 * Game in the text game
 *
 * @authors  Clif Kussmaul; Demo B: James Kmetz, Jason Gerstenfeld, Victor Lora
 * @version 2014-10
 */
public class Game {

    public static final int ACTION_COUNT_MAX    = 40;
    public static final int PLACE_COUNT_MAX     = 10;

    //************************************************************
    //*** instance fields

    private String      label, description;
    private Action []   actionList  = new Action[ACTION_COUNT_MAX];
    private Place  []   placeList   = new Place [PLACE_COUNT_MAX];
    private int         actionCount = 0,
    placeCount  = 0;

    //************************************************************
    //*** constructors

    /**
     * Create new Game with given label and description.
     * @param label         must not be null or blank
     * @param description   must not be null, may be blank
     */
    public Game(String label, String description) {
        setLabel(label);
        setDescription(description);
    }

    //************************************************************
    //*** accessor methods

    /**
     * Gets Game description
     * @return          Game description
     */
    public  String   getDescription() { return this.description; }

    /**
     * Sets Game description
     * @param newDescription
     * @return  true if successful, false otherwise
     */
    public  boolean  setDescription(String newDescription) {
        if (null == newDescription) { return false; }
        newDescription = newDescription.trim();
        this.description = newDescription;
        return true;
    }

        /**
     * Gets Game label
     * @return          Game label
     */
    public  String   getLabel() { return this.label; }

    /**
     * Sets Game label
     * @param newLabel
     * @return  true if successful, false otherwise
     */
    public  boolean  setLabel(String newLabel) {
        if (null == newLabel)       { return false; }
        newLabel = newLabel.trim();
        if (0 == newLabel.length()) { return false; }
        this.label = newLabel;
        return true;
    }

    /**
     * Adds given Action to current Game.
     * @param newAction     must not be null
     * @return              true if successful, false otherwise
     */
    public boolean addAction(Action newAction) {
        if (null == newAction) { return false; }
        // FUTURE: auto-expand actionList when full, prevent duplicates
        if (this.actionList.length == actionCount) { return false; }
        this.actionList[actionCount++] = newAction;
        return true;
    }

    // FUTURE: public Action makeAction(label, desc, prevPlace, nextPlace) { }
    // FUTURE: public Action makeAction(label, desc, prevLabel, nextLabel) { }

    /**
     * Gets Action at given index.
     * @param index         0 <= index < actionCount
     * @return              requested Action, null on error
     */
    public Action getAction(int index) {
        if (index < 0 || index >= this.actionCount) { return null; }
        return this.actionList[index];
    }

    /**
     * Gets Action with given label & previous Place.
     * @param label         must not be null or blank
     * @return              requested Action, null on error
     */
    public Action getAction(String label, Place place) {
        if (null == label || null == place) { return null; }
        label = label.trim();
        for (int i=0; i<actionCount; i++) {
            Action action = this.actionList[i];
            if ( label.equals(action.getLabel())
            && place.equals(action.getPrev ()) ) {
                return action;
            }
        }
        return null;
    }

        /**
     * Gets Action with given label & previous Place label.
     * @param label         must not be null or blank
     * @return              requested Action, null on error
     */
    public Action getAction(String label, String place) {
        if (null == label || null == place) { return null; }
        label = label.trim(); place = place.trim();
        for (int i=0; i<actionCount; i++) {
            Action action = this.actionList[i];
            if ( label.equals(action.getLabel())
            && place.equals(action.getPrev ().getLabel()) ) {
                return action;
            }
        }
        return null;
    }

    /**
     * Gets count of Actions in Game.
     * @return      count of actions
     */
    public int getActionCount() { return this.actionCount; }

    /**
     * Adds given Place to current Game.
     * @param newPlace     must not be null
     * @return              true if successful, false otherwise
     */
    public boolean addPlace(Place newPlace) {
        if (null == newPlace) { return false; }
        // FUTURE: auto-expand placeList when full; prevent duplicate
        if (this.placeList.length == placeCount) { return false; }
        this.placeList[placeCount++] = newPlace;
        return true;
    }

    // FUTURE: public Action makePlace(label, desc) { }

    /**
     * Gets Place at given index.
     * @param index         0 <= index < placeCount
     * @return              requested Place, null on error
     */
    public Place getPlace(int i) {
        if (i < 0 || i >= this.placeCount) { return null; }
        return this.placeList[i];
    }

    /**
     * Gets Place with given label.
     * @param label         must not be null or blank
     * @return              requested Place, null on error
     */
    public Place getPlace(String label) {
        if (null == label) { return null; }
        label = label.trim();
        for (int i=0; i<placeCount; i++) {
            if (label.equals(this.placeList[i].getLabel())) {
                return this.placeList[i];
            }
        }
        return null;
    }

    /**
     * Gets count of Places in Game.
     * @return      count of places
     */
    public int getPlaceCount() { return this.placeCount; }

    //************************************************************
    //*** other methods

    public void play() {
        Action  action;
        Place   place   = getPlace(0);
        Scanner scanner = new Scanner(System.in);
        String  text;

        System.out.println(getDescription());
        while (null != place) {
            System.out.println(place.getDescription());
            text    = scanner.nextLine();
            action  = getAction(text, place);
            if (null != action) {
                System.out.println("");
                System.out.println(action.getDescription());
                place   = action.getNext();
            }
        }
    }

    public static void demoA() {
        Game    game    = new Game("Trumbower", "This is the Trumbower Demo Game.");
        // create & add Places
        Place   outside = new Place("outside",
                "You are outside. There is a door to the north.");
        Place   foyer   = new Place("foyer",
                "You are in the foyer. There are stairs up and down, and a door south.");
        Place   hall0   = new Place("hall0",
                "You are in a hallway leading east and west, and there are stairs up.");
        Place   rm48    = new Place("rm48",
                "You are in a room full of computers and tables. There is a door to the south.");
        game.addPlace(outside);
        game.addPlace(foyer);
        game.addPlace(hall0);
        game.addPlace(rm48);
        // create and add Actions
        game.addAction(new Action("north",  "You enter the building.",  outside,    foyer   ));
        game.addAction(new Action("south",  "You leave the building.",  foyer,      outside ));
        game.addAction(new Action("down",   "You descend the stairs.",  foyer,      hall0   ));
        game.addAction(new Action("up",     "You ascend the stairs.",   hall0,      foyer   ));
        game.addAction(new Action("east",   "You walk down the hall.",  hall0,      rm48    ));
        game.addAction(new Action("south",  "You leave the room.",      rm48,       hall0   ));
        game.play();
    }

    public static void demoB() { 
        Game    game          = new Game("Break Out", "You've woken up in a prison cell of an abandoned prison." +
                                   "\nYour goal is to escape.");
        // create & add Places
        Place   myCell        = new Place("myCell", "You are in the cell you woke up in. There is a mirror to your left "+ 
                                   "\nand an open cell door in front of you. (leave cell/look left)");
        Place   myCellMirror  = new Place("cellMirror",
                                           "You see yourself in the mirror, you need to shave. (shave/leave cell)");
        Place   mirrorShaven  = new Place("shavenMirror", "You are freshly shaven. Looking good!" +
                                               "\n...Why is there a razor here? (leave cell)");
        Place   prisonHall    = new Place("prisonHall", "You are in a prison hallway leading left and right." +
                                         "\nIn front of you, there are stairs leading up. (go left/go right/go up)");                
        Place   prisonHallL   = new Place("hallL", "It's a dead end. There's nothing here. (go back)");        
        Place   prisonHallR   = new Place("hallR", "It's a dead end. There's illegible graffiti" + 
                                        " written everywhere. (go back)");        
        Place   mainFloor     = new Place("mainFloor", "You are on the main floor of the prison." +
                                         "\nThere is a door on your left and a cafeteria to your right." +
                                         "\nYou think you see sunlight through the door. (go left/go right/go down)");
        Place   exitDoor      = new Place("exitDoor", "The door is barred shut from the other side!" + 
                                         "\nYou have to find another way out.... (go back)");
        Place   cafeteria     = new Place("cafeteria", "You are in the cafeteria.The room has the pungent smell " +
                                          "of rotting meat. \nThe kitchen is in front of you." +
                                          "(go back/go forward)");
        Place   kitchen       = new Place("kitchen", "You are in the kitchen. The smell has gotten stronger." +
                                          "\nIt seems to be coming from the freezer to your left. (go back/ go left)");
        Place   freezer       = new Place("freezer", "You are in the freezer. It still appears to be working." +
                                          "\nThere is a fresh corpse sitting in the corner." +
                                          "\nAs you enter, the door closes behind you! You're trapped! (open door)");
        Place   lockedFreezer = new Place("lockedFreezer", "You are trapped! Something is written on the door. It reads:" +
                                          "\nNEED ITEMS TO CONTINUE");                           
        game.addPlace(myCell);
        game.addPlace(myCellMirror);
        game.addPlace(mirrorShaven);
        game.addPlace(prisonHall);
        game.addPlace(prisonHallL);
        game.addPlace(prisonHallR);
        game.addPlace(mainFloor);
        game.addPlace(exitDoor);
        game.addPlace(cafeteria);
        game.addPlace(kitchen);
        game.addPlace(freezer);
        game.addPlace(lockedFreezer);
        
        // create and add Actions
        game.addAction(new Action("leave cell", "You leave your cell.",         myCell,             prisonHall));
        game.addAction(new Action("look left",  "You look left.",               myCell,             myCellMirror));
        
        game.addAction(new Action("shave",      "You shave.",                   myCellMirror,       mirrorShaven));
        game.addAction(new Action("leave cell", "You leave your cell.",         myCellMirror,       prisonHall));
        
        game.addAction(new Action("leave cell", "You leave your cell.",         mirrorShaven,       prisonHall));
        
        game.addAction(new Action("go left",    "You walk to the left.",        prisonHall,         prisonHallL));
        game.addAction(new Action("go right",   "You walk to the right.",       prisonHall,         prisonHallR));
        game.addAction(new Action("go up",      "You ascend the stairs.",       prisonHall,         mainFloor));
        game.addAction(new Action("go up",      "You ascend the stairs.",       prisonHall,         mainFloor));
        
        game.addAction(new Action("go back",    "You're back at your cell.",    prisonHallL,        prisonHall));
        
        game.addAction(new Action("go back",    "You're back at your cell.",    prisonHallR,        prisonHall));
        
        game.addAction(new Action("go right",   "You go into the cafeteria.",   mainFloor,          cafeteria));
        game.addAction(new Action("go left",    "You go to the exit door.",     mainFloor,          exitDoor));
        game.addAction(new Action("go down",    "You descend the stairs.",      mainFloor,          prisonHall));
        
        game.addAction(new Action("go back",    "You go back to the lobby.",    exitDoor,           mainFloor));
        
        game.addAction(new Action("go back",    "You go back to the lobby.",    cafeteria,          mainFloor));
        game.addAction(new Action("go forward", "You go into the kitchen.",     cafeteria,          kitchen));
        
        game.addAction(new Action("go back",    "You go into the cafeteria.",   kitchen,            cafeteria));
        game.addAction(new Action("go left",    "You go into the freezer.",     kitchen,            freezer));
        
        game.addAction(new Action("open door",  "The door is locked.",          freezer,            lockedFreezer));
        
        game.play();
    }
} // end class
