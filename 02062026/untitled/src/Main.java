import java.util.Scanner;

class Solution {
    public int earliestFinishTime(final int[] landStartTime, final int[] landDuration, final int[] waterStartTime, final int[] waterDuration) {
        int answer = Integer.MAX_VALUE;
        final int m = landStartTime.length;
        final int n = waterStartTime.length;

        for (int i = 0; i < m; ++i) {
            final int endTime = landStartTime[i] + landDuration[i];

            for (int j = 0; j < n; ++j) {
                if (waterStartTime[j] <= endTime) {
                    answer = Math.min(answer, endTime + waterDuration[j]);
                } else {
                    answer = Math.min(answer, waterStartTime[j] + waterDuration[j]);
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            final int endTime = waterStartTime[i] + waterDuration[i];

            for (int j = 0; j < m; ++j) {
                if (landStartTime[j] <= endTime) {
                    answer = Math.min(answer, endTime + landDuration[j]);
                } else {
                    answer = Math.min(answer, landStartTime[j] + landDuration[j]);
                }
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final int[] landStartTime = new int[m];
        final int[] landDuration = new int[m];

        for (int i = 0; i < m; ++i) {
            landStartTime[i] = scanner.nextInt();
        }

        for (int i = 0; i < m; ++i) {
            landDuration[i] = scanner.nextInt();
        }

        final int n = scanner.nextInt();
        final int[] waterStartTime = new int[n];
        final int[] waterDuration = new int[n];

        for (int i = 0; i < n; ++i) {
            waterStartTime[i] = scanner.nextInt();
        }

        for (int i = 0; i < m; ++i) {
            waterDuration[i] = scanner.nextInt();
        }

        System.out.println(new Solution().earliestFinishTime(landStartTime, landDuration, waterStartTime, waterDuration));

        scanner.close();
    }
}
