import java.util.Scanner;

class Solution {
    public int maxIceCream(final int[] costs, final int coins) {
        final int n = costs.length;
        int maxValue = costs[0];

        for (int i = 1; i < n; ++i) {
            maxValue = Math.max(maxValue, costs[i]);
        }

        final int[] count = new int[maxValue + 1];

        for (final int cost : costs) {
            ++count[cost];
        }

        int remainingCoins = coins;
        int answer = 0;

        for (int i = 0; i <= maxValue && remainingCoins >= i; ++i) {
            while (count[i] > 0 && remainingCoins >= i) {
                ++answer;
                --count[i];
                remainingCoins -= i;
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] costs = new int[n];

        for (int i = 0; i < n; ++i) {
            costs[i] = scanner.nextInt();
        }

        final int coins = scanner.nextInt();

        System.out.println(new Solution().maxIceCream(costs, coins));

        scanner.close();
    }
}
