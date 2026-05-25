import java.util.Scanner;

class Solution {
    public boolean canReach(final String s, final int minJump, final int maxJump) {
        final int n = s.length();

        if (s.charAt(n - 1) != '0') {
            return false;
        }

        final int[] suffixSum = new int[n];

        suffixSum[n - 1] = 1;

        for (int i = n - 2; i >= 0; --i) {
            suffixSum[i] = suffixSum[i + 1];

            if (s.charAt(i) == '0') {
                final int minIndex = i + minJump;
                final int maxIndex = Math.min(n - 1, i + maxJump);

                if (minIndex <= maxIndex) {
                    final int rangeSum = suffixSum[minIndex] - (maxIndex < n - 1 ? suffixSum[maxIndex + 1] : 0 );

                    if (rangeSum > 0) {
                        ++suffixSum[i];

                        if (i == 0) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final int minJump = scanner.nextInt();
        final int maxJump = scanner.nextInt();

        System.out.println(new Solution().canReach(s, minJump, maxJump));

        scanner.close();
    }
}
