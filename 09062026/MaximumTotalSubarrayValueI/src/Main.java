import java.util.Scanner;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;

        for (final int num : nums) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
        }

        return ((long) maxValue - minValue) * k;
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

        final int k = scanner.nextInt();

        System.out.println(new Solution().maxTotalValue(nums, k));

        scanner.close();
    }
}
