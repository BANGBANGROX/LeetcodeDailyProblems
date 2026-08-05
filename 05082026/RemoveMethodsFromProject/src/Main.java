import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    private List<List<Integer>> graph;
    private boolean[] buggy;
    private boolean[] visited;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        graph = new ArrayList<>();
        buggy = new boolean[n];
        visited = new boolean[n];
        final List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (final int[] edge : invocations) {
            graph.get(edge[0]).add(edge[1]);
        }

        populateBuggy(k);

        boolean canRemoveBuggy = true;

        for (int i = 0; i < n; ++i) {
            if (!buggy[i] && !visited[i] && isTouchingBuggy(i)) {
                canRemoveBuggy = false;
                break;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (canRemoveBuggy) {
                if (!buggy[i]) {
                    answer.add(i);
                }
            } else {
                answer.add(i);
            }
        }

        return answer;
    }

    private void populateBuggy(final int node) {
        buggy[node] = true;

        for (final int child : graph.get(node)) {
            if (!buggy[child]) {
                populateBuggy(child);
            }
        }
    }

    private boolean isTouchingBuggy(final int node) {
        visited[node] = true;

        for (final int child : graph.get(node)) {
            if (buggy[child]) {
                return true;
            }

            if (!visited[child] && isTouchingBuggy(child)) {
                return true;
            }
        }

        return false;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[][] invocations = new int[m][2];

        for (int i = 0; i < m; ++i) {
            invocations[i][0] = scanner.nextInt();
            invocations[i][1] = scanner.nextInt();
        }

        System.out.println(new Solution().remainingMethods(n, k, invocations));

        scanner.close();
    }
}
