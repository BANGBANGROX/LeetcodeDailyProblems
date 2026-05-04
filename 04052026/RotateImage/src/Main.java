import java.util.Scanner;

class Solution {
    public void rotate(final int[][] matrix) {
        final int n = matrix.length;

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                final int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int[] ints : matrix) {
            reverse(ints);
        }
    }

    private void reverse(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            final int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            ++left;
            --right;
        }
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] matrix = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        new Solution().rotate(matrix);

        for (final int[] nums : matrix) {
            for (final int x : nums) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

        System.out.println();

        scanner.close();
    }
}
