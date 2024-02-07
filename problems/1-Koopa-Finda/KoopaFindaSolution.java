import java.util.*;

public class KoopaFindaSolution {

    private static int lookForKoopa(String str) {
        int result = -1;

        // Insert your code here
        if ( str.contains("Peach") ) {
            //count the Koopas
            result = 0;
            int searchPos = 0;
            int resultPos;
            while ( (resultPos=str.indexOf("Koopa",searchPos)) != -1) {
                result++;
                searchPos=resultPos+5;
            } 
        } else {
            result = -1;
        }

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

