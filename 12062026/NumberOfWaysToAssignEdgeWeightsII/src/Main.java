import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    private static class BinaryLifting {
        private final List<List<Integer>> tree;
        private final int[] depth;
        private final int[] timeIn;
        private final int[] timeOut;
        private final int[][] up;
        private final int maxLog;
        private int timer;

        BinaryLifting(final int[][] edges) {
            final int n = edges.length + 1;
            depth = new int[n + 1];
            timeIn = new int[n + 1];
            timeOut = new int[n + 1];
            maxLog = (int) Math.ceil((Math.log(n) / Math.log(2)));
            up = new int[n + 1][maxLog + 1];
            timer = 0;
            tree = new ArrayList<>();

            for (int i = 0; i <= n; ++i) {
                tree.add(new ArrayList<>());
            }

            for (final int[] edge : edges) {
                tree.get(edge[0]).add(edge[1]);
                tree.get(edge[1]).add(edge[0]);
            }

            dfs(1, 0, 0);
        }

        public int findDistance(final int u, final int v) {
            final int lca = getLCA(u, v);

            return depth[u] + depth[v] - 2 * depth[lca];
        }

        private int getLCA(final int u, final int v) {
            if (isAncestor(u, v)) {
                return u;
            }

            if (isAncestor(v, u)) {
                return v;
            }

            int start = u;

            for (int i = maxLog; i >= 0; --i) {
                if (up[start][i] != 0 && !isAncestor(up[start][i], v)) {
                    start = up[start][i];
                }
            }

            return up[start][0];
        }

        private void dfs(final int node, final int parent, final int currentDepth) {
            depth[node] = currentDepth;
            timeIn[node] = incrementAndGetTimer();

            up[node][0] = parent;

            for (int i = 1; i <= maxLog; ++i) {
                up[node][i] = up[up[node][i - 1]][i - 1];
            }

            for (final int child : tree.get(node)) {
                if (child != parent) {
                    dfs(child, node, currentDepth + 1);
                }
            }

            timeOut[node] = incrementAndGetTimer();
        }

        private boolean isAncestor(final int u, final int v) {
            return timeIn[u] <= timeIn[v] && timeOut[u] >= timeOut[v];
        }

        private int incrementAndGetTimer() {
            ++timer;

            return timer;
        }
    }

    public int[] assignEdgeWeights(final int[][] edges, final int[][] queries) {
        final BinaryLifting binaryLifting = new BinaryLifting(edges);
        final int q = queries.length;
        final int[] answer = new int[q];

        for (int i = 0; i < q; ++i) {
            final int distance = binaryLifting.findDistance(queries[i][0], queries[i][1]);
            answer[i] = binaryExponentiation(2, distance - 1);
        }

        return answer;
    }

    private static int binaryExponentiation(final int a, final int b) {
        if (b < 0) {
            return 0;
        }

        long result = 1;
        long base = a;
        long power = b;

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

        final int q = scanner.nextInt();
        final int[][] queries = new int[q][2];

        for (int i = 0; i < q; ++i) {
            queries[i][0] = scanner.nextInt();
            queries[i][1] = scanner.nextInt();
        }

        final int[] answer = new Solution().assignEdgeWeights(edges, queries);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();

    }
}
