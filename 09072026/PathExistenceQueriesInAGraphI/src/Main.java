import java.util.Scanner;

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        final int[] prefixSum = new int[n];
        final int q = queries.length;
        final boolean[] answer = new boolean[q];

        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                prefixSum[i] = prefixSum[i - 1] + 1;
            } else {
                prefixSum[i] = prefixSum[i - 1];
            }
        }

        for (int i = 0; i < q; ++i) {
            final int r = Math.max(queries[i][0], queries[i][1]);
            final int l = Math.min(queries[i][0], queries[i][1]);

            if (l == r) {
                answer[i] = true;
            } else {
                final int sum = (prefixSum[r] - prefixSum[l]);
                answer[i] = sum == (r - l);
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] nums = new int[n];

        for (int i = 0; i < n; ++i) {
            nums[i] = scanner.nextInt();
        }

        final int maxDiff = scanner.nextInt();
        final int q = scanner.nextInt();
        final int[][] queries = new int[q][2];

        for (int i = 0; i < q; ++i) {
            queries[i][0] = scanner.nextInt();
            queries[i][1] = scanner.nextInt();
        }

        final boolean[] answer = new Solution().pathExistenceQueries(n, nums, maxDiff, queries);

        for (final boolean x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
