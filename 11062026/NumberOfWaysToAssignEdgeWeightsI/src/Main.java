import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    private List<List<Integer>> tree;
    private int maxDepth;
    private int[] depth;
    private static final int MOD = (int) 1e9 + 7;

    public int assignEdgeWeights(final int[][] edges) {
        final int n = edges.length + 1;
        tree = new ArrayList<>();
        depth = new int[n + 1];
        maxDepth = 0;

        for (int i = 0; i <= n; ++i) {
            tree.add(new ArrayList<>());
        }

        for (final int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        dfs(1, 0, 0);

        return binaryExponentiation(2, maxDepth - 1);
    }

    private void dfs(final int node, final int parent, final int currentDepth) {
        depth[node] = currentDepth;
        maxDepth = Math.max(maxDepth, depth[node]);

        for (final int child : tree.get(node)) {
            if (child != parent) {
                dfs(child, node, currentDepth + 1);
            }
        }
    }

    private int binaryExponentiation(final int a, final int b) {
        long result = 1;
        long base = a;
        int power = b;

        while (power > 0) {
            if ((power & 1) > 0) {
                result = (result * base) % MOD;
                --power;
            }

            base = (base * base) % MOD;
            power >>= 1;
        }

        return (int) result;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] edges = new int[n - 1][2];

        for (int i = 0; i < n - 1; ++i) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        System.out.println(new Solution().assignEdgeWeights(edges));

        scanner.close();
    }
}
