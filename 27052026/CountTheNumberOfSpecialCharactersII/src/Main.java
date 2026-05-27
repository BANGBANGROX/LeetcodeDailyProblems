import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public int numberOfSpecialChars(final String word) {
        final int n = word.length();
        int answer = 0;
        final int[] characterIndex = new int[256];

        Arrays.fill(characterIndex, -1);

        for (int i = 0; i < n; ++i) {
            final char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {
                characterIndex[ch] = i;
            } else if (Character.isUpperCase(ch) && characterIndex[ch] == -1) {
                characterIndex[ch] = i;
            }
        }

        for (char ch = 'a'; ch <= 'z'; ++ch) {
            final char upperCh = Character.toUpperCase(ch);

            if (characterIndex[ch] != -1 && characterIndex[upperCh] != -1 && characterIndex[ch] < characterIndex[upperCh]) {
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
