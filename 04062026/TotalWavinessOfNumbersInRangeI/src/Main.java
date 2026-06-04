import java.util.Scanner;

class Solution {
    public int totalWaviness(final int num1, final int num2) {
        int answer = 0;

        for (int i = num1; i <= num2; ++i) {
            answer += getWaviness(i);
        }

        return answer;
    }

    private int getWaviness(final int num) {
        int waviness = 0;
        final String numString = String.valueOf(num);
        final int n = numString.length();

        for (int i = 1; i < n - 1; ++i) {
            if (numString.charAt(i) > numString.charAt(i - 1) && numString.charAt(i) > numString.charAt(i + 1)) {
                ++waviness;
            } else if (numString.charAt(i) < numString.charAt(i - 1) && numString.charAt(i) < numString.charAt(i + 1)) {
                ++waviness;
            }
        }

        return waviness;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int num1 = scanner.nextInt();
        final int num2 = scanner.nextInt();

        System.out.println(new Solution().totalWaviness(num1, num2));

        scanner.close();
    }
}
