import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Computes the largest platform avoiding a set of points. A set of points is given
 * in the x,y-plane all with positive y-coordinates. The problem is to compute
 * the largest-area "platform", that is, an axis-aligned rectangle whose lower edge
 * lies on the x-axis, and which contains none of the given points. To prohibit
 * infinite solutions, there is an upper limit on the dimensions of the final
 * rectangle.
 */

public class PlatformSolution {

	/** 
	 *  Main Program - Read the input and output the final answer.
	 */
	public static void main(String[] args) throws Exception {
//		String testInputFile = "test1-public.in";
//		String testOutputFile = "test1-public.out";
//		FileInputStream inStream = new FileInputStream(testInputFile);
//		PrintStream outStream = new PrintStream(testOutputFile);
//		System.setIn(inStream);
//		System.setOut(outStream);

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

	/*----------------------- INSERT CODE HERE --------------------------*/
		
	/*-------------------------------------------------------------------*/
	/*                   START OF PROTECTED MATERIAL                     */
	/*-------------------------------------------------------------------*/
		
//	boolean useBruteForce = false; // brute force solution (for testing)
//	boolean useCubic = true; // cubic-time solution (for testing)
//	
//	/*-------------------------------------------------------------------*/
//	/* Brute-force - Runs in O(N^2 n), where N is the grid width.        */
//	/*-------------------------------------------------------------------*/
//	if (useBruteForce) { // brute-force solution
//		int bestLeft = -maxWidth;
//		int bestRight = +maxWidth;
//		int bestTop = 0;
//		int bestArea = 0;
//		for (int left = -maxWidth; left < 0; left++) { // try all lefts
//			for (int right = 1; right <= maxWidth; right++) { // try all rights
//				int top = maxHeight;
//				for (int i = 0; i < x.length; i++) { // find highest legal top
//					if (x[i] > left && x[i] < right && y[i] < top) top = y[i];
//				}
//				int area = (right - left) * top;
//				if (area > bestArea) { // found a better solution?
//					bestLeft = left; // ...then save it
//					bestRight = right;
//					bestTop = top;
//					bestArea = area;
//				}
//			}
//		}
//		ArrayList<Integer> finalPlatform = new ArrayList<Integer>();
//		finalPlatform.add(bestLeft); // copy values to final result 
//		finalPlatform.add(bestRight);
//		finalPlatform.add(bestTop);
//		finalPlatform.add(bestArea);
//		System.err.println("Finished one run of brute-force solution");
//		return finalPlatform;
//	}
//	
//	/*-------------------------------------------------------------------*/
//	/* This is cubic time, O(n^3), where n is the number of points.      */
//	/*-------------------------------------------------------------------*/
//	if (useCubic) { // cubic time solution (a bit smarter)
//		int bestLeft = -maxWidth;
//		int bestRight = +maxWidth;
//		int bestTop = 0;
//		int bestArea = 0;
//		for (int l = 0; l < x.length; l++) { // try all lefts
//			int left = x[l];
//			if (left >= 0) continue;
//			for (int r = 0; r < x.length; r++) { // try all rights
//				int right = x[r]; 
//				int top = maxHeight;
//				for (int t = 0; t < x.length; t++) { // find highest legal top
//					if (x[t] > left && x[t] < right && y[t] < top) top = y[t];
//				}
//				int area = (right - left) * top;
//				if (area > bestArea) { // found a better solution?
//					bestLeft = left; // ...then save it
//					bestRight = right;
//					bestTop = top;
//					bestArea = area;
//				}
//			}
//		}
//		ArrayList<Integer> finalPlatform = new ArrayList<Integer>();
//		finalPlatform.add(bestLeft); // copy values to final result 
//		finalPlatform.add(bestRight);
//		finalPlatform.add(bestTop);
//		finalPlatform.add(bestArea);
//		System.err.println("Finished one run of cubic solution");
//		return finalPlatform;
//	}

	/**
	 * How it works: The solution starts with the observation that the coordinates
	 * of the max-area platform will be taken from the points themselves. We begin by
	 * sorting points in ascending order of y-coordinates. As we sweep upwards, the
	 * current y-coordinate can be thought of as the top of the current platform. Among
	 * the points seen so far in the sweep, we maintain the largest x-coordinate to
	 * the left of the origin (left wall) and the smallest x-coordinate to the right
	 * of the origin (right wall). Clearly, there are no points in the rectangle
	 * [left..right] x [0..top]. Further, if the x-coordinate of the current point
	 * lies between left and right, then this rectangle is locally maximal in area.
	 * We compute its area, and return the largest area seen throughout the sweep.
	 *
	 * Initially, we set left = -maxWidth, right = +maxWidth. To handle the height
	 * condition, we add a sentinel point at (0,maxHeight) which forbids our
	 * rectangle from exceeding this height.
	 */
	
		class Point implements Comparable<Point> { // class for storing points
			public int x;
			public int y;
			Point(int x, int y) { this.x = x; this.y = y; }
			public String toString() { return "(" + x + "," + y + ")"; }
			public int compareTo(Point p2) { // sort by y then x
				if (y < p2.y) return -1;
				if (y > p2.y) return +1;
				else if (x < p2.x) return -1;
				else if (x > p2.x) return +1; 
				else return 0;
			}
		}
		
		ArrayList<Point> points = new ArrayList<Point>();
		int nPts = x.length;
		for (int i = 0; i < nPts; i++) {
			points.add(new Point(x[i], y[i])); // copy points to ArrayList
		}
		points.add(new Point(0, maxHeight)); // sentinel point at top center
		
		Collections.sort(points); // sort points bottom-up
				
		int left = -maxWidth; // x-coordinate of current left side
		int right = +maxWidth; // x-coordinate of current right side
		int top = 0; // y-coordinate of current top
		int area = 0; // current area
		int bestLeft = left; // x-coordinate of best left
		int bestRight = right; // x-coordinate of best right
		int bestTop = top; // y-coordinate of best top
		int bestArea = 0; // best area
		
		for (Point pt : points) {
			top = Math.min(pt.y, maxHeight);
			area = (right - left) * top;
			if (area > bestArea) { // new best area?
				bestLeft = left; // ...then save it
				bestRight = right;
				bestTop = top;
				bestArea = area;
			}
			if (pt.x <= 0) left = Math.max(left, pt.x); // update platform sides
			if (pt.x >= 0) right = Math.min(right, pt.x);
		}
		
		ArrayList<Integer> finalPlatform = new ArrayList<Integer>();
		finalPlatform.add(bestLeft); // copy values to final result 
		finalPlatform.add(bestRight);
		finalPlatform.add(bestTop);
		finalPlatform.add(bestArea);
		return finalPlatform;
		

	/*-------------------------------------------------------------------*/
	/*                   END OF PROTECTED MATERIAL                       */
	/*-------------------------------------------------------------------*/
	}

}
