import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    private record Value(int score, int ways) {

    }

    private static final int INF = 1_000_000_000;
    private static final int MOD = 1_000_000_007;
    private Value[][] dp;
    private List<String> board;
    private int m;
    private int n;

    public int[] pathsWithMaxScore(final List<String> board) {
        this.board = board;
        m = board.size();
        n = board.getFirst().length();
        dp = new Value[m][n];
        final Value value = dfs(m - 1, n - 1);

        return new int[]{Math.max(value.score, 0), value.ways};
    }

    private Value dfs(final int i, final int j) {
        if (i == 0 && j == 0) {
            return new Value(0, 1);
        }

        if (i < 0 || j < 0 || i >= m || j >= n) {
            return new Value(-1 * INF, 0);
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (board.get(i).charAt(j) == 'X') {
            return dp[i][j] = new Value(-1 * INF, 0);
        }

        final Value left = dfs(i, j - 1);
        final Value up = dfs(i - 1, j);
        final Value diagonal = dfs(i - 1, j - 1);
        final int maxValue = Math.max(left.score, Math.max(up.score, diagonal.score));
        long totalWays = 0;

        if (maxValue == left.score) {
            totalWays = (totalWays + left.ways) % MOD;
        }

        if (maxValue == up.score) {
            totalWays = (totalWays + up.ways) % MOD;
        }

        if (maxValue == diagonal.score) {
            totalWays = (totalWays + diagonal.ways) % MOD;
        }

        final int totalScore = Character.isDigit(board.get(i).charAt(j)) ? maxValue + board.get(i).charAt(j) - '0' : maxValue;

        return dp[i][j] = new Value(totalScore, (int) totalWays);
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final List<String> board = new ArrayList<>();

        for (int i = 0; i < m; ++i) {
            board.add(scanner.next());
        }

        final int[] answer = new Solution().pathsWithMaxScore(board);
        System.out.println(answer[0] + " " + answer[1]);

        scanner.close();
    }
}
