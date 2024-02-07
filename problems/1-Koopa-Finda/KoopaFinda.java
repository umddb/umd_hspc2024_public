import java.util.*;

public class KoopaFinda {

    private static int lookForKoopa(String str) {
        int result = -1;

        // Insert your code here

        return result;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        sc.nextLine();

        for (int testCase=0; testCase < numOfTestCases; testCase++) { 
            String str = sc.nextLine();
            System.out.println(
                    "Result is " + lookForKoopa(str)
                    );
        }

        sc.close();
    }


}

