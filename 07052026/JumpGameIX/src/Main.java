import java.util.Scanner;

class Solution {
    public int[] maxValue(final int[] nums) {
        final int n = nums.length;
        final int[] answer = new int[n];
        final int[] prefixMax = new int[n];
        final int[] suffixMin = new int[n];

        prefixMax[0] = nums[0];
        suffixMin[n - 1] = nums[n - 1];

        for (int i = 1; i < n; ++i) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            suffixMin[n - i - 1] = Math.min(suffixMin[n - i], nums[n - i - 1]);
        }

        answer[n - 1] = prefixMax[n - 1];

        for (int i = n - 2; i >= 0; --i) {
            if (prefixMax[i] > suffixMin[i + 1]) {
                answer[i] = answer[i + 1];
            } else {
                answer[i] = prefixMax[i];
            }
        }

        return answer;
    }

    private void reverse(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            nums[left] += nums[right];
            nums[right] = nums[left] - nums[right];
            nums[left] = nums[left] - nums[right];

            ++left;
            --right;
        }
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

        final int[] answer = new Solution().maxValue(nums);
        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
