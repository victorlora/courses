import java.util.*;

/**
 * Game in the text game
 *
 * @authors  Clif Kussmaul; 
 *           prisonEscape: James Kmetz, Jason Gerstenfeld, Victor Lora
 *           Variability: Victor and Adrian
 * @version 2014-10
 */
public class Game extends ItemList{

    //************************************************************
    //***Constant scanner

    public static Scanner scanner = new Scanner(System.in);

    //************************************************************
    //*** instance fields

    private ArrayList <Action> actionList  = new ArrayList <Action> ();
    private ArrayList <Place>  placeList   = new ArrayList <Place>  ();
    private Player    player;
    private int       actionCount = 0,
                      placeCount  = 0;

    //************************************************************
    //*** constructors

    /**
     * Create new Game with given label and description.
     * 
     *@param label         must not be null or blank
     *       description   must not be null, may be blank
     *        
     *
     */
    public Game(String label, String description) {
        super(label, description);
    } // end Game constructor

    //************************************************************
    //*** accessor methods

    /**
     * Gets Game description
     * @param   ---
     * @return  Game          Game description
     */

    /**
     * Adds given Action to current Game.
     * @param       newAction     must not be null
     * @throws      Exception     if newAction is null
     */
    public void addAction(Action newAction)
    {
        if (null == newAction) { 
            throw new IllegalArgumentException("New action cannot be null");
        }
        this.actionList.add(newAction);
    } // end addAction() method

    // FUTURE: public Action makeAction(label, desc, prevPlace, nextPlace) { }
    // FUTURE: public Action makeAction(label, desc, prevLabel, nextLabel) { }

    /**
     * Gets Action at given index.
     * @param   index         0 <= index < actionCount
     * @return  Action        requested Action, null on error
     */
    public Action getAction(int index)
    {
        if (index < 0 || index >= this.actionList.size()) { return null; }
        return this.actionList.get(index);
    } // end getAction(int) method

    /**
     * Gets Action with given label & previous Place.
     * @param   label         must not be null or blank
     * @return  Action        requested Action, null on error
     */
    public Action getAction(String label, Place place)
    {
        if (null == label || null == place) { return null; }
        label = label.trim();
        for (int i=0; i<this.actionList.size(); i++) {
            Action action = this.actionList.get(i);
            if ( label.equalsIgnoreCase(action.getLabel())
            && place.equals(action.getPrev ()) ) {
                return action;
            }
        }
        return null;
    } // end getAction(String, Place) method

    /**
     * Gets Action with given label & previous Place label.
     * @param   label         must not be null or blank
     * @return  Action        requested Action, null on error
     */
    public Action getAction(String label, String place)
    {
        if (null == label || null == place) { return null; }
        label = label.trim(); place = place.trim();
        for (int i=0; i<this.actionList.size(); i++) {
            Action action = this.actionList.get(i);
            if ( label.equalsIgnoreCase(action.getLabel())
            && place.equals(action.getPrev().getLabel()) ) {
                return action;
            }
        }
        return null;
    } // end getAction(String, String) method

    /**
     * Gets Action with given label
     * @param   String              must not be null or blank
     * @return  Action              requested Player Action if found in actionList 
     *                              null - otherwise
     */
    public String getPlayerAction(String label)
    {
        if (null == label) { return null; }
        label = label.trim();
        for (int i=0; i<actionList.size(); i++) {
            Action action = this.actionList.get(i);
            if (label.equalsIgnoreCase(action.getLabel())) {
                return action.getLabel();
            }
        }
        return null;
    } // end getPlayerAction() method

    /**
     * Gets count of Actions in Game.
     * @param   ---
     * @return  int         count of actions
     */
    public int getActionCount() { return this.actionList.size(); }

    /**
     * Adds given Place to current Game.
     * @param       newPlace      must not be null
     * @throws      Exception     if newPlace is null         
     */
    public void addPlace(Place newPlace)
    {
        if (null == newPlace) { 
            throw new IllegalArgumentException("New place cannot be null");
        }
        this.placeList.add(newPlace);
    } // end addPlace() method

    // FUTURE: public Action makePlace(label, desc) { }

    /**
     * Gets Place at given index.
     * @param   index         0 <= index < placeCount
     * @return  Place         requested Place, null on error
     */
    public Place getPlace(int i)
    {
        if (i < 0 || i >= this.placeList.size()) { return null; }
        return this.placeList.get(i);
    } // end getPlace(int) method

