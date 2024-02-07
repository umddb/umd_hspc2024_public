import java.util.*;

public class PerilousPath {

    private static int findPath(char[][] grid) {
        int steps = -1;

        // You Code Here
        // Feel free to use helper functions if needed

        return steps;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        sc.nextLine();

        char[][] levelGrid;
        for (int testCase=0; testCase < numOfTestCases; testCase++) { 
            int width = sc.nextInt();
            int height = sc.nextInt();
            sc.nextLine();
            levelGrid = new char[height][width];
            for (int row=0; row<height; row++) {
                String currRow = sc.nextLine();
                for (int col=0; col<width; col++) {
                    char val = currRow.charAt(col);
                    levelGrid[row][col]=val;
                }
            }

            int result=findPath(levelGrid);
            if (result > 0) {
                System.out.println(
                        "Path length is " + findPath(levelGrid)
                        );
            }
            else {
                System.out.println(
                        "Path is blocked." 
                        );
            }
        }

        sc.close();
    }


}

