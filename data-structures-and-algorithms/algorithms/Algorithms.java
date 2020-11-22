import java.util.*;
/*
* Data Structures & Algorithms. *
* @version 1.0 24 Sept 2015
* @author Victor Lora
* @description 
*	 Various algorithms where counters are to be implemented, additionally
*	 qsort() should be optimized
*/
public class Algorithms {
	
	private static int[] A;    // The array we'll use for the Algorithms.
	private static int n;      // The size of the Array used in the Algorithms.
	private static long comp;  // Used to compute the number of comparisons.
	                           // performed by an Algorithm.
	
	private static Random rand = new Random();  // A random number generated used to
	                                            // populate the arrays.
	
	private static Scanner keyb = new Scanner( System.in );  // Keyboard scanner.
	
	public static void main(String[] args)
	{
		long search = 0;        // Counts the number of searches made.
		long sort = 0;          // Count the number of sorts done.
		final long TIMES = 10;  // The number of searches to make.
		int key;               // The integer to search for in the arrays.
		long sum;               // Accumulates comparisons for several searches.
		
		if (args.length == 0) {           // No program argument specified.
			                              // Print a reminder and bail.
			System.out.println("Argument required: " +
			                   "linearSearch | binarySearch | " +
					           "insertionSort | qsortAverage | " +
			                   "qsortWorst");
			return;
		}
		
		if (args[0].equals("linearSearch")) {
			
		    System.out.println("Analysis of Worst Case Linear Search\n");
			
			System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
			n = keyb.nextInt();
			
			
		    while (n > 0) {
		    	
				A = new int[n];                          // Construct the array.
				
				System.out.println("... initializing array with even integers");
				
				for (int i = 0; i < n; i++)          // Populate the array with a random
					A[i] = rand.nextInt(50000) * 2;  // collection of even integers.
				
				if (n <= 1000)
					display(A);
				
				System.out.println("\n... finished initializing the array\n");
				System.out.println("Now we'll look at some worst case linear searches\n");
				
				search = 0;   // Initialize search and comparison counts.
				sum = 0;
				
				do {                       // Do TIMES searches.
					
					search++;                           // Count the number of searches.
					key = rand.nextInt(50000) * 2 + 1;  // Get an odd number to search for.
					
					System.out.printf("Searching for %6d", key);
			
					comp = 0;   // Initial comparison count for this search.
										
					int indexLoc = linearSearch(A, key);   // Do the search
					
					
					if (indexLoc != -1)                         // return value of -1
						System.out.print(" found in list");     // signals key not found.
					else
						System.out.print(" not found in list");
					
					System.out.printf(" => %6d comparisons\n", comp);      // Print the number
					                                                       // of comparisons.
					sum += comp;  // Total comparisons of several searches.
					
				} while (search < TIMES);
				
				System.out.printf("\nAverage number of comparisons: %d\n\n", sum / TIMES);
				
				System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
				n = keyb.nextInt();
	
			}
		}
		else if (args[0].equals("binarySearch")) {
			
		    System.out.println("Analysis of Worst Case Binary Search\n");
			
			System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
			n = keyb.nextInt();
			
			
		    while (n > 0) {
		    	
				A = new int[n];                          // Construct the array.
				
				System.out.println("... initializing array with even integers");
				
				for (int i = 0; i < n; i++)    // Populate the array with a sorted
					A[i] = i * 2;              // collection of even integers.

				if (n <= 1000)
					display(A);
				
				System.out.println("\n... finished initializing the array\n");
				System.out.println("Now we'll look at some worst case binary searches\n");
				
				search = 0;   // Initialize search and comparison counts.
				sum = 0;
				
				do {                       // Do TIMES searches.
					
					search++;              // Count the number of searches.
					                       // Get an odd number to search for.
					key = rand.nextInt(n/2) * 2 + (int)(Math.pow(-1, rand.nextInt(2)))*3;
					
					System.out.printf("Searching for %6d", key);
			
					comp = 0;   // Initial comparison count for this search.
					
					int indexLoc = binarySearch(A, key);   // Do the search
										
					if (indexLoc != -1)                         // return value of -1
						System.out.print(" found in list");     // signals key not found.
					else
						System.out.print(" not found in list");
					
					System.out.printf(" => %6d comparisons\n", comp);      // Print the number
					                                                       // of comparisons.
					sum += comp;  // Total comparisons of several searches.
					
				} while (search < TIMES);
				
				System.out.printf("\nAverage number of comparisons: %d\n\n", sum / TIMES);
				
				System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
				n = keyb.nextInt();
	
			}
		}
		else if (args[0].equals("insertionSort")) {
			
			System.out.println("Analysis of Insertion Sort\n");
			
			System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
			n = keyb.nextInt();

			while (n > 0) {
				
				A = new int[n];                          // Construct the array.
				
				System.out.println("... each sort will initialize the array with");
				System.out.println("    a new collection of random integers");
				
				System.out.println("Now we'll look at some Average Case Insertion Sorts\n");
				
				sort = 0;   // Initialize sort and comparison counts.
				sum = 0;
				
				do {                       // Do TIMES sorts.
					
					sort++;                            // Count the number of sorts.
					
					for (int i = 0; i < n; i++)      // Populate the array with a random
						A[i] = rand.nextInt(50000);  // collection of integers.
					
					comp = 0;   // Initial comparison count for this search.

					if (sort == TIMES && n <= 20)
						display(A);
					
					System.out.printf("Sorting ...");

					insertionSort(A);   // Sort the array.

					System.out.printf(" => %6d comparisons\n", comp);      // Print the number

					if (sort == TIMES && n <= 20)
						display(A);
					                                                       // of comparisons.
					sum += comp;  // Total comparisons of several searches.
					
				} while (sort < TIMES);
				
				System.out.printf("\nAverage number of comparisons: %d\n\n", sum / TIMES);
				
				System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
				n = keyb.nextInt();
			}
			
		}
		else if  (args[0].equals("qsortAverage")) {
			
			System.out.println("Analysis of Average Case Quicksort\n");
			
			System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
			n = keyb.nextInt();

			while (n > 0) {
				
				A = new int[n];                          // Construct the array.
				
				System.out.println("... each sort will initialize the array with");
				System.out.println("    a new collection of random integers");
				
				System.out.println("Now we'll look at some Average Case Quicksorts\n");
				
				sort = 0;   // Initialize sort and comparison counts.
				sum = 0;
				
				do {                       // Do TIMES sorts.
					
					sort++;                            // Count the number of sorts.
					
					for (int i = 0; i < n; i++)      // Populate the array with a random
						A[i] = rand.nextInt(50000);  // collection of integers.
					
					comp = 0;   // Initial comparison count for this search.

					if (sort == TIMES && n <= 20)
						display(A);

					System.out.printf("Sorting ...");				

					QSort(A, 0, A.length-1);   // Sort the array.

					System.out.printf(" => %6d comparisons\n", comp);      // Print the number
                                                                           // of comparisons.
					
					if (sort == TIMES && n <= 20)
						display(A);
					
					sum += comp;  // Total comparisons of several searches.
					
				} while (sort < TIMES);
				
				System.out.printf("\nAverage number of comparisons: %d\n\n", sum / TIMES);
				
				System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
				n = keyb.nextInt();
			}
			
		}
		else if  (args[0].equals("qsortWorst")) {
			System.out.println("Analysis of Worst Case Quicksort\n");
			
			System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
			n = keyb.nextInt();

			while (n > 0) {
				
				A = new int[n];                          // Construct the array.
				
				System.out.println("... each sort will initialize the array with");
				System.out.println("    a new collection of random integers");
				
				System.out.println("Now we'll look at some Worst Case Quicksorts\n");
				
				sort = 0;   // Initialize sort and comparison counts.
				sum = 0;
				
				do {                       // Do TIMES sorts.
					
					sort++;                            // Count the number of sorts.
					
					for (int i = 0; i < n; i++)      // Populate the array so it is 
						A[i] = i * 2;                // already sorted!  
			
					comp = 0;   // Initial comparison count for this search.

					if (sort == TIMES && n <= 20)
						display(A);

					System.out.printf("Sorting ...");
					
					QSort(A, 0, A.length-1);   // Sort the array.
					
					System.out.printf(" => %6d comparisons\n", comp);      // Print the number
                    // of comparisons.

					if (sort == TIMES && n <= 20)
						display(A);
					
					sum += comp;  // Total comparisons of several searches.
					
				} while (sort < TIMES);
				
				System.out.printf("\nAverage number of comparisons: %d\n\n", sum / TIMES);
				
				System.out.print("Enter the array size (0 to quit): "); // Prompt for the array size.
				n = keyb.nextInt();
			}
			
			
		}
		else {
			System.out.println("Valid program arguments: " +
	                   "linearSearch | binarySearch | " +
			           "insertionSort | qsortAverage | " +
	                   "qsortWorst");
			return;
		}
			
	}
	
	

