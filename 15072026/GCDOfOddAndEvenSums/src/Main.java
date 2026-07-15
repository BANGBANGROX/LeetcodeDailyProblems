import java.util.Scanner;

class Solution {
    public int gcdOfOddEvenSums(final int n) {
        return n;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        System.out.println(new Solution().gcdOfOddEvenSums(n));

        scanner.close();
    }
}
