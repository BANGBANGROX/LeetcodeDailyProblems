import java.util.Scanner;

class Solution {
    public int findGCD(final int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (final int num : nums) {
            minValue = Math.min(minValue, num);
            maxValue = Math.max(maxValue, num);
        }

        return calculateGcd(minValue, maxValue);
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

        System.out.println(new Solution().findGCD(nums));

        scanner.close();
    }
}
