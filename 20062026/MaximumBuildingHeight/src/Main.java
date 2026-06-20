import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Solution {
    public int maxBuilding(final int n, final int[][] restrictions) {
        final int m = restrictions.length;

        if (m == 0) {
            return n - 1;
        }

        final int totalLen = m + 1;
        final int[][] finalRestrictions = new int[totalLen][2];
        int answer = 0;
        finalRestrictions[0] = new int[]{1, 0};

        System.arraycopy(restrictions, 0, finalRestrictions, 1, m);
        Arrays.sort(finalRestrictions, Comparator.comparingInt(a -> a[0]));

        for (int i = 1; i < totalLen; ++i) {
            final int maxReachFromLeft = finalRestrictions[i - 1][1] + (finalRestrictions[i][0] - finalRestrictions[i - 1][0]);
            finalRestrictions[i][1] = Math.min(finalRestrictions[i][1], maxReachFromLeft);
        }

        for (int i = totalLen - 2; i >= 0; --i) {
            final int maxReachFromRight = finalRestrictions[i + 1][1] + (finalRestrictions[i + 1][0] - finalRestrictions[i][0]);
            finalRestrictions[i][1] = Math.min(finalRestrictions[i][1], maxReachFromRight);
        }

        for (int i = 0; i < totalLen - 1; ++i) {
            final int idx1 = finalRestrictions[i][0];
            final int idx2 = finalRestrictions[i + 1][0];
            final int height1 = finalRestrictions[i][1];
            final int height2 = finalRestrictions[i + 1][1];

            answer = Math.max(answer, ((idx2 - idx1) + height1 + height2) / 2);
        }

        if (finalRestrictions[totalLen - 1][0] < n) {
            answer = Math.max(answer, finalRestrictions[totalLen - 1][1] + n - finalRestrictions[totalLen - 1][0]);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[][] restrictions = new int[m][2];

        for (int i = 0; i < m; ++i) {
            restrictions[i][0] = scanner.nextInt();
            restrictions[i][1] = scanner.nextInt();
        }

        System.out.println(new Solution().maxBuilding(n, restrictions));

        scanner.close();
    }
}