	/*
	 *    display() - print out the contents of the array A.
	 */
	
	public static void display(int[] A)
	{	
		System.out.println("----------------------------------------");
		for (int i = 0; i < A.length; i++) {
			System.out.printf("%6d", A[i]);
			if ((i + 1) % 20 == 0)
				System.out.println("");
		}
		System.out.println("");
	}

	
	/*
	 *   linearSearch()
	 *   
	 *   Search for the key within the array A.
	 *   Return the index where it is found or -1.
	 */
	
	public static int linearSearch(int[] A, int key)
	{	
		for (int i = 0; i < A.length; i++) {
			comp++;
			if (A[i] == key)          // We found the key!
				return i;
		}
		
		return -1;  // Key not found.
	}
	
	
	/*
	 *   binarySearch()
	 *   
	 *   Search for the key within the array A from location left to right.
	 *   Return the index where it is found or -1.
	 */
	
	public static int binarySearch(int[] A, int key)
	{	
		int mid = 0;               // Index at the middle of the search region.
		boolean found = false;     // Flags if the key is found.
		int left = 0;                  // Left index of the search region.
		int right = A.length-1;        // Right index of the search region.
		
		while (left <= right && !found) {   // While we've a valid Search Region
			                                // and the key isn't found ...
			mid = (left + right) / 2;       // ... compute middle of SR
			comp++;
			if (A[mid] == key)              // we found the key
				found = true;               //    set our flag
			else if (key < A[mid])          // key less than middle
				right = mid-1;              //    search left side of SR
			else                            // key greater than middle
				left = mid + 1;             //    search right side of SR
		}
		
		return found ? mid : -1;    // Return index or -1.
	}
	
