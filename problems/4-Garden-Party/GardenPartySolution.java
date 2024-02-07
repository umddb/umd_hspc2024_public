import java.util.Scanner;


class Solution {
    char[] four_set = new char[4];
    char[] three_set = new char[3];
}

public class GardenPartySolution {
    private static Solution solveRamsey(String[] pairs) {
        Solution sol = new Solution();


        // ------------------- INSERT CODE HERE ----------------------
        // ------ Feel free to add helper functions if needed. -------
        //

        // If you cannot find a 4-sset or 3-set with the respective property, then explicitly set that to null in "sol".
        // sol.four_set = null;
        // sol.three_set = null;

        // print pairs
        // for(int i = 0; i < pairs.length; i++) {
            // System.out.println(pairs[i]);
        // }

        // convert to an adjacency matrix
        int M = pairs.length;
        boolean[][] adj = new boolean[9][9];

        // each pair is of the form AB, where A and B are letters
        for(int i = 0; i < M; i++) {
            String pair = pairs[i];
            int a = pair.charAt(0) - 'A';
            int b = pair.charAt(1) - 'A';
            adj[a][b] = true;
            adj[b][a] = true;
        }

        // find a 4-set that have worked together
        boolean found = false;
        for(int i1 = 0; i1 < 9; i1++) {
            for(int i2 = i1 + 1; i2 < 9; i2++) {
                for(int i3 = i2 + 1; i3 < 9; i3++) {
                    for(int i4 = i3 + 1; i4 < 9; i4++) {
                        if(!found && adj[i1][i2] && adj[i1][i3] && adj[i1][i4] && adj[i2][i3] && adj[i2][i4] && adj[i3][i4]) {
                            // convert to letters
                            sol.four_set[0] = (char) (i1 + 'A');
                            sol.four_set[1] = (char) (i2 + 'A');
                            sol.four_set[2] = (char) (i3 + 'A');
                            sol.four_set[3] = (char) (i4 + 'A');
                            found = true;
                            break;
                        }
                    }
                }
            }
        }

        if (!found) {
            sol.four_set = null;
        }

        // find a 3-set that have not worked together
        found = false;
        for(int i1 = 0; i1 < 9; i1++) {
            for(int i2 = i1 + 1; i2 < 9; i2++) {
                for(int i3 = i2 + 1; i3 < 9; i3++) {
                    if(!found && !adj[i1][i2] && !adj[i1][i3] && !adj[i2][i3]) {
                        sol.three_set[0] = (char) (i1 + 'A');
                        sol.three_set[1] = (char) (i2 + 'A');
                        sol.three_set[2] = (char) (i3 + 'A');
                        found = true;
                        break;
                    }
                }
            }
        }

        if (!found) {
            sol.three_set = null;
        }
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

