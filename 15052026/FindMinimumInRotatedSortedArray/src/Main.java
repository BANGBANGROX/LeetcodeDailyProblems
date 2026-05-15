import java.util.Scanner;

class Solution {
    public int findMin(final int[] nums) {
        final int n = nums.length;
        final int firstElement = nums[0];
        final int lastElement = nums[n - 1];
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            final int mid = (left + ((right - left) >> 1));

            if (mid + 1 < n) {
                if (nums[mid] > nums[mid + 1]) {
                    return nums[mid + 1];
                }
            }

            if (mid > 0 && mid + 1 < n && nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
                return nums[mid];
            }

            if (nums[mid] > lastElement) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return firstElement;
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
