import java.util.Scanner;

class Solution {
    public String smallestSubsequence(final String s) {
        final int n = s.length();
        final boolean[] visited = new boolean[26];
        final int[] count = new int[26];
        final StringBuilder answer = new StringBuilder();

        for (final char ch : s.toCharArray()) {
            ++count[ch - 'a'];
        }

        for (final char ch : s.toCharArray()) {
            if (!visited[ch - 'a']) {
                while (!answer.isEmpty() && answer.charAt(answer.length() - 1) > ch) {
                    if (count[answer.charAt(answer.length() - 1) - 'a'] > 0) {
                        visited[answer.charAt(answer.length() - 1) - 'a'] = false;
                        answer.deleteCharAt(answer.length() - 1);
                    } else {
                        break;
                    }
                }

                visited[ch - 'a'] = true;
                answer.append(ch);
            }

            --count[ch - 'a'];
        }

        return answer.toString();
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();

        System.out.println(new Solution().smallestSubsequence(s));

        scanner.close();
    }
}
