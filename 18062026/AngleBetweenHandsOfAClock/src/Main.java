import java.util.Scanner;

class Solution {
    public double angleClock(final int hour, final int minutes) {
        final double angle = Math.abs(30 * hour - 5.5 * minutes);

        return Math.min(angle, 360 - angle);
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int hour = scanner.nextInt();
        final int minutes = scanner.nextInt();

        System.out.println(new Solution().angleClock(hour, minutes));

        scanner.close();
    }
}
