import java.util.Scanner;

class Solution {
    public int largestAltitude(final int[] gain) {
        int runningSum = 0;
        int answer = 0;

        for (final int height : gain) {
            runningSum += height;
            answer = Math.max(answer, runningSum);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] gain = new int[n];

        for (int i = 0; i < n; ++i) {
            gain[i] = scanner.nextInt();
        }

        System.out.println(new Solution().largestAltitude(gain));

        scanner.close();
    }
}
