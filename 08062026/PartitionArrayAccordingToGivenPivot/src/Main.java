import java.util.Scanner;

class Solution {
    public int[] pivotArray(final int[] nums, final int pivot) {
        final int n = nums.length;
        final int[] answer = new int[n];
        int left = 0;
        int right = n - 1;

        for (int i = 0, j = n - 1; i < n && j >= 0; ++i, --j) {
            if (nums[i] < pivot) {
                answer[left] = nums[i];
                ++left;
            }

            if (nums[j] > pivot) {
                answer[right] = nums[j];
                --right;
            }
        }

        while (left <= right) {
            answer[left] = pivot;
            ++left;
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

        final int pivot = scanner.nextInt();

        final int[] answer = new Solution().pivotArray(nums, pivot);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
