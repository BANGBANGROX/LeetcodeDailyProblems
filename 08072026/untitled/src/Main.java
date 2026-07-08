import java.util.Scanner;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(final String s, final int[][] queries) {
        final int n = s.length();
        final int q = queries.length;
        final int[] prefixSum = new int[n];
        final int[] number = new int[n];
        final int[] powerOf10 = new int[n];
        final int[] nonZeroDigits = new int[n];
        final int[] answer = new int[q];

        powerOf10[0] = 1;
        prefixSum[0] = s.charAt(0) - '0';
        number[0] = s.charAt(0) - '0';
        nonZeroDigits[0] = (s.charAt(0) == '0' ? 0 : 1);

        for (int i = 1; i < n; ++i) {
            powerOf10[i] = (int) (((long) powerOf10[i - 1] * 10) % MOD);
            prefixSum[i] = prefixSum[i - 1] + (s.charAt(i) - '0');

            if (s.charAt(i) == '0') {
                number[i] = number[i - 1];
                nonZeroDigits[i] = nonZeroDigits[i - 1];
            } else {
                number[i] = (int) (((long) number[i - 1] * 10 + s.charAt(i) - '0') % MOD);
                nonZeroDigits[i] = nonZeroDigits[i - 1] + 1;
            }
        }

        for (int i = 0; i < q; ++i) {
            final int left = queries[i][0];
            final int right = queries[i][1];
            final int sum;
            final int num;

            if (left == 0) {
                sum = prefixSum[right];
                num = number[right];
            } else {
                final int validDigits = nonZeroDigits[right] - nonZeroDigits[left - 1];
                num = (int) ((number[right] - ((long) number[left - 1] * powerOf10[validDigits]) % MOD + MOD) % MOD);
                sum = prefixSum[right] - prefixSum[left - 1];
            }

            answer[i] = (int) (((long) sum * num) % MOD);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final int q = scanner.nextInt();
        final int[][] queries = new int[q][2];

        for (int i = 0; i < q; ++i) {
            queries[i][0] = scanner.nextInt();
            queries[i][1] = scanner.nextInt();
        }

        final int[] answer = new Solution().sumAndMultiply(s, queries);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
