import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {
    private static final List<Integer> NUMBERS = new ArrayList<>();

    static {
        for (int dig = 1; dig < 9; ++dig) {
            build(1, dig, dig, 10 - dig);
        }

        Collections.sort(NUMBERS);
    }

    public List<Integer> sequentialDigits(final int low, final int high) {
        final List<Integer> answer = new ArrayList<>();
        final int left = lowerBound(low);
        final int right = upperBound(high);

        for (int i = left; i <= Math.min(right, NUMBERS.size() - 1); ++i) {
            answer.add(NUMBERS.get(i));
        }

        return answer;
    }

    private static int lowerBound(final int key) {
        int left = 0;
        int right = NUMBERS.size() - 1;
        int answer = NUMBERS.size();

        while (left <= right) {
            final int mid = (left + ((right - left) >> 1));

            if (NUMBERS.get(mid) == key) {
                return mid;
            } else if (NUMBERS.get(mid) > key) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static int upperBound(final int key) {
        int left = 0;
        int right = NUMBERS.size() - 1;
        int answer = NUMBERS.size();

        while (left <= right) {
            final int mid = (left + ((right - left) >> 1));

            if (NUMBERS.get(mid) == key) {
                return mid;
            } else if (NUMBERS.get(mid) < key) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static void build(final int pos, final int lastDigit, final int num, final int maxLength) {
        NUMBERS.add(num);

        if (pos == maxLength) {
            return;
        }

        build(pos + 1, lastDigit + 1, num * 10 + lastDigit + 1, maxLength);
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int low = scanner.nextInt();
        final int high = scanner.nextInt();

        System.out.println(new Solution().sequentialDigits(low, high));

        scanner.close();
    }
}
