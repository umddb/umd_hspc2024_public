import java.util.Scanner;

public class Anagrams {
	public static boolean areAnagrams(String word1, String word2) {
		boolean ret = false;
		// YOUR CODE HERE

		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine(); // skip the newline character

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			String word1 = sc.next(); 
            String word2 = sc.next();
			System.out.println(areAnagrams(word1, word2) ? "Yes": "No");
		}
		sc.close();
	}
}
