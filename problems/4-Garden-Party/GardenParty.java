import java.util.Scanner;


class Solution {
    char[] four_set = new char[4];
    char[] three_set = new char[3];
}

public class GardenParty {
    private static Solution solveRamsey(String[] pairs) {
        Solution sol = new Solution();


        // ------------------- INSERT CODE HERE ----------------------
        // ------ Feel free to add helper functions if needed. -------
        //

        // If you cannot find a 4-set or 3-set with the respective property, then explicitly set that to null in "sol".

        // Comment out these two lines -- they are only there to make the skeleton code run properly
        sol.four_set = null;
        sol.three_set = null;

        return sol;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            int M = sc.nextInt();

            String[] pairs = new String[M];

            for(int j = 0; j < M; j++) {
                pairs[j] = sc.next();
            }

            Solution sol = solveRamsey(pairs);

            if(sol.four_set == null) {
                System.out.println("No 4-set of people have all gone on quests with each other.");
            } else {
                System.out.println("These 4 people have gone on quests with each other : " + sol.four_set[0] + " " + sol.four_set[1] + " " + sol.four_set[2] + " " + sol.four_set[3]);
            }

            if(sol.three_set == null) {
                System.out.println("Every 3-set of people has a pair that has done a quest."); 
            } else {
                System.out.println("These 3 people have not gone on quests with each other : " + sol.three_set[0] + " " + sol.three_set[1] + " " + sol.three_set[2]);
            }
        }
    }
}

