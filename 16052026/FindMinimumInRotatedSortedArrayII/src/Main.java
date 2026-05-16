import java.util.Scanner;

class Solution {
    public int findMin(final int[] nums) {
        final int n = nums.length;
        int left = 0;
        int right = n - 1;

        if (nums[left] < nums[right]) {
            return nums[left];
        }

        while (left < right) {
            final int mid = (left + ((right - left) >> 1));

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                --right;
            }
        }

        return nums[left];
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

        System.out.println(new Solution().findMin(nums));

        scanner.close();
    }
}