    /**
     * Gets Place with given label.
     * @param   label         must not be null or blank
     * @return  Place         requested Place, null on error
     */
    public Place getPlace(String label)
    {
        if (null == label) { return null; }
        label = label.trim();
        for (int i=0; i<placeList.size(); i++) {
            if (label.equals(this.placeList.get(i).getLabel())) {
                return this.placeList.get(i);
            }
        }
        return null;
    } // end getPlace(String) method

    /**
     * Gets count of Places in Game. 
     * @param   --- 
     * @return  int     count of places
     */
    public int getPlaceCount() { return this.placeList.size(); }

     /**
     * Adds given Player to current Game.
     * @param       newPlayer     must not be null
     * @throws      Exception     if newPlayer is null
     */
    public void addPlayer(Player newPlayer)
    {
        if (null == newPlayer) { 
            throw new IllegalArgumentException("New player cannot be null");
        }
        // FUTURE: auto-expand actionList when full, prevent duplicates
        this.player = newPlayer;
    } // end addPlayer() method

    /**
     * Allows player to drop an item at a place
     * @param   Place       current place
     *          Player      player
     */
    public void playerDrop(Player player, Place place)
    {
        String userChoice = null;
        Item   item       = null;
        boolean done      = false;

        while(!done) {
            try {
                userChoice = scanner.nextLine();
                userChoice = userChoice.trim();
                item       = player.getItem(userChoice);

                player.removeItem(item);
                place.addItem(item);

                done = true;
            }
            catch (IllegalArgumentException e) {
                if (e.getMessage().equals("The item cannot be null")) {
                    System.out.println("");
                    System.out.println("That item is not in your inventory.");
                    break;
                }else{
                    System.out.println(e.getMessage());
                    System.out.println("You must expand the array");
                }
            }
        }

        if (null != item) {
            System.out.println("You dropped: " + userChoice);
        }
    }

    /**
     * Allows player to pick up an item at a place
     * @param   Place       current place
     *          Player      player
     */
    public void playerPickUp(Player player, Place place) 
    {
        String userChoice;
        Item   item = null;
        boolean done      = false;

        while(!done) {
            try {
                userChoice = scanner.nextLine();
                userChoice = userChoice.trim();
                item       = place.getItem(userChoice);

                place.removeItem(item);
                player.addItem(item);

                done = true;
            }
            catch (IllegalArgumentException e) {
                if (e.getMessage().equals("The item cannot be null")) {
                    System.out.println("");
                    System.out.println("That item is not in the room.");
                    break;
                }else{
                    System.out.println(e.getMessage());
                    System.out.println("You must expand the array");
                }
            }
        }

        if (null != item) {
            System.out.println("You picked up: " + item.getDescription());
        }
    }
    //************************************************************
    //*** other methods

    /**
     * Runs the game.
     * Displays descriptions and labels.
     * Requests user input and calls appropriate methods.
     */
    public void play()
    {
        Action  action  = null;
        Place   place   = getPlace(0);
        String  text;
        String  playerAction = null;

        System.out.println(getDescription());
        while (null != place) {
            if (place != getPlace(0) &&
            (null == playerAction || null == action) )
            {
                System.out.println("Try again.");
            }
            else if (null == playerAction ||
            !playerAction.equals("examine") &&
            !playerAction.equals("drop")    &&
            !playerAction.equals("pick up") &&
            action.allowedFor(player, -1))
            {
                System.out.println(place.getDescription());
            }
            else if(null == playerAction && null == action) {
                System.out.println("Try again");
            }
            
           
            text         = scanner.nextLine();
            action       = getAction(text, place);
            playerAction = getPlayerAction(text);

            if (text.equalsIgnoreCase("quit")) {
                System.out.println("");
                System.out.println("You have quit the game.");
                System.exit(0);
            }
            else if (null != action && action.allowedFor(player, 1)) {
                System.out.println("");
                System.out.println(action.getDescription());
                place = action.getNext();
            }
            else if (null == playerAction ) {
                System.out.println("That is not a valid command.");
            }
            else if (playerAction.equals("examine")) {
                String items = place.getItemNames();
                if (items.equals("empty")) {
                    System.out.println("There are no items in this room.");
                }else{
                    System.out.println("You see a: " + items);
                }
            }
            else if (playerAction.equals("drop") ) {
                playerDrop(player, place);
            }
            else if(playerAction.equals("pick up")) {
                playerPickUp(player, place);
            }
        }
    } // end play() method

