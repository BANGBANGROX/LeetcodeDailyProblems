import java.util.Scanner;

class Solution {
    public int[] gcdValues(final int[] nums, final long[] queries) {
        final int q = queries.length;
        final int[] answer = new int[q];
        int maxValue = 0;

        for (final int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        final long[] count = new long[maxValue + 1];

        for (final int num : nums) {
            ++count[num];
        }

        for (int i = 1; i <= maxValue; ++i) {
            for (int j = 2 * i; j <= maxValue; j += i) {
                count[i] += count[j];
            }
        }

        for (int i = 1; i <= maxValue; ++i) {
            count[i] = count[i] * (count[i] - 1) / 2;
        }

        for (int i = maxValue; i > 0; --i) {
            for (int j = 2 * i; j <= maxValue; j += i) {
                count[i] -= count[j];
            }
        }

        for (int i = 1; i <= maxValue; ++i) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < q; ++i) {
            final long idx = queries[i] + 1;
            int left = 1;
            int right = maxValue;

            while (left < right) {
                final int mid = (left + ((right - left) >> 1));

                if (count[mid] >= idx) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer[i] = left;
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

        final int q = scanner.nextInt();
        final long[] queries = new long[q];

        for (int i = 0; i < q; ++i) {
            queries[i] = scanner.nextLong();
        }

        final int[] answer = new Solution().gcdValues(nums, queries);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
