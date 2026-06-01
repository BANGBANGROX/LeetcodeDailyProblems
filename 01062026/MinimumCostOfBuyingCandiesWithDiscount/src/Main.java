import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int minimumCost(final int[] cost) {
        final int n = cost.length;
        int answer = 0;

        Arrays.sort(cost);

        for (int i = n - 1; i >= 0; i -= 3) {
            answer += cost[i];

            if (i > 0) {
                answer += cost[i - 1];
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] cost = new int[n];

        for (int i = 0; i < n; ++i) {
            cost[i] = scanner.nextInt();
        }

        System.out.println(new Solution().minimumCost(cost));

        scanner.close();
    }
}
