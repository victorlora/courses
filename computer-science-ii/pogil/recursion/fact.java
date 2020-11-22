
/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class fact
{

    /** calculates n factorial
     */
    public static int factorial(int n) {
        System.out.println("n is " + n);
        if (n<0) {
            System.out.println("Cannot find factorial of negative number");
            System.exit(0);
        }

        if (n == 0)
            return 1;
        else {
            System.out.println("need factorial of " + (n-1));
            int answer = factorial(n-1);
            System.out.println("factorial of " + (n-1)
                + " is " + answer);
            return answer * n;
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
    }

}
