import java.util.Scanner;

public class CountTrianglesSolution {
    private static int countTriangles(int M, boolean[][] paths) {
        int count = 0;

        /* ------------------- INSERT CODE HERE ----------------------*/
        /* ------------- Fill free to use subroutines ----------------*/
        for(int i = 0; i < M; i++) {
            for(int j = i + 1; j < M; j++) {
                for(int k = j + 1; k < M; k++) {
                    if(paths[i][j] && paths[j][k] && paths[k][i]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            int M = sc.nextInt();
            sc.nextLine(); // skip the newline

            boolean[][] paths = new boolean[M][M];

            // read the lines one by one until we encounter a blank line
            String line = sc.nextLine();
            while(line.length() > 0) {
                String[] parts = line.split(" ");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);
                paths[a][b] = true;
                paths[b][a] = true;
                line = sc.nextLine();
            }

            int result = countTriangles(M, paths);

            System.out.println("There are " + result + " distinct triangles among the landmarks.");
        }
    }
}

