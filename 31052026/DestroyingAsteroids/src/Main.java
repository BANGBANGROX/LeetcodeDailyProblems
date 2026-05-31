import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public boolean asteroidsDestroyed(final int mass, final int[] asteroids) {
        int remainingMass = mass;
        final int maxMass = 100001;

        Arrays.sort(asteroids);

        for (final int asteroid : asteroids) {
            if (remainingMass < asteroid) {
                return false;
            }

            remainingMass += asteroid;

            if (remainingMass >= maxMass) {
                return true;
            }
        }

        return true;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int mass = scanner.nextInt();
        final int n = scanner.nextInt();
        final int[] asteroids = new int[n];

        for (int i = 0; i < n; ++i) {
            asteroids[i] = scanner.nextInt();
        }

        System.out.println(new Solution().asteroidsDestroyed(mass, asteroids));

        scanner.close();
    }
}
