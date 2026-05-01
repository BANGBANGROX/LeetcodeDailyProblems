import java.util.Scanner;

class Solution {
    public int maxRotateFunction(final int[] nums) {
        final int n = nums.length;
        int totalSum = 0;
        int lastFunctionValue = 0;
        int last = nums[n - 1];

        for (int i = 0; i < n; ++i) {
            totalSum += nums[i];
            lastFunctionValue += i * nums[i];
        }

        int answer = lastFunctionValue;

        for (int i = 1; i < n; ++i) {
            final int nextFunctionValue = lastFunctionValue + totalSum - n * last;
            answer = Math.max(answer, nextFunctionValue);
            last = nums[n - i - 1];
            lastFunctionValue = nextFunctionValue;
        }

        return answer;
    }
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = scanner.nextInt();
        }

        System.out.println(new Solution().maxRotateFunction(nums));
    }
}