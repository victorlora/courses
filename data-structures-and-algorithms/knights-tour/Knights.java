/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 01 October 2015
 * @author Victor Lora
 * @description 
 *     Knights Tour
 *         Starting at a point hit all 
 *         spots on a 5x5 board.
 *   
 */
import java.util.Scanner;

public class Knights {
	private static final int nW = 0;		// Directions used to
	private static final int nE = 1;		// travel the board
	private static final int sW = 2;
	private static final int sE = 3;
	private static final int wN = 4;
	private static final int wS = 5;
	private static final int eN = 6;
	private static final int eS = 7;
	private static final int NP = 8;		// All directions exhausted (No Path)
	
	
	private static int[][] board = new int[9][9];	// The board stored as a 2D array
	
	
	private static int startRow;	// Starting Row in the maze.
	private static int startCol;	// Starting Column in the maze.
	
	private static Scanner keyb = new Scanner(System.in);	// A keyboard scanner.
	
	public static void main(String[] args)	{
		System.out.println("Input starting row (0-4): ");	 // Prompt user for starting row.
		startRow = keyb.nextInt() + 2;
		System.out.println("Input starting column (0-4): "); // Prompt user for starting col.
		startCol = keyb.nextInt() + 2;
		
		makeBoard();	// Make the board.
		writeBoard();	// Display the board.
		
		if (findPath(startRow, startCol, 0))	// If a path is found...
			System.out.print("\nWe found a path");	// Print statement
		else
			System.out.print("\nNo path found.");
		
		writeBoard();	// Display the final board.
		
		System.out.println("Program terminating.");
	} // end main()
	
	
	/*
	 * 	makeBoard()
	 * 	
	 * 	This function creates a 5x5 2D matrix of 0's
	 * 	with a 2x5 and 5x2 buffer on the sides of -1's.
	 */
	
	static void makeBoard() {
		System.out.println("");
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if ((r < 2 || r > 6) || (c < 2 || c > 6)) { // if not within the 5x5 board
					board[r][c] = -1;							// create a buffer of -1
				} else {
					board[r][c] = 0;							// fill the board with 0's 
				}
			}
		}
	}
	
	
	/*
	 * 	writeBoard() - print the board
	 */
	
	static void writeBoard() {
		System.out.println("");
		for (int r = 2; r < 7; r++) {
			for (int c = 2 ; c < 7; c++)
				System.out.print("  " + board[r][c] + "\t");
			System.out.println("\n");
		}
		System.out.print("Press enter to continue...\n");
		keyb.nextLine();
		
	} // end writeBoard()
	
	
	/*
	 * 	findPath()
	 * 
	 *  This function returns true if a path can be found in
	 *  the board starting at location [x][y]. Otherwise,
	 *  the function returns false.
	 */
	public static boolean findPath(int x, int y, int count) {
		int dir;			// The direction to travel on the board
		boolean foundPath;	// Signals if a path tour was found
		
		
							// Base Steps(s)
		
		if (count == 25)			// We have covered all 25 spots
			return true;
		else if (board[x][y] == -1)	// We are out of the board
			return false;
		else if (board[x][y] != 0)  // We've been to that spot already
			return false;
		else {						// We've found a spot we can move to
							// Inductive step
			count++;				// increase the count by 1
			board[x][y] = count;	// Mark the spot with the jump number
			
			foundPath = false;
			dir = nW;				// Starting direction
			
			while (!foundPath && dir != NP) {
				switch (dir) {
				case nW:	foundPath = findPath(x+2,y-1,count);	// Travel north-west
							break;
				case nE:	foundPath = findPath(x+2,y+1,count);	// Travel north-east
							break;
				case sW:	foundPath = findPath(x-2,y-1,count);	// Travel south-west
							break;
				case sE:	foundPath = findPath(x-2,y+1,count);	// Travel south-east
							break;
				case wN:	foundPath = findPath(x+1,y-2,count);	// Travel west-north
							break;
				case wS:	foundPath = findPath(x-1,y-2,count);	// Travel west-south
							break;
				case eN:	foundPath = findPath(x+1,y+2,count);	// Travel east-north
							break;
				case eS:	foundPath = findPath(x-1,y+2,count);	// Travel east-south
							break;
				} // end Switch
				
				dir = dir + 1;		// Try searching in the next direction
			} // end While
			
			if (!foundPath)		// If we couldn't find a path
				board[x][y] = 0;	// unmark the square
			
			return foundPath;	// return whether a path was found
		} // end Else			
	} // end findPath()
}
