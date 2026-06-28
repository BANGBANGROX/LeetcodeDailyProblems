import java.util.Scanner;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(final int[] arr) {
        final int n = arr.length;
        final int[] count = new int[n + 1];

        for (int i = 0; i < n; ++i) {
            if (arr[i] > n) {
                arr[i] = n;
            }
            ++count[arr[i]];
        }

        int ptr = 0;

        for (int i = 1; i <= n; ++i) {
            while (count[i] > 0) {
                arr[ptr] = i;
                ++ptr;
                --count[i];
            }
        }

        if (arr[0] != 1) {
            arr[0] = 1;
        }

        for (int i = 1; i < n; ++i) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }

        return arr[n - 1];
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] arr = new int[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(new Solution().maximumElementAfterDecrementingAndRearranging(arr));

        scanner.close();
    }
}
