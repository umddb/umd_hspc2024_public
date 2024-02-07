import java.util.*;

/**
 * Computes the largest platform avoiding a set of points. A set of points is given
 * in the x,y-plane all with positive y-coordinates. The problem is to compute
 * the largest-area "platform", that is, an axis-aligned rectangle whose lower edge
 * lies on the x-axis, and which contains none of the given points. To prohibit
 * infinite solutions, there is an upper limit on the dimensions of the final
 * rectangle.
 */

public class Platform {

    /** 
     *  Main Program - Read the input and output the final answer.
     */
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        try {
            int numCases = scanner.nextInt(); // number of test cases to run

            for(int t = 1; t <= numCases; t++) {
                int maxWidth = scanner.nextInt(); // maximum platform width (from origin)
                int maxHeight = scanner.nextInt(); // maximum platform height
                int nPts = scanner.nextInt(); // number of input points

                if (maxWidth <= 0 || maxHeight <= 0 || nPts <= 0) {
                    System.err.println("Error - Max width, max height, and number of points must be positive");
                    return;
                }

                int[] x = new int[nPts]; // point coordinate storage
                int[] y = new int[nPts];
                int[] x2 = new int[nPts]; // our backup copy
                int[] y2 = new int[nPts];

                for (int i = 0; i < nPts; i++) { // input the points
                    x[i] = x2[i] = scanner.nextInt();
                    y[i] = y2[i] = scanner.nextInt();
                    if (y[i] < 0) {
                        System.err.println("Error - Points must lie on or above the x-axis");
                        return;
                    }
                }
                System.out.println("Test case: " + t); // summarize the input
                System.out.println("  Max Width: " + maxWidth + " Max Height: " + maxHeight);
                System.out.print("  Points:");
                for (int i = 0; i < nPts; i++) {
                    System.out.print(" (" + x[i] + "," + y[i] + ")");
                    if (i != nPts-1 && i%10 == 9) System.out.print(System.lineSeparator() + "         ");
                }
                System.out.println();

                ArrayList<Integer> finalPlatform = getMaxPlatform(maxWidth, maxHeight, x, y); // get solution

                sumarizeResult(maxWidth, maxHeight, x2, y2, finalPlatform); // and summarize it
            }
        } catch (Exception e) {	// something went wrong
            System.err.println("Error encountered in processing input/output");
            e.printStackTrace();
        }
        finally { // close scanner resource
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /** Summarize the result and check for errors.
     */
    private static void sumarizeResult(int maxWidth, int maxHeight, int[] x, int[] y, ArrayList<Integer> finalPlatform)
    {
        int nPts = x.length; // number of points
        if (finalPlatform.size() != 4) {	// expecting three numbers
            System.out.println("  Error - Returned answer must have 4 numbers");
            return;
        }
        int left = finalPlatform.get(0); // solution parameters
        int right = finalPlatform.get(1);
        int top = finalPlatform.get(2);
        int area = finalPlatform.get(3);

        System.out.println("  Final platform dimensions [" + left + "..." + right + "] x [0..." + top + "]. Area = " + area);

        if (left > 0 || right < 0 || top < 0) {
            System.out.println("  Error - Final platform does not contain the origin");
        }
        if (left > right) {
            System.out.println("  Error - Minimum x exceeds maximum x");
        }
        int actualArea = (right - left) * top;
        if (area != actualArea) {
            System.out.println("  Error - Final area returned does not match that of the final platform");
        }

        for (int i = 0; i < nPts; i++) {
            if (x[i] > left && x[i] < right && y[i] < top) {
                System.out.println("  Error - Input point [" + i + "] lies within the final platform");
            }
        }
    }

    static ArrayList<Integer> getMaxPlatform(int maxWidth, int maxHeight, int[] x, int[] y) {
        ArrayList<Integer> finalPlatform = new ArrayList<Integer>();

        /*----------------------- INSERT CODE HERE --------------------------*/
        /*----------- Feel free to add helper functions if needed ------------*/

        return finalPlatform;
    }

}
