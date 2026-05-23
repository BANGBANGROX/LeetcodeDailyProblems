import java.util.Scanner;

class Solution {
    public boolean check(final int[] nums) {
        final int n = nums.length;
        int breakingPoints = 0;

        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                ++breakingPoints;

                if (breakingPoints > 1) {
                    return false;
                }
            }
        }

        return breakingPoints == 0 || nums[n - 1] <= nums[0];
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

        System.out.println(new Solution().check(nums));

        scanner.close();
    }
}
