import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        final int q = queries.length;
        final int log = (int) (Math.log(n) / Math.log(2)) + 1;
        final int[] answer = new int[q];
        final int[][] sortedNums = new int[n][2];
        final int[] path = new int[n];
        final int[][] dp = new int[n][log + 1];

        for (int i = 0; i < n; ++i) {
            sortedNums[i][0] = i;
            sortedNums[i][1] = nums[i];
        }

        Arrays.sort(sortedNums, Comparator.comparingInt(a -> a[1]));

        for (int i = 0; i < n; ++i) {
            path[sortedNums[i][0]] = i;
        }

        int right = 0;

        for (int i = 0; i < n; ++i) {
            while (right + 1 < n && sortedNums[right + 1][1] - sortedNums[i][1] <= maxDiff) {
                ++right;
            }
            dp[i][0] = Math.max(right, i);
        }

        for (int i = 1; i <= log; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
            }
        }

        for (int i = 0; i < q; ++i) {
            final int u = queries[i][0];
            final int v = queries[i][1];

            if (u == v) {
                answer[i] = 0;
                continue;
            }

            int sortedIndexU = path[u];
            int sortedIndexV = path[v];
            int steps = 0;

            if (sortedIndexU > sortedIndexV) {
                final int temp = sortedIndexU;
                sortedIndexU = sortedIndexV;
                sortedIndexV = temp;
            }

            for (int jump = log; jump >= 0; --jump) {
                if (dp[sortedIndexU][jump] < sortedIndexV) {
                    sortedIndexU = dp[sortedIndexU][jump];
                    steps += (1 << jump);
                }
            }

            if (dp[sortedIndexU][0] >= sortedIndexV) {
                answer[i] = steps + 1;
            } else {
                answer[i] = -1;
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

        final int[] answer = new Solution().pathExistenceQueries(n, nums, maxDiff, queries);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
