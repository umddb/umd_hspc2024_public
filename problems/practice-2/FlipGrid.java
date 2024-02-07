import java.util.Scanner;
import java.lang.Math;

public class FlipGrid {

	public static int[][] flipGrid(int[][] grid) {
		// YOUR CODE HERE

		return grid;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
            int[][] grid = new int[4][4];

            for (int i=0; i<4; i++) {
                for (int j=0; j<4; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            int[][] result = flipGrid(grid);

            // print out the result
            for (int i=0; i<4; i++) {
                for (int j=0; j<4; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
		}

		sc.close();
	}
}
