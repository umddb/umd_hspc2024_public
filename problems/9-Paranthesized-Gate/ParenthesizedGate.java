import java.util.Scanner;

public class ParenthesizedGate {

    private static int longestSequenceOfSwaps(String str) {
        int ret = 0;

        /* ------------------- INSERT CODE HERE ----------------------*/
        /* ------------- Fill free to use subroutines ----------------*/

        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            String str = sc.next();

            int result = longestSequenceOfSwaps(str);

            // print out
            System.out.println("The maximum number of swaps possible is " + result);
        }
    }
}

