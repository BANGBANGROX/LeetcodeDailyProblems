import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    public List<List<Integer>> shiftGrid(final int[][] grid, final int k) {
        final int m = grid.length;
        final int n = grid[0].length;
        final int rotations = k % (m * n);
        final List<List<Integer>> answer = new ArrayList<>();

        for (final int[] row : grid) {
            answer.add(new ArrayList<>());

            for (final int val : row) {
                answer.getLast().add(val);
            }
        }

        for (int i = 0; i < rotations; ++i) {
            rotate(answer);
        }

        return answer;
    }

    private void rotate(final List<List<Integer>> answer) {
        final int last = answer.getLast().getLast();
        final int m = answer.size();
        final int n = answer.getFirst().size();

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j > 0; --j) {
                answer.get(i).set(j, answer.get(i).get(j - 1));
            }

            if (i > 0) {
                answer.get(i).set(0, answer.get(i - 1).getLast());
            } else {
                answer.get(i).set(0, last);
            }
        }
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final int n = scanner.nextInt();
        final int[][] grid = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = scanner.nextInt();
            }
        }

        final int k = scanner.nextInt();

        System.out.println(new Solution().shiftGrid(grid, k));

        scanner.close();
    }
}
