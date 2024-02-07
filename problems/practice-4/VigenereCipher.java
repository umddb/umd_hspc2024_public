import java.util.Scanner;
import java.util.Arrays;

public class VigenereCipher {

    private static String solveVignere(String key, String plainText) {
        String cipherText = "";

        /* ------------------- INSERT CODE HERE ---------------------
         *
         * Your code should set "cipherText" to be the encrypted text.
         *
         * */


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
