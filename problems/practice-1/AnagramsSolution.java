import java.util.Scanner;

public class AnagramsSolution {
	public static boolean areAnagrams(String word1, String word2) {
		boolean ret = false;
		// YOUR CODE HERE
        // Implement the logic to check if the two words are anagrams

        if (word1.length() != word2.length()) {
            return false;
        }

        // Convert strings to char arrays
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();

        // Sort the char arrays
        java.util.Arrays.sort(word1Array);
        java.util.Arrays.sort(word2Array);

        // Check if sorted char arrays are equal
        ret = java.util.Arrays.equals(word1Array, word2Array);

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
