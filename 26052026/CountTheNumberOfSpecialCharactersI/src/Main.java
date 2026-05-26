import java.util.Scanner;

class Solution {
    public int numberOfSpecialChars(final String word) {
        final boolean[] visited = new boolean[256];
        int answer = 0;

        for (final char ch : word.toCharArray()) {
            visited[ch] = true;
        }

        for (char ch = 'a'; ch <= 'z'; ++ch) {
            final char upperCh = Character.toUpperCase(ch);

            if (visited[ch] && visited[upperCh]) {
                ++answer;
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String word = scanner.next();

        System.out.println(new Solution().numberOfSpecialChars(word));

        scanner.close();
    }
}
