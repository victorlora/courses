
/**
 * program that takes two user inputs and adds/multiplies them together
 * 
 * @author Victor Lora
 * @version (date submitted)
 * 
 * estimated time: 120 min
 *    actual time: 70 min
 * expected grade: 10/10
 *
 *
 */
//import Scanner class so it can be used below
import java.util.Scanner;
public class DistanceMath
{    
    // creates a static variable for keyboard to be used below
    public static final Scanner keyboard = new Scanner(System.in);   
    /**
     * multDistance multiplies 2 numbers with the same or different units.
     * 
     * Note: If the numbers have different units then it the method calls
     * on other methods to convert the second number into the same units as 
     * the first.
     * 
     * @params dist1                        value of the first distance
     *         unit1                        units of the first distance
     *         dist2                        value of the second distance
     *         unit2                        units of the second distance
     *         
     * @return Double.NaN                   As an error
     *         The product of the two numbers
     */       
    public static double multDistance(double dist1,  String unit1, double dist2, String unit2)    
    {

        if (unit1 == null || unit1 == null){
            return Double.NaN;
        }

        if (unit1.matches("cm|centimeter|centimeters") && unit2.matches("cm|CM|Cm|Centimeter|Centimeters")){
            return dist1 * dist2;
        }

        if (unit1.matches("cm|centimeter|centimeters") && unit2.matches("m|meters|meter")){
            double dist2inFt = convertMToFt(dist2);
            double dist2inIn = convertFtToIn(dist2inFt);
            double dist2inCm = convertInToCm(dist2inIn);
            return dist1 * dist2inCm;
        }

        if(unit1.matches("cm|centimeter|centimeters") && unit2.matches("in|inch|inches")){
            double dist2inCm = convertInToCm(dist2);
            return dist1 * dist2inCm;
        }

        if(unit1.matches("cm|centimeter|centimeters") && unit2.matches("ft|feet|feets")){
            double dist2inIn = convertFtToIn(dist2);
            double dist2inCm = convertInToCm(dist2inIn);
            return dist1 * dist2inCm;
        }

        if(unit1.matches("in|inch|inches") && unit2.matches("in|inch|inches")){
            return dist1 * dist2;
        }

        if (unit1.matches("in|inch|inches") && unit2.matches("m|meters|meter")){
            double dist2inFt = convertMToFt(dist2);
            double dist2inIn = convertFtToIn(dist2inFt);
            return dist1 * dist2inIn;
        }

        if(unit1.matches("in|inch|inches") && unit2.matches("cm|centimeter|centimeters")){
            double dist2inIn = convertCmToIn(dist2);
            return dist1 * dist2inIn;
        }

        if(unit1.matches("in|inch|inches") && unit2.matches("ft|feet|feets")){
            double dist2inIn = convertFtToIn(dist2);
            return dist1 * dist2inIn;
        }

        if (unit1.matches("ft|feet|feets") && unit2.matches("ft|feet|feets")){
            return dist1 * dist2;
        }

        if (unit1.matches("ft|feet|feets") && unit2.matches("m|meters|meter")){
            double dist2inFt = convertMToFt(dist2);
            return dist1 * dist2inFt;
        }

        if(unit1.matches("ft|feet|feets") && unit2.matches("in|inch|inches")){
            double dist2inFt = convertInToFt(dist2);
            return dist1 * dist2inFt;
        }

        if(unit1.matches("ft|feet|feets") && unit2.matches("cm|CM|Cm|Centimeter|Centimeters")){
            double dist2inIn = convertCmToIn(dist2);
            double dist2inFt = convertInToFt(dist2inIn);
            return dist1 * dist2inFt;
        }

        if (unit1.matches("m|meters|meter") && unit2.matches("m|meters|meter")){
            return dist1 * dist2;
        }

        if (unit1.matches("m|M|Meters|meters") && unit2.matches("cm|centimeter|centimeters")){
            double dist2inIn = convertCmToIn(dist2);
            double dist2inFt = convertInToFt(dist2inIn);
            double dist2inM = convertFtToM(dist2inFt);
            return dist1 * dist2inM;
        }

        if(unit1.matches("m|meters|meter") && unit2.matches("in|inch|inches")){
            double dist2inFt = convertInToFt(dist2);
            double dist2inM = convertFtToM(dist2inFt);
            return dist1 * dist2inM;
        }

        if(unit1.matches("m|meters|meter") && unit2.matches("ft|feet|feets")){
            double dist2inM = convertFtToM(dist2);
            return dist1 * dist2inM;
        }

        return Double.NaN;
    }// end multDistance() method

