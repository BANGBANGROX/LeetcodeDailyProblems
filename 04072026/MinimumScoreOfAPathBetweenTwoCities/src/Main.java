import java.util.Scanner;

class Solution {
    private static class DisjointSet {
        private final int[] parent;
        private final int[] rank;
        private final int[] weight;

        DisjointSet(final int n) {
            parent = new int[n];
            rank = new int[n];
            weight = new int[n];

            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                weight[i] = Integer.MAX_VALUE;
            }
        }

        public int find(final int node) {
            if (node == parent[node]) {
                return node;
            }

            return parent[node] = find(parent[node]);
        }

        public void union(final int u, final int v, int wt) {
            final int rootU = find(u);
            final int rootV = find(v);

            if (rootU == rootV) {
                weight[rootU] = Math.min(weight[rootU], wt);
                return;
            }

            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
                weight[rootU] = Math.min(weight[rootU], Math.min(weight[rootV], wt));
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
                weight[rootV] = Math.min(weight[rootV], Math.min(weight[rootU], wt));
            } else {
                parent[rootU] = rootV;
                weight[rootV] = Math.min(weight[rootV], Math.min(weight[rootU], wt));
                ++rank[rootV];
            }
        }

        public int getWeight(final int node) {
            return weight[find(node)];
        }
    }

    public int minScore(int n, int[][] roads) {
        final DisjointSet disjointSet = new DisjointSet(n);

        for (final int[] road : roads) {
            disjointSet.union(road[0] - 1, road[1] - 1, road[2]);
        }

        return disjointSet.getWeight(0);
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[][] edges = new int[m][3];

        for (int i = 0; i < m; ++i) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
            edges[i][2] = scanner.nextInt();
        }

        System.out.println(new Solution().minScore(n, edges));

        scanner.close();
    }
}
