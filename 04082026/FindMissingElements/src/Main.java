import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class Solution {
    public List<Integer> findMissingElements(final int[] nums) {
        final List<Integer> answer = new ArrayList<>();
        final int n = nums.length;
        final Set<Integer> visited = new HashSet<>();
        int minValue = nums[0];
        int maxValue = nums[0];

        visited.add(nums[0]);

        for (int i = 1; i < n; ++i) {
            minValue = Math.min(minValue, nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
            visited.add(nums[i]);
        }

        for (int i = minValue + 1; i < maxValue; ++i) {
            if (!visited.contains(i)) {
                answer.add(i);
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

        System.out.println(new Solution().findMissingElements(nums));

        scanner.close();
    }
}
