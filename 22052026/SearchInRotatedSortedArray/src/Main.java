import java.util.Scanner;

class Solution {
    public int search(final int[] nums, final int target) {
        final int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            final int mid = (left + ((right - left) >> 1));

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[0]) {
                if (target >= nums[0] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target <= nums[n - 1] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
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

        final int target = scanner.nextInt();

        System.out.println(new Solution().search(nums, target));

        scanner.close();
    }
}
