import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int minimumEffort(final int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> {
            final int diff = (b[1] - b[0]) - (a[1] - a[0]);

            if (diff != 0) {
                return diff;
            }

            if (b[1] != a[1]) {
                return b[1] - a[1];
            }

            return b[0] - a[0];
        });

        int answer = tasks[0][1];
        int currentEnergy = answer - tasks[0][0];

        for (int i = 1; i < tasks.length; ++i) {
            if (currentEnergy < tasks[i][1]) {
                answer += (tasks[i][1] - currentEnergy);
                currentEnergy = tasks[i][1];
            }

            currentEnergy -= tasks[i][0];
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] tasks = new int[n][2];

        for (int i = 0; i < n; ++i) {
            tasks[i][0] = scanner.nextInt();
            tasks[i][1] = scanner.nextInt();
        }

        System.out.println(new Solution().minimumEffort(tasks));

        scanner.close();
    }
}
