import java.util.Scanner;

public class LandmarkColors {

    private static int colorLandmarks(int M, int[][] paths) {
        int ret = -1;

        /* ------------------- INSERT CODE HERE ----------------------*/
        /* ------------- Fill free to use subroutines ----------------*/

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

