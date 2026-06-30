import java.util.Scanner;
import java.util.function.Supplier;

class Solution {
    public int numberOfSubstrings(final String s) {
        final int n = s.length();
        int answer = 0;
        int left = 0;
        final int[] count = new int[3];
        final Supplier<Boolean> isValid = () -> count[0] > 0 && count[1] > 0 && count[2] > 0;

        for (int right = 0; right < n; ++right) {
            ++count[s.charAt(right) - 'a'];

            while (isValid.get()) {
                answer += (n - right);
                --count[s.charAt(left) - 'a'];
                ++left;
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();

        System.out.println(new Solution().numberOfSubstrings(s));

        scanner.close();
    }
}
