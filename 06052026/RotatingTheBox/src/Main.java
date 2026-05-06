import java.util.Scanner;

class Solution {
    public char[][] rotateTheBox(final char[][] boxGrid) {
        final int m = boxGrid.length;
        final int n = boxGrid[0].length;
        final char[][] answer = new char[n][m];

        for (int i = 0; i < m; ++i) {
            int lastEmptyCell = -1;

            for (int j = n - 1; j >= 0; --j) {
                if (boxGrid[i][j] == '.') {
                    if (lastEmptyCell == -1) {
                        lastEmptyCell = j;
                    }
                } else if (boxGrid[i][j] == '*') {
                    lastEmptyCell = -1;
                } else {
                    if (lastEmptyCell != -1) {
                        boxGrid[i][lastEmptyCell] = '#';
                        boxGrid[i][j] = '.';
                        --lastEmptyCell;
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = m - 1; j >= 0; --j) {
                answer[i][j] = boxGrid[m - 1 - j][i];
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final int n = scanner.nextInt();
        final char[][] boxGrid = new char[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boxGrid[i][j] = scanner.next().charAt(0);
            }
        }

        final char[][] answer = new Solution().rotateTheBox(boxGrid);

        assert answer.length == n;
        assert answer[0].length == m;

        for (final char[] row : answer) {
            for (final char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
