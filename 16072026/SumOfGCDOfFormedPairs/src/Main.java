import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public long gcdSum(final int[] nums) {
        final int n = nums.length;
        final int[] prefixGcd = new int[n];
        int runningMax = 0;

        for (int i = 0; i < n; ++i) {
            runningMax = Math.max(runningMax, nums[i]);
            prefixGcd[i] = calculateGcd(nums[i], runningMax);
        }

        Arrays.sort(prefixGcd);

        int left = 0;
        int right = n - 1;
        long answer = 0;

        while (left < right) {
            answer += calculateGcd(prefixGcd[left], prefixGcd[right]);
            ++left;
            --right;
        }

        return answer;
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

        System.out.println(new Solution().gcdSum(nums));

        scanner.close();
    }
}
