import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    private record Edge(int to, int wt) {
    }

    private List<Edge>[] graph;
    private int[] topologicalOrder;
    private int[] inDegree;
    private long k;
    private int n;

    public int findMaxPathScore(final int[][] edges, final boolean[] online, final long k) {
        n = online.length;
        graph = new List[n];
        inDegree = new int[n];
        this.k = k;
        int maxWeight = 0;

        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (final int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];

            if (online[u] && online[v]) {
                final int wt = edge[2];
                graph[u].add(new Edge(v, wt));
                ++inDegree[v];
                maxWeight = Math.max(maxWeight, wt);
            }
        }

        if (!computeTopologicalOrder()) {
            return -1;
        }

        int left = 0;
        int right = maxWeight;
        int answer = -1;

        while (left <= right) {
            final int mid = (left + ((right - left) >> 1));

            if (check(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean computeTopologicalOrder() {
        final Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int idx = 0;
        topologicalOrder = new int[n];

        while (!queue.isEmpty()) {
            final int node = queue.poll();

            topologicalOrder[idx] = node;
            ++idx;

            for (final Edge edge : graph[node]) {
                --inDegree[edge.to];

                if (inDegree[edge.to] == 0) {
                    queue.offer(edge.to);
                }
            }
        }

        return idx > 0;
    }

    private boolean check(final int val) {
        final long[] distance = new long[n];

        Arrays.fill(distance, k + 1);
        distance[0] = 0;

        for (final int node : topologicalOrder) {
            if (distance[node] == k + 1) {
                continue;
            }

            for (final Edge edge : graph[node]) {
                if (edge.wt >= val) {
                    distance[edge.to] = Math.min(distance[edge.to], distance[node] + edge.wt);
                }
            }
        }

        return distance[n - 1] <= k;
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

        final boolean[] online = new boolean[n];

        for (int i = 0; i < n; ++i) {
            online[i] = scanner.nextBoolean();
        }

        final long k = scanner.nextLong();

        System.out.println(new Solution().findMaxPathScore(edges, online, k));

        scanner.close();
    }
}
