import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private int[][] grid;
    private int m;
    private int n;
    private int[][][] dp;

    public int maxPathScore(final int[][] grid, final int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n][k + 1];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], -2);
            }
        }

        return maxPathScoreHandler(0, 0, k);
    }

    private int maxPathScoreHandler(final int i, final int j, final int remainingCost) {
        if (i == m - 1 && j == n - 1) {
            return grid[m - 1][n - 1];
        }

        if (dp[i][j][remainingCost] != -2) {
            return dp[i][j][remainingCost];
        }

        final int scoreGoingForward = remainingCost - getCost(grid[i][j]);
        int rightScore = -1;
        int downScore = -1;

        if (i + 1 < m && scoreGoingForward >= getCost(grid[i + 1][j])) {
            downScore = maxPathScoreHandler(i + 1, j, scoreGoingForward);
        }

        if (j + 1 < n && scoreGoingForward >= getCost(grid[i][j + 1])) {
            rightScore = maxPathScoreHandler(i, j + 1, scoreGoingForward);
        }

        final int finalScore = Math.max(downScore, rightScore);

        return dp[i][j][remainingCost] = finalScore > 0 ? finalScore + grid[i][j] : -1;
    }

    private int getCost(final int score) {
        return switch (score) {
            case 0 -> 0;
            case 1, 2 -> 1;
            default -> -1;
        };
    }
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

        System.out.println(new Solution().maxPathScore(grid, k));
    }
}