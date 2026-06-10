import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
    private static class SparseTable {
        private final int[][] max;
        private final int[][] min;
        private final int[] logValue;

        SparseTable(final int[] nums) {
            final int n = nums.length;
            final int maxLog = 32 - Integer.numberOfLeadingZeros(n) + 1;
            logValue = new int[n + 1];
            max = new int[n][maxLog];
            min = new int[n][maxLog];

            for (int i = 2; i <= n; ++i) {
                logValue[i] = logValue[i >> 1] + 1;
            }

            for (int i = 0; i < n; ++i) {
                max[i][0] = nums[i];
                min[i][0] = nums[i];
            }

            for (int j = 1; j < maxLog; ++j) {
                for (int i = 0; i <= (n - (1 << j)); ++i) {
                    max[i][j] = Math.max(max[i][j - 1], max[i + (1 << (j - 1))][j - 1]);
                    min[i][j] = Math.min(min[i][j - 1], min[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int query(final int left, final int right) {
            final int log = logValue[right - left + 1];
            final int maxValue = Math.max(max[left][log], max[right - (1 << log) + 1][log]);
            final int minValue = Math.min(min[left][log], min[right - (1 << log) + 1][log]);

            return maxValue - minValue;
        }
    }

    private static class State {
        private final int value;
        private final int left;
        private final int right;

        State(final int left, final int right, final int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    public long maxTotalValue(final int[] nums, final int k) {
        final SparseTable sparseTable = new SparseTable(nums);
        final PriorityQueue<State> priorityQueue = new PriorityQueue<>((a, b) -> b.value - a.value);
        final int n = nums.length;
        long answer = 0;

        for (int i = 0; i < n; ++i) {
            priorityQueue.offer(new State(i, n - 1, sparseTable.query(i, n - 1)));
        }

        for (int i = 0; i < k; ++i) {
            final State state = priorityQueue.poll();

            answer += state.value;

            if (state.right - state.left > 0) {
                priorityQueue.offer(new State(state.left, state.right - 1,
                        sparseTable.query(state.left, state.right - 1)));
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

        final int k = scanner.nextInt();

        System.out.println(new Solution().maxTotalValue(nums, k));

        scanner.close();
    }
}