    public static void prisonEscape()
    { 
        Game    game          = new Game("Break Out", "You've woken up in a prison cell of an abandoned prison." +
                "\nYour goal is to escape." +
                "\nYou can use 'examine' to search rooms for items, 'pick up' to start picking up an item, " +
                "\nand 'drop' to start dropping an item.");

        Player  prisoner  = new Player("Prisoner", "You are the main character.");
        game.addPlayer(prisoner);
        // create & add Places
        ArrayList <String> myCellDescription = new ArrayList <String> ();
        myCellDescription.add("You are in the cell you woke up in. There is a mirror to your left "+ 
                "\n and a cell door in front of you. (leave cell/look left)");
        myCellDescription.add("You are in your cell. You look around and see a mirror to your left" + 
                "\n there is also a cell door in front of you. (leave cell/look left)");
        Place   myCell        = new Place("myCell", myCellDescription );
        Place   myCellMirror  = new Place("cellMirror",
                "You see yourself in the mirror, you need to shave. (shave/go back)");
        Place   mirrorShaven  = new Place("shavenMirror", "You are freshly shaven. Looking good!" +
                "\n...Why is there a razor here? (leave cell)");
        Place   prisonHall    = new Place("prisonHall", "You are in a prison hallway leading left and right." +
                "\nIn front of you, there are stairs leading up. (go left/go right/go up)");                
        Place   prisonHallL   = new Place("hallL", "It's a dead end. There's nothing here. (go back)");        
        Place   cellBlockB    = new Place("cellBlockB", "You enter what appears to be Cell Block B, " +
                "judging from the giant B \non the wall. All but one cell appears to be locked. " +
                "\nAlong the left wall, you see the Security room. (enter cell/enter security/go back)");
        Place   openCellB     = new Place("openCellB", "The decrepit cell contains a cot and a bucket, " +
                "but not much else. \nThe bucket has flies swarming it. (search cot/search bucket/go back)");
        Place   cellCot       = new Place("cellCot", "You search the cot area. There appears to be something " +
                "inside the mattress. (cut mattress/go back)");
        Place   cellCotCut    = new Place("cellCotCut", "Inside the mattress, you find a keycard. Seems " +
                "whoever was held here was \nintent on escaping. (pick up/exit cell)");
        Place   cellBucket    = new Place("cellBucket", "You search the bucket of dried-up feces. Yuck! " +
                "Inside, you see a rather smelly pocket knife. \nDid an inmate swallow this at one point? (pick up/go back)");
        Place   security      = new Place("security", "All the security monitors appear to be giving off static. " +
                "\nThere is a stash of flashlights next to the wall of TVs. (pick up/go back)");
        Place   mainFloor     = new Place("mainFloor", "You are on the main floor of the prison. It's pitch black in here." +
                "\nThere is a door on your left and a cafeteria to your right." +
                "\nYou think you see sunlight through the door. (go left/go right/go down)");
        Place   exitDoor      = new Place("exitDoor", "The door is barred shut from the other side!" + 
                "\nYou have to find another way out.... (go back)");
        Place   cafeteria     = new Place("cafeteria", "You are in the cafeteria.The room has the pungent smell " +
                "of rotting meat. \nThe kitchen is in front of you." +
                "(go back/go forward)");
        Place   kitchen       = new Place("kitchen", "You are in the kitchen. The smell has gotten stronger." +
                "\nIt seems to be coming from the freezer to your left. (go back/go left)");
        Place   freezer       = new Place("freezer", "You are in the freezer. It still appears to be working." +
                "\nThere is a fresh corpse sitting in the corner." +
                "\nAs you enter, the door closes behind you! You're trapped! (search corpse/open door)");         
        Place   freezerCorpse = new Place("freezerCorpse", "This guy hasn't been dead too long." +
                "\nHe has stitches covering a wound on his thigh. (cut stitches/continue freezing)");
        Place   cutCorpse = new Place("cutCorpse", "With the stitches cut, a blood-soaked key rolls " +
                "\nonto the ground at your feet. (pick up/open door)");
        Place   kitchen2 = new Place("kitchen2", "You're back in the kitchen. Muddy footprints lead" +
                "\ninto the Cafeteria. (enter cafeteria)");
        Place   cafeteria2 = new Place("cafeteria2","The footprints lead left, right into a wall." +
                "\n(face wall/enter lobby/enter kitchen)");
        Place   cafeWall = new Place("cafeWall", "You are facing a wall in the Cafeteria." +
                "\nThere is a painting to your left and a bookcase to your right. \n(twist painting/pull books)");
        Place   secretPass = new Place("secretPass", "You've uncovered a secret passageway! It looks like it was " +
                "used recently. \nThere is a door ahead of you, with a sign saying 'EXIT'(open door/go back)");
        Place   hatch = new Place("hatch", "You're falling! (continue)");
        Place   falling = new Place("falling", "And falling... (continue)");

        // add places
        game.addPlace(myCell);
        game.addPlace(myCellMirror);
        game.addPlace(mirrorShaven);
        game.addPlace(prisonHall);
        game.addPlace(prisonHallL);
        game.addPlace(cellBlockB);
        game.addPlace(openCellB);
        game.addPlace(cellCot);
        game.addPlace(cellCotCut);
        game.addPlace(cellBucket);
        game.addPlace(security);
        game.addPlace(mainFloor);
        game.addPlace(exitDoor);
        game.addPlace(cafeteria);
        game.addPlace(kitchen);
        game.addPlace(freezer);
        game.addPlace(freezerCorpse);
        game.addPlace(cutCorpse);
        game.addPlace(kitchen2);
        game.addPlace(cafeteria2);
        game.addPlace(cafeWall);
        game.addPlace(secretPass);
        game.addPlace(hatch);
        game.addPlace(falling);

        // Items

        Item    razor        = new Item("razor","A rusty razor-blade");
        myCellMirror.addItem(razor);
        myCell.addItem(razor);

        Item    foam         = new Item("foam","Shaving foam");
        myCellMirror.addItem(foam);

        Item    knife        = new Item("knife","A smelly pocket knife");
        cellBucket.addItem(knife);

        Item    keycard      = new Item("keycard","The security keycard");
        cellCotCut.addItem(keycard);

        Item    flashlight   = new Item("flashlight","A working flashlight");
        security.addItem(flashlight);

        Item    key          = new Item("key","Freezer key");
        cutCorpse.addItem(key);

        // create and add Player Actions

        game.addAction(new Action("drop", "You drop an item."));
        game.addAction(new Action("pick up", "You pick up an item."));
        game.addAction(new Action("examine", "You examine your surroundings."));

        // create and add Actions

        Action leaveCell = new Action("leave cell", "You pick-lock the door to your cell.",         myCell,             prisonHall);
        game.addAction(leaveCell);
        leaveCell.addRequired(razor);
        game.addAction(new Action("look left",  "You look left.",                                   myCell,             myCellMirror));
        game.addAction(new Action("warp cafe2",  "You enter a convenient portal.",                  myCell,             cafeteria2));

        Action shave = new Action("shave",      "You shave.",                                       myCellMirror,       mirrorShaven);
        game.addAction(shave);
        shave.addRequired(razor);
        shave.addRequired(foam);
        game.addAction(new Action("go back", "You back away from the mirror.",                      myCellMirror,       myCell));

        Action leaveCell2 = new Action("leave cell", "You leave your cell.",                        mirrorShaven,       prisonHall);
        game.addAction(leaveCell2);
        leaveCell2.addRequired(razor);

        ArrayList <String> goLeft = new ArrayList <String> ();
        goLeft.add("You head left");
        goLeft.add("You turn and walk left");
        goLeft.add("You make a left and go forward");
        game.addAction(new Action("go left",    goLeft,                            prisonHall,         prisonHallL));
        game.addAction(new Action("go right",   "You walk to the right.",                           prisonHall,         cellBlockB));
        Action goUp = new Action("go up",       "You ascend the dark stairs.",                      prisonHall,         mainFloor);
        game.addAction(goUp);
        goUp.addRequired(flashlight);
        goUp.addRequired(knife);

        game.addAction(new Action("go back",    "You're back at your cell.",                        prisonHallL,        prisonHall));

        game.addAction(new Action("go back",        "You're back at your cell.",                    cellBlockB,         prisonHall));
        Action enterCell = new Action("enter cell",     "You walk into the cell.",                  cellBlockB,         openCellB);
        game.addAction(enterCell);
        enterCell.addForbidden(keycard);
        Action enterSecurity = new Action("enter security", "You enter the security room.",         cellBlockB,         security);
        game.addAction(enterSecurity);
        enterSecurity.addRequired(keycard);

        game.addAction(new Action("go back",        "You exit the cell.",                           openCellB,          cellBlockB));
        Action searchBucket = new Action("search bucket",  "You walk to the bucket.",               openCellB,          cellBucket);
        game.addAction(searchBucket);
        searchBucket.addForbidden(knife);
        game.addAction(new Action("search cot",     "You walk to the cot.",                         openCellB,          cellCot));

        game.addAction(new Action("go back",  "You walk away from the bucket.",                     cellBucket,         openCellB));

        game.addAction(new Action("go back",  "You walk away from the cot.",                        cellCot,            openCellB));
        Action cutMattress = new Action("cut mattress",  "You cut the mattress open.",              cellCot,            cellCotCut);
        game.addAction(cutMattress);
        cutMattress.addRequired(knife);

        game.addAction(new Action("exit cell",  "You exit the cell. The gate locks behind you!",    cellCotCut,         cellBlockB));

        game.addAction(new Action("go back",    "You go back to Cell Block B.",                     security,           cellBlockB));

        game.addAction(new Action("go right",   "You go into the cafeteria.",                       mainFloor,          cafeteria));
        game.addAction(new Action("go left",    "You go to the exit door.",                         mainFloor,          exitDoor));
        game.addAction(new Action("go down",    "You descend the stairs.",                          mainFloor,          prisonHall));

        game.addAction(new Action("go back",    "You go back to the lobby.",                        exitDoor,           mainFloor));

        game.addAction(new Action("go back",    "You go back to the lobby.",                        cafeteria,          mainFloor));
        game.addAction(new Action("go forward", "You go into the kitchen.",                         cafeteria,          kitchen));

        game.addAction(new Action("go back",    "You go back to the cafeteria.",                    kitchen,            cafeteria));
        game.addAction(new Action("go left",    "You go into the freezer.",                         kitchen,            freezer));

        game.addAction(new Action("search corpse", "You walk to the corpse in the corner.",         freezer,            freezerCorpse));
        Action openFreezerDoor = new Action("open door", "Shivering, you open the door.",           freezer,            kitchen);
        game.addAction(openFreezerDoor);
        openFreezerDoor.addRequired(key);

        Action cutStitches = new Action("cut stitches", "You use the knife to cut the stitches.",   freezerCorpse,      cutCorpse);
        game.addAction(cutStitches);
        cutStitches.addRequired(knife);
        game.addAction(new Action("continue freezing", "You take the fetal position.",              freezerCorpse,      freezer));

        Action openFreezerDoor2 = new Action("open door", "Shivering, you open the door.",          cutCorpse,          kitchen2);
        game.addAction(openFreezerDoor2);
        openFreezerDoor2.addRequired(key);

        game.addAction(new Action("enter cafeteria", "You head into the Cafeteria.",                kitchen2,           cafeteria2));

        game.addAction(new Action("enter kitchen", "You head for the Kitchen.",                     cafeteria2,         kitchen2));
        game.addAction(new Action("enter lobby", "You head to the Lobby.",                          cafeteria2,         mainFloor));

        game.addAction(new Action("face wall", "You follow the footsteps to the wall.",             cafeteria2,         cafeWall));

        game.addAction(new Action("twist painting", "Twisting the painting does nothing.",          cafeWall,           cafeWall));
        game.addAction(new Action("pull books", "You mess with the books on the shelf, one of them was a switch!",  cafeWall, secretPass));

        game.addAction(new Action("go back", "You head back to the Cafeteria.",                     secretPass,         cafeteria2));
        game.addAction(new Action("open door", "A trapdoor opens at your feet!",                    secretPass,         hatch));

        game.addAction(new Action("continue", "",                                                  hatch,              falling));

        game.addAction(new Action("continue", "",                                                  falling,            falling));

        game.play();
    } // end prisonEscape() method

    public static void main(String[] args) 
    {
        prisonEscape();
    }// end main() method
} // end class
