import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

class Solution {
    private int[][][] dp;
    private int[] number;
    private static final int MAX_LENGTH = 5;
    private static final int MAX_DIGIT = 9;
    private static final int[] VALID_DIGITS = {0, 1, 2, 5, 6, 8, 9};
    private static final Set<Integer> CHANGING_MIRROR_DIGITS = Set.of(2, 5, 6, 9);

    public int rotatedDigits(final int n) {
        dp = new int[MAX_LENGTH][2][2];
        number = new int[MAX_LENGTH];
        final String numString = String.valueOf(n);
        final int leadingZeroes = MAX_LENGTH - numString.length();
        int ptr = 0;

        for (; ptr < leadingZeroes; ++ptr) {
            number[ptr] = 0;
        }

        for (final char ch : numString.toCharArray()) {
            number[ptr] = ch - '0';
            ++ptr;
        }

        for (final int[][] x : dp) {
            for (final int[] y : x) {
                Arrays.fill(y, -1);
            }
        }

        return dfs(0, true, false);
    }

    private int dfs(final int pos, boolean isLimit, boolean isChanged) {
        if (pos == MAX_LENGTH) {
            return isChanged ? 1 : 0;
        }

        final int intIsLimit = isLimit ? 1 : 0;
        final int intIsChanged = isChanged ? 1 : 0;

        if (dp[pos][intIsLimit][intIsChanged] != -1) {
            return dp[pos][intIsLimit][intIsChanged];
        }

        int count = 0;
        final int maxDigitAvb = isLimit ? number[pos] : MAX_DIGIT;

        for (final int digit : VALID_DIGITS) {
            if (digit > maxDigitAvb) {
                break;
            }

            final boolean nextIsLimit = isLimit && (digit == maxDigitAvb);
            final boolean nextIsChanged = isChanged || CHANGING_MIRROR_DIGITS.contains(digit);

            count += dfs(pos + 1, nextIsLimit, nextIsChanged);
        }

        return dp[pos][intIsLimit][intIsChanged] = count;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.println(new Solution().rotatedDigits(scanner.nextInt()));

        scanner.close();
    }
}