    /**
     * addDistance adds 2 numbers with the same or different units.
     * 
     * Note: If the numbers have different units then it the method calls
     * on other methods to convert the second number into the same units as 
     * the first.
     * 
     * @params dist1                        value of the first distance
     *         unit1                        units of the first distance
     *         dist2                        value of the second distance
     *         unit2                        units of the second distance
     *         
     * @return Double.NaN                   As an error
     *         The sum of the two numbers
     */
    public static double addDistance(double dist1, String unit1, double dist2, String unit2)    
    {
        if (unit1 == null || unit2 == null){
            return Double.NaN;
        }

        if (unit1.matches("cm|centimeter|centimeters") && unit2.matches("cm|centimeter|centimeters")){
            return dist1 + dist2;
        }

        if (unit1.matches("cm|centimeter|centimeters") && unit2.matches("m|meters|meter")){
            double dist2inFt = convertMToFt(dist2);
            double dist2inIn = convertFtToIn(dist2inFt);
            double dist2inCm = convertInToCm(dist2inIn);
            return dist1 + dist2inCm;
        }

        if(unit1.matches("cm|centimeter|centimeters") && unit2.matches("in|inch|inches")){
            double dist2inCm = convertInToCm(dist2);
            return dist1 + dist2inCm;
        }

        if(unit1.matches("cm|centimeter|centimeters") && unit2.matches("ft|feet|feets")){
            double dist2inIn = convertFtToIn(dist2);
            double dist2inCm = convertInToCm(dist2inIn);
            return dist1 + dist2inCm;
        }

        if(unit1.matches("in|inch|inches") && unit2.matches("in|inch|inches")){
            return dist1 + dist2;
        }

        if (unit1.matches("in|inch|inches") && unit2.matches("m|meters|meter")){
            double dist2inFt = convertMToFt(dist2);
            double dist2inIn = convertFtToIn(dist2inFt);
            return dist1 + dist2inIn;
        }

        if(unit1.matches("in|inch|inches") && unit2.matches("cm|centimeter|centimeters")){
            double dist2inIn = convertCmToIn(dist2);
            return dist1 + dist2inIn;
        }

        if(unit1.matches("in|inch|inches") && unit2.matches("ft|feet|feets")){
            double dist2inIn = convertFtToIn(dist2);
            return dist1 + dist2inIn;
        }

        if (unit1.matches("ft|feet|feets") && unit2.matches("ft|feet|feets")){
            return dist1 + dist2;
        }

        if (unit1.matches("ft|feet|feets") && unit2.matches("m|meters|meter")){
            double dist2inFt = convertMToFt(dist2);
            return dist1 + dist2inFt;
        }

        if(unit1.matches("ft|feet|feets") && unit2.matches("in|inch|inches")){
            double dist2inFt = convertInToFt(dist2);
            return dist1 + dist2inFt;
        }

        if(unit1.matches("ft|feet|feets") && unit2.matches("cm|CM|Cm|Centimeter|Centimeters")){
            double dist2inIn = convertCmToIn(dist2);
            double dist2inFt = convertInToFt(dist2inIn);
            return dist1 + dist2inFt;
        }

        if (unit1.matches("m|meters|meter") && unit2.matches("m|meters|meter")){
            return dist1 + dist2;
        }

        if (unit1.matches("m|M|Meters|meters") && unit2.matches("cm|centimeter|centimeters")){
            double dist2inIn = convertCmToIn(dist2);
            double dist2inFt = convertInToFt(dist2inIn);
            double dist2inM = convertFtToM(dist2inFt);
            return dist1 + dist2inM;
        }

        if(unit1.matches("m|meters|meter") && unit2.matches("in|inch|inches")){
            double dist2inFt = convertInToFt(dist2);
            double dist2inM = convertFtToM(dist2inFt);
            return dist1 + dist2inM;
        }

        if(unit1.matches("m|meters|meter") && unit2.matches("ft|feet|feets")){
            double dist2inM = convertFtToM(dist2);
            return dist1 + dist2inM;
        }

        return Double.NaN;
    }// end addDistance() method

