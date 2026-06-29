import java.util.Scanner;

class Solution {
    public int numOfStrings(final String[] patterns, final String word) {
        int answer = 0;

        for (final String pattern : patterns) {
            if (word.contains(pattern)) {
                ++answer;
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String[] patterns = new String[n];

        for (int i = 0; i < n; ++i) {
            patterns[i] = scanner.next();
        }

        final String word = scanner.next();

        System.out.println(new Solution().numOfStrings(patterns, word));

        scanner.close();
    }
}