	/*
	 *   insertionSort() - Sorts an array by repeatedly inserting
	 *                     each element A[i] (for i = 1, 2, 3, 4 ...)
	 *                     among the elements A[0] ... A[i].
	 */

	public static void insertionSort(int[] A)
	{

		int value;
		int i, j;
		
		if (A.length < 2)   // Check for an array of length 0 or 1.
			return;
		
		for (i = 1; i < A.length; i++) {
			
			// Insert A[i] in its correct position among the
			// elements A[0] ... A[i].  So the front of the array
			// increasingly contains a longer run of sorted elements.
			
			value = A[i];  // This is the value to insert.
			               // Move larger array elements over.
			
			for (j = i; j >= 1 && A[j-1] > value; j--) {
				comp++;
				A[j] = A[j-1];
			}
			
			A[j] = value;  // Put the current value in its correct position.
		}
				
	}
	
	public static int Split(int[] A, int first, int last)
	{
		int leftSrch = first;   // Index for doing the search from the left.
		int rightSrch = last;   // Index for doing the search from the right.
		int Pivot;              // The Pivot value.
		int temp;               // Aids in swapping values.
		
		int middle = (first + last) / 2;  // Middle index of the array
		
		
		// In order to split the list down the middle, the middle value
		// has to be swapped with the first pivot because after split() runs,
		// it becomes returns to its orignial position (in an ordered list,
		// which is what we're dealing with) with all values < A[middle] on the left 
		// and all values > A[middle] on the right
		
		temp = A[first];				  // swap first and middle index values
		A[first] = A[middle];			  
		A[middle] = temp;				  
		
		// qWorst is improved by this method, but qAverage seems to stay the same.
		
		// The Pivot is the first element of the region.
		Pivot = A[first];
		
		
		while (leftSrch < rightSrch) {  // While the searches haven't met.

			while (A[rightSrch] > Pivot) {  // Search from the right looking
				rightSrch--;               // for a value <= than Pivot.
				comp++;						// Increment comp
			}
			while ((leftSrch < rightSrch)   // While the searches haven't met
					        &&             // search from the left looking
				   (A[leftSrch] <= Pivot)) { // for a value > than Pivot.
				leftSrch++;
				comp++;
			}
			if (leftSrch < rightSrch) {    // If the searches haven't met
				temp = A[leftSrch];        // interchange these elements.
				A[leftSrch] = A[rightSrch];
				A[rightSrch] = temp;
			}
		}
		
		temp = A[leftSrch];      // Swap the pivot into the place
		A[leftSrch] = A[first];  // where the searches met.
		A[first] = temp;
		
		return leftSrch;   // Return the location of the Pivot.
	}
	
	
	/*
	 *   QSort()  - The Quicksort Algorithm to sort
	 *              an array from index i to index j.
	 *              
	 *   When first called, i is the index of the
	 *   first element (i.e. 0) and j is the index of
	 *   the last element( (i.e. A.length-1).
	 */
	
	
	public static void QSort(int[] A, int i, int j)
	{
		int mid;
		
		if (i < j) {               // If the array has more than one element.
			mid = Split(A, i, j);  //   Split the list about the pivot.
			QSort(A, i, mid-1);    //   Quicksort the left sublist.
			QSort(A, mid+1, j);    //   Quicksort the right sublist.   
		}
	}
}
