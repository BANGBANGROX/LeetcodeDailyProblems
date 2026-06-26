import java.util.Scanner;

class Solution {
    public long countMajoritySubarrays(final int[] nums, final int target) {
        final int n = nums.length;
        final int[] count = new int[2 * n + 1];
        long answer = 0;
        long preSum = 0;
        int cnt = n;

        for (final int num : nums) {
            if (num == target) {
                preSum += count[cnt];
                ++cnt;
            } else {
                --cnt;
                preSum -= count[cnt];
            }

            if (cnt > n) {
                ++answer;
            }

            ++count[cnt];
            answer += preSum;
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

        final int target = scanner.nextInt();

        System.out.println(new Solution().countMajoritySubarrays(nums, target));

        scanner.close();
    }
}
