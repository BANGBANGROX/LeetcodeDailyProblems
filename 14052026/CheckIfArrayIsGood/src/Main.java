import java.util.Scanner;

class Solution {
    public boolean isGood(final int[] nums) {
        final int n = nums.length;
        final int[] count = new int[n];
        final int maxNumber = n - 1;

        for (final int num : nums) {
            if (num < 1 || num >= n) {
                return false;
            }
            ++count[num];

            if (num == maxNumber) {
                if (count[num] > 2) {
                    return false;
                }
            } else if (count[num] > 1) {
                return false;
            }
        }

        return count[maxNumber] == 2;
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

        System.out.println(new Solution().isGood(nums));

        scanner.close();
    }
}
