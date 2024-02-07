import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

public class MaximizingHarmonySolution {

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

  public static class Pair implements Comparable<Pair> {
    int weight;
    int index;
  
    public Pair(int weight, int index) {
      this.weight = weight;
      this.index = index;
    }
  
    @Override
    public int compareTo(Pair other) {
      return Integer.compare(this.weight, other.weight);  
    }
  }

  public static class UnionFind {
    Integer[] parents;

    public UnionFind(int numVertices) {
      parents = new Integer[numVertices];
      for (int i = 0; i < numVertices; i++) {
        parents[i] = i;
      }
    }

    public int Find(int i) {
      int j = i;
      if (parents[j] == j) {
        return j;
      }
      while (parents[j] != j) {
        j = parents[j];
      }
      int tmp = parents[i];
      while (parents[tmp] != j) {
        parents[i] = j;
        i = tmp;
        tmp = parents[tmp];
      }
      return j;
    }

    public void Unite(int u, int v) {
      int c_u = Find(u);
      int c_v = Find(v);
      parents[c_v] = c_u;
    }

  }

  public static int maxHarmony(int numVertices, ArrayList<Edge> edges) {
    // Sort the edges by weight, and keep track of the index of the
    // edge (this helps avoid sorting the full edge tuple).
    Pair[] pairs = new Pair[edges.size()];
    for (int i = 0; i < edges.size(); i++) {
      pairs[i] = new Pair(edges.get(i).weight, i);
    }
    Collections.sort(Arrays.asList(pairs));

    Integer[] heights = new Integer[numVertices];
    for (int i = 0; i < numVertices; i++) {
      heights[i] = 0;
    }

    UnionFind UF = new UnionFind(numVertices);

    // Iterate over the edges by weight.
    int max_h = 0; 
    for (int i = 0; i < pairs.length; i++) {
      int index = pairs[i].index;
      Edge edge = edges.get(index);
      // System.out.println("" + edge.u + " " + edge.v + " " + edge.weight);
      int c_u = UF.Find(edge.u);
      int c_v = UF.Find(edge.v);
      //System.out.println("" + edge.u + " " + edge.v + " " + c_u + " " + c_v);
      UF.Unite(c_u, c_v);
      int h_u = heights[c_u];
      int h_v = heights[c_v];
      h_u = Math.max(h_u, h_v) + 1;
      heights[c_u] = h_u;
      max_h = Math.max(max_h, h_u);
    }

    return max_h;
  }

	public static int RunTestCase(Scanner sc) {
		// Read the seed and the number of nodes.
		int seed=sc.nextInt();
		int N=sc.nextInt();
    //System.out.println("" + seed + " " + N);

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
    //System.out.println("Num test cases = " + numOfTestCases);
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			int answer = RunTestCase(sc);
			System.out.println("Max Penalty: " + answer);
		}

		sc.close();
	}
}
