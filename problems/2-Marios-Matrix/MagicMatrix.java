import java.util.Scanner;
import java.lang.Math;

public class MagicMatrix {



    public static int reconstructMatrix(int[][] matrix) {
        int coin_haul = 0;

        // YOUR CODE HERE
        // Feel free to write helper functions if needed


        return coin_haul;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        sc.nextLine();

        for (int testCase=0; testCase < numOfTestCases; testCase++) { 
            int[][] matrix = new int[4][4];

            for (int i=0; i<4; i++) {
                for (int j=0; j<4; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            int coin_haul = reconstructMatrix(matrix);

            // print out the result
            System.out.println("The maximum number of coins that can be collected is: " + coin_haul);
        }

        sc.close();
    }
}
