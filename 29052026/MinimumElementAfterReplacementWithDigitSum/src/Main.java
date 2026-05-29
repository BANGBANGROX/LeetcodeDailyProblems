import java.util.Scanner;

class Solution {
    public int minElement(final int[] nums) {
        int answer = Integer.MAX_VALUE;

        for (final int num : nums) {
            answer = Math.min(answer, getDigitSum(num));
        }

        return answer;
    }

    private int getDigitSum(final int num) {
        int current = num;
        int sum = 0;

        while (current > 0) {
            sum += (current % 10);
            current /= 10;
        }

        return sum;
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

        System.out.println(new Solution().minElement(nums));

        scanner.close();
    }
}
