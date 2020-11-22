/*
 *   A program to demonstrate a Binary Search Tree.
 */

public class BSTdemo {


	public static void main(String[] args)
	{
		
		BST<Integer> t1 = new BST<Integer>(); 
		BST<Integer> t2 = new BST<Integer>();
		BST<Integer> t3 = new BST<Integer>();
		
		t1.insert(new Integer(40));   // Two identical trees are created to
		t1.insert(new Integer(60));                // test the equals method.  To test
		t1.insert(new Integer(20));                // trees that are not equal simply
		t1.insert(new Integer(50));                // comment out one of the Insertions.
		t1.insert(new Integer(80));                // and / or change one of the data
		t1.insert(new Integer(70));                // values.
		t1.insert(new Integer(10));
		t1.insert(new Integer(9));
		t1.insert(new Integer(15));
		t1.insert(new Integer(14));
		t1.insert(new Integer(16));
        t1.insert(new Integer(48));
        t1.insert(new Integer(41));
        t1.insert(new Integer(46));
        t1.insert(new Integer(47));
        t1.insert(new Integer(44));
        t1.insert(new Integer(5));
        
        
		
		t2.insert(new Integer(40));
		t2.insert(new Integer(60));
		t2.insert(new Integer(20));
		t2.insert(new Integer(50));
		t2.insert(new Integer(80));
		t2.insert(new Integer(70));
		t2.insert(new Integer(10));
        t2.insert(new Integer(48));
        t2.insert(new Integer(41));
        t2.insert(new Integer(46));
        t2.insert(new Integer(47));
        t2.insert(new Integer(44));
        t2.insert(new Integer(5));
        t2.insert(new Integer(15));

//        System.out.println("found: " + t2.find(72));
//        
//        if (t1.equals(t2))
//        	System.out.println("Tree t1 equals tree t2");
//        else
//        	System.out.println("Tree t1 doesn't equal tree t2");
//		
//		System.out.println("---------------------");
//		t1.printTree();
//		
		// Here we test findMax() and findMin() - Note: this code
		// fails if the tree is empty and null is returned.
		
//		System.out.println("\nt1 max is " + t1.findMax().toString());
//		System.out.println("\nt2 max is " + t2.findMax().toString());
//
//		System.out.println("\nt1 min is " + t1.findMin().toString());
//		System.out.println("\nt2 min is " + t2.findMin().toString());
		
		// Test removeMin) and removeMax()
		// After each removal print the tree.
		
		while (!t1.isEmpty()) {
			t1.removeMin();
		    System.out.println("--------------------");
		    t1.printTree();
		}
		
//		t1.removeMin();
//		System.out.println("---------------------");
//		t1.printTree();
//
//		t1.removeMin();
//		System.out.println("---------------------");
//		t1.printTree();
//		
//		t1.removeMax();
//		System.out.println("---------------------");
//		t1.printTree();
//
//		t1.removeMax();
//		System.out.println("---------------------");
//		t1.printTree();
//
//		t1.removeMax();
//		System.out.println("---------------------");
//		t1.printTree();
//		System.out.println("10");
//		t1.remove(10);
//		t1.printTree();
//		
//		
//		if (t1.isEmpty())
//			System.out.println("The tree is empty");
//		else
//			System.out.println("The tree is not empty");
//			
		
	
				
		
		
		
		// Now remove arbitrary Nodes - use the
		// Coverage feature to check that all
		// cases are handled.
		
		// Add your own checks, trees, and removals
		// so that all cases are handled and tested.
		
		
//		t1.remove(47);
//		System.out.println("---------------------");
//		t1.printTree();
		
//		t1.remove(50);
//		System.out.println("---------------------");
//		t1.printTree();
//		
//		t1.remove(20);
//		System.out.println("---------------------");
//		t1.printTree();
//		
//		t1.remove(60);
//		System.out.println("---------------------");
//		t1.printTree();
//		
//		t1.remove(40);
//		System.out.println("---------------------");
//		t1.printTree();
//		
//		t1.remove(70);
//		System.out.println("---------------------");
//		t1.printTree();
		
		
	}

}