    /**
     * Converts a number from Cm to Inches
     * 
     * @params  dist2                       value of the second distance
     * 
     * @returns dist2inInches               value of the second distance converted to Inches
     * 
     */
    private static double convertCmToIn(double dist2)
    {
        double dist2inInches = dist2 * 0.39370;
        return dist2inInches;
    }// end convertCmToIn() method

    /**
     * Converts a number from In to Ft
     * 
     * @params  dist2                       value of the second distance
     * 
     * @returns dist2inFeet                 value of the second distance converted to Feet
     * 
     */
    private static double convertInToFt(double dist2)
    {
        double dist2inFeet = dist2 * 0.083333;
        return dist2inFeet;
    }// end convertInToFt() method

    /**
     * Converts a number from Ft to M
     * 
     * @params  dist2                       value of the second distance
     * 
     * @returns dist2inMeters               value of the second distance converted to Meters
     * 
     */
    private static double convertFtToM(double dist2)
    {
        double dist2inMeters = dist2 / 3.2808;
        return dist2inMeters;
    }// end convertFtToM() method

    /**
     * Converts a number from M to Ft
     * 
     * @params  dist2                       value of the second distance
     * 
     * @returns dist2inFeet                 value of the second distance converted to Feet
     * 
     */
    private static double convertMToFt(double dist2)
    {
        double dist2inFeet = dist2 * 3.2808;
        return dist2inFeet;
    }// end convertMToFt() method

    /**
     * Converts a number from Ft to In
     * 
     * @params  dist2                       value of the second distance
     * 
     * @returns dist2inInches               value of the second distance converted to Inches
     * 
     */
    private static double convertFtToIn(double dist2)
    {
        double dist2inInches = dist2 * 12;
        return dist2inInches;
    }// end convertFtToCm() method

    /**
     * Converts a number from In to Cm
     * 
     * @params  dist2                       value of the second distance
     * 
     * @returns dist2inCm                   value of the second distance converted to Centimeters
     * 
     */
    private static double convertInToCm(double dist2)
    {
        double dist2inCm = dist2 / 0.39370;
        return dist2inCm;
    }// end convertInToCm() method

    /**
     * Request inputs from the user. Then displays the results.
     * 
     * @params ---
     * 
     * @return ---
     * 
     */
    public static void main(String[] args){

        System.out.println("Please input first number:");
        while (!keyboard.hasNextInt()) // As long as the user keeps entering something that isn't an integer.
        {
            System.out.print("Enter a valid number: ");
            keyboard.next(); // This makes it ask for another input.
        }
        int    dist1 = keyboard.nextInt();

        System.out.println("Please input first number's units:");
        String unit1 = keyboard.next();

        System.out.println("Please input second number:");
        while (!keyboard.hasNextInt()) // As long as the user keeps entering something that isn't an integer.
        {
            System.out.print("Enter a valid number: ");
            keyboard.next(); // This makes it ask for another input. 
        }

        int    dist2 = keyboard.nextInt();

        System.out.println("Please input second number's units:");
        String unit2 = keyboard.next();

        System.out.println("Would you like to add or multiply the two number?");
        String procedure = keyboard.next();

        /**
         * if statement checks to see what the user wants and then calls the appropriate
         * method to do the procedure
         * also the if statement allows for different variations of add and mult
         */ 
        if (procedure.equals("add") || procedure.equals("Add")){
            double sum = addDistance(dist1, unit1, dist2, unit2);
            System.out.println("Your sum is: " + sum + " " + unit1);
        }
        else if (procedure.equals("multiply") || procedure.equals("Multiply")){
            double product = multDistance(dist1, unit1, dist2, unit2);
            System.out.println("Your product is: " + product + " " + unit1);
        }
    }
} // end class
