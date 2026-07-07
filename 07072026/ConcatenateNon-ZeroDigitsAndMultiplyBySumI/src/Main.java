import java.util.Scanner;

class Solution {
    public long sumAndMultiply(final int n) {
        int sum = 0;
        int num = 0;
        int current = n;
        int mul = 1;

        while (current > 0) {
            final int dig = current % 10;

            if (dig > 0) {
                sum += dig;
                num = num + dig * mul;
                mul *= 10;
            }

            current /= 10;
        }

        return (long) sum * num;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        System.out.println(new Solution().sumAndMultiply(n));

        scanner.close();
    }
}
