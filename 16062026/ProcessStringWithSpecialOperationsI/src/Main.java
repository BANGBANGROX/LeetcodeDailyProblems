import java.util.Scanner;

class Solution {
    public String processStr(final String s) {
        final StringBuilder answer = new StringBuilder();

        for (final char ch : s.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                answer.append(ch);
            } else if (ch == '*') {
                if (!answer.isEmpty()) {
                    answer.deleteCharAt(answer.length() - 1);
                }
            } else if (ch == '#') {
                answer.append(answer);
            } else if (ch == '%') {
                answer.reverse();
            }
        }

        return answer.toString();
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();

        System.out.println(new Solution().processStr(s));

        scanner.close();
    }
}
