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

  public static class WeightedGraph {
    private int numVertices;
    private ArrayList<ArrayList<Edge>> adjList;

    // Constructor
    public WeightedGraph(int numVertices) {
      this.numVertices = numVertices;
      adjList = new ArrayList<>(numVertices);

      // Initialize empty adjacency lists for each vertex
      for (int i = 0; i < numVertices; i++) {
        adjList.add(new ArrayList<>());
      }
    }

    // Edge class to store destination and weight.
    static class Edge {
      int destination;
      double weight;

      public Edge(int destination, double weight) {
        this.destination = destination;
        this.weight = weight;
      }
    }

    // Add an undirected edge.
    public void addEdge(int source, int destination, double weight) {
      adjList.get(source).add(new Edge(destination, weight));
      adjList.get(destination).add(new Edge(source, weight));  // For undirected graph
    }

    // Add an undirected edge.
    public void addVertex() {
      this.numVertices += 1;
      adjList.add(new ArrayList<>());
    }

    public int[] dijkstra(int source) {
      double[] distances = new double[numVertices];
      boolean[] visited = new boolean[numVertices];
      int[] visitedBy = new int[numVertices];
      int[] clusterSize = new int[numVertices];
      PriorityQueue<Node> pq = new PriorityQueue<>(numVertices); // Min-heap

      // Initialize distances as infinite.
      for (int i = 0; i < numVertices; i++) {
        distances[i] = Double.MAX_VALUE;
        clusterSize[i] = 0;
      }

      // Distance from source to itself is 0.
      distances[source] = 0;
      visitedBy[source] = source;
      pq.add(new Node(source, 0));

      while (!pq.isEmpty()) {
        Node current = pq.poll();

        if (visited[current.vertex]) {
          continue;
        }

        // Mark as visited.
        visited[current.vertex] = true;
        // Check if we are visited by the super-source (i.e., we get to start a cluster at this node).
        if (visitedBy[current.vertex] == source) {
          // If so, set our visitedBy to ourselves.
          visitedBy[current.vertex] = current.vertex;
        }

        clusterSize[visitedBy[current.vertex]] += 1;

        // Iterate through neighbors of the current vertex
        for (Edge neighbor : adjList.get(current.vertex)) {
          double newDistance = distances[current.vertex] + neighbor.weight;

          if (newDistance < distances[neighbor.destination]) {
            distances[neighbor.destination] = newDistance;
            pq.add(new Node(neighbor.destination, newDistance));
            // Mark as visited by the current vertex's cluster.
            visitedBy[neighbor.destination] = visitedBy[current.vertex];
          }
        }
      }

//      // Print the shortest distances.
//      System.out.println("Shortest distances from source " + source + ":");
//      for (int i = 0; i < numVertices; i++) {
//        System.out.println("Vertex " + i + ": " + distances[i] + " " + visitedBy[i]);
//      }
      return clusterSize;
    }


    // Helper class for the priority queue
    static class Node implements Comparable<Node> {
      int vertex;
      double distance;

      public Node(int vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
      }

      @Override
      public int compareTo(Node other) {
        return Double.compare(this.distance, other.distance);
      }
    }
  }

  static int gridToVertexId(int gridWidth, int row, int col) {
    return (row * gridWidth) + col;
  }

	public static ArrayList<Integer> computeChompyChainClusterSizes(int gridWidth, ArrayList<Pair> chainLocations, ArrayList<Double> startTimes) {
		// Your code to determine and return the solution
		// YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE

    int N = gridWidth * gridWidth;
    WeightedGraph G = new WeightedGraph(N);

    for (int i = 0; i < gridWidth; i++) {
      for (int j = 0; j < gridWidth; j++) {
        int our_id = gridToVertexId(gridWidth, i, j);
        if ((i+1) < gridWidth) {
          int ngh_id = gridToVertexId(gridWidth, i+1, j);
          G.addEdge(our_id, ngh_id, 1.0f);
        }
        if ((j+1) < gridWidth) {
          int ngh_id = gridToVertexId(gridWidth, i, j+1);
          G.addEdge(our_id, ngh_id, 1.0f);
        }
      }
    }

    G.addVertex();
    for (int i = 0; i < chainLocations.size(); i++) {
      Pair loc = chainLocations.get(i);
      int id = gridToVertexId(gridWidth, loc.row, loc.column);
      G.addEdge(N, id, startTimes.get(i));
    }

    int[] clusterSizes = G.dijkstra(N);

    ArrayList<Integer> A = new ArrayList<Integer>();
    for (int i = 0; i < chainLocations.size(); i++) {
      Pair loc = chainLocations.get(i);
      int id = gridToVertexId(gridWidth, loc.row, loc.column);
      A.add(clusterSizes[id]);
    }
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
      int sumSizes = 0;
      for (int i = 0; i < ClusterSizes.size(); i++) {
        ClusterStrings.add("" + ClusterSizes.get(i));
        sumSizes += ClusterSizes.get(i);
      }
      String joinedString = String.join(" ", ClusterStrings);
      System.out.println("Cluster Sizes: " + joinedString);
      // System.out.println("Sum of sizes = " + sumSizes);
		}

		sc.close();
	}
}
