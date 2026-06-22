import java.util.Scanner;

class Solution {
    public int maxNumberOfBalloons(final String text) {
        final int[] count = new int[5];

        for (final char ch : text.toCharArray()) {
            switch (ch) {
                case 'b' : ++count[0];
                break;
                case 'a': ++count[1];
                break;
                case 'l': ++count[2];
                break;
                case 'o': ++count[3];
                break;
                case 'n': ++count[4];
                break;
                default:
            }
        }

        count[2] /= 2;
        count[3] /= 2;

        int answer = Integer.MAX_VALUE;

        for (final int val : count) {
            answer = Math.min(answer, val);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String text = scanner.next();

        System.out.println(new Solution().maxNumberOfBalloons(text));

        scanner.close();
    }
}
