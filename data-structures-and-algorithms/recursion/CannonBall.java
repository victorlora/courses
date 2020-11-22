import java.util.*;


/*
* Data Structures & Algorithms. *
* @version 1.0 06 Sept 2015
* @author Casie Ropski; Victor Lora
* @description A program that implements a recursive function
*    to compute the number of cannon balls in
*    a pyramid.
*/

public class CannonBall {


	public static void main(String [] args) {

		@SuppressWarnings("resource")
		Scanner keyb = new Scanner(System.in);  // Keyboard scanner.
		int cannonBalls;                        // Number of cannonballs
		                                        //    in the pyramid.
		int layers;                             // Number of layers in
		                                        //    the pyramid.

		do {
			// Prompt for and read the number of layers of
			// cannonballs in the pyramid.

		    System.out.print("\nNumber of layers (-1 to quit): ");
		    layers = keyb.nextInt();

		    if (layers != -1) {
		    	cannonBalls = cannon(layers);  // Determine and report the
		    	                               // number of cannonballs.
		    	System.out.printf("There are %d cannonballs in the %d layers.\n",
		    			                                  cannonBalls, layers);
		    }
		} while (layers != -1);
	}


	public static int cannon(int n)
	{
        if (n==1) 							// Base step
			return 1;

        return (n*n) + cannon(n-1);
	}
}
