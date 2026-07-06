import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int removeCoveredIntervals(final int[][] intervals) {
        final int n = intervals.length;

        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

        int answer = n;
        int maxR = intervals[0][1];

        for (int i = 1; i < n; ++i) {
            if (intervals[i][1] <= maxR) {
                --answer;
            } else {
                maxR = intervals[i][1];
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] intervals = new int[n][2];

        for (int i = 0; i < n; ++i) {
            intervals[i][0] = scanner.nextInt();
            intervals[i][1] = scanner.nextInt();
        }

        System.out.println(new Solution().removeCoveredIntervals(intervals));

        scanner.close();
    }
}
