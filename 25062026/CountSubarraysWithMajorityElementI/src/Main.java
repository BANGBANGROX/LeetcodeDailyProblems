import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public int countMajoritySubarrays(final int[] nums, final int target) {
        int answer = 0;
        final int n = nums.length;

        for (int i = 0; i < n; ++i) {
            final Map<Integer, Integer> count = new HashMap<>();

            for (int j = i; j < n; ++j) {
                count.put(nums[j], count.getOrDefault(nums[j], 0) + 1);

                if (count.getOrDefault(target, 0) > (j - i + 1) / 2) {
                    ++answer;
                }
            }
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
