import java.util.Scanner;

class Solution {
    public String mapWordWeights(final String[] words, final int[] weights) {
        final StringBuilder answer = new StringBuilder();

        for (final String word : words) {
            int totalWeight = 0;

            for (final char ch : word.toCharArray()) {
                totalWeight = (totalWeight + weights[ch - 'a']) % 26;
            }

            answer.append((char) ('a' + 25 - totalWeight));
        }

        return answer.toString();
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String[] words = new String[n];

        for (int i = 0; i < n; ++i) {
            words[i] = scanner.next();
        }

        final int[] weights = new int[26];

        for (int i = 0; i < 26; ++i) {
            weights[i] = scanner.nextInt();
        }

        System.out.println(new Solution().mapWordWeights(words, weights));

        scanner.close();
    }
}
