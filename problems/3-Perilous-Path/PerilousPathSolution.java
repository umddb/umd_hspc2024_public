import java.util.*;

public class PerilousPathSolution {

    //MY SOLUTION'S HELPER
    private static boolean validGridPosition(int width, int height, int row, int col) {
        return 
            row >=0 && row <height
            &&
            col >=0 && col < width
            ;
    } 
    //MY SOLUTION'S HELPER

    private static int findPath(char[][] grid) {
        int steps = -1;

        // You Code Here
        // Feel free to use helper functions if needed
        int height = grid.length;
        int width = grid[0].length;

        ArrayDeque< Integer[] > myQueue;
        myQueue = new ArrayDeque< Integer[] >(); //row,col,steps-to-get-here
        for (int row=0; row<height; row++) {
            for (int col=0; col<width; col++) {
                if (grid[row][col] == 'M') {
                    Integer[] toAdd = {row,col,0};
                    myQueue.add( toAdd );
                } 
            }
        }

        boolean[][] visited = new boolean[height][width];
        for (int row=0; row<height; row++) {
            for (int col=0; col<width; col++) {
                visited[row][col] = false;
            }
        }

        while (!myQueue.isEmpty()) {
            Integer[] pos = myQueue.poll();

            ArrayList< Integer[] > neighbors = new ArrayList< Integer[] >();
            Integer[] myUp = {pos[0], pos[1]-1, pos[2]+1};
            neighbors.add( myUp );
            Integer[] myDown = {pos[0], pos[1]+1, pos[2]+1};
            neighbors.add( myDown );
            Integer[] myLeft = {pos[0]-1, pos[1], pos[2]+1};
            neighbors.add( myLeft );
            Integer[] myRight = {pos[0]+1, pos[1], pos[2]+1};
            neighbors.add( myRight );

            for (int dir=0; dir<4; dir++) {
                int row = neighbors.get(dir)[0];
                int col = neighbors.get(dir)[1];
                int stepsToHere = neighbors.get(dir)[2];

                if ( validGridPosition(width,height,row,col) ) {
                    if ( grid[row][col] == 'P' ) {
                        steps = stepsToHere;
                    } else if ( !visited[row][col] && grid[row][col] == 'O') {
                        visited[row][col] = true;
                        Integer[] toAdd = {row,col,stepsToHere};
                        myQueue.add( toAdd );
                    }
                }
            }
        }

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

