import java.util.Scanner;

public class LandmarkColorsSolution {

    private static boolean checkForColoring(int M, boolean[][] adjMatrix, int numColors) {
        // We will do dynamic programming here of sorts
        // For each i = 0 to M/3, we will recursively check if it is possible to color the 3 landmarks in i'th group with a specified set of colors
        boolean possible[][][][] = new boolean[M/3][numColors][numColors][numColors];

        for (int i = 0; i < M/3; i++) {
            for (int c1 = 0; c1 < numColors; c1++) {
                for (int c2 = 0; c2 < numColors; c2++) {
                    for (int c3 = 0; c3 < numColors; c3++) {
                        // can we assign colors c1 c2 c3 to the i'th group of landmarks?

                        // let's check within group first
                        if ( (adjMatrix[i*3][i*3+1]   && (c1 == c2)) ||
                             (adjMatrix[i*3][i*3+2]   && (c1 == c3)) ||
                             (adjMatrix[i*3+1][i*3+2] && (c2 == c3)) ) {
                                possible[i][c1][c2][c3] = false;
                        } else {
                            if (i == 0) {
                                possible[i][c1][c2][c3] = true;
                            } else {
                                // need to check with the previous group
                                for (int c1p = 0; c1p < numColors; c1p++) {
                                    for (int c2p = 0; c2p < numColors; c2p++) {
                                        for (int c3p = 0; c3p < numColors; c3p++) {
                                            if (possible[i-1][c1p][c2p][c3p]) {
                                                if (adjMatrix[i*3][i*3-3] && c1 == c1p) continue;
                                                if (adjMatrix[i*3][i*3-2] && c1 == c2p) continue;
                                                if (adjMatrix[i*3][i*3-1] && c1 == c3p) continue;
                                                if (adjMatrix[i*3+1][i*3-3] && c2 == c1p) continue;
                                                if (adjMatrix[i*3+1][i*3-2] && c2 == c2p) continue;
                                                if (adjMatrix[i*3+1][i*3-1] && c2 == c3p) continue;
                                                if (adjMatrix[i*3+2][i*3-3] && c3 == c1p) continue;
                                                if (adjMatrix[i*3+2][i*3-2] && c3 == c2p) continue;
                                                if (adjMatrix[i*3+2][i*3-1] && c3 == c3p) continue;
                                                possible[i][c1][c2][c3] = true;
                                                break;
                                            }
                                        }
                                        if (possible[i][c1][c2][c3]) break;
                                    }
                                    if (possible[i][c1][c2][c3]) break;
                                }
                            }
                        }
                    }
                }
            }
        }
        // let's check if we can color the last group
        for (int c1 = 0; c1 < numColors; c1++) {
            for (int c2 = 0; c2 < numColors; c2++) {
                for (int c3 = 0; c3 < numColors; c3++) {
                    if (possible[M/3-1][c1][c2][c3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int colorLandmarks(int M, int[][] paths) {
        int ret = -1;

        /* ------------------- INSERT CODE HERE ----------------------*/
        /* ------------- Fill free to use subroutines ----------------*/

        // convert paths to an adjacency matrix
        boolean[][] adjMatrix = new boolean[M][M];
        for (int i = 0; i < paths.length; i++) {
            int a = paths[i][0]-1;
            int b = paths[i][1]-1;
            adjMatrix[a][b] = true;
            adjMatrix[b][a] = true;
        }

        for (int i = 1; i < 6; i++) {
            if (checkForColoring(M, adjMatrix, i)) {
                ret = i;
                break;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            int M = sc.nextInt();
            int E = sc.nextInt();
            int[][] paths = new int[E][2];

            for(int j = 0; j < E; j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                paths[j][0] = a;
                paths[j][1] = b;
            }

            int minColors = colorLandmarks(M, paths);

            // print out
            if (minColors == -1) {
                System.out.println("Not possible to color the landmarks with less than 5 colors.");
            } else {
                System.out.println("Possible to color the landmarks with " + minColors + " colors.");
            }
        }
    }
}

