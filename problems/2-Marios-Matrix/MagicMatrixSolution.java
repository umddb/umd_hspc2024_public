import java.util.Scanner;
import java.lang.Math;

public class MagicMatrixSolution {


    private static int computeHaul(int[][] matrix) {
        int coin_haul = 0;
        
        // sum up the products of each row and column
        // initialize the arrays to 1
        int[] row_products = new int[]{1, 1, 1, 1};
        int[] col_products = new int[]{1, 1, 1, 1};
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                row_products[i] *= matrix[i][j];
                col_products[j] *= matrix[i][j];
            }
        }

        // sum up and return
        for (int i=0; i<4; i++) {
            coin_haul += row_products[i] + col_products[i];
        }

        //// print the matrix and the coin haul
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("coin_haul: " + coin_haul);

        return coin_haul;
    }

	public static int reconstructMatrix(int[][] matrix) {
        int coin_haul = 0;

		// YOUR CODE HERE
        // Feel free to write helper functions if needed

        // find the 4 missing numbers
        int[] missing = new int[4];
        int index = 0;
        for (int k=0; k<16; k++) {
            boolean found = false;
            for (int i=0; i<4; i++) {
                for (int j=0; j<4; j++) {
                    if (matrix[i][j] == k+1) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                missing[index] = k+1;
                index++;
            }
        }

        // find the four missing positions
        int[] missing_positions_row = new int[4];
        int[] missing_positions_column = new int[4];
        index = 0;
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (matrix[i][j] == 0) {
                    missing_positions_row[index] = i;
                    missing_positions_column[index] = j;
                    index++;
                }
            }
        }

        // try all permutations of the missing numbers
        for(int i1 = 0; i1 < 4; i1++) {
            for(int i2 = 0; i2 < 4; i2++) {
                for(int i3 = 0; i3 < 4; i3++) {
                    for(int i4 = 0; i4 < 4; i4++) {
                        // need them distinct
                        if (i1 == i2 || i1 == i3 || i1 == i4 || i2 == i3 || i2 == i4 || i3 == i4) {
                            continue;
                        }

                        matrix[missing_positions_row[0]][missing_positions_column[0]] = missing[i1];
                        matrix[missing_positions_row[1]][missing_positions_column[1]] = missing[i2];
                        matrix[missing_positions_row[2]][missing_positions_column[2]] = missing[i3];
                        matrix[missing_positions_row[3]][missing_positions_column[3]] = missing[i4];

                        int new_coin_haul = computeHaul(matrix);
                        if (new_coin_haul > coin_haul) {
                            coin_haul = new_coin_haul;
                        }
                    }
                }
            }
        }

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
