import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    public int[] separateDigits(final int[] nums) {
        final List<Integer> answer = new ArrayList<>();

        for (final int num : nums) {
            answer.addAll(getDigitsAsList(num));
        }

        return answer.stream().mapToInt(k -> k).toArray();
    }

    private List<Integer> getDigitsAsList(final int num) {
        if (num == 0) {
            return List.of(0);
        }

        final List<Integer> digits = new ArrayList<>();
        int current = num;

        while (current > 0) {
            digits.add(current % 10);
            current /= 10;
        }

        return digits.reversed();
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

        final int[] answer = new Solution().separateDigits(nums);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
