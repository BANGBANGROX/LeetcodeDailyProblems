import java.util.Scanner;

class Solution {
    public int[] leftRightDifference(final int[] nums) {
        final int n = nums.length;
        final int[] suffixSum = new int[n];
        final int[] answer = new int[n];
        int prefixSum = 0;

        suffixSum[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; --i) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }

        for (int i = 0; i < n; ++i) {
            final int leftSum = prefixSum;
            final int rightSum = (i + 1 < n ? suffixSum[i + 1] : 0);

            answer[i] = Math.abs(leftSum - rightSum);

            prefixSum += nums[i];
        }

        return answer;
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

        final int[] answer = new Solution().leftRightDifference(nums);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
