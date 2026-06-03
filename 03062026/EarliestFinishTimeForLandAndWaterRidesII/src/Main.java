import java.util.Scanner;

class Solution {
    public int earliestFinishTime(final int[] landStartTime, final int[] landDuration,
                                  final int[] waterStartTime, final int[] waterDuration) {
        final int m = landStartTime.length;
        final int n = waterStartTime.length;
        int minLandComplete = Integer.MAX_VALUE;
        int minWaterComplete = Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < m; ++i) {
            minLandComplete = Math.min(minLandComplete, landStartTime[i] + landDuration[i]);
        }

        for (int i = 0; i < n; ++i) {
            minWaterComplete = Math.min(minWaterComplete, waterStartTime[i] + waterDuration[i]);
        }

        for (int i = 0; i < m; ++i) {
            answer = Math.min(answer, Math.max(minWaterComplete, landStartTime[i]) + landDuration[i]);
        }

        for (int i = 0; i < n; ++i) {
            answer = Math.min(answer, Math.max(minLandComplete, waterStartTime[i]) + waterDuration[i]);
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
