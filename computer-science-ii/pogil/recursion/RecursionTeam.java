
/**
 * Write a description of class RecursionTeam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecursionTeam
{
    //     /** calculates n sums
    //      */
    //     public static int recursiveSum(int n) {
    //         System.out.println("n is " + n);
    //         if (n<0) {
    //             System.out.println("Cannot find sum of negative number using this method");
    //             System.exit(0);
    //         }
    // 
    //         if (n == 1)
    //             return 1;
    //         else {
    //             System.out.println("need sum of " + n + "+" + (n-1));
    //             int answer = recursiveSum(n-1);
    //             System.out.println("need sum of " + (n-1)
    //                 + " is " + answer);
    //             return answer + n;
    //         }
    //     }
    // 
    //     public static int iterativeSum(int n) {
    //         int sum = 0;
    //         System.out.println("n is " + n);
    //         if (n<0) {
    //             System.out.println("Cannot find sum of negative number using this method");
    //             System.exit(0);
    //         }
    // 
    //         while (n != 0) {
    //             sum = n + sum;
    //             n--;
    //         }
    //         System.out.println(sum);
    //         return sum;
    //     }
    // 
    //     public static void main(String[] args) {
    //         System.out.println(recursiveSum(3));
    //     }

    public static void countdown(int n) {
        if (n >= 1) {
            System.out.println(n);
            countdown(n-1);
        }
    }

    public static void countUp(int n, int m) {
        if (n <= m) {
            System.out.println(n);
            countUp(n+1,m);

        }
    }

    public static void main(String[] args) {
        countdown(0);
    }

    public static int power(int n, int x) {
        if(n == 0) {
            return 1;
        }
        else if (n%2 == 0) {
            int powerx = power(n/2,x);
            return powerx*powerx;

        }
        else if (n%2 == 1) {
            int powerx = power((n-1)/2,x);
            return powerx*powerx*x;
        }
        return -1;
    }
}