import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static final int MAX_N = 205;
    private static final int[][] GCD = new int[MAX_N][MAX_N];
    private static final int MOD = 1_000_000_007;

    static {
        for (int i = 0; i < MAX_N; ++i) {
            for (int j = i; j < MAX_N; ++j) {
                GCD[i][j] = calculateGcd(i, j);
                GCD[j][i] = GCD[i][j];
            }
        }
    }

    private int[] nums;
    private int n;
    private int[][][] dp;

    public int subsequencePairCount(final int[] nums) {
        this.nums = nums;
        n = nums.length;
        int maxValue = 0;

        for (final int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        dp = new int[n][maxValue + 1][maxValue + 1];

        for (int[][] x : dp) {
            for (final int[] y : x) {
                Arrays.fill(y, -1);
            }
        }

        return dfs(0, 0, 0);
    }

    private int dfs(final int idx, final int gcd1, final int gcd2) {
        if (idx == n) {
            return (gcd1 == gcd2 && gcd1 > 0 ? 1 : 0);
        }

        if (dp[idx][gcd1][gcd2] != -1) {
            return dp[idx][gcd1][gcd2];
        }

        long answer = 0;

        answer = (answer + dfs(idx + 1, gcd1, gcd2)) % MOD;
        answer = (answer + dfs(idx + 1, GCD[gcd1][nums[idx]], gcd2)) % MOD;
        answer = (answer + dfs(idx + 1, gcd1, GCD[gcd2][nums[idx]])) % MOD;

        return dp[idx][gcd1][gcd2] = (int) answer;
    }

    private static int calculateGcd(final int a, final int b) {
        if (b == 0) {
            return a;
        }

        return calculateGcd(b, a % b);
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

        System.out.println(new Solution().subsequencePairCount(nums));

        scanner.close();
    }
}
