import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private List<List<Integer>> grid;
    private int[][] distance;
    private int n;

    public int maximumSafenessFactor(final List<List<Integer>> grid) {
        if (grid.getFirst().getFirst() == 1 || grid.getLast().getLast() == 1) {
            return 0;
        }

        this.grid = grid;
        n = grid.size();

        computeDistances();

        int left = 0;
        int right = Math.min(distance[0][0], distance[n - 1][n - 1]);
        int answer = -1;

        while (left <= right) {
            final int mid = (left + ((right - left) >> 1));

            if (check(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private void computeDistances() {
        distance = new int[n][n];
        final Queue<int[]> queue = new ArrayDeque<>();

        for (final int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid.get(i).get(j) == 1) {
                    distance[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            final int[] cell = queue.poll();
            final int x = cell[0];
            final int y = cell[1];

            for (final int[] direction : DIRECTIONS) {
                final int newX = x + direction[0];
                final int newY = y + direction[1];

                if (newX >= 0 && newX < n && newY >= 0 && newY < n && distance[newX][newY] > distance[x][y] + 1) {
                    distance[newX][newY] = distance[x][y] + 1;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }

    private boolean check(final int val) {
        final Queue<int[]> queue = new ArrayDeque<>();
        final boolean[][] visited = new boolean[n][n];

        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            final int[] cell = queue.poll();
            final int x = cell[0];
            final int y = cell[1];

            for (final int[] direction : DIRECTIONS) {
                final int newX = x + direction[0];
                final int newY = y + direction[1];

                if (newX >= 0 && newX < n && newY >= 0 && newY < n && distance[newX][newY] >= val && !visited[newX][newY]) {
                    if (newX == n - 1 && newY == n - 1) {
                        return true;
                    }

                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }

        return false;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<List<Integer>> grid = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            grid.add(new ArrayList<>());
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid.get(i).add(scanner.nextInt());
            }
        }

        System.out.println(new Solution().maximumSafenessFactor(grid));

        scanner.close();
    }
}
