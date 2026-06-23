import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static final int MOD = 1_000_000_007;
    private int n;
    private int maxValueAllowed;
    private int minValueAllowed;
    private int[][][] dp;

    public int zigZagArrays(final int n, final int l, final int r) {
        this.n = n;
        maxValueAllowed = r;
        minValueAllowed = l;
        final int range = maxValueAllowed - minValueAllowed + 1;
        dp = new int[n][range][2];
        long answer = 0;

        for (final int[][] x : dp) {
            for (final int[] y : x) {
                Arrays.fill(y, -1);
            }
        }

        for (int val = minValueAllowed; val <= maxValueAllowed; ++val) {
            answer = (answer + zigZagArraysHandler(1, val, true)) % MOD;
        }

        return (int) ((answer * 2) % MOD);
    }

    private int zigZagArraysHandler(final int idx, final int lastValue, final boolean isUp) {
        if (lastValue < minValueAllowed || lastValue > maxValueAllowed) {
            return 0;
        }

        if (idx == n) {
            return 1;
        }

        final int intIsUp = (isUp ? 1 : 0);
        final int normalizedValue = lastValue - minValueAllowed;

        if (dp[idx][normalizedValue][intIsUp] != -1) {
            return dp[idx][normalizedValue][intIsUp];
        }

        long result = 0;

        final int take;
        final int skip;

        if (isUp) {
            take = zigZagArraysHandler(idx + 1, lastValue + 1, false);
            skip = zigZagArraysHandler(idx, lastValue + 1, true);
        } else {
            take = zigZagArraysHandler(idx + 1, lastValue - 1, true);
            skip = zigZagArraysHandler(idx, lastValue - 1, false);
        }

        result = (result + take + skip) % MOD;

        return dp[idx][normalizedValue][intIsUp] = (int) result;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int l = scanner.nextInt();
        final int r = scanner.nextInt();

        System.out.println(new Solution().zigZagArrays(n, l, r));

        scanner.close();
    }
}
