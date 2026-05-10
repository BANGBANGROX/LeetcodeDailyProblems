import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int maximumJumps(final int[] nums, final int target) {
        final int n = nums.length;
        final int[] dp = new int[n];

        Arrays.fill(dp, -1);

        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (Math.abs(nums[i] - nums[j]) <= target && dp[j] != -1) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[0];
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

        final int target = scanner.nextInt();

        System.out.println(new Solution().maximumJumps(nums, target));

        scanner.close();
    }
}
