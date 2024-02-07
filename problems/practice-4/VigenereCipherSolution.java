import java.util.Scanner;
import java.util.Arrays;

public class VigenereCipherSolution {

    private static String solveVignere(String key, String plainText) {
        String cipherText = "";
        /* ------------------- INSERT CODE HERE ---------------------
         *
         * Your code should set "cipherText" to be the encrypted text.
         *
         * */

        int key_length = key.length();
        for(int i = 0; i < plainText.length(); i++) {
            int a = (int) 'A';
            int c = (int) plainText.charAt(i);
            c = c - a;
            int k = (int) key.charAt(i % key_length);
            k = k - a;

            if( (k + c) < 26) {
                cipherText += (char) ('A' + (k + c));
            } else {
                cipherText += (char) ('A' + (k + c - 26));
            }
        }

        /* -------------------- END OF INSERTION --------------------*/
        return cipherText;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            String key = sc.next();

            String plainText = sc.next();

            System.out.println("Ciphertext: " + solveVignere(key, plainText));
        }
    }
}
