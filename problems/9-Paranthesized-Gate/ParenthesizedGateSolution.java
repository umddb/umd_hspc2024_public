import java.util.Scanner;

public class ParenthesizedGateSolution {

    private static int longestSequenceOfSwaps(String str) {
        int ret = 0;

        /* ------------------- INSERT CODE HERE ----------------------*/
        /* ------------- Fill free to use subroutines ----------------*/

        // greedy heuristic -- find the closest )( and swap them.

        // convert string to char array for manipulation
        char[] arr = str.toCharArray();

        int[] counts = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if(i == 0) {
                counts[i] = (arr[i] == '(') ? 1 : -1;
            } else {
                counts[i] = counts[i-1] + ((arr[i] == '(') ? 1 : -1);
            }
        }

        // sum up all the counts
        int startingSum = 0;
        for (int i = 0; i < counts.length; i++) {
            startingSum += counts[i];
        }

        // print out the counts array
        // System.out.println("--------");
        // System.out.println(str);
        // for (int i = 0; i < counts.length; i++) {
            // System.out.print(counts[i] + " ");
        // }
        // System.out.println();

        // look for the position of first "-1" in the counts array
        int firstNegOne = -1;
        for (int i = 0; i < counts.length; i++) {
            if(counts[i] == -1) {
                firstNegOne = i;
                break;
            }
        }

        // look for the position of last negative number in the counts array
        int lastNegOne = -1;
        for (int i = counts.length-1; i >= 0; i--) {
            if(counts[i] < 0) {
                lastNegOne = i;
                break;
            }
        }

        if (firstNegOne == -1) {
            return 0;
        }


        int span1 = (firstNegOne == 0) ? 0 : firstNegOne;
        int span2 = (firstNegOne == counts.length - 2) ? 0 : counts.length - 2 - firstNegOne;
        int finalTotal1 = span1/2 * span1/2 + span2/2 * span2/2 + 1;
        // System.out.println("firstNegOne: " + firstNegOne + ", span1: " + span1 + ", span2: " + span2);

        span1 = (lastNegOne == 0) ? 0 : lastNegOne;
        span2 = (lastNegOne == counts.length - 2) ? 0 : counts.length - 2 - lastNegOne;
        int finalTotal2 = span1/2 * span1/2 + span2/2 * span2/2 + 1;
        // System.out.println("lastNegOne: " + lastNegOne + ", span1: " + span1 + ", span2: " + span2);


        // System.out.println("Starting sum: " + startingSum);
        // System.out.println("finalTotal1: " + finalTotal1);
        // System.out.println("finalTotal2: " + finalTotal2);

        ret = (Math.max(finalTotal1, finalTotal2) - startingSum) / 2;

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

