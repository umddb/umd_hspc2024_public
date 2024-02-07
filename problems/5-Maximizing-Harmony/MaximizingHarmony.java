import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

public class MaximizingHarmony {

    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
            public int compareTo(Edge other) {
                return Integer.compare(this.weight, other.weight);  
            }
    }

    public static int maxHarmony(int numVertices, ArrayList<Edge> edges) {
        return 0;
    }

    public static int RunTestCase(Scanner sc) {
        // Read the seed and the number of nodes.
        int seed=sc.nextInt();
        int N=sc.nextInt();

        Random r = new Random(seed);
        Integer[] P = new Integer[N-1];
        for (int i = 0; i < N-1; i++) {
            P[i] = i;
        }
        Collections.shuffle(Arrays.asList(P), r);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int i = 1; i < N; i++) {
            int target = r.nextInt(i);
            edges.add(new Edge(i, target, P[i-1]));
        }

        return maxHarmony(N, edges);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        sc.nextLine();

        for (int testCase=0; testCase < numOfTestCases; testCase++) { 
            int answer = RunTestCase(sc);
            System.out.println("Max Penalty: " + answer);
        }

        sc.close();
    }
}
