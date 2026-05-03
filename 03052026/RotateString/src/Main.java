import java.util.Scanner;

class Solution {
    public boolean rotateString(final String s, final String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        return (s + s).contains(goal);
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.println(new Solution().rotateString(scanner.next(), scanner.next()));

        scanner.close();
    }
}
