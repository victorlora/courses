/*
 *   A test program to exercise the
 *   functionality of the Map class.
 */

public class MapTest
{

	public static void main(String[] args)
	{
        // Create three Maps

        Map<String, Integer> m = new Map<String, Integer>();
        Map<Integer, Double> nums = new Map<Integer, Double>();
        Map<Integer, Boolean> b = new Map<Integer, Boolean>();

        // Create some Strings to insert into a Map

        String csi1 = new String("CSI 111");
        String csi2 = new String("CSI 111");
        String mth1 = new String("MTH 121");
        String bio1 = new String("BIO 140");
        String chm1 = new String("CHM 102");
        String csi3 = new String("CSI 111");

        // Insert <String, Integer> Items into the Map

        m.insert(csi1, new Integer(16));
        m.insert(mth1, new Integer(24));
        m.insert(bio1, new Integer(16));
        m.insert(csi2, new Integer(21));

        

        // Display the Map and its size.

        System.out.printf("Map size: %d\n", m.getSize());
        System.out.println(m.toString());
        System.out.printf("Map size: %d\n", m.getSize());

        // Get the values of some Map items.

        Integer n = m.getValue(csi3);

        if (n != null)
        	System.out.println(n.toString());
        else
        	System.out.println("n is null");

        n = m.getValue("CSI555");

        if (n != null)
        	System.out.println(n.toString());
        else
        	System.out.println("n is null");

        // INsert a new item in the Map

        m.insert(chm1, 42);
        System.out.println(m.toString());
        System.out.printf("Map size: %d\n", m.getSize());

        m.remove("MTH 121");
        System.out.println(m.toString());
        System.out.printf("Map size: %d\n", m.getSize());

        // Exercise the isEmpty() method.
        if (m.isEmpty())
            System.out.println("m is empty");
        else
            System.out.println("m is not empty");

        if (nums.isEmpty())
            System.out.println("nums is empty");
        else
            System.out.println("nums is not empty");

        // Now exercise the nums Map

        nums.makeEmpty();

        nums.insert(new Integer(1), 1.8);
        nums.insert(new Integer(2), 2.5);
        nums.insert(new Integer(3), 3.8);
        nums.insert(new Integer(4), 4.5);
        nums.insert(new Integer(5), 5.8);
        nums.insert(new Integer(5), 6.5);
        
        System.out.println(nums.toString());
        System.out.printf("Map size: %d\n", nums.getSize());

        nums.remove(1);
        nums.remove(1);

        System.out.println(nums.toString());
        System.out.printf("Map size: %d\n\n\n------\n\n", nums.getSize());

        // Finaly, exercise the Map implemenation on the
        // map b, whose values are Booleans

        if (b.isEmpty())
            System.out.println("b is empty");
        else
            System.out.println("b is not empty");

        Integer myValue = new Integer(7);

        // Try removing an item
        b.remove(myValue);

        // Print the size of the map

        System.out.printf("Map size of b: %d\n", b.getSize());

        // Make the map empty

        b.makeEmpty();

        // Print the size of the map

        System.out.printf("Map size of b: %d\n", b.getSize());

	}
}
