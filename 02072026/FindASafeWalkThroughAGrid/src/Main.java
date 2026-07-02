import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

class Solution {
    public boolean findSafeWalk(final List<List<Integer>> grid, final int health) {
        final int m = grid.size();
        final int n = grid.getFirst().size();
        final boolean[][] visited = new boolean[m][n];
        final Deque<int[]> deque = new ArrayDeque<>();
        final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        visited[0][0] = true;
        deque.add(new int[]{0, 0, health - grid.getFirst().getFirst()});

        while (!deque.isEmpty()) {
            final int[] cell = deque.poll();
            final int r = cell[0];
            final int c = cell[1];
            final int currentHealth = cell[2];

            for (final int[] direction : directions) {
                final int newR = r + direction[0];
                final int newC = c + direction[1];

                if (newR >= 0 && newR < m && newC >= 0 && newC < n) {
                    final int newHealth = currentHealth - grid.get(newR).get(newC);

                    if (newHealth > 0 && !visited[newR][newC]) {
                        if (newR == m - 1 && newC == n - 1) {
                            return true;
                        }

                        visited[newR][newC] = true;

                        if (newHealth < currentHealth) {
                            deque.addLast(new int[]{newR, newC, newHealth});
                        } else {
                            deque.addFirst(new int[]{newR, newC, newHealth});
                        }
                    }
                }
            }
        }

        return false;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final int n = scanner.nextInt();
        final List<List<Integer>> grid = new ArrayList<>();

        for (int i = 0; i < m; ++i) {
            grid.add(new ArrayList<>());

            for (int j = 0; j < n; ++j) {
                grid.get(i).add(scanner.nextInt());
            }
        }

        final int health = scanner.nextInt();

        System.out.println(new Solution().findSafeWalk(grid, health));

        scanner.close();
    }
}
