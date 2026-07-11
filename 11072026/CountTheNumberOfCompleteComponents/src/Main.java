import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    private List<List<Integer>> graph;
    private boolean[] visited;
    private int nodesInComponent;
    private int edgesInComponent;

    public int countCompleteComponents(int n, int[][] edges) {
        graph = new ArrayList<>();
        visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (final int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                edgesInComponent = nodesInComponent = 0;
                dfs(i);

                final long expectedEdges = (long) nodesInComponent * (nodesInComponent - 1) / 2;

                if (edgesInComponent == 2 * expectedEdges) {
                    ++answer;
                }
            }
        }

        return answer;
    }

    private void dfs(final int node) {
        visited[node] = true;

        ++nodesInComponent;

        for (final int child : graph.get(node)) {
            if (!visited[child]) {
                dfs(child);
            }

            ++edgesInComponent;
        }
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[][] edges = new int[m][2];

        for (int i = 0; i < m; ++i) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        System.out.println(new Solution().countCompleteComponents(n, edges));

        scanner.close();
    }
}
