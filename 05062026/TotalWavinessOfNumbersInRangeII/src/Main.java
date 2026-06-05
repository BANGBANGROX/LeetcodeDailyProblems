import java.util.Scanner;

class Solution {
    private record State(long count, long totalWaviness) {
    }

    private static final int MAX_LENGTH = 16;
    private static final int GLOBAL_MAX_DIGIT = 9;

    private int[] number;
    private State[][][][][] dp;

    public long totalWaviness(final long num1, final long num2) {
        return totalWavinessHandler(num2) - totalWavinessHandler(num1 - 1);
    }

    private long totalWavinessHandler(final long num) {
        if (num <= 0) {
            return 0;
        }

        number = new int[MAX_LENGTH];
        final String numString = String.valueOf(num);
        int leadingZeroesNeeded = MAX_LENGTH - numString.length();
        int ptr1 = 0;
        int ptr2 = 0;

        while (leadingZeroesNeeded > 0) {
            number[ptr1] = 0;
            ++ptr1;
            --leadingZeroesNeeded;
        }

        while (ptr1 < MAX_LENGTH) {
            number[ptr1] = (numString.charAt(ptr2) - '0');
            ++ptr1;
            ++ptr2;
        }

        dp = new State[MAX_LENGTH][2][2][11][11];

        return dfs(0, true, true, -1, -1).totalWaviness;
    }

    private State dfs(final int pos, final boolean isLimit, final boolean isLeading, final int dig1, final int dig2) {
        if (pos == MAX_LENGTH) {
            return new State(1, 0);
        }

        final int intIsLimit = isLimit ? 1 : 0;
        final int intIsLeading = isLeading ? 1 : 0;

        if (dp[pos][intIsLimit][intIsLeading][dig1 + 1][dig2 + 1] != null) {
            return dp[pos][intIsLimit][intIsLeading][dig1 + 1][dig2 + 1];
        }

        final int maxAvbDigit = isLimit ? number[pos] : GLOBAL_MAX_DIGIT;
        long count = 0;
        long totalWaviness = 0;

        for (int dig3 = 0; dig3 <= maxAvbDigit; ++dig3) {
            final boolean nextIsLimit = isLimit && (dig3 == maxAvbDigit);
            final boolean nextIsLeading = isLeading && (dig3 == 0);
            final State nextState;

            if (nextIsLeading) {
                nextState = dfs(pos + 1, nextIsLimit, true, -1, -1);
            } else {
                nextState = dfs(pos + 1, nextIsLimit, false, dig2, dig3);
            }

            count += nextState.count;
            totalWaviness += nextState.totalWaviness;

            if (dig1 != -1 && dig2 != -1 && (dig2 > Math.max(dig1, dig3) || dig2 < Math.min(dig1, dig3))) {
                totalWaviness += nextState.count;
            }
        }

        return dp[pos][intIsLimit][intIsLeading][dig1 + 1][dig2 + 1] = new State(count, totalWaviness);
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long num1 = scanner.nextLong();
        final long num2 = scanner.nextLong();

        System.out.println(new Solution().totalWaviness(num1, num2));

        scanner.close();
    }
}
