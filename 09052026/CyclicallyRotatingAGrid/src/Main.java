import java.util.Scanner;

class Solution {
    public int[][] rotateGrid(final int[][] grid, final int k) {
        final int m = grid.length;
        final int n = grid[0].length;
        int firstRow = 0;
        int lastRow = m - 1;
        int firstCol = 0;
        int lastCol = n - 1;

        while (firstRow < lastRow && firstCol < lastCol) {
            rotate(grid, firstRow, lastRow, firstCol, lastCol, k);

            ++firstRow;
            --lastRow;
            ++firstCol;
            --lastCol;
        }

        return grid;
    }

    private void rotate(
            final int[][] grid,
            final int firstRow,
            final int lastRow,
            final int firstCol,
            final int lastCol,
            final int k
    ) {
        final int totalElements = 2 * ((lastRow - firstRow + 1) + (lastCol - firstCol + 1)) - 4;
        final int rotationsNeeded = k % totalElements;
        int extraElement;

        for (int i = 0; i < rotationsNeeded; ++i) {
            extraElement = grid[firstRow][firstCol];

            // firstRow rotation
            for (int col = firstCol; col < lastCol; ++col) {
                grid[firstRow][col] = grid[firstRow][col + 1];
            }

            // firstCol rotation
            int temp = grid[lastRow][firstCol];
            int last = grid[firstRow + 1][firstCol];
            grid[firstRow + 1][firstCol] = extraElement;

            for (int row = firstRow + 2; row <= lastRow; ++row) {
                final int next = grid[row][firstCol];
                grid[row][firstCol] = last;
                last = next;
            }
            extraElement = temp;

            // lastRow rotation
            temp = grid[lastRow][lastCol];
            last = grid[lastRow][firstCol + 1];
            grid[lastRow][firstCol + 1] = extraElement;

            for (int col = firstCol + 2; col <= lastCol; ++col) {
                final int next = grid[lastRow][col];
                grid[lastRow][col] = last;
                last = next;
            }
            extraElement = temp;

            // lastCol rotation
            last = grid[lastRow - 1][lastCol];
            grid[lastRow - 1][lastCol] = extraElement;

            for (int row = lastRow - 2; row >= firstRow; --row) {
                final int next = grid[row][lastCol];
                grid[row][lastCol] = last;
                last = next;
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

        final int[][] answer = new Solution().rotateGrid(grid, k);

        for (final int[] row : answer) {
            for (final int x : row) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

        System.out.println();

        scanner.close();
    }
}
