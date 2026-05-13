import java.util.Scanner;

class Solution {
    public int minMoves(final int[] nums, final int limit) {
        final int n = nums.length;
        final int[] difference = new int[2 * limit + 2];
        int answer = n;

        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            final int currentSum = nums[left] + nums[right];
            final int minSumWith1Change = Math.min(nums[left], nums[right]) + 1;
            final int maxSumWith1Change = Math.max(nums[left], nums[right]) + limit;
            final int minSumWith2Change = 2;
            final int maxSumWith2Change = 2 * limit;

            difference[minSumWith2Change] += 2;
            difference[maxSumWith2Change + 1] -= 2;

            difference[minSumWith1Change] -= 1;
            difference[maxSumWith1Change + 1] += 1;

            difference[currentSum] -= 1;
            difference[currentSum + 1] += 1;
        }

        for (int i = 2; i <= 2 * limit; ++i) {
            difference[i] += difference[i - 1];
            answer = Math.min(answer, difference[i]);
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

        final int limit = scanner.nextInt();

        System.out.println(new Solution().minMoves(nums, limit));

        scanner.close();
    }
}
