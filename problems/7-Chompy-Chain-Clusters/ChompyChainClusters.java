import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

import java.util.PriorityQueue;

public class ChompyChainClusters {

    public static class Pair {
        int row;
        int column;

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public static ArrayList<Integer> computeChompyChainClusterSizes(int gridWidth, ArrayList<Pair> chainLocations, ArrayList<Double> startTimes) {
        // Your code to determine and return the solution
        // YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE
        ArrayList<Integer> A = new ArrayList<Integer>();
        return A;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        sc.nextLine();

        for (int testCase=0; testCase < numOfTestCases; testCase++) {
            int gridWidth = sc.nextInt();
            int numChains = sc.nextInt();

            ArrayList<Pair> chainLocations = new ArrayList<Pair>();
            ArrayList<Double> startTimes = new ArrayList<Double>();

            for (int i = 0; i < numChains; i++) {
                int row = sc.nextInt();
                int col = sc.nextInt();
                chainLocations.add(new Pair(row, col));
                double start = sc.nextDouble();
                startTimes.add(start);
            }

            ArrayList<Integer> ClusterSizes = computeChompyChainClusterSizes(gridWidth, chainLocations, startTimes);
            ArrayList<String> ClusterStrings = new ArrayList<String>();
            for (int i = 0; i < ClusterSizes.size(); i++) {
                ClusterStrings.add("" + ClusterSizes.get(i));
            }
            String joinedString = String.join(" ", ClusterStrings);
            System.out.println("Cluster Sizes: " + joinedString);
        }

        sc.close();
    }
}
